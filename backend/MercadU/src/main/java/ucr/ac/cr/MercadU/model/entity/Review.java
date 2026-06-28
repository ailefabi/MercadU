package ucr.ac.cr.MercadU.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "tb_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReview")
    private Integer idReview;

    @Column(name = "comment", nullable = false, length = 500)
    @NotBlank(message = "El comentario no puede estar vacio.")
    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres.")
    private String comment;

    @Column(name = "rating", nullable = false)
    @NotNull(message = "La calificacion es obligatoria.")
    @Min(value = 1, message = "La calificacion minima es 1.")
    @Max(value = 5, message = "La calificacion maxima es 5.")
    private Integer rating;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publicationDate", nullable = false, updatable = false)
    private Date publicationDate;

    //Relacion con User
    /*
    @ManyToOne(fetch = FetchType.LAZY) //Use Lazy xq supuestamente es menor carga ala memoria y solo carga lo necesario en el momento
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Esto es para que no genere un error el FetchType.Lazy
    private User user;
    */

    public Review() {
    }

    public Review(Integer idReview, String comment, Integer rating, Date publicationDate) {
        this.idReview = idReview;
        this.comment = comment;
        this.rating = rating;
        this.publicationDate = publicationDate;
    }

    public Integer getIdReview() {
        return idReview;
    }

    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "review{" +
                "idReview=" + idReview +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
