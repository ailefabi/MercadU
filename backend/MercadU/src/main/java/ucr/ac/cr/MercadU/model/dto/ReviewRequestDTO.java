package ucr.ac.cr.MercadU.model.dto;

import jakarta.validation.constraints.*;

public class ReviewRequestDTO {

    @NotBlank(message = "El comentario no puede estar vacío.")
    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres.")
    private String comment;

    @NotNull(message = "La calificación es obligatoria.")
    @Min(value = 1, message = "La calificación mínima es 1.")
    @Max(value = 5, message = "La calificación máxima es 5.")
    private Integer rating;

    //Solo enviara el ID del estudiante
    //private Integer idUsuario;

    public ReviewRequestDTO() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
