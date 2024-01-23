package hellowoori.backendproproject.domain.user.userinterface.controller;

import hellowoori.backendproproject.domain.user.application.UserService;
import hellowoori.backendproproject.domain.user.domain.User;
import hellowoori.backendproproject.domain.user.userinterface.dto.UserLoginForm;
import hellowoori.backendproproject.global.argumentresolver.Login;
import hellowoori.backendproproject.global.session.SessionConst;
import hellowoori.backendproproject.global.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SessionManager sessionManager;

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

    //@PostMapping("/login")
    public String login(@Validated @ModelAttribute UserLoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "user/userLoginForm";
        }

        User loginUser = userService.login(form.getEmail(), form.getPassword());
        if (loginUser == null) {
            bindingResult.reject("loginFail",  "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/userLoginForm";
        }

        //쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료 시 모두 종료)
        Cookie cookie = new Cookie("userId", String.valueOf(loginUser.getId()));
        response.addCookie(cookie);
        return "redirect:/users/home";
    }

    //@PostMapping("/login")
    public String loginV2(@Validated @ModelAttribute UserLoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "user/userLoginForm";
        }

        User loginUser = userService.login(form.getEmail(), form.getPassword());
        if (loginUser == null) {
            bindingResult.reject("loginFail",  "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/userLoginForm";
        }

        //세션 관리자를 통해 세션을 생성하고, 회원 데이터 보관
        sessionManager.createSession(loginUser, response);

        return "redirect:/users/home";
    }

    //@PostMapping("/login")
    public String loginV3(@Validated @ModelAttribute UserLoginForm form, BindingResult bindingResult, HttpServletRequest request) {
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

        return "redirect:/users/home";
    }

    @PostMapping("/login")
    public String loginV4(@Validated @ModelAttribute UserLoginForm form, BindingResult bindingResult,
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

    //@GetMapping("/home")
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

    //@GetMapping("/home")
    public String showHomeV2(HttpServletRequest request, Model model) {

        //세션 관리자에 저장된 회원 정보 조회
        User loginUser = (User)sessionManager.getSession(request);

        if (loginUser == null) {
            return "/";
        }

        model.addAttribute("user", loginUser);
        return "user/home";
    }

    //@GetMapping("/home")
    public String showHomeV3(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false); //세션은 메모리를 사용하기 때문에 꼭 필요할 때만 생성해야한다
        if (session == null) {
            return "/";
        }

        User loginUser = (User)session.getAttribute(SessionConst.LOGIN_USER);

        //세션에 회원 데이터가 없으면
        if (loginUser == null) {
            return "/";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);
        return "user/home";
    }

    //@GetMapping("/home")
    public String showHomeV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {

        //세션에 회원 데이터가 없으면
        if (loginUser == null) {
            return "/";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);
        return "user/home";
    }

    @GetMapping("/home")
    public String showHomeV3ArgumentResolver(
            @Login User loginUser, Model model) {

        //세션에 회원 데이터가 없으면
        if (loginUser == null) {
            return "/";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);
        return "user/home";
    }

    //@PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expiredCookie(response, "userId");
        return "redirect:/";
    }

    //@PostMapping("/logout")
    public String logoutV2(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    private void expiredCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
