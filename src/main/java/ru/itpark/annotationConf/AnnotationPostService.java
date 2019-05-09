package ru.itpark.annotationConf;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("postService")
public class AnnotationPostService {

    private final AnnotationRequestClient client;

    public AnnotationPostService(AnnotationRequestClient client,
                                 @Value("${jsonPath}") String jsonPath,
                                 @Value("${url}") String url) {
        this.client = client;
        client.setJsonPath(jsonPath, url);
    }

    public AnnotationRequestClient getClient() {
        return client;
    }
}
