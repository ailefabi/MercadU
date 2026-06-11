package ucr.ac.cr.MercadU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.model.entity.User;
import ucr.ac.cr.MercadU.model.dto.UserRequestDTO;
import ucr.ac.cr.MercadU.model.dto.UserRespondDTO;
import ucr.ac.cr.MercadU.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserRespondDTO saveUser(UserRequestDTO request) {
        if (userRepository.findByEmailUcr(request.getEmailUcr()).isPresent()) return null;

        User user = this.convertToEntity(request);
        return this.convertToRespondDTO(this.userRepository.save(user));
    }

    public List<UserRespondDTO> findAll() {
        return this.convertListDTO(this.userRepository.findAll());
    }

    public UserRespondDTO findByIDUser(Integer id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()) return this.convertToRespondDTO(optional.get());
        return null;
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteById(id);
    }

    public UserRespondDTO editUser(Integer id, UserRequestDTO request) {
        Optional<User> userOp = this.userRepository.findById(id);
        if (userOp.isPresent()) {
            User user = userOp.get();
            user.setName(request.getName());
            user.setEmailUcr(request.getEmailUcr());
            return this.convertToRespondDTO(this.userRepository.save(user));
        }
        return null;
    }

    public UserRespondDTO convertToRespondDTO(User user) {
        UserRespondDTO dto = new UserRespondDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmailUcr(user.getEmailUcr());
        dto.setRol(user.getRol());
        return dto;
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

    public List<UserRespondDTO> convertListDTO(List<User> listUser) {
        List<UserRespondDTO> listDTO = new ArrayList<>();
        for (User user : listUser) {
            listDTO.add(this.convertToRespondDTO(user));
        }
        return listDTO;
    }

    public List<UserRespondDTO> findByName(String name) {
        return this.convertListDTO(this.userRepository.findByName(name));
    }

    public List<User> findAllByOrderByNameAsc() {
        return this.userRepository.findAllByOrderByNameAsc();
    }

    public User login(String email, String password) {
        return this.userRepository.loginUserDTO(email, password);
    }
}

