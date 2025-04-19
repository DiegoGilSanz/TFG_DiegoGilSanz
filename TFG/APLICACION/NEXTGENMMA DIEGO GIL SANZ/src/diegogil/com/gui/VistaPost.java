package diegogil.com.gui;

import javax.swing.*;

public class VistaPost extends JFrame {
    public JPanel panel1;
    public JButton postBackBtn;
    public JButton postNextBtn;
    public JTextArea textArea1;
    public VistaPost() {
        setContentPane(panel1);
        setTitle("Post");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);



    }
}
