package hellowoori.backendproproject.domain.article.infrastructure;

import hellowoori.backendproproject.domain.article.application.UserServiceClient;
import hellowoori.backendproproject.domain.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserServiceClientImpl implements UserServiceClient {

    private final UserService userService;

    @Override
    public String findNickname(UUID userId) {
        return userService.findNickname(userId);
    }
}
