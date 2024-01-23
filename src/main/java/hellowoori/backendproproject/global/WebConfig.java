package hellowoori.backendproproject.global;

import hellowoori.backendproproject.global.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1); //필터는 체인으로 동작하므로, 순서가 필요하고 낮을수록 먼저 동작함
        filterRegistrationBean.addUrlPatterns("/*"); //필터를 적용할 URL 패턴을 지정함

        return filterRegistrationBean;
    }
}
