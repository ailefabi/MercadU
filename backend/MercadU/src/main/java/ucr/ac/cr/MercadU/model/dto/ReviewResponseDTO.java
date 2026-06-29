package ucr.ac.cr.MercadU.model.dto;

import java.util.Date;

public class ReviewResponseDTO {
    private Integer idReview;
    private String comment;
    private Integer rating;
    private Date publicationDate;

    private Integer businessId;
    private Integer userId;
    private String userName;

    public ReviewResponseDTO(Integer idReview, String comment, Integer rating, Date publicationDate, Integer businessId, Integer userId, String userName) {
        this.idReview = idReview;
        this.comment = comment;
        this.rating = rating;
        this.publicationDate = publicationDate;
        this.businessId = businessId;
        this.userId = userId;
        this.userName = userName;
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

    public Integer getBusinessId() {
        return businessId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}