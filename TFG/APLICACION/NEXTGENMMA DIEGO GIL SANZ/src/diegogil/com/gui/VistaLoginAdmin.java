package diegogil.com.gui;

import javax.swing.*;

public class VistaLoginAdmin extends JFrame{
     public JPanel panel1;
     public JTextField administradorContraseñaTxt;
     public JButton administradorLoginBtn;

     public VistaLoginAdmin() {
          setContentPane(panel1);
          setTitle("Login Administrador");
          setSize(800, 600);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          setVisible(true);



     }


}
