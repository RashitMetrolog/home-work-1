package ru.itpark.programmatic;


import org.springframework.beans.factory.annotation.Value;

public class ProgrammaticPostService {
    private final ProgrammaticRequestClient client;

    public ProgrammaticPostService(ProgrammaticRequestClient client,
                                   @Value("${jsonPath}") String jsonPath,
                                   @Value("${url}") String url) {
        this.client = client;
        client.setJsonPath(jsonPath, url);
    }

    public ProgrammaticRequestClient getClient() {
        return client;
    }
}
