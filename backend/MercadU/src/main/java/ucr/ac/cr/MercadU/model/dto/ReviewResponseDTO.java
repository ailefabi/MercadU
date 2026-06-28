package ucr.ac.cr.MercadU.model.dto;

import java.util.Date;

public class ReviewResponseDTO {
    private Integer idReview;
    private String comment;
    private Integer rating;
    private Date publicationDate;

    //Datos del usuario
    //private Integer idUsuario;
    //private String nombreUsuario;

    public ReviewResponseDTO(Integer idReview, String comment, Integer rating, Date publicationDate) {
        this.idReview = idReview;
        this.comment = comment;
        this.rating = rating;
        this.publicationDate = publicationDate;
    }


    public Integer getIdReview() {
        return idReview;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }
}