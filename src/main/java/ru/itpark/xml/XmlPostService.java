package ru.itpark.xml;


public class XmlPostService {
    private final XmlRequestClient client;

    public XmlPostService(XmlRequestClient client) {
        this.client = client;
    }

    public XmlRequestClient getClient() {
        return client;
    }
}
