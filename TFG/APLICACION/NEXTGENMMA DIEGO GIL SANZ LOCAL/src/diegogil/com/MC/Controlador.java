package diegogil.com.MC;

import diegogil.com.clases.*;
import diegogil.com.gui.*;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.codec.digest.DigestUtils;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Clase Controlador que implementa la lógica de la aplicación.
 * Se encarga de gestionar las interacciones entre el modelo, las vistas y los eventos de usuario.
 */
public class Controlador implements ActionListener, ListSelectionListener {
    public static final File AYUDA = new File("src/html/Ayuda.html");
    private Modelo modelo;
    private VistaUsuarios vistaUsers;
    private VistaPost vistaPost;
    private VistaLoginAdmin vistaLoginAdmin;
    private VistaGimnasio vistaGimnasio;
    private VistaLoginGimnasio vistaLoginGimnasio;
    private VistaGestion vistaGestion;
    int numeroPost = 1;

    /**
     * Constructor de la clase Controlador.
     * Inicializa el modelo y las vistas, y establece los listeners para los eventos.
     *
     * @param modelo          El modelo de la aplicación.
     * @param vistaUsers      La vista de usuarios.
     * @param vistaPost       La vista de publicaciones.
     * @param vistaLoginAdmin La vista de inicio de sesión del administrador.
     * @param vistaGimnasio   La vista del gimnasio.
     * @param vistaLoginGimnasio La vista de inicio de sesión del gimnasio.
     * @param vistaGestion    La vista de gestión.
     */

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

