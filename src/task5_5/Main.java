package task5_5;

public class Main {

  public static void main(String[] args) {
    Calculator calculator = new CalculatorImpl();
    Calculator cachedCalculator = CacheProxy.createCachedProxy(calculator, Calculator.class);

    System.out.println("First call: " + cachedCalculator.calc(5));  // Реальный расчет
    System.out.println("Second call: " + cachedCalculator.calc(5)); // Возвращается из кеша
    System.out.println("Third call: " + cachedCalculator.calc(6));  // Реальный расчет
    System.out.println("Fourth call: " + cachedCalculator.calc(5)); // Возвращается из кеша
    System.out.println("Fifth call: " + cachedCalculator.calc(6));  // Возвращается из кеша
  }
}

