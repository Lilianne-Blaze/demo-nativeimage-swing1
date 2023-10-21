package dnis1.lib;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DNISFrame extends JFrame {

    public DNISFrame() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Set the icon for the JFrame
        ImageIcon frameIcon = new ImageIcon(getClass().getResource("/shared/icon.png"));
        setIconImage(frameIcon.getImage());

        // Create a split pane with a 2:1 ratio
        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.66); // 2:1 ratio

        // Create a text area for the left side
        JTextArea textArea = new JTextArea();
        textArea.setText("This is a text area.");
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        splitPane.setLeftComponent(textAreaScrollPane);

        // Create a label with an image for the right side
        // ImageIcon imageIcon = new ImageIcon("shared/image.png"); // Replace with your
        // image path
        // Image image = ImageIO.read(getClass().getResource("/shared/image.png"));
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/shared/image.png"));

        JLabel imageLabel = new JLabel(imageIcon);
        JScrollPane imageScrollPane = new JScrollPane(imageLabel);
        splitPane.setRightComponent(imageScrollPane);

        // Add the split pane to the frame
        add(splitPane);

        // Add the icon to the system tray
        if (SystemTray.isSupported()) {
            SystemTray systemTray = SystemTray.getSystemTray();
            TrayIcon trayIcon = new TrayIcon(frameIcon.getImage(), "Simple Java Swing App with Icon");
            trayIcon.setImageAutoSize(true);

            // Add an action listener to restore the window when the tray icon is clicked
            trayIcon.addActionListener(e -> {
                if (getState() == JFrame.ICONIFIED) {
                    setState(JFrame.NORMAL);
                }
                setVisible(true);
                requestFocus();
            });

            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

    }
}
