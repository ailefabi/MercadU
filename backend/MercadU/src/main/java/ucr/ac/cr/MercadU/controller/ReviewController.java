package ucr.ac.cr.MercadU.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.MercadU.model.dto.ReviewRequestDTO;
import ucr.ac.cr.MercadU.model.dto.ReviewResponseDTO;
import ucr.ac.cr.MercadU.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    //Crear una nueva reseña
    @PostMapping
    public ResponseEntity<?> saveReview(@RequestBody ReviewRequestDTO reviewDTO) {
        try {
            ReviewResponseDTO newReview = this.reviewService.saveReview(reviewDTO);
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar la reseña: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> findall() {
        List<ReviewResponseDTO> list = this.reviewService.findAllReviews();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //Obtener una reseña por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> findByID(@PathVariable Integer id) {
        ReviewResponseDTO dto = this.reviewService.findByID(id);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Eliminar una reseña por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable Integer id) {
        try {
            boolean eliminado = this.reviewService.deleteByID(id);
            if (eliminado) {
                return new ResponseEntity<>("Reseña eliminada correctamente.", HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontró la reseña.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en el servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Obtener el promedio de calificación general
    @GetMapping("/avg")
    public ResponseEntity<Double> obtainAvgRating() {
        Double promedio = this.reviewService.findAvgRating();
        return new ResponseEntity<>(promedio, HttpStatus.OK);
    }

    //Filtrar reseñas por cantidad de estrellas
    @GetMapping("/rating/{stars}")
    public ResponseEntity<List<ReviewResponseDTO>> filterByRating(@PathVariable Integer stars) {
        List<ReviewResponseDTO> lista = this.reviewService.findByRating(stars);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    //Buscar reseñas que contengan una palabra clave
    @GetMapping("/search")
    public ResponseEntity<List<ReviewResponseDTO>> findByKeyword(@RequestParam String keyword) {
        List<ReviewResponseDTO> lista = this.reviewService.findByKeyword(keyword);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }










}
