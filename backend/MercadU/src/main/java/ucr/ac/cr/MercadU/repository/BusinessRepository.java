package ucr.ac.cr.MercadU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.MercadU.model.entity.Business;

import java.util.List;

public interface BusinessRepository extends JpaRepository <Business, Integer>{


    List<Business> findByName(String name);
}
