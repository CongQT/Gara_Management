package com.example.gara_management.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

import java.nio.charset.StandardCharsets;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

@Configuration
public class TemplateConfig {

    @Bean
    @Primary
    public SpringResourceTemplateResolver htmlTemplateResolver() {
        return new SpringResourceTemplateResolver() {{
            setPrefix("classpath:/templates/html/");
            setSuffix(".html");
            setTemplateMode(HTML);
            setCharacterEncoding(StandardCharsets.UTF_8.name());
        }};
    }

    @Bean
    public SpringTemplateEngine templateEngine(
            MessageSource messageSource,
            SpringResourceTemplateResolver templateResolver
    ) {
        return new SpringTemplateEngine() {{
            setMessageSource(messageSource);
            setTemplateResolver(templateResolver);
        }};
    }

}
