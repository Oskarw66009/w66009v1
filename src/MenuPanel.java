import javax.swing.*;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Ustawienia");
        JMenuItem exitMenuItem = new JMenuItem("Wyjście");
        exitMenuItem.addActionListener(e -> System.exit(0));

        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        add(menuBar);
    }
}