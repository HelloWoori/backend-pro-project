package hellowoori.backendproproject.domain.article.application;

import hellowoori.backendproproject.domain.user.domain.User;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface UserServiceClient {

    User getCurrentUser();

    String findNickname(UUID userId);

    Map<UUID, String> findAllNicknames(Set<UUID> userIdList);

    User findById(UUID userId);
}
