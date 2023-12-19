package hellowoori.backendproproject.domain.user.application;

import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User join(String nickname) {
        User user = new User(nickname);
        return userRepository.save(user);
    }

    public Optional<User> findOne(UUID userId) {
        return userRepository.findById(userId);
    }

    public String findNickname(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(User::getNickname).orElse(null);
    }
}
