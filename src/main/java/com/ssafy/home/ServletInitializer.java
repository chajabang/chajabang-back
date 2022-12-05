package com.ssafy.home;

import com.ssafy.home.interceptor.MyInterceptor;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ServletInitializer extends SpringBootServletInitializer implements WebMvcConfigurer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WhereIsMyHomeApplication.class);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/test/a") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .excludePathPatterns("/test/b"); // 해당 경로는 인터셉터가 가로채지 않는다.
    }

}
