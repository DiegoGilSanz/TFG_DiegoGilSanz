package diegogil.com.gui;

import javax.swing.*;

public class VistaUsuarios extends JFrame {
     JPanel panel1;
     public JButton usuarioAdministradorBtn;
    public JButton usuarioGimnasioBtn;
    public JButton usuarioInvitadosBtn;
    public VistaUsuarios() {
        setContentPane(panel1);
        setTitle("Post");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);



    }
}
