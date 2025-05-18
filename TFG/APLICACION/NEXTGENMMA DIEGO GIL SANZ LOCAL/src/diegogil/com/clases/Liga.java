package diegogil.com.clases;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Liga {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_liga")
    private int idLiga;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;
    @Basic
    @Column(name = "participantes")
    private int participantes;
    @ManyToOne
    @JoinColumn(name = "id_federacion", referencedColumnName = "id_federacion")
    private Federacion federacion;
    @OneToMany(mappedBy = "liga")
    private List<Peleador> peleadores;

    @Override
    public String toString() {
        return "Liga{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", participantes=" + participantes ;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getParticipantes() {
        return participantes;
    }

    public void setParticipantes(int participantes) {
        this.participantes = participantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Liga liga = (Liga) o;
        return idLiga == liga.idLiga && participantes == liga.participantes && Objects.equals(nombre, liga.nombre) && Objects.equals(ciudad, liga.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLiga, nombre, ciudad, participantes);
    }

    public Federacion getFederacion() {
        return federacion;
    }

    public void setFederacion(Federacion federacion) {
        this.federacion = federacion;
    }

    public List<Peleador> getPeleadores() {
        return peleadores;
    }

    public void setPeleadores(List<Peleador> peleadores) {
        this.peleadores = peleadores;
    }
}
