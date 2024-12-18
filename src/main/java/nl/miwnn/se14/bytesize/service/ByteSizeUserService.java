package nl.miwnn.se14.bytesize.service;

import nl.miwnn.se14.bytesize.dto.ByteSizeUserDTO;
import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import nl.miwnn.se14.bytesize.repositories.ByteSizeUserRepository;
import nl.miwnn.se14.bytesize.service.mapper.ByteSizeUserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Heron
 * Services the User Repository
 */

@Service
public class ByteSizeUserService implements UserDetailsService {
    private final ByteSizeUserRepository byteSizeUserRepository;
    private final PasswordEncoder passwordEncoder;

    public ByteSizeUserService(ByteSizeUserRepository byteSizeUserRepository, PasswordEncoder passwordEncoder) {
        this.byteSizeUserRepository = byteSizeUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
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

    public boolean usernameInUse(String username) {
        return byteSizeUserRepository.findByUsername(username).isPresent();
    }

    public void save(ByteSizeUserDTO userDTO) {
        save(ByteSizeUserMapper.fromDTO(userDTO));
    }

    public void save(ByteSizeUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        byteSizeUserRepository.save(user);
    }

    public List<ByteSizeUser> getAllUsers() {
        return byteSizeUserRepository.findAll();
    }
}
