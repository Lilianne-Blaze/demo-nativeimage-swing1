package dnis1.app;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class InitClassesTest {

    @Test
    public void test1() {
        new JFrame();
        new JPanel();
        new JButton();
        new JCheckBox();
    }

}
