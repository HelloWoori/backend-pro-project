package hellowoori.backendproproject.global.interceptor;

import hellowoori.backendproproject.global.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //참고) 인증은 컨트롤러 호출 전에만 호출되면 되기 때문에 preHandle 만 구현하면 된다
        //참고) default 는 자바8에 도입된 것으로, 기본 구현이 된 인터페이스이므로 구현하지 않아도 OK
        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            log.info("미인증 사용자 요청");
            //로그인으로 redirect
            response.sendRedirect("/users/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
