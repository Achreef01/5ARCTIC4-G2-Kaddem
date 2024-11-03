package tn.esprit.spring.kaddem.configurations;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // NOSONAR
                .allowedOrigins("http://192.168.33.10:4200") // NOSONAR
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // NOSONAR
                .allowedHeaders("*") // NOSONAR
                .allowCredentials(true); // NOSONAR

    }
}
