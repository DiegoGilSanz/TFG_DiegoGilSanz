package diegogil.com.MC;

import diegogil.com.clases.*;
import diegogil.com.gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private Modelo modelo;
    private VistaUsuarios vistaUsers;
    private VistaPost vistaPost;
    private VistaLoginAdmin vistaLoginAdmin;
    private VistaGimnasio vistaGimnasio;
    private VistaLoginGimnasio vistaLoginGimnasio;
    private VistaGestion vistaGestion;

    public Controlador(Modelo modelo, VistaUsuarios vistaUsers, VistaPost vistaPost, VistaLoginAdmin vistaLoginAdmin, VistaGimnasio vistaGimnasio, VistaLoginGimnasio vistaLoginGimnasio, VistaGestion vistaGestion) {
        this.modelo = modelo;
        this.vistaUsers = vistaUsers;
        this.vistaPost = vistaPost;
        this.vistaLoginAdmin = vistaLoginAdmin;
        this.vistaGimnasio = vistaGimnasio;
        this.vistaLoginGimnasio = vistaLoginGimnasio;
        this.vistaGestion = vistaGestion;
        iniciar();
        addActionListeners(this);
        modelo.conectar();
    }

    public void iniciar() {
        vistaUsers.setVisible(true);
        vistaPost.setVisible(false);
        vistaLoginAdmin.setVisible(false);
        vistaGimnasio.setVisible(false);
        vistaLoginGimnasio.setVisible(false);
        vistaGestion.setVisible(false);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando){
            case "administrador":
                vistaUsers.setVisible(false);
                vistaLoginAdmin.setVisible(true);
                break;
            case "gimnasio":
                vistaUsers.setVisible(false);
                vistaLoginGimnasio.setVisible(true);
                break;
            case "invitado":
                vistaUsers.setVisible(false);
                vistaPost.setVisible(true);
                break;
            case "login admin":

                if (modelo.verificarContrasena(vistaLoginAdmin.administradorContraseñaTxt.getText())){
                    vistaLoginAdmin.setVisible(false);
                    vistaGestion.setVisible(true);
            }
                break;
            case "añadir peleador":
                Peleador peleador = new Peleador();
                peleador.setNombre(vistaGestion.peleadorNombreTxt.getText());
                peleador.setApellido(vistaGestion.peleadorApellidoTxt.getText());
                peleador.setDni(vistaGestion.peleadorDniTxt.getText());
                peleador.setPeso(Integer.parseInt(vistaGestion.peleadorPesoTxt.getText()));
                peleador.setVictorias(Integer.parseInt(vistaGestion.peleadorVictoriasTxt.getText()));
                peleador.setApodo(vistaGestion.peleadorApodoTxt.getText());
                peleador.setGimnasio((Gimnasio) vistaGestion.peleadorGimnasioCombo.getSelectedItem());
                peleador.setEntrenador((Entrenador) vistaGestion.peleadorEntrenadorCombo.getSelectedItem());
                peleador.setLiga((Liga) vistaGestion.peleadorLigaCombo.getSelectedItem());
                modelo.altaPeleador(peleador);
                break;
            case "eliminar peleador":
                modelo.eliminarPeleador((Peleador) vistaGestion.peleadorLista.getSelectedValue());

                break;
            case "modificar peleador":
                Peleador peleadorModificar = (Peleador) vistaGestion.peleadorLista.getSelectedValue();
                peleadorModificar.setNombre(vistaGestion.peleadorNombreTxt.getText());
                peleadorModificar.setApellido(vistaGestion.peleadorApellidoTxt.getText());
                peleadorModificar.setDni(vistaGestion.peleadorDniTxt.getText());
                peleadorModificar.setPeso(Integer.parseInt(vistaGestion.peleadorPesoTxt.getText()));
                peleadorModificar.setVictorias(Integer.parseInt(vistaGestion.peleadorVictoriasTxt.getText()));
                peleadorModificar.setApodo(vistaGestion.peleadorApodoTxt.getText());
                peleadorModificar.setGimnasio((Gimnasio) vistaGestion.peleadorGimnasioCombo.getSelectedItem());
                peleadorModificar.setEntrenador((Entrenador) vistaGestion.peleadorEntrenadorCombo.getSelectedItem());
                peleadorModificar.setLiga((Liga) vistaGestion.peleadorLigaCombo.getSelectedItem());
                modelo.modificarPeleador(peleadorModificar);
                break;
            case "insertar gimnasio":
                Gimnasio gimnasio = new Gimnasio();
                gimnasio.setNombre(vistaGestion.gimnasioNombreTxt.getText());
                gimnasio.setDireccion(vistaGestion.gimnasioDireccionTxt.getText());
                gimnasio.setCiudad(vistaGestion.gimnasioCiudadTxt.getText());
                gimnasio.setContraseña(vistaGestion.gimnasioContraseñaTxt.getText());
                gimnasio.setNif(vistaGestion.gimnasioNifTxt.getText());
                gimnasio.setWeb(vistaGestion.gimnasioWebTxt.getText());
                modelo.altaGimnasio(gimnasio);
                break;
            case "eliminar gimnasio":
                modelo.eliminarGimnasio((Gimnasio) vistaGestion.gimnasioLista.getSelectedValue());
                break;
            case "modificar gimnasio":
                Gimnasio gimnasioModificar = (Gimnasio) vistaGestion.gimnasioLista.getSelectedValue();
                gimnasioModificar.setNombre(vistaGestion.gimnasioNombreTxt.getText());
                gimnasioModificar.setDireccion(vistaGestion.gimnasioDireccionTxt.getText());
                gimnasioModificar.setCiudad(vistaGestion.gimnasioCiudadTxt.getText());
                gimnasioModificar.setContraseña(vistaGestion.gimnasioContraseñaTxt.getText());
                gimnasioModificar.setNif(vistaGestion.gimnasioNifTxt.getText());
                gimnasioModificar.setWeb(vistaGestion.gimnasioWebTxt.getText());
                modelo.modificarGimnasio(gimnasioModificar);
                break;
            case "insertar liga":
                Liga liga = new Liga();
                liga.setNombre(vistaGestion.ligaNombreTxt.getText());
                liga.setCiudad(vistaGestion.ligaCiudadTxt.getText());
                liga.setParticipantes(Integer.parseInt(vistaGestion.ligaParticipantesTxt.getText()));
                liga.setFederacion((Federacion) vistaGestion.ligaFederacionCombo.getSelectedItem());
                modelo.altaLiga(liga);
                break;
            case "eliminar liga":
                modelo.eliminarLiga((Liga) vistaGestion.ligaLista.getSelectedValue());
                break;
            case "modificar liga":
                Liga ligaModificar = (Liga) vistaGestion.ligaLista.getSelectedValue();
                ligaModificar.setNombre(vistaGestion.ligaNombreTxt.getText());
                ligaModificar.setCiudad(vistaGestion.ligaCiudadTxt.getText());
                ligaModificar.setParticipantes(Integer.parseInt(vistaGestion.ligaParticipantesTxt.getText()));
                ligaModificar.setFederacion((Federacion) vistaGestion.ligaFederacionCombo.getSelectedItem());
                modelo.modificarLiga(ligaModificar);
                break;
            case "insertar entrenador":
                Entrenador entrenador = new Entrenador();
                entrenador.setNombre(vistaGestion.entrenadorNombreTxt.getText());
                entrenador.setApellido(vistaGestion.entrenadorApellidoTxt.getText());
                entrenador.setDni(vistaGestion.entrenadorDniTxt.getText());
                entrenador.setNumeroColegiado(Integer.parseInt(vistaGestion.entrenadorColegiadoTxt.getText()));
                entrenador.setExperiencia(Integer.parseInt(vistaGestion.entrenadorExperienciaTxt.getText()));
                entrenador.setGimnasio((Gimnasio) vistaGestion.entrenadorGimnasioCombo.getSelectedItem());
                modelo.altaEntrenador(entrenador);
                break;
            case "eliminar entrenador":
                modelo.eliminarEntrenador((Entrenador) vistaGestion.entradorLista.getSelectedValue());
                break;
            case "modificar entrenador":
                Entrenador entrenadorModificar = (Entrenador) vistaGestion.entradorLista.getSelectedValue();
                entrenadorModificar.setNombre(vistaGestion.entrenadorNombreTxt.getText());
                entrenadorModificar.setApellido(vistaGestion.entrenadorApellidoTxt.getText());
                entrenadorModificar.setDni(vistaGestion.entrenadorDniTxt.getText());
                entrenadorModificar.setNumeroColegiado(Integer.parseInt(vistaGestion.entrenadorColegiadoTxt.getText()));
                entrenadorModificar.setExperiencia(Integer.parseInt(vistaGestion.entrenadorExperienciaTxt.getText()));
                entrenadorModificar.setGimnasio((Gimnasio) vistaGestion.entrenadorGimnasioCombo.getSelectedItem());
                modelo.modificarEntrenador(entrenadorModificar);

                break;
            case "insertar post":
                Post post = new Post();
                post.setTitulo(vistaGestion.postTituloTxt.getText());
                post.setMensaje(vistaGestion.postMensajeTxt.getText());

                modelo.altaPost(post);
                break;
            case "insertar federacion":


        }

    }
    public void addActionListeners(ActionListener listener){
        vistaUsers.usuarioAdministradorBtn.addActionListener(listener);
        vistaUsers.usuarioAdministradorBtn.setActionCommand("administrador");
        vistaUsers.usuarioGimnasioBtn.addActionListener(listener);
        vistaUsers.usuarioGimnasioBtn.setActionCommand("gimnasio");
        vistaUsers.usuarioInvitadosBtn.addActionListener(listener);
        vistaUsers.usuarioInvitadosBtn.setActionCommand("invitado");
        vistaPost.postBackBtn.addActionListener(listener);
        vistaPost.postBackBtn.setActionCommand("volver");
        vistaPost.postNextBtn.addActionListener(listener);
        vistaPost.postNextBtn.setActionCommand("siguiente");
        vistaLoginAdmin.administradorLoginBtn.addActionListener(listener);
        vistaLoginAdmin.administradorLoginBtn.setActionCommand("login admin");
        vistaGimnasio.gimnasioEnviarBtn.addActionListener(listener);
        vistaGimnasio.gimnasioEnviarBtn.setActionCommand("enviar");
        vistaGimnasio.gimnasioInformeBtn.addActionListener(listener);
        vistaGimnasio.gimnasioInformeBtn.setActionCommand("informe Gimnasio");
        vistaLoginGimnasio.loginGimnasioBtn.addActionListener(listener);
        vistaLoginGimnasio.loginGimnasioBtn.setActionCommand("login gimnasio");
        vistaGestion.peleadorAñadirBtn.addActionListener(listener);
        vistaGestion.peleadorAñadirBtn.setActionCommand("añadir peleador");
        vistaGestion.peleadorEliminarBtn.addActionListener(listener);
        vistaGestion.peleadorEliminarBtn.setActionCommand("eliminar peleador");
        vistaGestion.peleadorModificarBtn.addActionListener(listener);
        vistaGestion.peleadorModificarBtn.setActionCommand("modificar peleador");
        vistaGestion.gimnasioInsertarBtn.addActionListener(listener);
        vistaGestion.gimnasioInsertarBtn.setActionCommand("insertar gimnasio");
        vistaGestion.gimnasioModificarBtn.addActionListener(listener);
        vistaGestion.gimnasioModificarBtn.setActionCommand("modificar gimnasio");
        vistaGestion.gimnasioEliminarBtn.addActionListener(listener);
        vistaGestion.gimnasioEliminarBtn.setActionCommand("eliminar gimnasio");
        vistaGestion.ligaInsertarBtn.addActionListener(listener);
        vistaGestion.ligaInsertarBtn.setActionCommand("insertar liga");
           vistaGestion.ligaModificarBtn.addActionListener(listener);
        vistaGestion.ligaModificarBtn.setActionCommand("modificar liga");
        vistaGestion.ligaEliminarBtn.addActionListener(listener);
        vistaGestion.ligaEliminarBtn.setActionCommand("eliminar liga");
        vistaGestion.entrenadorInsertarBtn.addActionListener(listener);
        vistaGestion.entrenadorInsertarBtn.setActionCommand("insertar entrenador");
        vistaGestion.entrenadorModificarBtn.addActionListener(listener);
        vistaGestion.entrenadorModificarBtn.setActionCommand("modificar entrenador");
        vistaGestion.entrenadorEliminarBtn.addActionListener(listener);
        vistaGestion.entrenadorEliminarBtn.setActionCommand("eliminar entrenador");
        vistaGestion.federacionInsertarBtn.addActionListener(listener);
        vistaGestion.federacionInsertarBtn.setActionCommand("insertar federacion");
        vistaGestion.federacionModificarBtn.addActionListener(listener);
        vistaGestion.federacionModificarBtn.setActionCommand("modificar federacion");
        vistaGestion.federacionEliminarBtn.addActionListener(listener);
        vistaGestion.federacionEliminarBtn.setActionCommand("eliminar federacion");
        vistaGestion.postInsertarBtn.addActionListener(listener);
        vistaGestion.postInsertarBtn.setActionCommand("insertar post");
        vistaGestion.postModificarBtn.addActionListener(listener);
        vistaGestion.postModificarBtn.setActionCommand("modificar post");
        vistaGestion.postEliminarBtn.addActionListener(listener);
        vistaGestion.postEliminarBtn.setActionCommand("eliminar post");
        vistaGestion.informesEntrenadoresBtn.addActionListener(listener);
        vistaGestion.informesEntrenadoresBtn.setActionCommand("informes entrenadores");
        vistaGestion.informesGimnasiosBtn.addActionListener(listener);
        vistaGestion.informesGimnasiosBtn.setActionCommand("informes gimnasios");
        vistaGestion.informesLigasBtn.addActionListener(listener);
        vistaGestion.informesLigasBtn.setActionCommand("informes ligas");
        vistaGestion.informesPeleadoresBtn.addActionListener(listener);
        vistaGestion.informesPeleadoresBtn.setActionCommand("informes peleadores");
        vistaGestion.informesPeleadoresGimnasioBtn.addActionListener(listener);
        vistaGestion.informesPeleadoresGimnasioBtn.setActionCommand("informes peleadores gimnasio");
        vistaGestion.informesPeleadoresLigaBtn.addActionListener(listener);
        vistaGestion.informesPeleadoresLigaBtn.setActionCommand("informes peleadores liga");



    }
    void actualizarCombos(){
        comboPeleadorGimnasio();
        comboPeleadorEntrenador();
        comboEntrenadorGimnasio();
        comboPeleadorLiga();
        comboLigaFederacion();
        comboGimnasioLogin();
        
    }
    void comboPeleadorGimnasio(){
        vistaGestion.peleadorGimnasioCombo.removeAllItems();
        for (Gimnasio gimnasio:modelo.getGimnasios()){
            vistaGestion.peleadorGimnasioCombo.addItem(gimnasio);
        }
    }
    void comboPeleadorEntrenador(){
         vistaGestion.peleadorEntrenadorCombo.removeAllItems();
          for (Entrenador entrenador:modelo.getEntrenadores()){
                vistaGestion.peleadorEntrenadorCombo.addItem(entrenador);
          }
     }
    void comboPeleadorLiga(){
        vistaGestion.peleadorLigaCombo.removeAllItems();
        for (Liga liga:modelo.getLigas()){
            vistaGestion.peleadorLigaCombo.addItem(liga);
        }
    }
    void comboEntrenadorGimnasio() {
        vistaGestion.entrenadorGimnasioCombo.removeAllItems();
        for (Gimnasio gimnasio : modelo.getGimnasios()) {
            vistaGestion.entrenadorGimnasioCombo.addItem(gimnasio);
        }
    }
    void comboLigaFederacion() {
        vistaGestion.ligaFederacionCombo.removeAllItems();
        for (Federacion federacion : modelo.getFederaciones()) {
            vistaGestion.ligaFederacionCombo.addItem(federacion);
        }
    }
    void comboGimnasioLogin() {
        vistaLoginGimnasio.loginGimnasioCombo.removeAllItems();
        for (Gimnasio gimnasio : modelo.getGimnasios()) {
            vistaLoginGimnasio.loginGimnasioCombo.addItem(gimnasio);
        }
    }


}
