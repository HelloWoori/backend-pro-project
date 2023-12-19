package hellowoori.backendproproject.domain.gathering.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Gathering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gatheringId;

    @Column(nullable = false, length = 10)
    private String gatheringName;

    @Column(length = 150)
    private String introduction;

    public Gathering(String gatheringName, String introduction) {
        this.gatheringName = gatheringName;
        this.introduction = introduction;
    }
}
