package hellowoori.backendproproject.domain.community.userinterface.dto;

import hellowoori.backendproproject.domain.community.domain.ECommunityJoinStatus;
import hellowoori.backendproproject.domain.community.domain.EMemberJoinStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityDetailDto {

    private Long communityId;
    private String communityName;
    private String introduction;
    private ECommunityJoinStatus communityJoinStatus;
    private EMemberJoinStatus memberJoinStatus;

    public CommunityDetailDto(
            Long communityId,
            String communityName,
            String introduction,
            ECommunityJoinStatus communityJoinStatus,
            EMemberJoinStatus memberJoinStatus) {
        this.communityId = communityId;
        this.communityName = communityName;
        this.introduction = introduction;
        this.communityJoinStatus = communityJoinStatus;
        this.memberJoinStatus = memberJoinStatus;
    }
}