        addListSelectionListener(this);
        inicializarPost(modelo.getPosts());
    }
    /**
     * Método para inicializar las vistas
     *
     *
     */
    public void iniciar() {
        vistaUsers.setVisible(true);
        vistaPost.setVisible(false);
        vistaLoginAdmin.setVisible(false);
        vistaGimnasio.setVisible(false);
        vistaLoginGimnasio.setVisible(false);
        vistaGestion.setVisible(false);

    }
    /**
     * Método que gestiona los eventos de acción de los botones.
     *
     * @param e Evento de acción capturado.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "administrador":
                vistaUsers.setVisible(false);
                vistaLoginAdmin.setVisible(true);
                break;
            case "gimnasio":

                vistaUsers.setVisible(false);
                comboGimnasioLogin();
                vistaLoginGimnasio.setVisible(true);

                break;
            case "invitado":
                vistaUsers.setVisible(false);
                vistaPost.setVisible(true);
                inicializarPost(modelo.getPosts());
                break;
            case "login admin":
                String hashGimnasioContraseñaLoginAdmin;
                hashGimnasioContraseñaLoginAdmin = DigestUtils.sha256Hex(vistaLoginAdmin.administradorContraseñaTxt.getText());
                Admin admin=modelo.getAdmin();
                if (modelo.verificarContrasena(admin,hashGimnasioContraseñaLoginAdmin)) {
                    vistaLoginAdmin.setVisible(false);
                    vistaLoginAdmin.administradorContraseñaTxt.setText("");
                    actualizarListas();
                    actualizarCombos();
                    vistaGestion.pack();
                    vistaGestion.setVisible(true);

                }else {
                    JOptionPane.showMessageDialog(vistaLoginAdmin, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "login gimnasio":
                String hashGimnasioContraseñaLogin;
                hashGimnasioContraseñaLogin = DigestUtils.sha256Hex(vistaLoginGimnasio.loginGimnasioContraseñaTxt.getText());
                if (modelo.verificarContrasenaGimnasio((Gimnasio) vistaLoginGimnasio.loginGimnasioCombo.getSelectedItem(), hashGimnasioContraseñaLogin)) {
                    vistaLoginGimnasio.setVisible(false);
                    vistaLoginGimnasio.loginGimnasioContraseñaTxt.setText("");
                    vistaGimnasio.setVisible(true);
                    comboInformesGimnasio();
                    comboInformesLiga();
                    comboGimnasioEntrenador();

                    vistaGimnasio.solicitudPeleadoresActivosTxt.setText(String.valueOf(modelo.peleadoresActivos((Gimnasio) vistaLoginGimnasio.loginGimnasioCombo.getSelectedItem())));


                }else {
                    JOptionPane.showMessageDialog(vistaLoginGimnasio, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;


            case "añadir peleador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres añadir este peleador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (validarCamposPeleador()) {
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
                        listarPeleadores(modelo.getPeleadores());
                        vaciarCamposPeleador();

                    }
                }
                break;
            case "eliminar peleador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar este peleador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        modelo.eliminarPeleador((Peleador) vistaGestion.peleadorLista.getSelectedValue());
                        listarPeleadores(modelo.getPeleadores());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede eliminar el peleador porque tiene peleas asociadas o no existen peleadores.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
                break;
            case "modificar peleador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar este peleador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                    if (validarCamposPeleador()) {
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
                        listarPeleadores(modelo.getPeleadores());
                        vaciarCamposPeleador();
                    }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede modificar el peleador porque no existe o no esta seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "insertar gimnasio":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres insertar este gimnasio?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    String hashGimnasioContraseña;
                    if (validarCamposGimnasio()) {
                        Gimnasio gimnasio = new Gimnasio();
                        gimnasio.setNombre(vistaGestion.gimnasioNombreTxt.getText());
                        gimnasio.setDireccion(vistaGestion.gimnasioDireccionTxt.getText());
                        gimnasio.setCiudad(vistaGestion.gimnasioCiudadTxt.getText());
                        hashGimnasioContraseña = DigestUtils.sha256Hex(vistaGestion.gimnasioContraseñaTxt.getText());
                        gimnasio.setContraseña(hashGimnasioContraseña);
                        gimnasio.setNif(vistaGestion.gimnasioNifTxt.getText());
                        gimnasio.setWeb(vistaGestion.gimnasioWebTxt.getText());
                        modelo.altaGimnasio(gimnasio);
                        listarGimnasios(modelo.getGimnasios());
                        comboPeleadorGimnasio();
                        comboEntrenadorGimnasio();
                        comboInformesGimnasio();
                        comboGimnasioLogin();
                        vaciarCamposGimnasio();

                    }
                }
                break;
            case "eliminar gimnasio":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar este gimnasio?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        modelo.eliminarGimnasio((Gimnasio) vistaGestion.gimnasioLista.getSelectedValue());
                        listarGimnasios(modelo.getGimnasios());
                        comboPeleadorGimnasio();
                        comboEntrenadorGimnasio();
                        comboInformesGimnasio();
                        comboGimnasioLogin();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede eliminar el gimnasio porque tiene peleadores asociados o no existen gimnasios.", "Error", JOptionPane.ERROR_MESSAGE);
                    }


                }
                break;
            case "modificar gimnasio":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar este gimnasio?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                   try {


                    String hash;
                    if (validarCamposGimnasio()) {
                        Gimnasio gimnasioModificar = (Gimnasio) vistaGestion.gimnasioLista.getSelectedValue();
                        gimnasioModificar.setNombre(vistaGestion.gimnasioNombreTxt.getText());
                        gimnasioModificar.setDireccion(vistaGestion.gimnasioDireccionTxt.getText());
                        gimnasioModificar.setCiudad(vistaGestion.gimnasioCiudadTxt.getText());
                        hash = DigestUtils.sha256Hex(vistaGestion.gimnasioContraseñaTxt.getText());
                        gimnasioModificar.setContraseña(hash);
                        gimnasioModificar.setNif(vistaGestion.gimnasioNifTxt.getText());
                        gimnasioModificar.setWeb(vistaGestion.gimnasioWebTxt.getText());
                        modelo.modificarGimnasio(gimnasioModificar);
                        listarGimnasios(modelo.getGimnasios());
                        comboPeleadorGimnasio();
                        comboEntrenadorGimnasio();
                        comboInformesGimnasio();
                        comboGimnasioLogin();
                        vaciarCamposGimnasio();
                    }
                   } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede modificar el gimnasio porque no existe o no esta seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;

            case "insertar liga":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres insertar esta liga?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (validarCamposLiga()) {
                        Liga liga = new Liga();
                        liga.setNombre(vistaGestion.ligaNombreTxt.getText());
                        liga.setCiudad(vistaGestion.ligaCiudadTxt.getText());
                        liga.setParticipantes(Integer.parseInt(vistaGestion.ligaParticipantesTxt.getText()));
                        liga.setFederacion((Federacion) vistaGestion.ligaFederacionCombo.getSelectedItem());
                        modelo.altaLiga(liga);
                        listarLigas(modelo.getLigas());
                        comboPeleadorLiga();
                        comboInformesLiga();
                        validarCamposLiga();
                        vaciarCamposLiga();
                    }
                }
                break;
            case "eliminar liga":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar esta liga?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        modelo.eliminarLiga((Liga) vistaGestion.ligaLista.getSelectedValue());
                        comboPeleadorLiga();
                        comboInformesLiga();
                        vaciarCamposLiga();
                        listarLigas(modelo.getLigas());

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede eliminar la liga porque tiene peleadores asociados o no existen ligas.", "Error", JOptionPane.ERROR_MESSAGE);
                    }


                }
                break;
            case "modificar liga":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar esta liga?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {


                    if (validarCamposLiga()) {
                        Liga ligaModificar = (Liga) vistaGestion.ligaLista.getSelectedValue();
                        ligaModificar.setNombre(vistaGestion.ligaNombreTxt.getText());
                        ligaModificar.setCiudad(vistaGestion.ligaCiudadTxt.getText());
                        ligaModificar.setParticipantes(Integer.parseInt(vistaGestion.ligaParticipantesTxt.getText()));
                        ligaModificar.setFederacion((Federacion) vistaGestion.ligaFederacionCombo.getSelectedItem());
                        modelo.modificarLiga(ligaModificar);
                        listarLigas(modelo.getLigas());
                        comboPeleadorLiga();
                        comboInformesLiga();
                        vaciarCamposLiga();
                        validarCamposLiga();
                    }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede modificar la liga porque no existe o no esta seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "insertar entrenador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres insertar este entrenador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (validarCamposEntrenador()) {
                        Entrenador entrenador = new Entrenador();
                        entrenador.setNombre(vistaGestion.entrenadorNombreTxt.getText());
                        entrenador.setApellido(vistaGestion.entrenadorApellidoTxt.getText());
                        entrenador.setDni(vistaGestion.entrenadorDniTxt.getText());
                        entrenador.setNumeroColegiado(Integer.parseInt(vistaGestion.entrenadorColegiadoTxt.getText()));
                        entrenador.setExperiencia(Integer.parseInt(vistaGestion.entrenadorExperienciaTxt.getText()));
                        entrenador.setGimnasio((Gimnasio) vistaGestion.entrenadorGimnasioCombo.getSelectedItem());
                        modelo.altaEntrenador(entrenador);
                        listarEntrenadores(modelo.getEntrenadores());
                        comboPeleadorEntrenador();
                        vaciarCamposEntrenador();

                    }
                }
                break;
            case "eliminar entrenador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar este entrenador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        modelo.eliminarEntrenador((Entrenador) vistaGestion.entradorLista.getSelectedValue());
                        listarEntrenadores(modelo.getEntrenadores());
                        comboPeleadorEntrenador();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede eliminar el entrenador porque tiene peleadores asociados o no existen entrenadores.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "modificar entrenador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar este entrenador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                    if (validarCamposEntrenador()) {
                        Entrenador entrenadorModificar = (Entrenador) vistaGestion.entradorLista.getSelectedValue();
                        entrenadorModificar.setNombre(vistaGestion.entrenadorNombreTxt.getText());
                        entrenadorModificar.setApellido(vistaGestion.entrenadorApellidoTxt.getText());
                        entrenadorModificar.setDni(vistaGestion.entrenadorDniTxt.getText());
                        entrenadorModificar.setNumeroColegiado(Integer.parseInt(vistaGestion.entrenadorColegiadoTxt.getText()));
                        entrenadorModificar.setExperiencia(Integer.parseInt(vistaGestion.entrenadorExperienciaTxt.getText()));
                        entrenadorModificar.setGimnasio((Gimnasio) vistaGestion.entrenadorGimnasioCombo.getSelectedItem());
                        modelo.modificarEntrenador(entrenadorModificar);
                        listarEntrenadores(modelo.getEntrenadores());
                        comboPeleadorEntrenador();
                        vaciarCamposEntrenador();

                    }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede modificar el entrenador porque no existe o no esta seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

                break;
            case "adjuntar imagen":
              adjuntarImagen();
                break;
            case "insertar post":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres insertar este post?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (validarCamposPost()) {
                        Post post = new Post();
                        post.setTitulo(vistaGestion.postTituloTxt.getText());
                        post.setMensaje(vistaGestion.postMensajeTxt.getText());
                        post.setFoto(vistaGestion.postImagenSeleccionada); // Asigna la imagen seleccionada
                        modelo.altaPost(post);
                        listarPosts(modelo.getPosts());
                        vaciarCamposPost();
                    }
                }
                break;
            case "eliminar post":

                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar este post?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        modelo.eliminarPost((Post) vistaGestion.postLista.getSelectedValue());
                        listarPosts(modelo.getPosts());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede eliminar el post porque no existe o no tiene federaciones asociadas.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
                break;
            case "modificar post":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar este post?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                     try{
                    if (validarCamposPost()) {
                        Post postModificar = (Post) vistaGestion.postLista.getSelectedValue();
                        postModificar.setTitulo(vistaGestion.postTituloTxt.getText());
                        postModificar.setMensaje(vistaGestion.postMensajeTxt.getText());
                        modelo.modificarPost(postModificar);
                        listarPosts(modelo.getPosts());
                        vaciarCamposPost();
                    }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(vistaGestion, "No se puede modificar el post porque no existe o no esta seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                }
                break;
            case "insertar federacion":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres insertar esta federación?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (validarCamposFederacion()) {
                        Federacion federacion = new Federacion();
                        federacion.setNombre(vistaGestion.federacionNombreTxt.getText());
                        federacion.setNumeroAsociacion(Integer.parseInt(vistaGestion.federacionNumeroAsociacionTxt.getText()));
                        federacion.setArteMarcial(vistaGestion.federacionArteMarcialTxt.getText());
                        federacion.setFechaFundacion(Date.valueOf(vistaGestion.federacionAñoFundacionTxt.getDate()));
                        modelo.altaFederacion(federacion);
                        listarFederaciones(modelo.getFederaciones());
                        comboLigaFederacion();
                        vaciarCamposFederacion();
                    }
                }
                break;
            case "eliminar federacion":

                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar esta federación?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {

                        modelo.eliminarFederacion((Federacion) vistaGestion.federacionLista.getSelectedValue());
                        listarFederaciones(modelo.getFederaciones());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede eliminar la federación porque tiene ligas asociadas o no existen federaciones.", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }
                break;
            case "modificar federacion":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar esta federación?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                    if (validarCamposFederacion()) {
                        Federacion federacionModificar = (Federacion) vistaGestion.federacionLista.getSelectedValue();
                        federacionModificar.setNombre(vistaGestion.federacionNombreTxt.getText());
                        federacionModificar.setNumeroAsociacion(Integer.parseInt(vistaGestion.federacionNumeroAsociacionTxt.getText()));
                        federacionModificar.setArteMarcial(vistaGestion.federacionArteMarcialTxt.getText());
                        federacionModificar.setFechaFundacion(Date.valueOf(vistaGestion.federacionAñoFundacionTxt.getDate()));
                        modelo.modificarFederacion(federacionModificar);
                        listarFederaciones(modelo.getFederaciones());
                        comboLigaFederacion();
                        vaciarCamposFederacion();
                    }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vistaGestion, "No se puede modificar la federación porque no existe o no esta seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "enviar":
                Peleador peleadorEnviar = new Peleador();

                peleadorEnviar.setNombre(vistaGimnasio.solicitudNombreTxt.getText());
                peleadorEnviar.setApellido(vistaGimnasio.solicitudApellidoTxt.getText());
                peleadorEnviar.setDni(vistaGimnasio.solicitudDniTxt.getText());
                peleadorEnviar.setPeso(Integer.parseInt(vistaGimnasio.solicitudPesoTxt.getText()));
                peleadorEnviar.setVictorias(Integer.parseInt(vistaGimnasio.solicitudVictoriasTxt.getText()));
                peleadorEnviar.setApodo(vistaGimnasio.solicitudApodoTxt.getText());
                peleadorEnviar.setFechaNacimiento(Date.valueOf(vistaGimnasio.solicitudNacimientoTxt.getDate()));
                peleadorEnviar.setGimnasio((Gimnasio) vistaGimnasio.solicitudGimnasioCombo.getSelectedItem());
                peleadorEnviar.setEntrenador((Entrenador) vistaGimnasio.solicitudEntrenadorCombo.getSelectedItem());
                peleadorEnviar.setLiga((Liga) vistaGimnasio.solicitudLigaCombo.getSelectedItem());


                modelo.enviarCorreo(peleadorEnviar, vistaGimnasio.solicitudAsuntoTxt.getText());
                break;
            case "inicio":
                vistaGestion.setVisible(false);
                vistaUsers.setVisible(true);
                vistaPost.setVisible(false);
                vistaLoginAdmin.setVisible(false);
                vistaGimnasio.setVisible(false);
                vistaLoginGimnasio.setVisible(false);

                break;
            case "volver":
               volverPost();


                break;
            case "siguiente":
               siguientePost();
                break;
            case "informes peleadores":

                JasperPrint reporteLleno = modelo.generarPeleadores();
                JasperViewer viewer1 = new JasperViewer(reporteLleno, false);
                viewer1.setVisible(true);
                break;
            case "informes gimnasios":
                JasperPrint reporteLleno2 = modelo.generarGimnasios();
                JasperViewer viewer2 = new JasperViewer(reporteLleno2, false);
                viewer2.setVisible(true);
                break;
            case "informes ligas":
                JasperPrint reporteLleno3 = modelo.generarLigas();
                JasperViewer viewer3 = new JasperViewer(reporteLleno3, false);
                viewer3.setVisible(true);
                JasperPrint reporteLleno6 = modelo.generarRecuentoLigas();
                JasperViewer viewer6 = new JasperViewer(reporteLleno6, false);
                viewer6.setVisible(true);

                break;
            case "informes entrenadores":
                JasperPrint reporteLleno4 = modelo.generarEntrenadores();
                JasperViewer viewer4 = new JasperViewer(reporteLleno4, false);
                viewer4.setVisible(true);
                break;
            case "informes peleadores liga":
                JasperPrint reporteLleno5 = modelo.generarPeleadoresLiga((Liga) vistaGestion.InformesComboLiga.getSelectedItem());
                JasperViewer viewer5 = new JasperViewer(reporteLleno5, false);
                viewer5.setVisible(true);

                break;
            case "informes peleadores gimnasio":
                JasperPrint reporteLleno7 = modelo.generarPeleadoresGimnasio((Gimnasio) vistaGestion.InformesComboGimnasio.getSelectedItem());
                JasperViewer viewer7 = new JasperViewer(reporteLleno7,false);
                viewer7.setVisible(true);
                viewer7.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            break;
            case "salir":
                System.exit(0);
                break;
            case "informacion":
                try {
                    Desktop.getDesktop().browse(AYUDA.toURI());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;






        }


    }
    /**
     * Método para vaciar los campos del formulario de peleador.
     *
     */
    private void vaciarCamposPeleador() {
        vistaGestion.peleadorNombreTxt.setText("");
        vistaGestion.peleadorApellidoTxt.setText("");
        vistaGestion.peleadorApodoTxt.setText("");
        vistaGestion.peleadorDniTxt.setText("");
        vistaGestion.peleadorPesoTxt.setText("");
        vistaGestion.peleadorVictoriasTxt.setText("");
        vistaGestion.peleadorNacimientoTxt.setDate(null);
        vistaGestion.peleadorGimnasioCombo.setSelectedItem(null);
        vistaGestion.peleadorEntrenadorCombo.setSelectedItem(null);
        vistaGestion.peleadorLigaCombo.setSelectedItem(null);
    }

    /**
     * Método para vaciar los campos del formulario de gimnasio.
     *
     */


    private void vaciarCamposGimnasio() {
        vistaGestion.gimnasioNombreTxt.setText("");
        vistaGestion.gimnasioDireccionTxt.setText("");
        vistaGestion.gimnasioCiudadTxt.setText("");
        vistaGestion.gimnasioContraseñaTxt.setText("");
        vistaGestion.gimnasioNifTxt.setText("");
        vistaGestion.gimnasioWebTxt.setText("");
    }
    /**
     * Método para vaciar los campos del formulario de entrenador.
     *
     */


    private void vaciarCamposEntrenador() {
        vistaGestion.entrenadorNombreTxt.setText("");
        vistaGestion.entrenadorApellidoTxt.setText("");
        vistaGestion.entrenadorDniTxt.setText("");
        vistaGestion.entrenadorColegiadoTxt.setText("");
        vistaGestion.entrenadorExperienciaTxt.setText("");
        vistaGestion.entrenadorGimnasioCombo.setSelectedItem(null);
    }
    /**
     * Método para vaciar los campos del formulario de liga.
     *
     */


    private void vaciarCamposLiga() {
        vistaGestion.ligaNombreTxt.setText("");
        vistaGestion.ligaCiudadTxt.setText("");
        vistaGestion.ligaParticipantesTxt.setText("");
        vistaGestion.ligaFederacionCombo.setSelectedItem(null);
    }
    /**
     * Método para vaciar los campos del formulario de federación.
     *
     */
    private void vaciarCamposFederacion() {
        vistaGestion.federacionNombreTxt.setText("");
        vistaGestion.federacionArteMarcialTxt.setText("");
        vistaGestion.federacionNumeroAsociacionTxt.setText("");
        vistaGestion.federacionAñoFundacionTxt.setDate(null);
    }
    /**
     * Método para vaciar los campos del formulario de post.
     *
     *
     */
    private void vaciarCamposPost() {
        vistaGestion.postTituloTxt.setText("");
        vistaGestion.postMensajeTxt.setText("");
        vistaGestion.postImagenSeleccionada = null;
    }
    /**
     * Método para validar los campos del formulario de peleador.
     *
     * @return true si los campos son válidos, false en caso contrario.
     */
    private boolean validarCamposPeleador() {
        // Validar campos vacíos
        if (vistaGestion.peleadorNombreTxt.getText().isEmpty() ||
                vistaGestion.peleadorApellidoTxt.getText().isEmpty() ||
                vistaGestion.peleadorDniTxt.getText().isEmpty() ||
                vistaGestion.peleadorPesoTxt.getText().isEmpty() ||
                vistaGestion.peleadorVictoriasTxt.getText().isEmpty() ||
                vistaGestion.peleadorApodoTxt.getText().isEmpty() ||
                vistaGestion.peleadorNacimientoTxt.getDate() == null ||
                vistaGestion.peleadorGimnasioCombo.getSelectedItem() == null ||
                vistaGestion.peleadorEntrenadorCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Todos los campos del peleador son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (vistaGestion.peleadorNombreTxt.getText().length() > 50) {
            JOptionPane.showMessageDialog(vistaGestion, "El nombre no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (vistaGestion.peleadorApellidoTxt.getText().length() > 50) {
            JOptionPane.showMessageDialog(vistaGestion, "El apellido no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (vistaGestion.peleadorApodoTxt.getText().length() > 30) {
            JOptionPane.showMessageDialog(vistaGestion, "El apodo no puede exceder 30 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (vistaGestion.peleadorDniTxt.getText().length() > 15) {
            JOptionPane.showMessageDialog(vistaGestion, "El DNI no puede exceder 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int peso = Integer.parseInt(vistaGestion.peleadorPesoTxt.getText());
            if (peso <= 0) {
                JOptionPane.showMessageDialog(vistaGestion, "El peso debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "El peso debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int victorias = Integer.parseInt(vistaGestion.peleadorVictoriasTxt.getText());
            if (victorias < 0) {
                JOptionPane.showMessageDialog(vistaGestion, "El número de victorias no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "El número de victorias debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(LocalDate.parse(vistaGestion.peleadorNacimientoTxt.getDate().toString()).isAfter(LocalDate.now()) ){
            JOptionPane.showMessageDialog(vistaGestion, "La fecha de nacimiento no puede ser futura.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    /**
     * Método para validar los campos del formulario de gimnasio.
     *
     * @return true si los campos son válidos, false en caso contrario.
     */
    private boolean validarCamposEntrenador() {
        if (vistaGestion.entrenadorNombreTxt.getText().isEmpty() || vistaGestion.entrenadorApellidoTxt.getText().isEmpty() ||
                vistaGestion.entrenadorDniTxt.getText().isEmpty() || vistaGestion.entrenadorColegiadoTxt.getText().isEmpty() ||
                vistaGestion.entrenadorExperienciaTxt.getText().isEmpty() || vistaGestion.entrenadorGimnasioCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Todos los campos del entrenador son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (vistaGestion.entrenadorNombreTxt.getText().length() > 50) {
            JOptionPane.showMessageDialog(vistaGestion, "El nombre no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (vistaGestion.entrenadorApellidoTxt.getText().length() > 50) {
            JOptionPane.showMessageDialog(vistaGestion, "El apellido no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (vistaGestion.entrenadorDniTxt.getText().length() > 15) {
            JOptionPane.showMessageDialog(vistaGestion, "El DNI no puede exceder 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int numeroColegiado = Integer.parseInt(vistaGestion.entrenadorColegiadoTxt.getText());
            if (numeroColegiado <= 0) {
                JOptionPane.showMessageDialog(vistaGestion, "El número de colegiado debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "El número de colegiado debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int experiencia = Integer.parseInt(vistaGestion.entrenadorExperienciaTxt.getText());
            if (experiencia < 0) {
                JOptionPane.showMessageDialog(vistaGestion, "La experiencia no puede ser negativa.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "La experiencia debe ser un valor numérico, se mide en años.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (vistaGestion.entrenadorGimnasioCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Debe seleccionar un gimnasio para el entrenador.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    /**
     * Método para validar los campos del formulario de federación.
     *
     * @return true si los campos son válidos, false en caso contrario.
     */


    private boolean validarCamposLiga() {
        // Validar campos vacíos
        if (vistaGestion.ligaNombreTxt.getText().isEmpty() ||
                vistaGestion.ligaCiudadTxt.getText().isEmpty() ||
                vistaGestion.ligaParticipantesTxt.getText().isEmpty() ||
                vistaGestion.ligaFederacionCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Todos los campos de la liga son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar longitud de nombre
        if (vistaGestion.ligaNombreTxt.getText().length() > 50) {
            JOptionPane.showMessageDialog(vistaGestion, "El nombre no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar longitud de ciudad
        if (vistaGestion.ligaCiudadTxt.getText().length() > 50) {
            JOptionPane.showMessageDialog(vistaGestion, "La ciudad no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar número de participantes
        try {
            int participantes = Integer.parseInt(vistaGestion.ligaParticipantesTxt.getText());
            if (participantes <= 0) {
                JOptionPane.showMessageDialog(vistaGestion, "El número de participantes debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "El número de participantes debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar selección de federación
        if (vistaGestion.ligaFederacionCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Debe seleccionar una federación para la liga.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    /**
     * Método para validar los campos del formulario de federación.
     *
     * @return true si los campos son válidos, false en caso contrario.
     */
    private boolean validarCamposFederacion() {
        // Validar campos vacíos
        if (vistaGestion.federacionNombreTxt.getText().isEmpty() ||
                vistaGestion.federacionArteMarcialTxt.getText().isEmpty() ||
                vistaGestion.federacionNumeroAsociacionTxt.getText().isEmpty() ||
                vistaGestion.federacionAñoFundacionTxt.getDate() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Todos los campos de la federación son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar longitud de nombre
        if (vistaGestion.federacionNombreTxt.getText().length() > 50) {
            JOptionPane.showMessageDialog(vistaGestion, "El nombre no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar longitud de arte marcial
        if (vistaGestion.federacionArteMarcialTxt.getText().length() > 50) {
            JOptionPane.showMessageDialog(vistaGestion, "El arte marcial no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar número de asociación
        try {
            int numeroAsociacion = Integer.parseInt(vistaGestion.federacionNumeroAsociacionTxt.getText());
            if (numeroAsociacion <= 0) {
                JOptionPane.showMessageDialog(vistaGestion, "El número de asociación debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "El número de asociación debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (LocalDate.parse(vistaGestion.federacionAñoFundacionTxt.getDate().toString()).isAfter(LocalDate.now())   ) {

            JOptionPane.showMessageDialog(vistaGestion, "La fecha no puede ser posterior", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        return true;
    }
    /**
     * Método para validar los campos del formulario de gimnasio.
     *
     * @return true si los campos son válidos, false en caso contrario.
     */

private boolean validarCamposGimnasio() {
    // Validar campos vacíos
    if (vistaGestion.gimnasioNombreTxt.getText().isEmpty() ||
        vistaGestion.gimnasioDireccionTxt.getText().isEmpty() ||
        vistaGestion.gimnasioCiudadTxt.getText().isEmpty() ||
        vistaGestion.gimnasioContraseñaTxt.getText().isEmpty() ||
        vistaGestion.gimnasioNifTxt.getText().isEmpty() ||
        vistaGestion.gimnasioWebTxt.getText().isEmpty()) {
        JOptionPane.showMessageDialog(vistaGestion, "Todos los campos del gimnasio son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Validar longitud de nombre
    if (vistaGestion.gimnasioNombreTxt.getText().length() > 50) {
        JOptionPane.showMessageDialog(vistaGestion, "El nombre no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Validar longitud de dirección
    if (vistaGestion.gimnasioDireccionTxt.getText().length() > 100) {
        JOptionPane.showMessageDialog(vistaGestion, "La dirección no puede exceder 100 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Validar longitud de ciudad
    if (vistaGestion.gimnasioCiudadTxt.getText().length() > 50) {
        JOptionPane.showMessageDialog(vistaGestion, "La ciudad no puede exceder 50 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Validar longitud de NIF
    if (vistaGestion.gimnasioNifTxt.getText().length() > 15) {
        JOptionPane.showMessageDialog(vistaGestion, "El NIF no puede exceder 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Validar longitud de web
    if (vistaGestion.gimnasioWebTxt.getText().length() > 100) {
        JOptionPane.showMessageDialog(vistaGestion, "La URL de la web no puede exceder 100 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Validar longitud mínima de contraseña
    if (vistaGestion.gimnasioContraseñaTxt.getText().length() < 8) {
        JOptionPane.showMessageDialog(vistaGestion, "La contraseña debe tener al menos 8 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    return true;
}
    /**
     * Método para validar los campos del formulario de post.
     *
     * @return true si los campos son válidos, false en caso contrario.
     */

    private boolean validarCamposPost() {
        if (vistaGestion.postTituloTxt.getText().isEmpty() || vistaGestion.postMensajeTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vistaGestion, "El título y el mensaje del post son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    /**
     * Método para agregar ActionListeners a los botones de las vistas.
     *
     * @param listener el ActionListener a agregar.
     */
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
        vistaGestion.postAdjuntarImageBtn.addActionListener(listener);
        vistaGestion.postAdjuntarImageBtn.setActionCommand("adjuntar imagen");
        vistaGestion.inicio.addActionListener(listener);
        vistaGestion.inicio.setActionCommand("inicio");
        vistaGimnasio.inicio2.addActionListener(listener);
        vistaGimnasio.inicio2.setActionCommand("inicio");
        vistaLoginAdmin.inicio3.addActionListener(listener);
        vistaLoginAdmin.inicio3.setActionCommand("inicio");
        vistaLoginGimnasio.inicio4.addActionListener(listener);
        vistaLoginGimnasio.inicio4.setActionCommand("inicio");
        vistaPost.inicio5.addActionListener(listener);
        vistaPost.inicio5.setActionCommand("inicio");
        vistaGimnasio.salir2.addActionListener(listener);
        vistaGimnasio.salir2.setActionCommand("salir");
        vistaGestion.salir.addActionListener(listener);
        vistaGestion.salir.setActionCommand("salir");
        vistaLoginAdmin.salir3.addActionListener(listener);
        vistaLoginAdmin.salir3.setActionCommand("salir");
        vistaLoginGimnasio.salir4.addActionListener(listener);
        vistaLoginGimnasio.salir4.setActionCommand("salir");
        vistaPost.salir5.addActionListener(listener);
        vistaPost.salir5.setActionCommand("salir");
        vistaUsers.salir4.addActionListener(listener);
        vistaUsers.salir4.setActionCommand("salir");
        vistaUsers.inicio4.addActionListener(listener);
        vistaUsers.inicio4.setActionCommand("inicio");
        vistaUsers.informacion4.addActionListener(listener);
        vistaUsers.informacion4.setActionCommand("informacion");
        vistaGestion.informacion.addActionListener(listener);
        vistaGestion.informacion.setActionCommand("informacion");
        vistaGimnasio.informacion2.addActionListener(listener);
        vistaGimnasio.informacion2.setActionCommand("informacion");
        vistaLoginAdmin.informacion3.addActionListener(listener);
        vistaLoginAdmin.informacion3.setActionCommand("informacion");
        vistaLoginGimnasio.informacion4.addActionListener(listener);
        vistaLoginGimnasio.informacion4.setActionCommand("informacion");
        vistaPost.informacion5.addActionListener(listener);
        vistaPost.informacion5.setActionCommand("informacion");
        vistaPost.informacion5.addActionListener(listener);
        vistaPost.informacion5.setActionCommand("informacion");




    }
    /**
     * Método para agregar ListSelectionListeners a las listas de la vista de gestión.
     *
     * @param listener el ListSelectionListener a agregar.
     */
    void addListSelectionListener(ListSelectionListener listener){
        vistaGestion.peleadorLista.addListSelectionListener(listener);
        vistaGestion.gimnasioLista.addListSelectionListener(listener);
        vistaGestion.ligaLista.addListSelectionListener(listener);
        vistaGestion.entradorLista.addListSelectionListener(listener);
        vistaGestion.federacionLista.addListSelectionListener(listener);
        vistaGestion.postLista.addListSelectionListener(listener);

    }
    /**
     * Método para actualizar los combos de la vista de gestión.
     *
     */
    void actualizarCombos(){
        comboPeleadorGimnasio();
        comboPeleadorEntrenador();
        comboEntrenadorGimnasio();
        comboPeleadorLiga();
        comboLigaFederacion();
        comboGimnasioLogin();
        comboGimnasioGimnasio();
        comboGimnasioEntrenador();
        comboGimnasioLiga();
        comboInformesLiga();
        comboInformesGimnasio();
        
    }
    /**
     * Método para actualizar el combo gimnasio del peleador en la vista de gestión.
     *
     */
    void comboPeleadorGimnasio() {
        vistaGestion.peleadorGimnasioCombo.removeAllItems();
        for (Gimnasio gimnasio : modelo.getGimnasios()) {
            vistaGestion.peleadorGimnasioCombo.addItem(gimnasio);
        }
        vistaGestion.peleadorGimnasioCombo.setSelectedItem(null); // No item selected by default
    }
    /**
     * Método para actualizar el combo entrenador del peleador en la vista de gestión.
     *
     */

    void comboPeleadorEntrenador() {
        vistaGestion.peleadorEntrenadorCombo.removeAllItems();
        for (Entrenador entrenador : modelo.getEntrenadores()) {
            vistaGestion.peleadorEntrenadorCombo.addItem(entrenador);
        }
        vistaGestion.peleadorEntrenadorCombo.setSelectedItem(null); // No item selected by default
    }
    /**
     * Método para actualizar el combo liga del peleador en la vista de gestión.
     *
     */

    void comboPeleadorLiga() {
        vistaGestion.peleadorLigaCombo.removeAllItems();
        for (Liga liga : modelo.getLigas()) {
            vistaGestion.peleadorLigaCombo.addItem(liga);
        }
        vistaGestion.peleadorLigaCombo.setSelectedItem(null); // No item selected by default
    }
    /**
     * Método para actualizar el combo gimnasio del entrenador en la vista de gestión.
     *
     */
    void comboEntrenadorGimnasio() {
        vistaGestion.entrenadorGimnasioCombo.removeAllItems();
        for (Gimnasio gimnasio : modelo.getGimnasios()) {
            vistaGestion.entrenadorGimnasioCombo.addItem(gimnasio);
        }
    }
    /**
     * Método para actualizar el combo federación de la liga en la vista de gestión.
     *
     */
    void comboLigaFederacion() {
        vistaGestion.ligaFederacionCombo.removeAllItems();
        for (Federacion federacion : modelo.getFederaciones()) {
            vistaGestion.ligaFederacionCombo.addItem(federacion);
        }
    }
    /**
     * Método para actualizar el combo federación de la liga en la vista de gestión.
     *
     */
    void comboGimnasioLogin() {
        vistaLoginGimnasio.loginGimnasioCombo.removeAllItems();
        for (Gimnasio gimnasio : modelo.getGimnasios()) {
            vistaLoginGimnasio.loginGimnasioCombo.addItem(gimnasio);
        }
    }
    /**
     * Método para actualizar el combo gimnasio de la liga en la vista de gestión.
     *
     */
    void  comboGimnasioGimnasio(){
        vistaGimnasio.solicitudGimnasioCombo.removeAllItems();
        for (Gimnasio gimnasio:modelo.getGimnasios()){
            vistaGimnasio.solicitudGimnasioCombo.addItem(gimnasio);
        }
    }
    void comboGimnasioEntrenador(){
        vistaGimnasio.solicitudEntrenadorCombo.removeAllItems();
        for (Entrenador entrenador:modelo.getEntrenadores()){
            vistaGimnasio.solicitudEntrenadorCombo.addItem(entrenador);
        }
    }
    /**
     * Método para actualizar el combo gimnasio de la liga en la vista de gestión.
     *
     */
    void comboGimnasioLiga(){
        vistaGimnasio.solicitudLigaCombo.removeAllItems();
        for (Liga liga:modelo.getLigas()){
            vistaGimnasio.solicitudLigaCombo.addItem(liga);
        }
    }
    /**
     * Método para actualizar el combo gimnasio de la liga en la vista de gestión.
     *
     */
    void comboInformesLiga(){
        vistaGestion.InformesComboLiga.removeAllItems();
        for (Liga liga:modelo.getLigas()){
            vistaGestion.InformesComboLiga.addItem(liga);
        }
    }
    /**
     * Método para actualizar el combo gimnasio de la liga en la vista de gestión.
     *
     */
    void comboInformesGimnasio(){
        vistaGestion.InformesComboGimnasio.removeAllItems();
        for (Gimnasio gimnasio:modelo.getGimnasios()){
            vistaGestion.InformesComboGimnasio.addItem(gimnasio);
        }
    }
    /**
     * Método para mostrar la información de un peleador, gimnasio, entrenador, liga o federación en la vista de gestión.
     *
     *
     */


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
    /**
     * Método para actualizar las listas de la vista de gestión.
     *
     */
    void actualizarListas(){
        listarPeleadores(modelo.getPeleadores());
        listarGimnasios(modelo.getGimnasios());
        listarEntrenadores(modelo.getEntrenadores());
        listarLigas(modelo.getLigas());
        listarFederaciones(modelo.getFederaciones());
        listarPosts(modelo.getPosts());

    }
    /**
     * Método para listar los peleadores en la vista de gestión.
     *
     * @param peleadores la lista de peleadores a mostrar.
     */

    private void listarPeleadores(ArrayList<Peleador> peleadores){
        vistaGestion.dflmpeleador.clear();
        for (Peleador peleador:peleadores){
            vistaGestion.dflmpeleador.addElement(peleador);
        }
    }
    /**
     * Método para listar los gimnasios en la vista de gestión.
     *
     * @param gimnasios la lista de gimnasios a mostrar.
     */
    private void listarGimnasios(ArrayList<Gimnasio> gimnasios){
        vistaGestion.dflmgimnasio.clear();
        for (Gimnasio gimnasio:gimnasios){
            vistaGestion.dflmgimnasio.addElement(gimnasio);
        }
    }
    /**
     * Método para listar los entrenadores en la vista de gestión.
     *
     * @param entrenadores la lista de entrenadores a mostrar.
     */
private void listarEntrenadores(ArrayList<Entrenador> entrenadores){
        vistaGestion.dflmentrenador.clear();
        for (Entrenador entrenador:entrenadores){
            vistaGestion.dflmentrenador.addElement(entrenador);
        }
    }
    /**
     * Método para listar las ligas en la vista de gestión.
     *
     * @param ligas la lista de ligas a mostrar.
     */
private void listarLigas(ArrayList<Liga> ligas){
        vistaGestion.dflmliga.clear();
        for (Liga liga:ligas){
            vistaGestion.dflmliga.addElement(liga);
        }
    }
    /**
     * Método para listar las federaciones en la vista de gestión.
     *
     * @param federaciones la lista de federaciones a mostrar.
     */
private void listarFederaciones(ArrayList<Federacion> federaciones){
        vistaGestion.dflmfederacion.clear();
        for (Federacion federacion:federaciones){
            vistaGestion.dflmfederacion.addElement(federacion);
        }
    }
    /**
     * Método para listar los posts en la vista de gestión.
     *
     * @param posts la lista de posts a mostrar.
     */
private void listarPosts(ArrayList<Post> posts) {
    vistaGestion.dflmpost.clear();
    for (Post post : posts) {
        vistaGestion.dflmpost.addElement(post);
    }
}
 /*
 Metodo para inicializar el post en la vista de post.
  */

private void inicializarPost(ArrayList<Post> posts){
        try {


        vistaPost.postTituloTxt.setText(posts.get(0).getTitulo());
        vistaPost.postMensajeTxt.setText(posts.get(0).getMensaje());
        if (posts.get(0).getFoto() != null) {
            vistaPost.postImagenTxt.setIcon(new ImageIcon(posts.get(0).getFoto()));
        } else {
            vistaPost.postImagenTxt.setIcon(null);
        }
        } catch (IndexOutOfBoundsException e) {
            vistaPost.postTituloTxt.setText("");
            vistaPost.postMensajeTxt.setText("");
            vistaPost.postImagenTxt.setIcon(null);
        }

}
    /**
     * Método para mostrar el siguiente post en la vista de post.
     *
     */
    private void siguientePost() {
        if (modelo.getPosts().isEmpty()) {
            JOptionPane.showMessageDialog(vistaPost, "No hay posts disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (modelo.getPosts().size() == 1) {
            mostrarPost(0); // Only one post, always show the first
            return;
        }
        if (numeroPost == modelo.getPosts().size() - 1) {
            numeroPost = 0;
        } else {
            numeroPost++;
        }
        mostrarPost(numeroPost);
    }
    /**
     * Método para volver al post anterior.
     */

    private void volverPost() {
        if (modelo.getPosts().isEmpty()) {
            JOptionPane.showMessageDialog(vistaPost, "No hay posts disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (modelo.getPosts().size() == 1) {
            mostrarPost(0); // Only one post, always show the first
            return;
        }
        if (numeroPost == 0) {
            numeroPost = modelo.getPosts().size() - 1;
        } else {
            numeroPost--;
        }
        mostrarPost(numeroPost);
    }
    /**
     * Método para mostrar un post en la vista de post.
     *
     * @param index el índice del post a mostrar.
     */
    private void mostrarPost(int index) {
        if (index < 0 || index >= modelo.getPosts().size()) {
            JOptionPane.showMessageDialog(vistaPost, "Índice de post inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Post post = modelo.getPosts().get(index);
        vistaPost.postTituloTxt.setText(post.getTitulo());
        vistaPost.postMensajeTxt.setText(post.getMensaje());
        if (post.getFoto() != null) {
            vistaPost.postImagenTxt.setIcon(new ImageIcon(post.getFoto()));
        } else {
            vistaPost.postImagenTxt.setIcon(null);
        }
    }
    /**
     * Método para adjuntar una imagen al post.
     *
     * @return true si la imagen se adjunta correctamente, false en caso contrario.
     */
    private boolean adjuntarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes (jpg, jpeg, png)", "jpg", "jpeg", "png"));
        int result = fileChooser.showOpenDialog(vistaGestion);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            String fileName = file.getName().toLowerCase();

            if (!(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png"))) {
                JOptionPane.showMessageDialog(vistaGestion, "El archivo debe ser una imagen en formato JPG, JPEG o PNG.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try {
                BufferedImage imagenOriginal = javax.imageio.ImageIO.read(file);
                if (imagenOriginal == null) {
                    JOptionPane.showMessageDialog(vistaGestion, "El archivo seleccionado no es una imagen válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                Image imagenRedimensionada = imagenOriginal.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                BufferedImage bufferedResizedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = bufferedResizedImage.createGraphics();
                g2d.drawImage(imagenRedimensionada, 0, 0, null);
                g2d.dispose();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                javax.imageio.ImageIO.write(bufferedResizedImage, "jpg", baos);
                byte[] imageBytes = baos.toByteArray();

                if (imageBytes.length > 16 * 1024 * 1024) {
                    JOptionPane.showMessageDialog(vistaGestion, "La imagen es demasiado grande. Seleccione una imagen más pequeña.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                vistaGestion.postImagenSeleccionada = imageBytes; // Guardar la imagen redimensionada
                return true;

            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(vistaGestion, "Error al procesar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(vistaGestion, "No se seleccionó ninguna imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
