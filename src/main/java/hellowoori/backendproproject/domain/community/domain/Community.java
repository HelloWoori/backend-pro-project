package hellowoori.backendproproject.domain.community.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String communityName;

    @Column(length = 150)
    private String introduction;

    public Community(String communityName, String introduction) {
        this.communityName = communityName;
        this.introduction = introduction;
    }
}
