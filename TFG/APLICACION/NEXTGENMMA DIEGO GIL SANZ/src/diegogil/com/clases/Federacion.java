package diegogil.com.clases;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Federacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_federacion")
    private int idFederacion;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "arte_marcial")
    private String arteMarcial;
    @Basic
    @Column(name = "fecha_fundacion")
    private Date fechaFundacion;
    @Basic
    @Column(name = "numero_asociacion")
    private int numeroAsociacion;
    @OneToMany(mappedBy = "federacion")
    private List<Liga> ligas;

    public int getIdFederacion() {
        return idFederacion;
    }

    public void setIdFederacion(int idFederacion) {
        this.idFederacion = idFederacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArteMarcial() {
        return arteMarcial;
    }

    public void setArteMarcial(String arteMarcial) {
        this.arteMarcial = arteMarcial;
    }

    public Date getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(Date fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public int getNumeroAsociacion() {
        return numeroAsociacion;
    }

    public void setNumeroAsociacion(int numeroAsociacion) {
        this.numeroAsociacion = numeroAsociacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Federacion that = (Federacion) o;
        return idFederacion == that.idFederacion && numeroAsociacion == that.numeroAsociacion && Objects.equals(nombre, that.nombre) && Objects.equals(arteMarcial, that.arteMarcial) && Objects.equals(fechaFundacion, that.fechaFundacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFederacion, nombre, arteMarcial, fechaFundacion, numeroAsociacion);
    }

    public List<Liga> getLigas() {
        return ligas;
    }

    public void setLigas(List<Liga> ligas) {
        this.ligas = ligas;
    }
}
