package ucr.ac.cr.MercadU.model.DTO;

import jakarta.validation.constraints.*;

import java.util.Date;

public class ReviewDTO {
    private Integer idResena;

    @NotBlank(message = "El comentario no puede estar vacío.")
    private String comentario;

    @Min(1) @Max(5)
    private Integer calificacion;
    private Date fechaPublicacion;

    // Solo exponemos estos datos del usuario
    private Integer idUsuario;
    private String nombreUsuario;

    public ReviewDTO() {
    }

    public ReviewDTO(Integer idResena, String comentario, Integer calificacion, Date fechaPublicacion, Integer idUsuario, String nombreUsuario) {
        this.idResena = idResena;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fechaPublicacion = fechaPublicacion;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
