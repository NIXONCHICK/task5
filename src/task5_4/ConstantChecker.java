package task5_4;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ConstantChecker {

  public static void checkStringConstants(Class<?> clazz) {
    Field[] fields = clazz.getDeclaredFields();

    for (Field field : fields) {
      if (Modifier.isPublic(field.getModifiers()) &&
          Modifier.isStatic(field.getModifiers()) &&
          Modifier.isFinal(field.getModifiers()) &&
          field.getType().equals(String.class)) {
        try {
          String fieldName = field.getName();
          String fieldValue = (String) field.get(null); // Получаем значение статического поля

          if (fieldName.equals(fieldValue)) {
            System.out.println("OK: " + fieldName + " = " + fieldValue);
          } else {
            System.out.println("ERROR: " + fieldName + " != " + fieldValue);
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
