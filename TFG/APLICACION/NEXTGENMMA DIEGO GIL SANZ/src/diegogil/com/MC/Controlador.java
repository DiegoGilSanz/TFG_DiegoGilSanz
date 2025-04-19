package diegogil.com.MC;

import diegogil.com.clases.*;
import diegogil.com.gui.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class Controlador implements ActionListener, ListSelectionListener {
    private Modelo modelo;
    private VistaUsuarios vistaUsers;
    private VistaPost vistaPost;
    private VistaLoginAdmin vistaLoginAdmin;
    private VistaGimnasio vistaGimnasio;
    private VistaLoginGimnasio vistaLoginGimnasio;
    private VistaGestion vistaGestion;

    public Controlador(Modelo modelo, VistaUsuarios vistaUsers, VistaPost vistaPost, VistaLoginAdmin vistaLoginAdmin, VistaGimnasio vistaGimnasio, VistaLoginGimnasio vistaLoginGimnasio, VistaGestion vistaGestion) {
        this.modelo = modelo;
        modelo.conectar();
        this.vistaUsers = vistaUsers;
        this.vistaPost = vistaPost;
        this.vistaLoginAdmin = vistaLoginAdmin;
        this.vistaGimnasio = vistaGimnasio;
        this.vistaLoginGimnasio = vistaLoginGimnasio;
        this.vistaGestion = vistaGestion;
        iniciar();
        addActionListeners(this);

        addListSelectionListener(this);
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
                peleador.setFechaNacimiento(Date.valueOf(vistaGestion.peleadorNacimientoTxt.getDate()));
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
            case "eliminar post":
                modelo.eliminarPost((Post) vistaGestion.postLista.getSelectedValue());
                break;
            case "modificar post":
                Post postModificar = (Post) vistaGestion.postLista.getSelectedValue();
                postModificar.setTitulo(vistaGestion.postTituloTxt.getText());
                postModificar.setMensaje(vistaGestion.postMensajeTxt.getText());
                modelo.modificarPost(postModificar);
                break;
            case "insertar federacion":
                Federacion federacion = new Federacion();
                federacion.setNombre(vistaGestion.federacionNombreTxt.getText());
                federacion.setNumeroAsociacion(Integer.parseInt(vistaGestion.federacionNumeroAsociacionTxt.getText()));
                federacion.setArteMarcial(vistaGestion.federacionArteMarcialTxt.getText());
                federacion.setFechaFundacion(Date.valueOf(vistaGestion.federacionAñoFundacionTxt.getDate()));
                modelo.altaFederacion(federacion);
                break;
            case "eliminar federacion":
                modelo.eliminarFederacion((Federacion) vistaGestion.federacionLista.getSelectedValue());
                break;
            case "modificar federacion":
                Federacion federacionModificar = (Federacion) vistaGestion.federacionLista.getSelectedValue();
                federacionModificar.setNombre(vistaGestion.federacionNombreTxt.getText());
                federacionModificar.setNumeroAsociacion(Integer.parseInt(vistaGestion.federacionNumeroAsociacionTxt.getText()));
                federacionModificar.setArteMarcial(vistaGestion.federacionArteMarcialTxt.getText());
                federacionModificar.setFechaFundacion(Date.valueOf(vistaGestion.federacionAñoFundacionTxt.getDate()));
                modelo.modificarFederacion(federacionModificar);
                break;


        }
        actualizarCombos();
        actualizarListas();

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
    void addListSelectionListener(ListSelectionListener listener){
        vistaGestion.peleadorLista.addListSelectionListener(listener);
        vistaGestion.gimnasioLista.addListSelectionListener(listener);
        vistaGestion.ligaLista.addListSelectionListener(listener);
        vistaGestion.entradorLista.addListSelectionListener(listener);
        vistaGestion.federacionLista.addListSelectionListener(listener);
        vistaGestion.postLista.addListSelectionListener(listener);

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


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            if (e.getSource() == vistaGestion.peleadorLista) {
                Peleador peleadorSeleccion = (Peleador) vistaGestion.peleadorLista.getSelectedValue();
                vistaGestion.peleadorNombreTxt.setText(peleadorSeleccion.getNombre());
                vistaGestion.peleadorApellidoTxt.setText(peleadorSeleccion.getApellido());
                vistaGestion.peleadorApodoTxt.setText(peleadorSeleccion.getApodo());
                vistaGestion.peleadorDniTxt.setText(peleadorSeleccion.getDni());
                vistaGestion.peleadorPesoTxt.setText(String.valueOf(peleadorSeleccion.getPeso()));
                vistaGestion.peleadorVictoriasTxt.setText(String.valueOf(peleadorSeleccion.getVictorias()));
                vistaGestion.peleadorNacimientoTxt.setDate(peleadorSeleccion.getFechaNacimiento().toLocalDate());
                vistaGestion.peleadorGimnasioCombo.setSelectedItem(peleadorSeleccion.getGimnasio());
                vistaGestion.peleadorEntrenadorCombo.setSelectedItem(peleadorSeleccion.getEntrenador());
                vistaGestion.peleadorLigaCombo.setSelectedItem(peleadorSeleccion.getLiga());



            } else if (e.getSource() == vistaGestion.gimnasioLista) {
                Gimnasio gimnasioSeleccion = (Gimnasio) vistaGestion.gimnasioLista.getSelectedValue();
                vistaGestion.gimnasioNombreTxt.setText(gimnasioSeleccion.getNombre());
                vistaGestion.gimnasioCiudadTxt.setText(gimnasioSeleccion.getCiudad());
                vistaGestion.gimnasioDireccionTxt.setText(gimnasioSeleccion.getDireccion());
                vistaGestion.gimnasioWebTxt.setText(gimnasioSeleccion.getWeb());
                vistaGestion.gimnasioNifTxt.setText(gimnasioSeleccion.getNif());
                vistaGestion.gimnasioContraseñaTxt.setText(gimnasioSeleccion.getContraseña());

            } else if (e.getSource() == vistaGestion.entradorLista) {
                Entrenador entrenadorSeleccion = (Entrenador) vistaGestion.entradorLista.getSelectedValue();
                vistaGestion.entrenadorNombreTxt.setText(entrenadorSeleccion.getNombre());
                vistaGestion.entrenadorApellidoTxt.setText(entrenadorSeleccion.getApellido());
                vistaGestion.entrenadorExperienciaTxt.setText(String.valueOf(entrenadorSeleccion.getExperiencia()));
                vistaGestion.entrenadorDniTxt.setText(entrenadorSeleccion.getDni());
                vistaGestion.entrenadorColegiadoTxt.setText(String.valueOf(entrenadorSeleccion.getNumeroColegiado()));

                vistaGestion.entrenadorGimnasioCombo.setSelectedItem(entrenadorSeleccion.getGimnasio());
            } else if (e.getSource() == vistaGestion.ligaLista) {
                Liga ligaSeleccion = (Liga) vistaGestion.ligaLista.getSelectedValue();
                vistaGestion.ligaNombreTxt.setText(ligaSeleccion.getNombre());
                vistaGestion.ligaCiudadTxt.setText(ligaSeleccion.getCiudad());
                vistaGestion.ligaParticipantesTxt.setText(String.valueOf(ligaSeleccion.getParticipantes()));
                vistaGestion.ligaFederacionCombo.setSelectedItem(ligaSeleccion.getFederacion());

            }
            else if (e.getSource() == vistaGestion.federacionLista) {
                Federacion federacionSeleccion = (Federacion) vistaGestion.federacionLista.getSelectedValue();
                vistaGestion.federacionNombreTxt.setText(federacionSeleccion.getNombre());
                vistaGestion.federacionArteMarcialTxt.setText(federacionSeleccion.getArteMarcial());
                vistaGestion.federacionNumeroAsociacionTxt.setText(String.valueOf(federacionSeleccion.getNumeroAsociacion()));
                vistaGestion.federacionAñoFundacionTxt.setDate(federacionSeleccion.getFechaFundacion().toLocalDate());

            } else if (e.getSource() == vistaGestion.postLista) {
                Post postSeleccion = (Post) vistaGestion.postLista.getSelectedValue();
                vistaGestion.postTituloTxt.setText(postSeleccion.getTitulo());
                vistaGestion.postMensajeTxt.setText(postSeleccion.getMensaje());

            }
        }

    }
    void actualizarListas(){
        listarPeleadores(modelo.getPeleadores());
        listarGimnasios(modelo.getGimnasios());
        listarEntrenadores(modelo.getEntrenadores());
        listarLigas(modelo.getLigas());
        listarFederaciones(modelo.getFederaciones());
        listarPosts(modelo.getPosts());

    }

    private void listarPeleadores(ArrayList<Peleador> peleadores){
        vistaGestion.dflmpeleador.clear();
        for (Peleador peleador:peleadores){
            vistaGestion.dflmpeleador.addElement(peleador);
        }
    }
    private void listarGimnasios(ArrayList<Gimnasio> gimnasios){
        vistaGestion.dflmgimnasio.clear();
        for (Gimnasio gimnasio:gimnasios){
            vistaGestion.dflmgimnasio.addElement(gimnasio);
        }
    }
private void listarEntrenadores(ArrayList<Entrenador> entrenadores){
        vistaGestion.dflmentrenador.clear();
        for (Entrenador entrenador:entrenadores){
            vistaGestion.dflmentrenador.addElement(entrenador);
        }
    }
private void listarLigas(ArrayList<Liga> ligas){
        vistaGestion.dflmliga.clear();
        for (Liga liga:ligas){
            vistaGestion.dflmliga.addElement(liga);
        }
    }
private void listarFederaciones(ArrayList<Federacion> federaciones){
        vistaGestion.dflmfederacion.clear();
        for (Federacion federacion:federaciones){
            vistaGestion.dflmfederacion.addElement(federacion);
        }
    }
private void listarPosts(ArrayList<Post> posts) {
    vistaGestion.dflmpost.clear();
    for (Post post : posts) {
        vistaGestion.dflmpost.addElement(post);
    }
}

}
