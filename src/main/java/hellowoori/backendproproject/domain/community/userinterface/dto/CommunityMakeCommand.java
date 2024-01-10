package hellowoori.backendproproject.domain.community.userinterface.dto;

import hellowoori.backendproproject.domain.community.domain.Community;
import hellowoori.backendproproject.domain.community.domain.ECommunityJoinStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityMakeCommand {

    private String name;
    private String introduction;
    private ECommunityJoinStatus joinStatus;

    public CommunityMakeCommand(String name, String introduction, String joinStatus) {
        this.name = name;
        this.introduction = introduction;
        this.joinStatus = this.mapToCommunityJoinStatus(joinStatus);
    }

    public Community toEntity() {
        return new Community(this.name, this.introduction, this.joinStatus);
    }

    private ECommunityJoinStatus mapToCommunityJoinStatus(String joinStatus) {
        return ECommunityJoinStatus.valueOf(joinStatus);
    }
}
