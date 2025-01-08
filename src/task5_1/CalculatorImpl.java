package task5_1;

public class CalculatorImpl implements Calculator {

  @Override
  public int calc(int number) {
    if (number < 0) {
      throw new IllegalArgumentException("Number must be non-negative.");
    }
    int factorial = 1;
    for (int i = 1; i <= number; i++) {
      factorial *= i;
    }
    return factorial;
  }
}

