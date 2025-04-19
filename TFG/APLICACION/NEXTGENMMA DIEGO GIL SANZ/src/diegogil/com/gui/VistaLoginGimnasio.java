package diegogil.com.gui;

import javax.swing.*;

public class VistaLoginGimnasio extends JFrame {
    public JPanel panel1;
    public JComboBox loginGimnasioCombo;
    public JButton loginGimnasioBtn;
     JTextField loginGimnasioContraseñaTxt;
    public VistaLoginGimnasio() {
        setContentPane(panel1);
        setTitle("Login Gimnasio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);



    }
}
