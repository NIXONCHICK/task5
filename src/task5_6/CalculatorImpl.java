package task5_6;

public class CalculatorImpl implements Calculator {
  @Override
  public int calc(int number) {
    int result = 1;
    for (int i = 1; i <= number; i++) {
      result *= i;
    }
    return result;
  }
}

