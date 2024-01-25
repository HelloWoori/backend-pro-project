package hellowoori.backendproproject.domain.user.application;

import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Map<UUID, String> findAllNicknames(Set<UUID> userIds) {
        Map<UUID, String> nicknamesByUserIdsMap = new HashMap<>();
        List<Object[]> nicknamesByUserIds = userRepository.findNicknamesByUserIds(userIds);
        for (Object[] nicknameByUserId : nicknamesByUserIds) {
            UUID userId = (UUID) nicknameByUserId[0];
            String nickname = (String) nicknameByUserId[1];
            nicknamesByUserIdsMap.put(userId, nickname);
        }

        return nicknamesByUserIdsMap;
    }
}
