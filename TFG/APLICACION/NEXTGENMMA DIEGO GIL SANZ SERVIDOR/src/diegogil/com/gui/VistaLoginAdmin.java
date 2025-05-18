package diegogil.com.gui;

import javax.swing.*;

public class VistaLoginAdmin extends JFrame{
     public JPanel panel1;
     public JPasswordField administradorContraseñaTxt;
     public JButton administradorLoginBtn;
     JMenuBar menuBar1;
     JMenu menu1;
     public JMenuItem inicio3;

     public VistaLoginAdmin() {
          setContentPane(panel1);
          setTitle("Login Administrador");
          pack();
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          crearMenu();
          this.setVisible(false);



     }
     private void crearMenu() {
          menuBar1 = new JMenuBar();
          menu1 = new JMenu("Menu");
          inicio3 = new JMenuItem("Inicio");

          menu1.add(inicio3);
          menuBar1.add(menu1);
          setJMenuBar(menuBar1);

     }


}
