package hellowoori.backendproproject.domain.user.userinterface.controller;

import hellowoori.backendproproject.domain.user.application.UserService;
import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.domain.user.userinterface.dto.UserLoginForm;
import hellowoori.backendproproject.global.argumentresolver.Login;
import hellowoori.backendproproject.global.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/add")
    public String showUserAddForm(@ModelAttribute("user") User user) {
        return "user/userAddForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/userAddForm";
        }
        userService.join(user);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showUserLoginForm(@ModelAttribute("userLoginForm") UserLoginForm form) {
        return "user/userLoginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute UserLoginForm form, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/users/home") String redirectURL,
                          HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "user/userLoginForm";
        }

        User loginUser = userService.login(form.getEmail(), form.getPassword());
        if (loginUser == null) {
            bindingResult.reject("loginFail",  "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/userLoginForm";
        }

        //로그인 성공 처리
        //세션이 있으면, 있는 세션을 반환하고 없으면 신규 세션을 생성해서 반환
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보를 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return "redirect:" + redirectURL;
    }

    @GetMapping("/home")
    public String showHome(
            @Login User loginUser, Model model) {

        //세션에 회원 데이터가 없으면
        if (loginUser == null) {
            return "/";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);
        return "user/home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
