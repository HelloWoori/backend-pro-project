package hellowoori.backendproproject.domain.community.domain;

public enum EMemberJoinStatus {
    DEFAULT,  // 미신청
    PENDING,  // 가입신청
    APPROVED; // 가입완료

    public static String toString(EMemberJoinStatus joinStatus) {
        switch (joinStatus) {
            case DEFAULT:
                return "미신청";
            case PENDING:
                return "가입신청";
            case APPROVED:
                return "가입완료";
            default:
                throw new IllegalArgumentException("Unknown MemberJoinStatus: " + joinStatus);
        }
    }
}
