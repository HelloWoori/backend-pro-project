package hellowoori.backendproproject.domain.article.infrastructure;

import hellowoori.backendproproject.domain.article.application.CommunityServiceClient;
import hellowoori.backendproproject.domain.community.application.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityServiceClientImpl implements CommunityServiceClient {

    private final CommunityService communityService;

    @Override
    public String findCommunityName(Long communityId) {
        return communityService.findCommunityName(communityId);
    }
}
