package ucr.ac.cr.MercadU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.model.dto.ReviewRequestDTO;
import ucr.ac.cr.MercadU.model.dto.ReviewResponseDTO;
import ucr.ac.cr.MercadU.model.entity.Review;
import ucr.ac.cr.MercadU.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Guardar una nueva review
    // Recibe un RequestDTO y devuelve un ResponseDTO
    public ReviewResponseDTO saveReview(ReviewRequestDTO dto) {
        Review review = new Review();
        review.setComment(dto.getComment());
        review.setRating(dto.getRating());
        review.setPublicationDate(new java.util.Date());

        Review reviewS = this.reviewRepository.save(review);
        return this.convertToResponseDTO(reviewS);
    }

    // Obtener todas las reviews
    public List<ReviewResponseDTO> findAllReviews() {
        List<Review> listReviews = this.reviewRepository.findAll();
        return this.convertListDTO(listReviews);
    }

    // Obtener una review por su ID
    public ReviewResponseDTO findByID(Integer id) {
        Optional<Review> optional = this.reviewRepository.findById(id);
        if (optional.isPresent()) {
            return this.convertToResponseDTO(optional.get());
        }
        return null;
    }

    // Eliminar una review por su ID
    public boolean deleteByID(Integer id) {
        if (this.reviewRepository.existsById(id)) {
            this.reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener el promedio de calificación de las reviews
    public Double findAvgRating() {
        Double promedio = this.reviewRepository.getAvgRating();
        if (promedio != null) {
            return promedio;
        }
        return 0.0;
    }

    // Filtrar comentarios por una calificación específica
    public List<ReviewResponseDTO> findByRating(Integer rating) {
        List<Review> lista = this.reviewRepository.findByRating(rating);
        return this.convertListDTO(lista);
    }

    // Buscar comentarios que contengan palabras clave
    public List<ReviewResponseDTO> findByKeyword(String keyword) {
        List<Review> lista = this.reviewRepository.findByCommentContainingIgnoreCase(keyword);
        return this.convertListDTO(lista);
    }

    //Zona DTO
    public ReviewResponseDTO convertToResponseDTO(Review review) {
        return new ReviewResponseDTO(
          review.getIdReview(),
          review.getComment(),
          review.getRating(),
          review.getPublicationDate()
        );
    }

    public List<ReviewResponseDTO> convertListDTO(List<Review> listaReview) {
        List<ReviewResponseDTO> listaDTO = new ArrayList<>();
        for (Review review : listaReview) {
            listaDTO.add(this.convertToResponseDTO(review));
        }
        return listaDTO;
    }
}
