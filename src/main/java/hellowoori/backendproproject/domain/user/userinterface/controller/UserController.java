package hellowoori.backendproproject.domain.user.userinterface.controller;

import hellowoori.backendproproject.domain.user.application.UserService;
import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.domain.user.userinterface.dto.UserLoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    public String login(@Validated @ModelAttribute UserLoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "user/userLoginForm";
        }

        User loginUser = userService.login(form.getEmail(), form.getPassword());
        if (loginUser == null) {
            bindingResult.reject("loginFail",  "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/userLoginForm";
        }

        // 쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료 시 모두 종료)
        Cookie cookie = new Cookie("userId", String.valueOf(loginUser.getId()));
        response.addCookie(cookie);
        return "redirect:/users/home";
    }

    @GetMapping("/home")
    public String showHome(@CookieValue(name="userId", required = false) UUID userId, Model model) {
        if (userId == null) {
            return "/";
        }

        User loginUser = userService.findOne(userId);
        if (loginUser == null) {
            return "/";
        }

        model.addAttribute("user", loginUser);
        return "user/home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expiredCookie(response, "userId");
        return "redirect:/";
    }

    private void expiredCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
