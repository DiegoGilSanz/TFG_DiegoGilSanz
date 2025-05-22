package diegogil.com.gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
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
     public JPasswordField gimnasioContraseñaTxt;
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
     public JComboBox InformesComboLiga;
     public JComboBox InformesComboGimnasio;
     public byte[] postImagenSeleccionada;
     JMenuBar menuBar1;
        JMenu menu1;
      public JMenuItem informacion;
     public JMenuItem inicio;
        public JMenuItem salir;

     public VistaGestion() {

          this.setContentPane(panel1);
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pack();
          crearMenu();
          this.setResizable(false);
          this.setLocationRelativeTo(null);
          colocarDefault();
          try {
               UIManager.setLookAndFeel(new FlatMacLightLaf());
          } catch (UnsupportedLookAndFeelException e) {
               throw new RuntimeException(e);
          }
          SwingUtilities.updateComponentTreeUI(this);

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
     private void crearMenu() {
          menuBar1 = new JMenuBar();
          menu1 = new JMenu("Menu");
            menu1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
            menu1.setBounds(100, 80, 100, 30);
          inicio = new JMenuItem("Inicio");
            salir = new JMenuItem("Salir");
            menu1.add(salir);
          informacion = new JMenuItem("Informacion");
            menu1.add(informacion);


          menu1.add(inicio);
          menuBar1.add(menu1);
          setJMenuBar(menuBar1);

     }
     private void cambiarIcono(){
            ImageIcon icono = new ImageIcon("src/diegogil/com/iconoNextGenMma.png");
            setIconImage(icono.getImage());

     }

}
