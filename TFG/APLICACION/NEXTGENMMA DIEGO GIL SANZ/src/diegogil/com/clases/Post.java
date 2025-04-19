package diegogil.com.clases;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_post")
    private int idPost;
    @Basic
    @Column(name = "titulo")
    private String titulo;
    @Basic
    @Column(name = "mensaje")
    private String mensaje;
    @Basic
    @Column(name = "foto")
    private byte[] foto;

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return idPost == post.idPost && Objects.equals(titulo, post.titulo) && Objects.equals(mensaje, post.mensaje) && Arrays.equals(foto, post.foto);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idPost, titulo, mensaje);
        result = 31 * result + Arrays.hashCode(foto);
        return result;
    }
}
