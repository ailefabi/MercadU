package ucr.ac.cr.MercadU.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.MercadU.model.DTO.ReviewDTO;
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
    public ResponseEntity<?> crearReview(@RequestBody ReviewDTO reviewDTO) {
        try {
            ReviewDTO nuevaReview = this.reviewService.guardarReview(reviewDTO);
            return new ResponseEntity<>(nuevaReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar la reseña: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> obtenerTodas() {
        List<ReviewDTO> lista = this.reviewService.obtenerTodas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    //Obtener una reseña por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> obtenerPorId(@PathVariable Integer id) {
        ReviewDTO dto = this.reviewService.obtenerPorId(id);
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
            return new ResponseEntity<>("No se encontró la reseña con el ID especificado.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en el servidor al intentar eliminar.", HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<List<ReviewDTO>> obtenerPorCalificacion(@PathVariable Integer estrellas) {
        List<ReviewDTO> lista = this.reviewService.obtenerPorCalificacion(estrellas);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    //Buscar reseñas que contengan una palabra clave
    @GetMapping("/buscar")
    public ResponseEntity<List<ReviewDTO>> buscarPorPalabraClave(@RequestParam String palabraClave) {
        List<ReviewDTO> lista = this.reviewService.buscarPorPalabraClave(palabraClave);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }










}
