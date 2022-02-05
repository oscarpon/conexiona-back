package com.opbaquero.conexionaback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.swing.*;

@Configuration
public class ThymeleafTemplateConfig implements WebMvcConfigurer {

    @Bean
    public SpringTemplateEngine springTemplateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ClassLoaderTemplateResolver templateResolver(){
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(1);
        resolver.setCheckExistence(true);

        return resolver;
    }

}
