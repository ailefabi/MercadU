package ucr.ac.cr.MercadU.model.dto;

import jakarta.validation.constraints.*;

public class ReviewRequestDTO {

    @NotBlank(message = "El comentario no puede estar vacío.")
    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres.")
    private String comentario;

    @NotNull(message = "La calificación es obligatoria.")
    @Min(value = 1, message = "La calificación mínima es 1.")
    @Max(value = 5, message = "La calificación máxima es 5.")
    private Integer calificacion;

    //Solo enviara el ID del estudiante
    //private Integer idUsuario;

    public ReviewRequestDTO() {
    }

    public ReviewRequestDTO(String comentario, Integer calificacion) {
        this.comentario = comentario;
        this.calificacion = calificacion;
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
}
