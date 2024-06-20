package vn.mobileid.voucherapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.mobileid.voucherapp.filter.RequestAndResponseLoggingFilter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://wso2am.mobile-id.vn", "https://api.mobile-id.vn", "http://localhost:8080")
                .allowedMethods("GET", "POST")
                .allowedHeaders("header1", "header2", "header3")
                .exposedHeaders("header1", "header2")
                .allowCredentials(true).maxAge(3600);
    }

    @Bean
    public RequestAndResponseLoggingFilter requestAndResponseLoggingFilter() {
        return new RequestAndResponseLoggingFilter();
    }
}
