package hellowoori.backendproproject.domain.community.application;

import hellowoori.backendproproject.domain.community.domain.Community;
import hellowoori.backendproproject.domain.community.domain.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public String findCommunityName(Long communityId) {
        Optional<Community> optionalCommunity = communityRepository.findById(communityId);
        return optionalCommunity.map(Community::getCommunityName).orElse(null);
    }
}
