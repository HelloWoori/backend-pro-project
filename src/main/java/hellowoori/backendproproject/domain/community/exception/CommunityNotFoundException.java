package hellowoori.backendproproject.domain.community.exception;

import hellowoori.backendproproject.global.error.BaseRuntimeException;
import lombok.Getter;

@Getter
public class CommunityNotFoundException extends BaseRuntimeException {

    public CommunityNotFoundException(Long communityId) {
        super("Community not found with id: " + communityId);
    }
}
