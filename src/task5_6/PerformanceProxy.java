package task5_6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PerformanceProxy implements InvocationHandler {

  private final Object target;

  public PerformanceProxy(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // Проверяем, помечен ли метод аннотацией @Metric
    if (method.isAnnotationPresent(Metric.class)) {
      long startTime = System.nanoTime();
      Object result = method.invoke(target, args); // Вызываем реальный метод
      long endTime = System.nanoTime();
      System.out.println("Время работы метода: " + (endTime - startTime) + " наносек.");
      return result;
    }

    // Если метод не помечен аннотацией @Metric, просто вызываем его
    return method.invoke(target, args);
  }

  // Фабричный метод для создания прокси
  public static <T> T createProxy(T target, Class<T> interfaceType) {
    return (T) Proxy.newProxyInstance(
        interfaceType.getClassLoader(),
        new Class<?>[]{interfaceType},
        new PerformanceProxy(target)
    );
  }
}
