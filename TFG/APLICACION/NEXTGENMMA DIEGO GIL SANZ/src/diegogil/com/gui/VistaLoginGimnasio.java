package diegogil.com.gui;

import javax.swing.*;

public class VistaLoginGimnasio extends JFrame {
    public JPanel panel1;
    public JComboBox loginGimnasioCombo;
    public JButton loginGimnasioBtn;
     public JTextField loginGimnasioContraseñaTxt;
    JMenuBar menuBar1;
    JMenu menu1;
    public JMenuItem inicio4;
    public VistaLoginGimnasio() {
        setContentPane(panel1);
        setTitle("Login Gimnasio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        crearMenu();
        this.setVisible(false);



    }
    private void crearMenu() {
        menuBar1 = new JMenuBar();
        menu1 = new JMenu("Menu");
        inicio4 = new JMenuItem("Inicio");

        menu1.add(inicio4);
        menuBar1.add(menu1);
        setJMenuBar(menuBar1);

    }
}
