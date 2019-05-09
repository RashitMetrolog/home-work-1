package ru.itpark.java;

import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ru.itpark.util.CustomPropertyPlaceholderConfigurer;

@Configuration
public class JavaConfiguration {

    @Bean
    public static CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
        CustomPropertyPlaceholderConfigurer configurer = new CustomPropertyPlaceholderConfigurer();
        configurer.setLocations("application.json");
        return configurer;
    }

    @Bean
    public JavaRequestClient requestClient(){
        return new JavaRequestClient();
    }

    @Bean
    public JavaCachedAnnotationBPP cachedAnnotationBPP(){
        return new JavaCachedAnnotationBPP();
    }

    @Bean
    public JavaPostService postService(JavaRequestClient client,
                                       @Value("${jsonPath}") String jsonPath,
                                       @Value("${url}") String url){
        return new JavaPostService(client, jsonPath, url);
    }





}
