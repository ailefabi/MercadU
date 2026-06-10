package ucr.ac.cr.MercadU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ucr.ac.cr.MercadU.model.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    //Buscar usuario especifico
    //List<Review> findByUserId(Integer userId);

    //Obtener comentarios por orden reciente a antiguo
    List<Review> findByOrderByFechaPublicacionDesc();

    //Filtrar comentarios por calificacion especifica
    List<Review> findByCalificacion(Integer calificacion);

    //Calcular el promedio de calificacion
    @Query("SELECT AVG(r.calificacion) FROM Review r")
    Double getPromedioCalificacionGeneral();

    //Buscar comentarios con palabras clave
    List<Review> findByComentarioContainingIgnoreCase(String palabraClave);
}
