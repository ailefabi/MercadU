package ucr.ac.cr.MercadU.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.MercadU.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}