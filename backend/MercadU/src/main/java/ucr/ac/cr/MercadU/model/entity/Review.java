package ucr.ac.cr.MercadU.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "tb_resenas")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Integer idResena;

    @Column(name = "comentario", nullable = false, length = 500)
    @NotBlank(message = "El comentario no puede estar vacio.")
    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres.")
    private String comentario;

    @Column(name = "calificacion", nullable = false)
    @NotNull(message = "La calificacion es obligatoria.")
    @Min(value = 1, message = "La calificacion minima es 1.")
    @Max(value = 5, message = "La calificacion maxima es 5.")
    private Integer calificacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_publicacion", nullable = false, updatable = false)
    private Date fechaPublicacion;

    //Relacion con User
    /*
    @ManyToOne(fetch = FetchType.LAZY) //Use Lazy xq supuestamente es menor carga ala memoria y solo carga lo necesario en el momento
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Esto es para que no genere un error el FetchType.Lazy
    private User user;
    */

    public Review() {
    }

    public Review(Integer idResena, String comentario, Integer calificacion, Date fechaPublicacion) {
        this.idResena = idResena;
        this.comentario = comentario;
        this.calificacion = calificacion;
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

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
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
                ", calificacion=" + calificacion +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}
