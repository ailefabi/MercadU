package ucr.ac.cr.MercadU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ucr.ac.cr.MercadU.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);
    List<User> findAllByOrderByNameAsc();
    User findByEmailAndPassword(String emailUcr, String password);

    @Query("SELECT u FROM User u WHERE u.email=:email AND u.password=:password")
    User loginDTO(@Param("email") String email, @Param("password") String password);



}
