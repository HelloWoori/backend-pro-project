package hellowoori.backendproproject.domain.community.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityMakeRequest {

    private String name;
    private String introduction;
    private String joinStatus;

    public CommunityMakeRequest(String name, String introduction, String joinStatus) {
        this.name = name;
        this.introduction = introduction;
        this.joinStatus = joinStatus;
    }

    public CommunityMakeCommand toCommand() {
        return new CommunityMakeCommand(this.name, this.introduction, this.joinStatus);
    }
}
