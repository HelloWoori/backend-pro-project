package hellowoori.backendproproject.global.interceptor;

import hellowoori.backendproproject.global.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AlreadyLoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("이미 로그인한 사용자 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute(SessionConst.LOGIN_USER) != null) {
            log.info("이미 로그인한 사용자 요청");
            response.sendRedirect("/users/home");
        }

        return true;
    }
}
