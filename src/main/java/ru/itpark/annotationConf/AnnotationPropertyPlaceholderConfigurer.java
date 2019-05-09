package ru.itpark.annotationConf;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.itpark.util.CustomPropertyPlaceholderConfigurer;

@Component
public class AnnotationPropertyPlaceholderConfigurer extends CustomPropertyPlaceholderConfigurer {
    public AnnotationPropertyPlaceholderConfigurer() {
        setLocations("application.json");
    }
}
