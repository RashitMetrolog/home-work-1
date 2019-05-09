package ru.itpark.java;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.itpark.annotation.Cached;
import ru.itpark.domain.Post;
import ru.itpark.exception.NotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JavaRequestClient {

    private String jsonPath;
    private String url;

    public JavaRequestClient() {
    }

    void setJsonPath(String jsonPath, String url) {
        this.jsonPath = jsonPath;
        this.url = url;
    }

    public void printURL() {
        System.out.println(url);;
    }

    private List<Post> getAll() {
        JSONParser parser = new JSONParser();

        List<Post> listOfPosts = new ArrayList<Post>();
        {
            try {
                Object obj = parser.parse(new FileReader(jsonPath));
                JSONArray jsonArray = (JSONArray) obj;

                for (Object o : jsonArray) {
                    Post post = new Post();

                    JSONObject jsonObject = (JSONObject) o;
                    String userId = jsonObject.get("userId").toString();
                    post.setUserId(userId);
                    String id = jsonObject.get("id").toString();
                    post.setId(id);
                    String title = (String) jsonObject.get("title");
                    post.setTitle(title);
                    StringBuilder body = new StringBuilder((String) jsonObject.get("body"));
                    String[] strings = body.toString().split("\n");
                    body = new StringBuilder();
                    body.append(strings[0]);
                    for (int i = 1; i < strings.length; i++) {
                        body.append(" ").append(strings[i]);
                    }
                    post.setBody(body.toString());
                    listOfPosts.add(post);

                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return listOfPosts;
    }

    @Cached
    public String getPost(String id) {
        List<Post> posts = getAll();
        for (Post post : posts) {
           if (post.getId().equals(id)) {
               return post.toString();
           }
        }
        throw new NotFoundException("Пост с id = " +id+ " не найден");
    }
}
