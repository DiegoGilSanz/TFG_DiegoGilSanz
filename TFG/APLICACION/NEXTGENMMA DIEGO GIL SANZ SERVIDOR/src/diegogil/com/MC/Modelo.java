package diegogil.com.MC;

import diegogil.com.clases.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class Modelo {
    private SessionFactory sessionFactory;
    public static final String JASPER_PELEADORES = "src/jaspers/peleadores.jasper";
    public static final String JASPER_ENTRENADORES = "src/jaspers/entrenadores.jasper";
    public static final String JASPER_GIMNASIOS = "src/jaspers/gimnasios.jasper";
    public static final String JASPER_LIGAS = "src/jaspers/ligas.jasper";
    public static final String JASPER_PELEADORESLIGA = "src/jaspers/Peleadores_Liga.jasper";
    public static final String JASPER_PELEADORESGIMNASIO = "src/jaspers/Peleadores_Gimnasio.jasper";

    private static Connection conexion;

    private static String ip="54.82.86.20";
    private static String port="3306";
    private static String name="nextgenmma";
    private static String user="tfg_api";
    private static String password="55NN,5&8SH,oMY7F~6v1/t0~;VH[N[G";
    private static String sqlRoute="src/SQLSCRIPT/ScriptTFGMMA_JAVA.sql";
    private static String sgbd="mysql";

    public Modelo() {


        conectar();


    }







    public static String leerFichero(String ruta) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public void conectar() {
        try {
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            config.addAnnotatedClass(Entrenador.class);
            config.addAnnotatedClass(Gimnasio.class);
            config.addAnnotatedClass(Peleador.class);
            config.addAnnotatedClass(Liga.class);
            config.addAnnotatedClass(Post.class);
            config.addAnnotatedClass(Federacion.class);
            config.addAnnotatedClass(Admin.class);


            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties())
                    .build();
            sessionFactory = config.buildSessionFactory(ssr);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error inicializando Hibernate SessionFactory.");
        }
    }

    private static Connection conectarJasper() {
        try {
            return DriverManager.getConnection("jdbc:mysql://54.82.86.20:3306/nextgenmma", "tfg_api", "55NN,5&8SH,oMY7F~6v1/t0~;VH[N[G");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void desconectar() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }

        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Session getSession() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory no inicializado");
        }
        return sessionFactory.openSession();
    }

    public void altaPeleador(Peleador peleador) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(peleador);
            session.getTransaction().commit();

        }
    }

    public void eliminarPeleador(Peleador peleador) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.delete(peleador);
            session.getTransaction().commit();
        }
    }

    public void modificarPeleador(Peleador peleador) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(peleador);
            session.getTransaction().commit();
        }
    }

    public void altaEntrenador(Entrenador entrenador) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(entrenador);
            session.getTransaction().commit();
        }
    }

    public void eliminarEntrenador(Entrenador entrenador) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.delete(entrenador);
            session.getTransaction().commit();
        }
    }

    public void modificarEntrenador(Entrenador entrenador) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entrenador);
            session.getTransaction().commit();
        }
    }

    public void altaGimnasio(Gimnasio gimnasio) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(gimnasio);
            session.getTransaction().commit();
        }
    }

    public void eliminarGimnasio(Gimnasio gimnasio) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.delete(gimnasio);
            session.getTransaction().commit();
        }
    }

    public void modificarGimnasio(Gimnasio gimnasio) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(gimnasio);
            session.getTransaction().commit();
        }
    }

    public void altaLiga(Liga liga) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(liga);
            session.getTransaction().commit();
        }
    }

    public void eliminarLiga(Liga liga) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.delete(liga);
            session.getTransaction().commit();
        }
    }

    public void modificarLiga(Liga liga) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(liga);
            session.getTransaction().commit();
        }
    }

    public void altaFederacion(Federacion federacion) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(federacion);
            session.getTransaction().commit();
        }
    }

    public void eliminarFederacion(Federacion federacion) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.delete(federacion);
            session.getTransaction().commit();
        }
    }

    public void modificarFederacion(Federacion federacion) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(federacion);
            session.getTransaction().commit();
        }
    }

    public void altaPost(Post post) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(post);
            session.getTransaction().commit();
        }
    }

    public void eliminarPost(Post post) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.delete(post);
            session.getTransaction().commit();
        }
    }

    public void modificarPost(Post post) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(post);
            session.getTransaction().commit();
        }
    }

    public ArrayList<Gimnasio> getGimnasios() {
        try (Session session = getSession()) {
            session.beginTransaction();
            ArrayList<Gimnasio> gimnasios = (ArrayList<Gimnasio>) session.createQuery("from Gimnasio").list();
            session.getTransaction().commit();
            return gimnasios;
        }
    }

    public ArrayList<Entrenador> getEntrenadores() {
        try (Session session = getSession()) {
            session.beginTransaction();
            ArrayList<Entrenador> entrenadores = (ArrayList<Entrenador>) session.createQuery("from Entrenador").list();
            session.getTransaction().commit();
            return entrenadores;
        }
    }

    public ArrayList<Peleador> getPeleadores() {
        try (Session session = getSession()) {
            session.beginTransaction();
            ArrayList<Peleador> peleadores = (ArrayList<Peleador>) session.createQuery("from Peleador").list();
            session.getTransaction().commit();
            return peleadores;
        }
    }

    public ArrayList<Liga> getLigas() {
        try (Session session = getSession()) {
            session.beginTransaction();
            ArrayList<Liga> ligas = (ArrayList<Liga>) session.createQuery("from Liga").list();
            session.getTransaction().commit();
            return ligas;
        }
    }

    public ArrayList<Federacion> getFederaciones() {
        try (Session session = getSession()) {
            session.beginTransaction();
            ArrayList<Federacion> federaciones = (ArrayList<Federacion>) session.createQuery("from Federacion").list();
            session.getTransaction().commit();
            return federaciones;
        }
    }

    public ArrayList<Post> getPosts() {
        try (Session session = getSession()) {
            session.beginTransaction();
            ArrayList<Post> posts = (ArrayList<Post>) session.createQuery("from Post").list();
            session.getTransaction().commit();
            return posts;
        }
    }
    public Admin getAdmin() {
        try (Session session = getSession()) {
            session.beginTransaction();
            Admin admin = session.createQuery("from Admin", Admin.class).getSingleResult();
            session.getTransaction().commit();
            return admin;
        }
    }

    public boolean verificarContrasena(Admin admin, String contrasena) {

        try (Session session = getSession()) {
            session.beginTransaction();
            // Recuperar el admin desde la base de datos
            Admin adminDB = session.get(Admin.class, admin.getId());
            session.getTransaction().commit();

            // Comprobar si el admin existe
            if (adminDB != null) {
                // Comparar contraseñas
                return adminDB.getContraseña().equals(contrasena);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean verificarContrasenaGimnasio(Gimnasio gimnasio, String contrasena) {
        try (Session session = getSession()) {
            session.beginTransaction();
            // Recuperar el gimnasio desde la base de datos
            Gimnasio gimnasioDB = session.get(Gimnasio.class, gimnasio.getIdGimnasio());
            session.getTransaction().commit();

            // Comprobar si el gimnasio existe
            if (gimnasioDB != null) {
                // Comparar contraseñas
                return gimnasioDB.getContraseña().equals(contrasena);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void enviarCorreo(Peleador peleador, String asunto) {
        String host = "smtp.gmail.com";
        String puerto = "587";
        String remitente = "diegogilzg@gmail.com";
        String contraseña = "ylqy gmxn svib vjbc";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", puerto);

        javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contraseña);
            }
        });

        try {
            // Crear el contenido HTML del mensaje
            String contenido = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; max-width: 600px; margin: 0 auto; padding: 20px; }" +
                    ".header { background-color: #2c3e50; color: white; padding: 20px; text-align: center; border-radius: 5px 5px 0 0; }" +
                    ".content { background-color: #f9f9f9; padding: 20px; border: 1px solid #ddd; border-radius: 0 0 5px 5px; }" +
                    ".section { margin-bottom: 20px; padding: 15px; background-color: white; border-radius: 5px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }" +
                    ".section-title { color: #2c3e50; font-size: 18px; font-weight: bold; margin-bottom: 10px; border-bottom: 2px solid #3498db; padding-bottom: 5px; }" +
                    ".info-row { display: flex; margin-bottom: 8px; }" +
                    ".label { font-weight: bold; width: 150px; color: #7f8c8d; }" +
                    ".value { flex: 1; }" +
                    ".footer { text-align: center; margin-top: 20px; padding: 10px; color: #7f8c8d; font-size: 12px; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='header'>" +
                    "<h1>Información del Peleador</h1>" +
                    "</div>" +
                    "<div class='content'>" +
                    "<div class='section'>" +
                    "<div class='section-title'>Datos Personales</div>" +
                    "<div class='info-row'><span class='label'>Nombre:</span><span class='value'>" + peleador.getNombre() + "</span></div>" +
                    "<div class='info-row'><span class='label'>Apellido:</span><span class='value'>" + peleador.getApellido() + "</span></div>" +
                    "<div class='info-row'><span class='label'>Apodo:</span><span class='value'>" + peleador.getApodo() + "</span></div>" +
                    "<div class='info-row'><span class='label'>DNI:</span><span class='value'>" + peleador.getDni() + "</span></div>" +
                    "<div class='info-row'><span class='label'>Fecha de Nacimiento:</span><span class='value'>" + peleador.getFechaNacimiento() + "</span></div>" +
                    "</div>" +
                    "<div class='section'>" +
                    "<div class='section-title'>Información Deportiva</div>" +
                    "<div class='info-row'><span class='label'>Peso:</span><span class='value'>" + peleador.getPeso() + " kg</span></div>" +
                    "<div class='info-row'><span class='label'>Victorias:</span><span class='value'>" + peleador.getVictorias() + "</span></div>" +
                    "<div class='info-row'><span class='label'>Liga:</span><span class='value'>" + (peleador.getLiga() != null ? peleador.getLiga().getNombre() : "N/A") + "</span></div>" +
                    "</div>" +
                    "<div class='section'>" +
                    "<div class='section-title'>Equipo y Entrenamiento</div>" +
                    "<div class='info-row'><span class='label'>Gimnasio:</span><span class='value'>" + (peleador.getGimnasio() != null ? peleador.getGimnasio().getNombre() : "N/A") + "</span></div>" +
                    "<div class='info-row'><span class='label'>Entrenador:</span><span class='value'>" + (peleador.getEntrenador() != null ? peleador.getEntrenador().getNombre() + " " + peleador.getEntrenador().getApellido() : "N/A") + "</span></div>" +
                    "</div>" +
                    "</div>" +
                    "<div class='footer'>" +
                    "<p>Este es un correo automático. Por favor, no responda a este mensaje.</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            // Crear el mensaje
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse("diegogilzg@gmail.com"));
            mensaje.setSubject(asunto);
            mensaje.setContent(contenido, "text/html; charset=utf-8");

            // Enviar el mensaje
            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public JasperPrint generarPeleadores() {
        try {
            conexion = conectarJasper();
            return JasperFillManager.fillReport(JASPER_PELEADORES, null, conexion);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
    public JasperPrint generarEntrenadores() {
        try {
            conexion = conectarJasper();
            return JasperFillManager.fillReport(JASPER_ENTRENADORES, null, conexion);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
    public JasperPrint generarGimnasios() {
        try {
            conexion = conectarJasper();
            return JasperFillManager.fillReport(JASPER_GIMNASIOS, null, conexion);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
    public JasperPrint generarLigas() {
        try {
            conexion = conectarJasper();
            return JasperFillManager.fillReport(JASPER_LIGAS, null, conexion);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
    public static JasperPrint generarPeleadoresLiga(Liga liga){

        try {
            conexion = conectarJasper();
            HashMap<String,Object> parametros=new HashMap<String,Object>();
            parametros.put("idLiga", liga.getIdLiga());
           return JasperFillManager.fillReport(JASPER_PELEADORESLIGA, parametros, conexion);

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
    public static JasperPrint generarPeleadoresGimnasio(Gimnasio gimnasio){

        try {
            conexion = conectarJasper();
            HashMap<String,Object> parametros=new HashMap<String,Object>();
            parametros.put("idGimnasio", gimnasio.getIdGimnasio());
            return JasperFillManager.fillReport(JASPER_PELEADORESGIMNASIO, parametros, conexion);

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
    public int peleadoresActivos(Gimnasio gimnasio) {
        try (Session session = getSession()) {
            Long count = (Long) session.createQuery("SELECT COUNT(p) FROM Peleador p WHERE p.gimnasio.idGimnasio = :idGimnasio")
                    .setParameter("idGimnasio", gimnasio.getIdGimnasio())
                    .uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }


}