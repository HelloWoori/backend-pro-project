package hellowoori.backendproproject.domain.community.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityMyListDto {

    private Long communityId;
    private String communityName;

    public CommunityMyListDto(Long communityId, String communityName) {
        this.communityId = communityId;
        this.communityName = communityName;
    }
}
