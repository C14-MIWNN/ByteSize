package nl.miwnn.se14.bytesize.service;

import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import nl.miwnn.se14.bytesize.repositories.ByteSizeUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Heron
 * Purpose for the class
 */

@Service
public class ByteSizeUserService implements UserDetailsService {
    private final ByteSizeUserRepository byteSizeUserRepository;
    private final PasswordEncoder passwordEncoder;

    public ByteSizeUserService(ByteSizeUserRepository byteSizeUserRepository, PasswordEncoder passwordEncoder) {
        this.byteSizeUserRepository = byteSizeUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ByteSizeUser> user = byteSizeUserRepository.findByUsername(username);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(String.format("User %s was not found.", username));
        }
    }

    public void save(ByteSizeUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        byteSizeUserRepository.save(user);
    }
}
