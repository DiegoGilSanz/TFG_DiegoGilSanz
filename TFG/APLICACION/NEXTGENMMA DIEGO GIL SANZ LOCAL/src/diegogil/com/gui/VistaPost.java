package diegogil.com.gui;

import javax.swing.*;
import java.awt.*;

public class VistaPost extends JFrame {
    public JPanel panel1;
    public JButton postBackBtn;
    public JButton postNextBtn;
    public JTextArea postMensajeTxt;
    public JLabel postTituloTxt;
    public JLabel postImagenTxt;
    JMenuBar menuBar1;
    JMenu menu1;
    public JMenuItem inicio5;
    public JMenuItem informacion5;
    public JMenuItem salir5;

    public VistaPost() {
        setContentPane(panel1);
        setTitle("Post");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        crearMenu();
        this.setVisible(false);



    }
    private void crearMenu() {
        menuBar1 = new JMenuBar();
        menu1 = new JMenu("Menu");
        menu1.setFont(new Font("Arial", Font.BOLD, 15));
        menu1.setBounds(100, 80, 100, 30);
        inicio5 = new JMenuItem("Inicio");
        salir5 = new JMenuItem("Salir");
        informacion5 = new JMenuItem("Informacion");
        menu1.add(informacion5);
        menu1.add(salir5);
        menu1.add(inicio5);
        menuBar1.add(menu1);
        setJMenuBar(menuBar1);

    }
}
