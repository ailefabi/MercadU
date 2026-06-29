package ucr.ac.cr.MercadU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ucr.ac.cr.MercadU.model.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    //Buscar usuario especifico
    //List<Review> findByUserId(Integer userId);

    //Obtener comentarios por orden reciente a antiguo
    List<Review> findAllByOrderByPublicationDateDesc();

    //Filtrar comentarios por calificacion especifica
    List<Review> findByRating(Integer Rating);

    //Calcular el promedio de calificacion
    @Query("SELECT AVG(r.rating) FROM Review r")
    Double getAvgRating();

    //Buscar comentarios con palabras clave
    List<Review> findByCommentContainingIgnoreCase(String keyword);

    List<Review> findByBusinessId(Integer businessId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.business.id = :businessId")
    Double getAvgRatingByBusiness(@Param("businessId") Integer businessId);

    boolean existsByUser_IdAndBusiness_Id(Integer userId, Integer businessId);
}
