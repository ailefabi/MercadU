package ucr.ac.cr.MercadU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.model.entity.User;
import ucr.ac.cr.MercadU.model.dto.UserRequestDTO;
import ucr.ac.cr.MercadU.model.dto.UserResponseDTO;
import ucr.ac.cr.MercadU.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> findAll() {
        return this.convertListDTO(this.userRepository.findAll());
    }

    public UserResponseDTO findByIDUser(Integer id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()) return this.convertToRespondDTO(optional.get());
        return null;
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteById(id);
    }

    public UserResponseDTO editUser(Integer id, UserRequestDTO request) {
        Optional<User> userOp = this.userRepository.findById(id);
        if (userOp.isPresent()) {
            User user = userOp.get();
            user.setName(request.getName());
            user.setEmailUcr(request.getEmailUcr());

            if (request.getPassword() != null && !request.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }

            return this.convertToRespondDTO(this.userRepository.save(user));
        }
        return null;
    }

    public UserResponseDTO convertToRespondDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmailUcr(),
                user.getRol()
        );
    }

    public User convertToEntity(UserRequestDTO request) {
        User user = new User();
        user.setId(request.getId());
        user.setName(request.getName());
        user.setEmailUcr(request.getEmailUcr());
        user.setRol(request.getRol());         // faltaba
        user.setPassword(request.getPassword()); // faltaba
        return user;
    }

    public List<UserResponseDTO> convertListDTO(List<User> listUser) {
        List<UserResponseDTO> listDTO = new ArrayList<>();
        for (User user : listUser) {
            listDTO.add(this.convertToRespondDTO(user));
        }
        return listDTO;
    }

    public List<UserResponseDTO> findByName(String name) {
        return this.convertListDTO(this.userRepository.findByName(name));
    }

    public List<User> findAllByOrderByNameAsc() {
        return this.userRepository.findAllByOrderByNameAsc();
    }

}

