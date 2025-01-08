package task5_2;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionExample {

  public static void printAllMethods(Class<?> clazz) {
    while (clazz != null) {
      System.out.println("Methods in class: " + clazz.getName());
      Method[] methods = clazz.getDeclaredMethods(); // Получаем все методы, включая приватные

      for (Method method : methods) {
        int modifiers = method.getModifiers();
        System.out.print(" - " + method.getName() + "()");
        if (Modifier.isPublic(modifiers)) {
          System.out.println(" - Public");
        } else if (Modifier.isPrivate(modifiers)) {
          System.out.println(" - Private");
        } else if (Modifier.isProtected(modifiers)) {
          System.out.println(" - Protected");
        } else {
          System.out.println(" - Package-private");
        }
      }

      clazz = clazz.getSuperclass(); // Переходим к родительскому классу
    }
  }
}
