package hellowoori.backendproproject.domain.article.application;

import java.util.UUID;

public interface UserServiceClient {

    String findNickname(UUID userId);
}
