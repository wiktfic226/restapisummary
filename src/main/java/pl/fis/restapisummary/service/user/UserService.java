package pl.fis.restapisummary.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.fis.restapisummary.config.PasswordEncoderConfig;
import pl.fis.restapisummary.entity.user.UserEntity;
import pl.fis.restapisummary.exception.NotFoundException;
import pl.fis.restapisummary.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public UserEntity getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                String.format("User with username %s does not exists", username)));
    }

    public void deleteUser(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                String.format("User with username %s does not exists", username)));
        userRepository.delete(user);
    }

    public void updatePassword(String username, String password) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                String.format("User with username %s does not exists", username)));
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(password));
        userRepository.save(user);
    }
}
