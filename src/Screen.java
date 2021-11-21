import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

abstract class Screen {

    // GUI
    public static void window() {

        JFrame frame = new JFrame();

        ImageIcon favicon = new ImageIcon("src/img/favicon.png");
        frame.setIconImage(favicon.getImage());

        ImageIcon mainLogoImage = new ImageIcon("src/img/big_logo.png");
        JLabel mainPage = new JLabel();
        mainPage.setIcon(mainLogoImage);
        mainPage.setHorizontalAlignment(JLabel.CENTER);
        frame.add(mainPage);

        frame.setTitle("Hotel Plaza");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1500, 1000);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(0x44949B));

    }

}
