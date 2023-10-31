package dnis1.app;

import dnis1.lib.DNISFrame;
import javax.swing.SwingUtilities;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WinLauncher {

    public static void main(String[] args) {
        log.info("main...");
        try {

            DNISFrame frame = new DNISFrame();

            SwingUtilities.invokeAndWait(() -> {
                frame.setVisible(true);
            });
            frame.addLine("Hello world!");
            log.info("frame shown.");

        } catch (Exception e) {
            log.error("Exception " + e.getMessage(), e);
        }
    }
}
