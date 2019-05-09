package ru.itpark.groovy;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itpark.annotationConf.AnnotationRequestClient;


public class GroovyPostService {

    private final GroovyRequestClient client;

    public GroovyPostService(GroovyRequestClient client,
                             @Value("${jsonPath}") String jsonPath,
                             @Value("${url}") String url) {
        this.client = client;
        client.setJsonPath(jsonPath, url);
    }

    public GroovyRequestClient getClient() {
        return client;
    }
}
