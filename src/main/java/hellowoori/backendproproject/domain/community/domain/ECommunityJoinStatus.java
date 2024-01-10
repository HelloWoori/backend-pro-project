package hellowoori.backendproproject.domain.community.domain;

public enum ECommunityJoinStatus {
    JOIN_FREE,  // 자유가입
    JOIN_APPLY; // 신청가입

    public static String toString(ECommunityJoinStatus joinStatus) {
        switch (joinStatus) {
            case JOIN_FREE:
                return "자유 가입";
            case JOIN_APPLY:
                return "신청 가입";
            default:
                throw new IllegalArgumentException("Unknown CommunityJoinStatus: " + joinStatus);
        }
    }
}
