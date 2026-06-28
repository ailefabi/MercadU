package ucr.ac.cr.MercadU.service;

import ucr.ac.cr.MercadU.model.entity.User;
import ucr.ac.cr.MercadU.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmailUcr(email);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = optionalUser.get();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmailUcr())
                .password(user.getPassword())
                .roles(user.getRol())
                .build();
    }
}