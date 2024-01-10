package hellowoori.backendproproject.domain.community.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommunityMyListResponse {

    private Long communityId;
    private String communityName;

    public CommunityMyListResponse(Long communityId, String communityName) {
        this.communityId = communityId;
        this.communityName = communityName;
    }

    public static List<CommunityMyListResponse> of(List<CommunityMyListDto> communityMyListDtoList) {
        return communityMyListDtoList.stream()
                .map(community -> new CommunityMyListResponse(
                        community.getCommunityId(),
                        community.getCommunityName()))
                .collect(Collectors.toList());
    }
}
