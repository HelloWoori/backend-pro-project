package hellowoori.backendproproject.domain.community.userinterface.dto;

import hellowoori.backendproproject.domain.community.domain.ECommunityJoinStatus;
import hellowoori.backendproproject.domain.community.domain.EMemberJoinStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityDetailResponse {

    private Long communityId;
    private String communityName;
    private String introduction;
    private String communityJoinStatusStr;
    private String memberJoinStatusStr;

    public CommunityDetailResponse(
            Long communityId,
            String communityName,
            String introduction,
            ECommunityJoinStatus communityJoinStatus,
            EMemberJoinStatus memberJoinStatus) {
        this.communityId = communityId;
        this.communityName = communityName;
        this.introduction = introduction;
        this.communityJoinStatusStr = ECommunityJoinStatus.toString(communityJoinStatus);
        this.memberJoinStatusStr = EMemberJoinStatus.toString(memberJoinStatus);
    }

    public static CommunityDetailResponse of(CommunityDetailDto communityDetailDto) {
        return new CommunityDetailResponse(
                communityDetailDto.getCommunityId(),
                communityDetailDto.getCommunityName(),
                communityDetailDto.getIntroduction(),
                communityDetailDto.getCommunityJoinStatus(),
                communityDetailDto.getMemberJoinStatus()
        );
    }
}
