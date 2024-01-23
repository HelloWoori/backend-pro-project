package hellowoori.backendproproject.global;

import hellowoori.backendproproject.global.argumentresolver.LoginUserArgumentResolver;
import hellowoori.backendproproject.global.interceptor.AlreadyLoginCheckInterceptor;
import hellowoori.backendproproject.global.interceptor.LogInterceptor;
import hellowoori.backendproproject.global.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new AlreadyLoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/", "/users/add", "/users/login");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(3)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/users/add", "/users/login", "/users/logout",
                        "/css/**", "/*.ico", "/error"
                );
    }
}
