package hellowoori.backendproproject.domain.article.infrastructure;

import hellowoori.backendproproject.domain.article.application.UserServiceClient;
import hellowoori.backendproproject.domain.user.application.UserService;
import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.global.ThreadLocalContext;
import hellowoori.backendproproject.global.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceClientImpl implements UserServiceClient {

    private final UserService userService;

    @Override
    public User getCurrentUser() {
        User user = (User) ThreadLocalContext.get(SessionConst.LOGIN_USER);
        log.info("getCurrentUser...email [{}], nickname [{}]", user.getEmail(), user.getNickname());
        return user;
    }

    @Override
    public String findNickname(UUID userId) {
        return userService.findNickname(userId);
    }

    @Override
    public Map<UUID, String> findAllNicknames(Set<UUID> userIds) {
        return userService.findAllNicknames(userIds);
    }

    @Override
    public User findById(UUID userId) {
        return userService.findOne(userId);
    }
}
