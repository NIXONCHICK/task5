package task5_3;

import java.lang.reflect.Method;

public class GetterPrinter {

  public static void printGetters(Class<?> clazz) {
    Method[] methods = clazz.getDeclaredMethods(); // Получаем все методы класса

    System.out.println("Getters in class: " + clazz.getName());
    for (Method method : methods) {
      if (isGetter(method)) {
        System.out.println(" - " + method.getName() + "(): " + method.getReturnType().getSimpleName());
      }
    }
  }

  // Метод для проверки, является ли метод геттером
  public static boolean isGetter(Method method) {
    if (!method.getName().startsWith("get")) {
      return false; // Имя должно начинаться с "get"
    }
    if (method.getParameterCount() != 0) {
      return false; // Геттер не должен принимать параметры
    }
    if (method.getReturnType() == void.class) {
      return false; // Геттер должен возвращать значение
    }
    return true;
  }
}
