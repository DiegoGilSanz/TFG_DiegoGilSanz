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
     public VistaGimnasio() {
          setContentPane(panel1);
          setTitle("Gimnasios");
          setSize(800, 600);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          setVisible(true);



     }
}
