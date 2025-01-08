package task5_7;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {

  public static void assign(Object to, Object from) {
    if (to == null || from == null) {
      throw new IllegalArgumentException("Both 'to' and 'from' objects must be non-null");
    }

    Class<?> fromClass = from.getClass();
    Class<?> toClass = to.getClass();

    // Получаем все геттеры из объекта "from"
    Map<String, Method> getters = findGetters(fromClass);

    // Ищем сеттеры в объекте "to" и вызываем их, если есть соответствующий геттер
    for (Method setter : findSetters(toClass)) {
      String propertyName = getPropertyName(setter.getName());
      Method getter = getters.get(propertyName);

      if (getter != null && isCompatible(setter, getter)) {
        try {
          Object value = getter.invoke(from); // Получаем значение из "from"
          setter.invoke(to, value);           // Устанавливаем значение в "to"
        } catch (Exception e) {
          e.printStackTrace(); // Обработка исключений при вызове методов
        }
      }
    }
  }

  // Находит все геттеры класса
  private static Map<String, Method> findGetters(Class<?> clazz) {
    Map<String, Method> getters = new HashMap<>();
    for (Method method : clazz.getMethods()) { // getMethods() возвращает только public методы
      if (isGetter(method)) {
        String propertyName = getPropertyName(method.getName());
        getters.put(propertyName, method);
      }
    }
    return getters;
  }

  // Находит все сеттеры класса
  private static Method[] findSetters(Class<?> clazz) {
    return clazz.getMethods(); // Возвращает все public методы, включая сеттеры
  }

  // Проверяет, является ли метод геттером
  private static boolean isGetter(Method method) {
    return method.getName().startsWith("get") &&
        method.getParameterCount() == 0 &&
        !method.getReturnType().equals(void.class);
  }

  // Проверяет, является ли метод сеттером
  private static boolean isSetter(Method method) {
    return method.getName().startsWith("set") &&
        method.getParameterCount() == 1; // Проверяем, что метод принимает ровно один параметр
  }

  // Получает имя свойства из названия метода (убирает "get" или "set")
  private static String getPropertyName(String methodName) {
    return methodName.substring(3); // Убираем "get" или "set"
  }

  // Проверяет совместимость типов геттера и сеттера
  private static boolean isCompatible(Method setter, Method getter) {
    if (setter.getParameterCount() == 1) { // Добавляем проверку количества параметров
      Class<?> setterParamType = setter.getParameterTypes()[0];
      Class<?> getterReturnType = getter.getReturnType();
      return setterParamType.isAssignableFrom(getterReturnType);
    }
    return false;
  }
}
