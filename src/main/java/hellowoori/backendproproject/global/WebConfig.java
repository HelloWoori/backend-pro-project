package hellowoori.backendproproject.global;

import hellowoori.backendproproject.global.filter.LogFilter;
import hellowoori.backendproproject.global.filter.LoginCheckFilter;
import hellowoori.backendproproject.global.interceptor.LogInterceptor;
import hellowoori.backendproproject.global.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/users/add", "/users/login", "/users/logout",
                        "/css/**", "/*.ico", "/error"
                );
    }

    //@Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1); //필터는 체인으로 동작하므로, 순서가 필요하고 낮을수록 먼저 동작함
        filterRegistrationBean.addUrlPatterns("/*"); //필터를 적용할 URL 패턴을 지정함

        return filterRegistrationBean;
    }

    //@Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2); //필터는 체인으로 동작하므로, 순서가 필요하고 낮을수록 먼저 동작함
        filterRegistrationBean.addUrlPatterns("/*"); //필터를 적용할 URL 패턴을 지정함

        return filterRegistrationBean;
    }
}
