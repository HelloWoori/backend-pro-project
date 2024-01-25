package hellowoori.backendproproject.domain.community.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findTop10ByOrderByCreatedAtDesc();
    List<Community> findByMembersUserIdAndMembersStatus(UUID userId, EMemberJoinStatus memberJoinStatus);
}
