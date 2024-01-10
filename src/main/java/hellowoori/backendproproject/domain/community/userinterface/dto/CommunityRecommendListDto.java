package hellowoori.backendproproject.domain.community.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityRecommendListDto {

    private Long communityId;
    private String communityName;

    public CommunityRecommendListDto(Long communityId, String communityName) {
        this.communityId = communityId;
        this.communityName = communityName;
    }
}
