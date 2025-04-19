package diegogil.com.MC;

import diegogil.com.clases.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class Modelo {
    SessionFactory sessionFactory;

    void desconectar(){
        if (sessionFactory != null &&sessionFactory.isOpen()){
            sessionFactory.close();
        }

    }
    void conectar(){
        Configuration config=new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Entrenador.class);
        config.addAnnotatedClass(Gimnasio.class);
        config.addAnnotatedClass(Peleador.class);
        config.addAnnotatedClass(Liga.class);
        config.addAnnotatedClass(Post.class);
        config.addAnnotatedClass(Federacion.class);


        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        sessionFactory=config.buildSessionFactory(ssr);


    }    public void altaPeleador(Peleador peleador){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(peleador);
        session.getTransaction().commit();
        session.close();
    }
    public void eliminarPeleador(Peleador peleador){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(peleador);
        session.getTransaction().commit();
        session.close();
    }
    public void modificarPeleador(Peleador peleador){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(peleador);
        session.getTransaction().commit();
        session.close();
    }
    public void altaEntrenador(Entrenador entrenador){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(entrenador);
        session.getTransaction().commit();
        session.close();
    }
    public void eliminarEntrenador(Entrenador entrenador){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entrenador);
        session.getTransaction().commit();
        session.close();
    }
    public void modificarEntrenador(Entrenador entrenador){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(entrenador);
        session.getTransaction().commit();
        session.close();
    }
    public void altaGimnasio(Gimnasio gimnasio){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(gimnasio);
        session.getTransaction().commit();
        session.close();
    }
    public void eliminarGimnasio(Gimnasio gimnasio){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(gimnasio);
        session.getTransaction().commit();
        session.close();
    }
    public void modificarGimnasio(Gimnasio gimnasio){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(gimnasio);
        session.getTransaction().commit();
        session.close();
    }
    public void altaLiga(Liga liga){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(liga);
        session.getTransaction().commit();
        session.close();
    }
    public void eliminarLiga(Liga liga){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(liga);
        session.getTransaction().commit();
        session.close();
    }
    public void modificarLiga(Liga liga){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(liga);
        session.getTransaction().commit();
        session.close();
    }
    public void altaFederacion(Federacion federacion){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(federacion);
        session.getTransaction().commit();
        session.close();
    }
    public void eliminarFederacion(Federacion federacion){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(federacion);
        session.getTransaction().commit();
        session.close();
    }
    public void modificarFederacion(Federacion federacion){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(federacion);
        session.getTransaction().commit();
        session.close();
    }
    public void altaPost(Post post){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(post);
        session.getTransaction().commit();
        session.close();
    }
    public void eliminarPost(Post post){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(post);
        session.getTransaction().commit();
        session.close();
    }
    public void modificarPost(Post post){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(post);
        session.getTransaction().commit();
        session.close();
    }
    public ArrayList<Gimnasio> getGimnasios(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Gimnasio> gimnasios=(ArrayList<Gimnasio>) session.createQuery("from Gimnasio").list();
        session.getTransaction().commit();
        session.close();
        return gimnasios;
    }
    public ArrayList<Entrenador> getEntrenadores(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Entrenador> entrenadores=(ArrayList<Entrenador>) session.createQuery("from Entrenador").list();
        session.getTransaction().commit();
        session.close();
        return entrenadores;
    }
    public ArrayList<Peleador> getPeleadores(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Peleador> peleadores=(ArrayList<Peleador>) session.createQuery("from Peleador").list();
        session.getTransaction().commit();
        session.close();
        return peleadores;
    }
    public ArrayList<Liga> getLigas(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Liga> ligas=(ArrayList<Liga>) session.createQuery("from Liga").list();
        session.getTransaction().commit();
        session.close();
        return ligas;
    }
    public ArrayList<Federacion> getFederaciones(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Federacion> federaciones=(ArrayList<Federacion>) session.createQuery("from Federacion").list();
        session.getTransaction().commit();
        session.close();
        return federaciones;
    }
    public ArrayList<Post> getPosts(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Post> posts=(ArrayList<Post>) session.createQuery("from Post").list();
        session.getTransaction().commit();
        session.close();
        return posts;
    }

    public boolean verificarContrasena(String contrasena){
        if (contrasena.equals("admin")) {
            return true;
        }
        return false;



    }

}
