package hellowoori.backendproproject.domain.community.application;

import hellowoori.backendproproject.domain.article.application.UserServiceClient;
import hellowoori.backendproproject.domain.community.domain.Community;
import hellowoori.backendproproject.domain.community.domain.CommunityRepository;
import hellowoori.backendproproject.domain.community.domain.EMemberJoinStatus;
import hellowoori.backendproproject.domain.community.exception.CommunityNotFoundException;
import hellowoori.backendproproject.domain.community.userinterface.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    private final UserServiceClient userServiceClient;

    public String findCommunityName(Long communityId) {
        Optional<Community> optionalCommunity = communityRepository.findById(communityId);
        return optionalCommunity.map(Community::getCommunityName).orElse(null);
    }

    @Transactional
    public Long createCommunity(CommunityMakeCommand communityMakeCmd) {
        Community community = communityRepository.save(communityMakeCmd.toEntity());
        community.addPresident(userServiceClient.getCurrentUser());
        return community.getId();
    }

    @Transactional
    public void deleteCommunity(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new CommunityNotFoundException(communityId));
        community.validatePresident(userServiceClient.getCurrentUser());
        communityRepository.delete(community);
    }

    @Transactional(readOnly = true)
    public List<CommunityRecommendListDto> getRecommendedCommunities(){
        List<Community> communities = communityRepository.findTop10ByOrderByCreatedAtDesc();
        return communities.stream()
                .map(community -> new CommunityRecommendListDto(
                        community.getId(),
                        community.getCommunityName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityMyListDto> getMyCommunities() {
        List<Community> communities = communityRepository.findByMembersUserIdAndMembersStatus(
                userServiceClient.getCurrentUser().getId(), EMemberJoinStatus.APPROVED);
        return communities.stream()
                .map(community -> new CommunityMyListDto(
                        community.getId(),
                        community.getCommunityName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommunityDetailDto getCommunityDetail(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new CommunityNotFoundException(communityId));
        return new CommunityDetailDto(
                community.getId(),
                community.getCommunityName(),
                community.getIntroduction(),
                community.getJoinStatus(),
                community.getMemberJoinStatus(userServiceClient.getCurrentUser()));
    }

    @Transactional
    public void addMemberToCommunity(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new CommunityNotFoundException(communityId));
        community.addMember(userServiceClient.getCurrentUser());
    }

    // TODO: 아래는 작업해야하는 부분
    public void appointVicePresident() {

    }

    public void revokeVicePresident() {

    }

    public void delegatePresident() {

    }

    public void deleteMember() {

    }

    public void getMembers() {

    }
}
