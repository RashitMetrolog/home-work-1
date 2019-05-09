package ru.itpark.java;


        import org.springframework.beans.factory.annotation.Value;

public class JavaPostService {
    private final JavaRequestClient client;

    public JavaPostService(JavaRequestClient client,
                           @Value("${jsonPath}") String jsonPath,
                           @Value("${url}") String url) {
        this.client = client;
        client.setJsonPath(jsonPath, url);
    }

    public JavaRequestClient getClient() {
        return client;
    }
}
