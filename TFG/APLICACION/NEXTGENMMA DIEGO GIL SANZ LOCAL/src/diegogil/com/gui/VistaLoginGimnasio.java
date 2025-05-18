package diegogil.com.gui;

import javax.swing.*;

public class VistaLoginGimnasio extends JFrame {
    public JPanel panel1;
    public JComboBox loginGimnasioCombo;
    public JButton loginGimnasioBtn;
     public JPasswordField loginGimnasioContraseñaTxt;
    JMenuBar menuBar1;
    JMenu menu1;

    public JMenuItem inicio4;
    public JMenuItem informacion4;
    public JMenuItem salir4;
    public VistaLoginGimnasio() {
        setContentPane(panel1);
        setTitle("Login Gimnasio");
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
        inicio4 = new JMenuItem("Inicio");
        salir4 = new JMenuItem("Salir");
        menu1.add(salir4);
        informacion4 = new JMenuItem("Informacion");
        menu1.add(informacion4);
        menu1.add(inicio4);
        menuBar1.add(menu1);
        setJMenuBar(menuBar1);

    }
}
