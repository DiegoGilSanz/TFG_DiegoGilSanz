package diegogil.com.clases;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Peleador {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_peleador")
    private int idPeleador;
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
    @Column(name = "peso")
    private int peso;
    @Basic
    @Column(name = "victorias")
    private int victorias;
    @Basic
    @Column(name = "apodo")
    private String apodo;
    @Basic
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @ManyToOne
    @JoinColumn(name = "id_entrenador", referencedColumnName = "id_entrenador")
    private Entrenador entrenador;
    @ManyToOne
    @JoinColumn(name = "id_gimnasio", referencedColumnName = "id_gimnasio")
    private Gimnasio gimnasio;
    @ManyToOne
    @JoinColumn(name = "id_liga", referencedColumnName = "id_liga")
    private Liga liga;

    public int getIdPeleador() {
        return idPeleador;
    }

    public void setIdPeleador(int idPeleador) {
        this.idPeleador = idPeleador;
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Peleador" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", peso=" + peso ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peleador peleador = (Peleador) o;
        return idPeleador == peleador.idPeleador && peso == peleador.peso && victorias == peleador.victorias && Objects.equals(nombre, peleador.nombre) && Objects.equals(apellido, peleador.apellido) && Objects.equals(dni, peleador.dni) && Objects.equals(apodo, peleador.apodo) && Objects.equals(fechaNacimiento, peleador.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPeleador, nombre, apellido, dni, peso, victorias, apodo, fechaNacimiento);
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
}
