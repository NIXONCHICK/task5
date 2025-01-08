package task5_5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

  private final Object target;
  private final Map<MethodCall, Object> cache = new HashMap<>();

  public CacheProxy(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // Проверяем, помечен ли метод аннотацией @Cache
    if (method.isAnnotationPresent(Cache.class)) {
      MethodCall call = new MethodCall(method, args);
      if (cache.containsKey(call)) {
        System.out.println("Returning cached result for: " + Arrays.toString(args));
        return cache.get(call);
      }

      // Вызываем реальный метод и сохраняем результат в кеш
      Object result = method.invoke(target, args);
      cache.put(call, result);
      return result;
    }

    // Если метод не помечен аннотацией @Cache, просто вызываем его
    return method.invoke(target, args);
  }

  public static <T> T createCachedProxy(T target, Class<T> interfaceType) {
    return (T) Proxy.newProxyInstance(
        interfaceType.getClassLoader(),
        new Class<?>[]{interfaceType},
        new CacheProxy(target)
    );
  }

  // Вспомогательный класс для хранения вызова метода и его аргументов
  private static class MethodCall {
    private final Method method;
    private final Object[] args;

    public MethodCall(Method method, Object[] args) {
      this.method = method;
      this.args = args != null ? args.clone() : null;
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(args) + method.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      MethodCall that = (MethodCall) obj;
      return method.equals(that.method) && Arrays.equals(args, that.args);
    }
  }
}

