package shaart.debugging.conditional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConditionalBreakpointRunner {

  public static void main(String[] args) {
    new ConditionalBreakpointRunner().start();
  }

  private void start() {
    long sum = 0;
    for (int i = 0; i < 20; i++) {
      final int fibonacci = fibonacci(i);
      log.info("f({}) = {}", i, fibonacci);
      sum += fibonacci;
    }
    log.info("Sum = " + sum);
  }

  private int fibonacci(int n) {
    if (n <= 0) {
      return 1;
    }
    return n + fibonacci(n - 1);
  }
}
