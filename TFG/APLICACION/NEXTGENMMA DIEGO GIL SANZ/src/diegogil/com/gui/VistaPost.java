package diegogil.com.gui;

import javax.swing.*;

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

    public VistaPost() {
        setContentPane(panel1);
        setTitle("Post");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        crearMenu();
        this.setVisible(false);



    }
    private void crearMenu() {
        menuBar1 = new JMenuBar();
        menu1 = new JMenu("Menu");
        inicio5 = new JMenuItem("Inicio");

        menu1.add(inicio5);
        menuBar1.add(menu1);
        setJMenuBar(menuBar1);

    }
}
