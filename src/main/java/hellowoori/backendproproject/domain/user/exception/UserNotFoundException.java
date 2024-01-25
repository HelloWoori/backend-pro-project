package hellowoori.backendproproject.domain.user.exception;

import hellowoori.backendproproject.global.error.BaseRuntimeException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserNotFoundException extends BaseRuntimeException {

    public UserNotFoundException(UUID userId) {
        super("User not found with id: " + userId);
    }
}
