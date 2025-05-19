package diegogil.com.gui;

import javax.swing.*;
import java.awt.*;

public class VistaUsuarios extends JFrame {
     JPanel panel1;
     public JButton usuarioAdministradorBtn;
    public JButton usuarioGimnasioBtn;
    public JButton usuarioInvitadosBtn;
    JMenuBar menuBar1;
    JMenu menu1;
    public JMenuItem inicio4;
    public JMenuItem informacion4;
    public JMenuItem salir4;
    /**
     * Constructor de la clase VistaUsuarios
     */
    public VistaUsuarios() {
        setContentPane(panel1);
        setTitle("Post");
        pack();
        crearMenu();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);



    }
    /**
     * Metodo que crea el menu de la ventana
     */
    private void crearMenu() {

        menuBar1 = new JMenuBar();

        menu1 = new JMenu("Menu");
        menu1.setFont(new Font("Arial", Font.BOLD, 15));
        menu1.setBounds(100, 80, 100, 30);

        inicio4 = new JMenuItem("Inicio");
        salir4 = new JMenuItem("Salir");
        informacion4 = new JMenuItem("Informacion");
        menu1.add(informacion4);
        menu1.add(salir4);


        menu1.add(inicio4);
        menuBar1.add(menu1);
        setJMenuBar(menuBar1);

    }
}
