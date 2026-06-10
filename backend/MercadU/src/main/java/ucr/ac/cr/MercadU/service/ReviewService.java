package ucr.ac.cr.MercadU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.model.DTO.ReviewRequestDTO;
import ucr.ac.cr.MercadU.model.DTO.ReviewResponseDTO;
import ucr.ac.cr.MercadU.model.Review;
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
    public ReviewResponseDTO guardarReview(ReviewRequestDTO dto) {
        Review review = new Review();
        review.setComentario(dto.getComentario());
        review.setCalificacion(dto.getCalificacion());
        review.setFechaPublicacion(new java.util.Date());

        Review reviewGuardada = this.reviewRepository.save(review);
        return this.convertirToResponseDTO(reviewGuardada);
    }

    // Obtener todas las reviews
    public List<ReviewResponseDTO> obtenerTodas() {
        List<Review> listaReviews = this.reviewRepository.findAll();
        return this.convertirListaDTO(listaReviews);
    }

    // Obtener una review por su ID
    public ReviewResponseDTO obtenerPorId(Integer id) {
        Optional<Review> optional = this.reviewRepository.findById(id);
        if (optional.isPresent()) {
            return this.convertirToResponseDTO(optional.get());
        }
        return null;
    }

    // Eliminar una review por su ID
    public boolean eliminarPorId(Integer id) {
        if (this.reviewRepository.existsById(id)) {
            this.reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener el promedio de calificación de las reviews
    public Double obtenerPromedioCalificacion() {
        Double promedio = this.reviewRepository.getPromedioCalificacionGeneral();
        if (promedio != null) {
            return promedio;
        }
        return 0.0;
    }

    // Filtrar comentarios por una calificación específica
    public List<ReviewResponseDTO> obtenerPorCalificacion(Integer calificacion) {
        List<Review> lista = this.reviewRepository.findByCalificacion(calificacion);
        return this.convertirListaDTO(lista);
    }

    // Buscar comentarios que contengan palabras clave
    public List<ReviewResponseDTO> buscarPorPalabraClave(String palabraClave) {
        List<Review> lista = this.reviewRepository.findByComentarioContainingIgnoreCase(palabraClave);
        return this.convertirListaDTO(lista);
    }

    //Zona DTO
    public ReviewResponseDTO convertirToResponseDTO(Review review) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setIdResena(review.getIdResena());
        dto.setComentario(review.getComentario());
        dto.setCalificacion(review.getCalificacion());
        dto.setFechaPublicacion(review.getFechaPublicacion());
        return dto;
    }

    public List<ReviewResponseDTO> convertirListaDTO(List<Review> listaReview) {
        List<ReviewResponseDTO> listaDTO = new ArrayList<>();
        for (Review review : listaReview) {
            listaDTO.add(this.convertirToResponseDTO(review));
        }
        return listaDTO;
    }
}
