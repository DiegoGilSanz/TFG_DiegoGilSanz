package diegogil.com;

import diegogil.com.mc.Controlador;
import diegogil.com.mc.Modelo;
import diegogil.com.gui.*;

public class Main {

    public static void main(String[] args) {
        Modelo.connect();
        Modelo.disconnect();
        Modelo modelo = new Modelo();
        VistaGestion vistaGestion = new VistaGestion();
        VistaLoginGimnasio vistaLoginGimnasio = new VistaLoginGimnasio();
        VistaGimnasio vistaGimnasio = new VistaGimnasio();
        VistaPost vistaPost = new VistaPost();
        VistaLoginAdmin vistaLoginAdmin = new VistaLoginAdmin();
        VistaUsuarios vistaUsers = new VistaUsuarios();
        Controlador controlador = new Controlador(modelo, vistaUsers, vistaPost, vistaLoginAdmin, vistaGimnasio, vistaLoginGimnasio, vistaGestion);

    }

}
