package ucr.ac.cr.MercadU.model.dto;

import java.util.Date;

public class ReviewResponseDTO {
    private Integer idResena;
    private String comentario;
    private Integer calificacion;
    private Date fechaPublicacion;

    //Datos del usuario
    //private Integer idUsuario;
    //private String nombreUsuario;

    public ReviewResponseDTO() {
    }

    public ReviewResponseDTO(Integer idResena, String comentario, Integer calificacion, Date fechaPublicacion) {
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
}