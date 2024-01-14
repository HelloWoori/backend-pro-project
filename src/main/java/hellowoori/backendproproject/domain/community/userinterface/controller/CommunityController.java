package hellowoori.backendproproject.domain.community.userinterface.controller;

import hellowoori.backendproproject.domain.community.application.CommunityService;
import hellowoori.backendproproject.domain.community.userinterface.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/communities")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public String showCommunityMakeForm() {
        return "community/communityMakeForm";
    }

    @PostMapping
    public String makeCommunity(@ModelAttribute CommunityMakeRequest communityMakeReq) {
        CommunityMakeCommand communityMakeCmd = communityMakeReq.toCommand();
        Long communityId = communityService.createCommunity(communityMakeCmd);
        return "redirect:/communities/" + communityId + "/articles";
    }

    @PostMapping("/{communityId}/delete")
    public String removeCommunity(@PathVariable Long communityId) {
        communityService.deleteCommunity(communityId);
        return "redirect:/users/home";
    }

    @GetMapping("/recommend")
    public String showRecommendCommunities(Model model) {
        List<CommunityRecommendListDto> communities = communityService.getRecommendedCommunities();
        model.addAttribute("communities", communities);
        return "community/communityRecommendList";
    }

    @GetMapping("/my")
    public String showMyCommunities(Model model) {
        List<CommunityMyListDto> myListDtoList = communityService.getMyCommunities();
        List<CommunityMyListResponse> myCommunities = CommunityMyListResponse.of(myListDtoList);
        model.addAttribute("myCommunities", myCommunities);
        return "community/communityMyList";
    }

    @GetMapping("/{communityId}")
    public String showCommunityDetail(@PathVariable Long communityId, Model model) {
        CommunityDetailDto communityDetailDto = communityService.getCommunityDetail(communityId);
        CommunityDetailResponse community = CommunityDetailResponse.of(communityDetailDto);
        model.addAttribute("community", community);
        return "community/communityDetail";
    }

    @PostMapping("/{communityId}/apply")
    public String applyToJoinCommunity(@PathVariable Long communityId) {
        communityService.addMemberToCommunity(communityId);
        return "redirect:/communities/{communityId}";
    }

    // TODO: 아래는 작업해야하는 부분
    public String acceptJoinCommunity() {
        return "";
    }

    public String appointVicePresident() {
        return "";
    }

    public String revokeVicePresident() {
        return "";
    }

    public String delegatePresident() {
        return "";
    }

    public String kickMemberOut() {
        return "";
    }
}
