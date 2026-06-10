package ucr.ac.cr.MercadU.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.MercadU.model.DTO.ReviewRequestDTO;
import ucr.ac.cr.MercadU.model.DTO.ReviewResponseDTO;
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
    public ResponseEntity<?> crearReview(@RequestBody ReviewRequestDTO reviewDTO) {
        try {
            ReviewResponseDTO nuevaReview = this.reviewService.guardarReview(reviewDTO);
            return new ResponseEntity<>(nuevaReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar la reseña: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> obtenerTodas() {
        List<ReviewResponseDTO> lista = this.reviewService.obtenerTodas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    //Obtener una reseña por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> obtenerPorId(@PathVariable Integer id) {
        ReviewResponseDTO dto = this.reviewService.obtenerPorId(id);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Eliminar una reseña por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable Integer id) {
        try {
            boolean eliminado = this.reviewService.eliminarPorId(id);
            if (eliminado) {
                return new ResponseEntity<>("Reseña eliminada correctamente.", HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontró la reseña.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en el servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Obtener el promedio de calificación general
    @GetMapping("/promedio")
    public ResponseEntity<Double> obtenerPromedioGeneral() {
        Double promedio = this.reviewService.obtenerPromedioCalificacion();
        return new ResponseEntity<>(promedio, HttpStatus.OK);
    }

    //Filtrar reseñas por cantidad de estrellas
    @GetMapping("/calificacion/{estrellas}")
    public ResponseEntity<List<ReviewResponseDTO>> obtenerPorCalificacion(@PathVariable Integer estrellas) {
        List<ReviewResponseDTO> lista = this.reviewService.obtenerPorCalificacion(estrellas);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    //Buscar reseñas que contengan una palabra clave
    @GetMapping("/buscar")
    public ResponseEntity<List<ReviewResponseDTO>> buscarPorPalabraClave(@RequestParam String palabraClave) {
        List<ReviewResponseDTO> lista = this.reviewService.buscarPorPalabraClave(palabraClave);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }










}
