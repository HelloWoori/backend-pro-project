package hellowoori.backendproproject.domain.article.infrastructure;

import hellowoori.backendproproject.domain.article.application.UserServiceClient;
import hellowoori.backendproproject.domain.dummy.application.DummyService;
import hellowoori.backendproproject.domain.user.application.UserService;
import hellowoori.backendproproject.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserServiceClientImpl implements UserServiceClient {

    private final UserService userService;
    private final DummyService dummyService;

    @Override
    public User getCurrentUser() {
        return dummyService.getDummyUser();
    }

    @Override
    public String findNickname(UUID userId) {
        return userService.findNickname(userId);
    }

    @Override
    public User findById(UUID userId) {
        return userService.findOne(userId);
    }
}
