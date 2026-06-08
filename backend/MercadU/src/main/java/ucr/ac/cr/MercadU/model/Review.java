package ucr.ac.cr.MercadU.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "tb_resenas")
public class Review {
    private Integer idResena;
    private String comentario;
    private Integer calicacion;
    private Date fechaPublicacion;

    public Review() {
    }

    public Review(Integer idResena, String comentario, Integer calicacion, Date fechaPublicacion) {
        this.idResena = idResena;
        this.comentario = comentario;
        this.calicacion = calicacion;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getIdResena() {
        return idResena;
    }

    public void setIdResena(Integer idResena) {
        this.idResena = idResena;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalicacion() {
        return calicacion;
    }

    public void setCalicacion(Integer calicacion) {
        this.calicacion = calicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "review{" +
                "idResena=" + idResena +
                ", comentario='" + comentario + '\'' +
                ", calicacion=" + calicacion +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}
