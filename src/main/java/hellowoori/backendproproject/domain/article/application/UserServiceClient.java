package hellowoori.backendproproject.domain.article.application;

import hellowoori.backendproproject.domain.user.domain.User;

import java.util.UUID;

public interface UserServiceClient {

    User getCurrentUser();

    String findNickname(UUID userId);

    User findById(UUID userId);
}
