package ucr.ac.cr.MercadU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.model.dto.ReviewRequestDTO;
import ucr.ac.cr.MercadU.model.dto.ReviewResponseDTO;
import ucr.ac.cr.MercadU.model.entity.Business;
import ucr.ac.cr.MercadU.model.entity.Review;
import ucr.ac.cr.MercadU.model.entity.User;
import ucr.ac.cr.MercadU.repository.BusinessRepository;
import ucr.ac.cr.MercadU.repository.ReviewRepository;
import ucr.ac.cr.MercadU.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private UserRepository userRepository;

    // Guardar una nueva review
    // Recibe un RequestDTO y devuelve un ResponseDTO
    public ReviewResponseDTO saveReview(ReviewRequestDTO dto) {
        Business business = this.businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("El emprendimiento no existe"));

        User user = this.userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        // Regla de negocio: un usuario no puede reseñar el mismo emprendimiento dos veces
        if (this.reviewRepository.existsByUser_IdAndBusiness_Id(dto.getUserId(), dto.getBusinessId())) {
            throw new RuntimeException("Ya registraste una reseña para este emprendimiento");
        }

        Review review = new Review();
        review.setComment(dto.getComment());
        review.setRating(dto.getRating());
        review.setPublicationDate(new java.util.Date());
        review.setBusiness(business);
        review.setUser(user);

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

    public List<ReviewResponseDTO> findByBusiness(Integer businessId) {
        return this.convertListDTO(this.reviewRepository.findByBusinessId(businessId));
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
    public Double findAvgRatingByBusiness(Integer businessId) {
        Double promedio = this.reviewRepository.getAvgRatingByBusiness(businessId);
        return promedio != null ? promedio : 0.0;
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
                review.getPublicationDate(),
                review.getBusiness().getId(),
                review.getUser().getId(),
                review.getUser().getName()
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
