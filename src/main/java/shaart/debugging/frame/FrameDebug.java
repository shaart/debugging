package shaart.debugging.frame;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FrameDebug {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            print(i);
        }
    }

    private static void print(int i) {
        log.info("Received i = {}", i);
        takeALookAtBreakpoint();
    }

    private static void takeALookAtBreakpoint() {
    }
}
