package ucr.ac.cr.MercadU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.MercadU.model.entity.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Metodo  para buscar solo productos disponibles
    List<Product> findByAvailableTrue();

    //Metodo para buscar productos más baratos o iguales a un precio
    List<Product> findByPriceLessThanEqual(Integer priceMax);

    List<Product> findByBusinessId(Integer businessId);
}
