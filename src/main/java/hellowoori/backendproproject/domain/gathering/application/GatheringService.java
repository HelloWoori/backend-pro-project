package hellowoori.backendproproject.domain.gathering.application;

import hellowoori.backendproproject.domain.gathering.domain.Gathering;
import hellowoori.backendproproject.domain.gathering.domain.GatheringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GatheringService {

    private final GatheringRepository gatheringRepository;

    public Optional<Gathering> findOne(Long gatheringId) {
        return gatheringRepository.findById(gatheringId);
    }

    public String findGatheringName(Long gatheringId) {
        Optional<Gathering> optionalGathering = gatheringRepository.findById(gatheringId);
        return optionalGathering.map(Gathering::getGatheringName).orElse(null);
    }
}
