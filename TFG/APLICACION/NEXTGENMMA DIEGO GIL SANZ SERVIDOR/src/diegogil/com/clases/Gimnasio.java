package diegogil.com.clases;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Gimnasio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_gimnasio")
    private int idGimnasio;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "contraseña")
    private String contraseña;
    @Basic
    @Column(name = "NIF")
    private String nif;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @Basic
    @Column(name = "web")
    private String web;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;
    @OneToMany(mappedBy = "gimnasio")
    private List<Peleador> peleadores;
    @OneToMany(mappedBy = "gimnasio")
    private List<Entrenador> entrenadores;

    public int getIdGimnasio() {
        return idGimnasio;
    }

    public void setIdGimnasio(int idGimnasio) {
        this.idGimnasio = idGimnasio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Gimnasio" +  nombre + '\'' +
                ", nif='" + nif + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gimnasio gimnasio = (Gimnasio) o;
        return idGimnasio == gimnasio.idGimnasio && Objects.equals(nombre, gimnasio.nombre) && Objects.equals(contraseña, gimnasio.contraseña) && Objects.equals(nif, gimnasio.nif) && Objects.equals(direccion, gimnasio.direccion) && Objects.equals(web, gimnasio.web) && Objects.equals(ciudad, gimnasio.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGimnasio, nombre, contraseña, nif, direccion, web, ciudad);
    }

    public List<Peleador> getPeleadores() {
        return peleadores;
    }

    public void setPeleadores(List<Peleador> peleadores) {
        this.peleadores = peleadores;
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(List<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }
}

