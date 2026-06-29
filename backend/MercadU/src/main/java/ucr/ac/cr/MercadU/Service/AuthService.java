package ucr.ac.cr.MercadU.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.model.dto.LoginUserDTO;
import ucr.ac.cr.MercadU.model.dto.UserRequestDTO;
import ucr.ac.cr.MercadU.model.dto.UserResponseDTO;
import ucr.ac.cr.MercadU.model.entity.Business;
import ucr.ac.cr.MercadU.model.entity.User;
import ucr.ac.cr.MercadU.repository.BusinessRepository;
import ucr.ac.cr.MercadU.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BusinessRepository businessRepository;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       BusinessRepository businessRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.businessRepository = businessRepository;
    }

    public UserResponseDTO register(UserRequestDTO dto) {
        if (dto.getEmailUcr() == null || dto.getEmailUcr().isBlank()) {
            throw new RuntimeException("El correo es obligatorio");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }
        if (dto.getRol() == null || dto.getRol().isBlank()) {
            throw new RuntimeException("El rol es obligatorio");
        }
        if (userRepository.findByEmailUcr(dto.getEmailUcr()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmailUcr(dto.getEmailUcr());
        user.setRol(dto.getRol());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);
        return convertToResponseDTO(savedUser);
    }

    public UserResponseDTO login(LoginUserDTO dto) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        authenticationManager.authenticate(authToken);

        Optional<User> optionalUser = userRepository.findByEmailUcr(dto.getEmail());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return convertToResponseDTO(optionalUser.get());
    }

    private UserResponseDTO convertToResponseDTO(User user) {
        List<String> businessNames = this.businessRepository.findByOwnerId(user.getId())
                .stream()
                .map(Business::getName)
                .toList();

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmailUcr(),
                user.getRol(),
                businessNames
        );
    }
}