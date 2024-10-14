//package JavaSwing_1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public class JFrame_3 {
    private JFrame frame3;
    public JFrame_3(){
        initialize();
    }

    private void initialize(){
        frame3 = new JFrame();
        frame3.setTitle("Frame 3");
        frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame3.setSize(500,500);
        frame3.setLocationRelativeTo(null);
        frame3.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        panel.setBackground(Color.red);
        frame3.add(panel,BorderLayout.CENTER);
    }


}
