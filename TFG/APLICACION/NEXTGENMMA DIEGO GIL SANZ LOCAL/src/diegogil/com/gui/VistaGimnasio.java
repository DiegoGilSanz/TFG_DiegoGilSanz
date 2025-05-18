package diegogil.com.gui;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class VistaGimnasio extends JFrame {
     JTabbedPane tabbedPane1;
     JPanel panel1;
     public  JButton gimnasioEnviarBtn;
     public  JComboBox solicitudLigaCombo;
     public JComboBox solicitudEntrenadorCombo;
     public  JComboBox solicitudGimnasioCombo;
     public JTextField solicitudApodoTxt;
     public  JTextField solicitudVictoriasTxt;
     public JTextField solicitudPesoTxt;
     public JTextField solicitudDniTxt;
     public JTextField solicitudApellidoTxt;
     public JTextField solicitudNombreTxt;
     public JTextField solicitudAsuntoTxt;
     public DatePicker solicitudNacimientoTxt;
     public JButton gimnasioInformeBtn;
     public JLabel solicitudPeleadoresActivosTxt;
     JMenuBar menuBar1;
     JMenu menu1;
     public JMenuItem inicio2;
        public JMenuItem informacion2;
     public JMenuItem salir2;
     public VistaGimnasio() {
          setContentPane(panel1);
          setTitle("Gimnasios");

          pack();
          this.setResizable(false);

          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          this.setVisible(false);
            crearMenu();



     }
     private void crearMenu() {
          menuBar1 = new JMenuBar();
          menu1 = new JMenu("Menu");
            menu1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
            menu1.setBounds(100, 80, 100, 30);
          inicio2 = new JMenuItem("Inicio");
            salir2 = new JMenuItem("Salir");
            informacion2 = new JMenuItem("Informacion");
            menu1.add(informacion2);
            menu1.add(salir2);
          menu1.add(inicio2);
          menuBar1.add(menu1);
          setJMenuBar(menuBar1);

     }
}
