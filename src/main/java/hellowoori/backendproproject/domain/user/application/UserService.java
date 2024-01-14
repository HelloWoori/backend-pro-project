package hellowoori.backendproproject.domain.user.application;

import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User join(User user) {
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }

    public User findOne(UUID userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    public String findNickname(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(User::getNickname).orElse(null);
    }
}
