package ru.itpark.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Post {
    private String userId;
    private String id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return "Post{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
