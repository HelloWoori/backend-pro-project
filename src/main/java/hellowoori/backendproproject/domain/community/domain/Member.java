package hellowoori.backendproproject.domain.community.domain;

import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = true)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "communityId", nullable = false)
    private Community community;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private EMemberRole role;

    @Enumerated(EnumType.STRING)
    private EMemberJoinStatus status;

    public Member(Community community, User user, EMemberRole role, EMemberJoinStatus status) {
        this.community = community;
        this.user = user;
        this.role = role;
        this.status = status;
    }

    public void setRole(EMemberRole role) {
        this.role = role;
    }
}
