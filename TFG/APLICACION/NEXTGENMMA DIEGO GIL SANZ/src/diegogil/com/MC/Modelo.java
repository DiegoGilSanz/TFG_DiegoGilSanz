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
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.DriverManager;
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

    public Modelo() {
        conectar();


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

            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties())
                    .build();
            sessionFactory = config.buildSessionFactory(ssr);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initializing Hibernate SessionFactory.");
        }
    }

    private static Connection conectarJasper() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenmma", "root", "");
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
            throw new IllegalStateException("SessionFactory is not initialized. Call conectar() first.");
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

    public boolean verificarContrasena(String contrasena) {
        return "admin".equals(contrasena);
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
        String remitente = "diegogilzg@gmail.com"; // Cambia por tu correo
        String contraseña = "ylqy gmxn svib vjbc"; // Cambia por tu contraseña

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", puerto);

        javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(remitente, contraseña);
            }
        });

        try {
            // Crear el contenido del mensaje con los datos del peleador
            String contenido = "Datos del Peleador:\n" +
                    "Nombre: " + peleador.getNombre() + "\n" +
                    "Apellido: " + peleador.getApellido() + "\n" +
                    "DNI: " + peleador.getDni() + "\n" +
                    "Peso: " + peleador.getPeso() + "\n" +
                    "Victorias: " + peleador.getVictorias() + "\n" +
                    "Apodo: " + peleador.getApodo() + "\n" +
                    "Fecha de Nacimiento: " + peleador.getFechaNacimiento() + "\n" +
                    "Gimnasio: " + (peleador.getGimnasio() != null ? peleador.getGimnasio().getNombre() : "N/A") + "\n" +
                    "Entrenador: " + (peleador.getEntrenador() != null ? peleador.getEntrenador().getNombre() + " " + peleador.getEntrenador().getApellido() : "N/A") + "\n" +
                    "Liga: " + (peleador.getLiga() != null ? peleador.getLiga().getNombre() : "N/A");

            // Crear el mensaje
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse("diegogilzg@gmail.com")); // Cambia por el destinatario
            mensaje.setSubject(asunto);
            mensaje.setText(contenido);

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