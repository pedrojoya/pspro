package es.iessaladillo.pedrojoya.httpurlconnection;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@SuppressWarnings("SameParameterValue")
public class Main {

    public static void main(String[] args) throws IOException {
        showPosts();
        showPostsHeaders();
        showPostsAccessOptions();
        showPost(1);
        createPost("{\"title\": \"Baldomero\", \"body\": \"Llegate Ligero\", \"userId\": 1}");
        updatePost(1, "{\"id\": 1, \"title\": \"Baldomero\", \"body\": \"Llegate Ligero\", \"userId\": 1}");
        patchPost(1, "{\"title\": \"Baldomero\"}");
        deletePost(1);
        showUserPosts(1);
    }

    private static void showPosts() throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpUtils.doHttpRequest(url, "GET", null, null, 5000);
    }

    private static void showPost(int postId) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + postId);
        HttpUtils.doHttpRequest(url, "GET", null, null, 5000);
    }

    private static void createPost(String jsonPost) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpUtils.doHttpRequest(url, "POST",
                Map.of("Content-type", "application/json; charset=UTF-8"),
                jsonPost.getBytes(), 5000);
    }

    private static void updatePost(int postId, String jsonPost) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + postId);
        HttpUtils.doHttpRequest(url, "PUT",
                Map.of("Content-type", "application/json; charset=UTF-8"),
                jsonPost.getBytes(), 5000);
    }

    private static void patchPost(int postId, String jsonPost) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + postId);
        HttpUtils.doHttpRequest(url, "PATCH",
                Map.of("Content-type", "application/json; charset=UTF-8"),
                jsonPost.getBytes(), 5000);
    }

    private static void deletePost(int postId) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + postId);
        HttpUtils.doHttpRequest(url, "DELETE", null, null, 5000);
    }

    private static void showUserPosts(int userId) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/users/" + userId + "/posts");
        HttpUtils.doHttpRequest(url, "GET", null, null, 5000);
    }

    private static void showPostsHeaders() throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpUtils.doHttpRequest(url, "HEAD", null, null, 5000);
    }

    private static void showPostsAccessOptions() throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpUtils.doHttpRequest(url, "OPTIONS", null, null, 5000);
    }

}
