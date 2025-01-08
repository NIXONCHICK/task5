package task5_5;

public class CalculatorImpl implements Calculator {

  @Override
  public int calc(int number) {
    System.out.println("Calculating factorial for: " + number);
    int result = 1;
    for (int i = 1; i <= number; i++) {
      result *= i;
    }
    return result;
  }
}

