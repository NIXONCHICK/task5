package task5_6;

public class Main {
  public static void main(String[] args) {
    Calculator calculator = new CalculatorImpl();
    Calculator proxyCalculator = PerformanceProxy.createProxy(calculator, Calculator.class);

    System.out.println("Результат: " + proxyCalculator.calc(15));
    System.out.println("Результат: " + proxyCalculator.calc(11));
    System.out.println("Результат: " + proxyCalculator.calc(7));
    System.out.println("Результат: " + proxyCalculator.calc(7));
    System.out.println("Результат: " + proxyCalculator.calc(4));
  }
}

