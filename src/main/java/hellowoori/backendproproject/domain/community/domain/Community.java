package hellowoori.backendproproject.domain.community.domain;

import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends BaseTimeEntity {

    private static final int MAX_MEMBERS = 50;
    private static final int MAX_VICE_PRESIDENT = 2;
    private static final int MAX_JOINED_COMMUNITIES = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String communityName;

    @Column(length = 150)
    private String introduction;

    @Enumerated(EnumType.STRING)
    private ECommunityJoinStatus joinStatus;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    public Community(String communityName, String introduction, ECommunityJoinStatus joinStatus) {
        this.communityName = communityName;
        this.introduction = introduction;
        this.joinStatus = joinStatus;
    }

    public EMemberJoinStatus getMemberJoinStatus(User user) {
        return members.stream()
                .filter(member -> member.getUser().equals(user))
                .findFirst()
                .map(Member::getStatus)
                .orElse(EMemberJoinStatus.DEFAULT);
    }

    public void addPresident(User user) {
        Member member = new Member(this, user, EMemberRole.PRESIDENT, EMemberJoinStatus.APPROVED);
        members.add(member);
    }

    public Member addMember(User user) {
        this.validateMembersSize();
        Member member = new Member(this, user, EMemberRole.BASIC, this.getMemberJoinStatus());
        members.add(member);
        return member;
    }

    public void removeMember(User user) {
        members.stream()
                .filter(member -> member.getUser().equals(user))
                .findFirst().ifPresent(memberToRemove -> members.remove(memberToRemove));
    }

    public void appointVicePresident(User president, User vicePresident) {
        validatePresident(president);
        validateVicePresidentCount();
        changeMemberRole(vicePresident, EMemberRole.VICE_PRESIDENT);
    }

    public void revokeVicePresident(User user) {
        this.changeMemberRole(user, EMemberRole.BASIC);
    }

    public void delegatePresident(User currPresident, User nextPresident) {
        validatePresident(currPresident);
        changeMemberRole(currPresident, EMemberRole.BASIC);
        changeMemberRole(nextPresident, EMemberRole.PRESIDENT);
    }

    private void changeMemberRole(User user, EMemberRole newRole) {
        Member member = findMemberByUser(user);
        member.setRole(newRole);
    }

    private EMemberJoinStatus getMemberJoinStatus() {
        switch (this.joinStatus) {
            case JOIN_FREE:
                return EMemberJoinStatus.APPROVED;
            case JOIN_APPLY:
                return EMemberJoinStatus.PENDING;
            default:
                throw new IllegalStateException("Unexpected value: " + this.joinStatus);
        }
    }

    private Member findMemberByUser(User user) {
        return members.stream()
                .filter(member -> member.getUser().equals(user))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Member not found for user: " + user.getNickname()));
    }

    private void validateMembersSize() {
        if (MAX_MEMBERS <= members.size()) {
            throw new IllegalStateException("Community is full. cannot add more members.");
        }
    }

    public void validatePresident(User user) {
        Member member = findMemberByUser(user);
        if (member == null || member.getRole() != EMemberRole.PRESIDENT) {
            throw new IllegalStateException("this user isn't president.");
        }
    }

    private void validateVicePresidentCount() {
        long count = members.stream()
                .filter(member -> member.getRole() == EMemberRole.VICE_PRESIDENT)
                .count();
        if (MAX_VICE_PRESIDENT <= count) {
            throw new IllegalStateException("Cannot appoint more than two vice-president.");
        }
    }
}
