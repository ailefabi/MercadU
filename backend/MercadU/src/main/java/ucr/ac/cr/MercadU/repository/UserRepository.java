package ucr.ac.cr.MercadU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ucr.ac.cr.MercadU.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);
    List<User> findAllByOrderByNameAsc();
    User findByEmailUcrAndPassword(String emailUcr, String password);

    Optional<User> findByEmailUcr(String emailUcr);

    @Query("SELECT u FROM User u WHERE u.emailUcr=:emailUcr AND u.password=:password")
    User loginUserDTO(@Param("emailUcr") String email, @Param("password") String password);



}
