package shaart.debugging.threading;

import java.util.concurrent.ForkJoinPool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadSample {

  public static void main(String[] args) {
//    final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
//    forkJoinPool.submit(printTask());
//    forkJoinPool.submit(printTask());

    Thread firstThread = new Thread(printTask());
    firstThread.setName("FIRST");

    Thread secondThread = new Thread(printHi());
    secondThread.setName("SECOND");

    firstThread.start();
    secondThread.start();

    log.info("Threading end. Main exit");
  }

  private static Runnable printTask() {
    return () -> {
      for (int i = 0; i < 1_000_000; i++) {
        log.info("{} working {} time...", Thread.currentThread().toString(), i);
      }
    };
  }

  private static Runnable printHi() {
    return () -> {
      for (int i = 0; i < 1_000_000; i++) {
        log.info("{} saying: \"Hi!\" ({} time)", Thread.currentThread().toString(), i);
      }
    };
  }
}
