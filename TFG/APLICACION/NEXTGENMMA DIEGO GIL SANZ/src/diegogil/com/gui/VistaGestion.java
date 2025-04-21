package diegogil.com.gui;

import com.github.lgooddatepicker.components.DatePicker;
import diegogil.com.clases.*;

import javax.swing.*;

public class VistaGestion extends JFrame{
     JTabbedPane tabbedPane1;
       JPanel panel1;
     public JButton peleadorAñadirBtn;
     public JButton peleadorModificarBtn;
     public JButton peleadorEliminarBtn;
     public  JList peleadorLista;
     public   DefaultListModel<Peleador> dflmpeleador;
     public JComboBox peleadorLigaCombo;
     public JComboBox peleadorEntrenadorCombo;
     public JComboBox peleadorGimnasioCombo;
     public JTextField peleadorApodoTxt;
     public  JTextField peleadorVictoriasTxt;
     public JTextField peleadorPesoTxt;
     public JTextField peleadorDniTxt;
     public  JTextField peleadorApellidoTxt;
     public  JTextField peleadorNombreTxt;
     public  DatePicker peleadorNacimientoTxt;
     public  JTextField gimnasioNombreTxt;
     public  JTextField gimnasioDireccionTxt;
     public  JTextField gimnasioNifTxt;
     public  JTextField gimnasioCiudadTxt;
     public  JTextField gimnasioContraseñaTxt;
     public  JTextField gimnasioWebTxt;
     public  JButton gimnasioInsertarBtn;
     public  JButton gimnasioModificarBtn;
     public JButton gimnasioEliminarBtn;
     public JList gimnasioLista;
        public DefaultListModel<Gimnasio> dflmgimnasio;
     public JButton ligaInsertarBtn;
     public JButton ligaModificarBtn;
     public JButton ligaEliminarBtn;
     public JList ligaLista;
        public DefaultListModel<Liga> dflmliga;
     public JComboBox ligaFederacionCombo;
     public  JTextField ligaParticipantesTxt;
     public JTextField ligaCiudadTxt;
     public JTextField ligaNombreTxt;
     public JButton entrenadorInsertarBtn;
     public  JButton entrenadorModificarBtn;
     public  JButton entrenadorEliminarBtn;
     public JTextField entrenadorNombreTxt;
     public  JTextField entrenadorApellidoTxt;
     public  JTextField entrenadorDniTxt;
     public JTextField entrenadorColegiadoTxt;
     public JTextField entrenadorExperienciaTxt;
     public JComboBox entrenadorGimnasioCombo;
     public JList entradorLista;
     public DefaultListModel<Entrenador> dflmentrenador;
     public JButton federacionInsertarBtn;
     public JButton federacionModificarBtn;
     public JButton federacionEliminarBtn;
     public JTextField federacionArteMarcialTxt;
     public JTextField federacionNumeroAsociacionTxt;
     public JTextField federacionNombreTxt;
     public JList federacionLista;
     public DefaultListModel<Federacion> dflmfederacion;
     public  DatePicker federacionAñoFundacionTxt;
     public JButton postInsertarBtn;
     public JButton postModificarBtn;
     public  JButton postEliminarBtn;
     public  JTextField postTituloTxt;
     public JButton postAdjuntarImageBtn;
     public  JList postLista;
     public  DefaultListModel<Post> dflmpost;
     public  JTextArea postMensajeTxt;
     public  JButton informesGimnasiosBtn;
     public  JButton informesPeleadoresBtn;
     public  JButton informesEntrenadoresBtn;
     public  JButton informesLigasBtn;
     public  JButton informesPeleadoresLigaBtn;
     public JButton informesPeleadoresGimnasioBtn;
     public JComboBox comboBox1;
     public JComboBox comboBox2;
     public byte[] postImagenSeleccionada;

     public VistaGestion() {

          this.setContentPane(panel1);
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setSize(1000,1000);
          this.setResizable(false);
          this.setLocationRelativeTo(null);
          colocarDefault();

          this.setVisible(false);
     }
     private void colocarDefault(){
          dflmpeleador = new DefaultListModel<>();
          peleadorLista.setModel(dflmpeleador);
          dflmgimnasio = new DefaultListModel<>();
          gimnasioLista.setModel(dflmgimnasio);
          dflmentrenador = new DefaultListModel<>();
          entradorLista.setModel(dflmentrenador);
          dflmliga = new DefaultListModel<>();
          ligaLista.setModel(dflmliga);
          dflmfederacion = new DefaultListModel<>();
          federacionLista.setModel(dflmfederacion);
          dflmpost = new DefaultListModel<>();
          postLista.setModel(dflmpost);


     }

}
