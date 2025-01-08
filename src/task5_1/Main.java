package task5_1;

public class Main {
  public static void main(String[] args) {
    Calculator calculator = new CalculatorImpl();
    System.out.println("Factorial of 5: " + calculator.calc(5));
  }
}
