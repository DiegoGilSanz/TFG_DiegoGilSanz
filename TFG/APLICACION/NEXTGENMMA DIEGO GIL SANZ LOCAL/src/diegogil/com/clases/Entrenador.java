package diegogil.com.clases;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Entrenador {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_entrenador")
    private int idEntrenador;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellido")
    private String apellido;
    @Basic
    @Column(name = "dni")
    private String dni;
    @Basic
    @Column(name = "experiencia")
    private int experiencia;
    @Basic
    @Column(name = "numero_colegiado")
    private int numeroColegiado;
    @OneToMany(mappedBy = "entrenador")
    private List<Peleador> peleadores;
    @ManyToOne
    @JoinColumn(name = "id_gimnasio", referencedColumnName = "id_gimnasio")
    private Gimnasio gimnasio;

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(int numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    @Override
    public String toString() {
        return "Entrenador{" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", numeroColegiado=" + numeroColegiado ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrenador that = (Entrenador) o;
        return idEntrenador == that.idEntrenador && experiencia == that.experiencia && numeroColegiado == that.numeroColegiado && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEntrenador, nombre, apellido, dni, experiencia, numeroColegiado);
    }

    public List<Peleador> getPeleadores() {
        return peleadores;
    }

    public void setPeleadores(List<Peleador> peleadores) {
        this.peleadores = peleadores;
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }
}
