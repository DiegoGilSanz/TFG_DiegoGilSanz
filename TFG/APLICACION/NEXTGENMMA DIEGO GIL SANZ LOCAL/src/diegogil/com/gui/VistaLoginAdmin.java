package diegogil.com.gui;

import javax.swing.*;

public class VistaLoginAdmin extends JFrame{
     public JPanel panel1;
     public JPasswordField administradorContraseñaTxt;
     public JButton administradorLoginBtn;
     JMenuBar menuBar1;
     JMenu menu1;
     public JMenuItem inicio3;
     public JMenuItem informacion3;
     public JMenuItem salir3;

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
            menu1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
            menu1.setBounds(100, 80, 100, 30);
          inicio3 = new JMenuItem("Inicio");
          salir3 = new JMenuItem("Salir");
            informacion3 = new JMenuItem("Informacion");
            menu1.add(informacion3);
            menu1.add(salir3);
          menu1.add(inicio3);
          menuBar1.add(menu1);
          setJMenuBar(menuBar1);

     }


}
