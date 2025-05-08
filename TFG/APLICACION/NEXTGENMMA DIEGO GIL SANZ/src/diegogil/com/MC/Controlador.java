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
    int numeroPost = 1;

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
                System.out.println("hola");
                vistaUsers.setVisible(false);
                vistaLoginGimnasio.setVisible(true);
                break;
            case "invitado":
                vistaUsers.setVisible(false);
                vistaPost.setVisible(true);
                inicializarPost(modelo.getPosts());
                break;
            case "login admin":

                if (modelo.verificarContrasena(vistaLoginAdmin.administradorContraseñaTxt.getText())){
                    vistaLoginAdmin.setVisible(false);
                    vistaGestion.pack();
                    vistaGestion.setVisible(true);

            }
                break;
            case "login gimnasio":
                String hashGimnasioContraseñaLogin;
                hashGimnasioContraseñaLogin = DigestUtils.sha256Hex(vistaLoginGimnasio.loginGimnasioContraseñaTxt.getText());
                if (modelo.verificarContrasenaGimnasio((Gimnasio) vistaLoginGimnasio.loginGimnasioCombo.getSelectedItem(),hashGimnasioContraseñaLogin)){
                    vistaLoginGimnasio.setVisible(false);
                    vistaGimnasio.setVisible(true);
                    vistaGimnasio.solicitudPeleadoresActivosTxt.setText(String.valueOf(modelo.peleadoresActivos((Gimnasio) vistaLoginGimnasio.loginGimnasioCombo.getSelectedItem())));
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
                    }
                }
                break;
            case "eliminar peleador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar este peleador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    modelo.eliminarPeleador((Peleador) vistaGestion.peleadorLista.getSelectedValue());
                }
                break;
            case "modificar peleador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar este peleador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
                    }
                }
                break;
            case "eliminar gimnasio":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar este gimnasio?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    modelo.eliminarGimnasio((Gimnasio) vistaGestion.gimnasioLista.getSelectedValue());
                }
                break;
            case "modificar gimnasio":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar este gimnasio?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
                    }
                }
                break;

            case "insertar liga":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres insertar esta liga?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if(validarCamposLiga()) {
                    Liga liga = new Liga();
                    liga.setNombre(vistaGestion.ligaNombreTxt.getText());
                    liga.setCiudad(vistaGestion.ligaCiudadTxt.getText());
                    liga.setParticipantes(Integer.parseInt(vistaGestion.ligaParticipantesTxt.getText()));
                    liga.setFederacion((Federacion) vistaGestion.ligaFederacionCombo.getSelectedItem());
                    modelo.altaLiga(liga);
                    }
                }
                break;
            case "eliminar liga":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar esta liga?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                modelo.eliminarLiga((Liga) vistaGestion.ligaLista.getSelectedValue());
                }
                break;
            case "modificar liga":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar esta liga?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if(validarCamposLiga()) {
                    Liga ligaModificar = (Liga) vistaGestion.ligaLista.getSelectedValue();
                    ligaModificar.setNombre(vistaGestion.ligaNombreTxt.getText());
                    ligaModificar.setCiudad(vistaGestion.ligaCiudadTxt.getText());
                    ligaModificar.setParticipantes(Integer.parseInt(vistaGestion.ligaParticipantesTxt.getText()));
                    ligaModificar.setFederacion((Federacion) vistaGestion.ligaFederacionCombo.getSelectedItem());
                    modelo.modificarLiga(ligaModificar);
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
                    }
                }
                break;
            case "eliminar entrenador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar este entrenador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                modelo.eliminarEntrenador((Entrenador) vistaGestion.entradorLista.getSelectedValue());
                }
                break;
            case "modificar entrenador":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar este entrenador?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (validarCamposEntrenador()){
                Entrenador entrenadorModificar = (Entrenador) vistaGestion.entradorLista.getSelectedValue();
                entrenadorModificar.setNombre(vistaGestion.entrenadorNombreTxt.getText());
                entrenadorModificar.setApellido(vistaGestion.entrenadorApellidoTxt.getText());
                entrenadorModificar.setDni(vistaGestion.entrenadorDniTxt.getText());
                entrenadorModificar.setNumeroColegiado(Integer.parseInt(vistaGestion.entrenadorColegiadoTxt.getText()));
                entrenadorModificar.setExperiencia(Integer.parseInt(vistaGestion.entrenadorExperienciaTxt.getText()));
                entrenadorModificar.setGimnasio((Gimnasio) vistaGestion.entrenadorGimnasioCombo.getSelectedItem());
                modelo.modificarEntrenador(entrenadorModificar);
                }

                }

                break;
            case "adjuntar imagen":
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));
                int result = fileChooser.showOpenDialog(vistaGestion);
                if (result == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileChooser.getSelectedFile();
                    try {

                        BufferedImage imagenOriginal = javax.imageio.ImageIO.read(file);


                        Image imagenRedimensionada = imagenOriginal.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                        BufferedImage bufferedResizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = bufferedResizedImage.createGraphics();
                        g2d.drawImage(imagenRedimensionada, 0, 0, null);
                        g2d.dispose();

                        // Convertir la imagen redimensionada a un array de bytes
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        javax.imageio.ImageIO.write(bufferedResizedImage, "png", baos);
                        vistaGestion.postImagenSeleccionada = baos.toByteArray(); // Guarda la imagen redimensionada
                        System.out.println("Imagen seleccionada y redimensionada: " + file.getName());
                    } catch (java.io.IOException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "insertar post":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres insertar este post?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (validarCamposPost()) {
                    Post post = new Post();
                    post.setTitulo(vistaGestion.postTituloTxt.getText());
                    post.setMensaje(vistaGestion.postMensajeTxt.getText());
                    post.setFoto(vistaGestion.postImagenSeleccionada); // Asigna la imagen seleccionada
                    modelo.altaPost(post);
                    }
                }
                break;
            case "eliminar post":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar este post?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                modelo.eliminarPost((Post) vistaGestion.postLista.getSelectedValue());
                }
                break;
            case "modificar post":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar este post?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (validarCamposPost()) {
                    Post postModificar = (Post) vistaGestion.postLista.getSelectedValue();
                    postModificar.setTitulo(vistaGestion.postTituloTxt.getText());
                    postModificar.setMensaje(vistaGestion.postMensajeTxt.getText());
                    modelo.modificarPost(postModificar);
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
                 }
                }
                break;
            case "eliminar federacion":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres eliminar esta federación?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                modelo.eliminarFederacion((Federacion) vistaGestion.federacionLista.getSelectedValue());
                }
                break;
            case "modificar federacion":
                if (JOptionPane.showConfirmDialog(vistaGestion, "¿Estás seguro de que quieres modificar esta federación?", "Confirmar acción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (validarCamposFederacion()) {
                Federacion federacionModificar = (Federacion) vistaGestion.federacionLista.getSelectedValue();
                federacionModificar.setNombre(vistaGestion.federacionNombreTxt.getText());
                federacionModificar.setNumeroAsociacion(Integer.parseInt(vistaGestion.federacionNumeroAsociacionTxt.getText()));
                federacionModificar.setArteMarcial(vistaGestion.federacionArteMarcialTxt.getText());
                federacionModificar.setFechaFundacion(Date.valueOf(vistaGestion.federacionAñoFundacionTxt.getDate()));
                modelo.modificarFederacion(federacionModificar);
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
                    if (numeroPost == 0){
                        numeroPost= modelo.getPosts().size()-1;
                        vistaPost.postTituloTxt.setText(modelo.getPosts().get(numeroPost).getTitulo());
                        vistaPost.postMensajeTxt.setText(modelo.getPosts().get(numeroPost).getMensaje());
                        if (modelo.getPosts().get(numeroPost).getFoto() != null) {
                            vistaPost.postImagenTxt.setIcon(new ImageIcon(modelo.getPosts().get(numeroPost).getFoto()));
                        } else {
                            vistaPost.postImagenTxt.setIcon(null);
                        }

                    }else {
                        numeroPost--;
                        vistaPost.postTituloTxt.setText(modelo.getPosts().get(numeroPost).getTitulo());
                        vistaPost.postMensajeTxt.setText(modelo.getPosts().get(numeroPost).getMensaje());
                        if (modelo.getPosts().get(numeroPost).getFoto() != null) {
                            vistaPost.postImagenTxt.setIcon(new ImageIcon(modelo.getPosts().get(numeroPost).getFoto()));
                        } else {
                            vistaPost.postImagenTxt.setIcon(null);
                        }
                    }


                    break;
                case "siguiente":
                    if (numeroPost == modelo.getPosts().size()-1){
                        numeroPost=0;
                        vistaPost.postTituloTxt.setText(modelo.getPosts().get(numeroPost).getTitulo());
                        vistaPost.postMensajeTxt.setText(modelo.getPosts().get(numeroPost).getMensaje());
                        if (modelo.getPosts().get(numeroPost).getFoto() != null) {
                            vistaPost.postImagenTxt.setIcon(new ImageIcon(modelo.getPosts().get(numeroPost).getFoto()));
                        } else {
                            vistaPost.postImagenTxt.setIcon(null);
                        }
                    }else {
                        numeroPost++;
                        vistaPost.postTituloTxt.setText(modelo.getPosts().get(numeroPost).getTitulo());
                        vistaPost.postMensajeTxt.setText(modelo.getPosts().get(numeroPost).getMensaje());
                        if (modelo.getPosts().get(numeroPost).getFoto() != null) {
                            vistaPost.postImagenTxt.setIcon(new ImageIcon(modelo.getPosts().get(numeroPost).getFoto()));
                        } else {
                            vistaPost.postImagenTxt.setIcon(null);
                        }
                    }
                    break;
                    case "informes peleadores":

                        JasperPrint reporteLleno = modelo.generarPeleadores();
                        JasperViewer viewer1 = new JasperViewer(reporteLleno,false);
                        viewer1.setVisible(true);
                        break;
                    case "informes gimnasios":
                        JasperPrint reporteLleno2 = modelo.generarGimnasios();
                        JasperViewer viewer2 = new JasperViewer(reporteLleno2,false);
                        viewer2.setVisible(true);
                        break;
                    case "informes ligas":
                        JasperPrint reporteLleno3 = modelo.generarLigas();
                        JasperViewer viewer3 = new JasperViewer(reporteLleno3,false);
                        viewer3.setVisible(true);
                        break;
                    case "informes entrenadores":
                        JasperPrint reporteLleno4 = modelo.generarEntrenadores();
                        JasperViewer viewer4 = new JasperViewer(reporteLleno4, false);
                        viewer4.setVisible(true);
                        break;
                    case "informes peleadores liga":
                        JasperPrint reporteLleno5 = modelo.generarPeleadoresLiga((Liga) vistaGestion.InformesComboLiga.getSelectedItem());
                        JasperViewer viewer5 = new JasperViewer(reporteLleno5,false);
                        viewer5.setVisible(true);

                        break;
                    case "informe Gimnasio":

                        JasperPrint reporteLleno6 = modelo.generarPeleadoresGimnasio((Gimnasio) vistaGestion.InformesComboGimnasio.getSelectedItem());
                        JasperViewer viewer6 = new JasperViewer(reporteLleno6,false);
                        viewer6.setVisible(true);
                        viewer6.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
                        break;





        }
        actualizarCombos();
        actualizarListas();

    }
    private boolean validarCamposPeleador() {
        if (vistaGestion.peleadorNombreTxt.getText().isEmpty() || vistaGestion.peleadorApellidoTxt.getText().isEmpty() ||
                vistaGestion.peleadorDniTxt.getText().isEmpty() || vistaGestion.peleadorPesoTxt.getText().isEmpty() ||
                vistaGestion.peleadorVictoriasTxt.getText().isEmpty() || vistaGestion.peleadorApodoTxt.getText().isEmpty() ||
                vistaGestion.peleadorNacimientoTxt.getDate() == null || vistaGestion.peleadorGimnasioCombo.getSelectedItem() == null ||
                vistaGestion.peleadorEntrenadorCombo.getSelectedItem() == null || vistaGestion.peleadorLigaCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Todos los campos del peleador son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(vistaGestion.peleadorPesoTxt.getText());
            Integer.parseInt(vistaGestion.peleadorVictoriasTxt.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "Peso y victorias deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarCamposEntrenador() {
        if (vistaGestion.entrenadorNombreTxt.getText().isEmpty() || vistaGestion.entrenadorApellidoTxt.getText().isEmpty() ||
                vistaGestion.entrenadorDniTxt.getText().isEmpty() || vistaGestion.entrenadorColegiadoTxt.getText().isEmpty() ||
                vistaGestion.entrenadorExperienciaTxt.getText().isEmpty() || vistaGestion.entrenadorGimnasioCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Todos los campos del entrenador son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(vistaGestion.entrenadorColegiadoTxt.getText());
            Integer.parseInt(vistaGestion.entrenadorExperienciaTxt.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "Número de colegiado y experiencia deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    private boolean validarCamposLiga() {
        if (vistaGestion.ligaNombreTxt.getText().isEmpty() || vistaGestion.ligaCiudadTxt.getText().isEmpty() ||
                vistaGestion.ligaParticipantesTxt.getText().isEmpty() || vistaGestion.ligaFederacionCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Todos los campos de la liga son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(vistaGestion.ligaParticipantesTxt.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "El número de participantes debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarCamposFederacion() {
        if (vistaGestion.federacionNombreTxt.getText().isEmpty() || vistaGestion.federacionArteMarcialTxt.getText().isEmpty() ||
                vistaGestion.federacionNumeroAsociacionTxt.getText().isEmpty() || vistaGestion.federacionAñoFundacionTxt.getDate() == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Todos los campos de la federación son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(vistaGestion.federacionNumeroAsociacionTxt.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaGestion, "El número de asociación debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarCamposGimnasio() {
        if (vistaGestion.gimnasioNombreTxt.getText().isEmpty() || vistaGestion.gimnasioDireccionTxt.getText().isEmpty() ||
                vistaGestion.gimnasioCiudadTxt.getText().isEmpty() || vistaGestion.gimnasioContraseñaTxt.getText().isEmpty() ||
                vistaGestion.gimnasioNifTxt.getText().isEmpty() || vistaGestion.gimnasioWebTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vistaGestion, "Todos los campos del gimnasio son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarCamposPost() {
        if (vistaGestion.postTituloTxt.getText().isEmpty() || vistaGestion.postMensajeTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vistaGestion, "El título y el mensaje del post son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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
        comboGimnasioGimnasio();
        comboGimnasioEntrenador();
        comboGimnasioLiga();
        comboInformesLiga();
        comboInformesGimnasio();
        
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
    void comboGimnasioLiga(){
        vistaGimnasio.solicitudLigaCombo.removeAllItems();
        for (Liga liga:modelo.getLigas()){
            vistaGimnasio.solicitudLigaCombo.addItem(liga);
        }
    }
    void comboInformesLiga(){
        vistaGestion.InformesComboLiga.removeAllItems();
        for (Liga liga:modelo.getLigas()){
            vistaGestion.InformesComboLiga.addItem(liga);
        }
    }
    void comboInformesGimnasio(){
        vistaGestion.InformesComboGimnasio.removeAllItems();
        for (Gimnasio gimnasio:modelo.getGimnasios()){
            vistaGestion.InformesComboGimnasio.addItem(gimnasio);
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

}
