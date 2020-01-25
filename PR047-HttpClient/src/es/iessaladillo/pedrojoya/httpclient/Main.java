package es.iessaladillo.pedrojoya.httpclient;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;

@SuppressWarnings("SameParameterValue")
public class Main {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    private final ApiService apiService = new ApiService(httpClient);

    private Main() {
        showPostsSync();
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

    public static void main(String[] args) {
        new Main();
    }

    private static void showError(Throwable e) {
        System.out.println(e.toString());
    }

    private static void showResponse(HttpResponse<String> response) {
        System.out.println(response.statusCode());
        response.headers().map().forEach((key, value) -> System.out.println(key + ": " + String.join(";", value)));
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            String responseBody = response.body();
            if (responseBody != null) {
                System.out.println("\n" + responseBody);
            }
        }
    }

    private void showPostsSync() {
        try {
            HttpResponse<String> httpResponse = apiService.getPostsSync();
            showResponse(httpResponse);
        } catch (InterruptedException | IOException e) {
            showError(e);
        }
    }

    private void showPosts() {
        apiService.getPosts().whenComplete(this::showResponseOrError).join();
    }

    private void showPostsHeaders() {
        apiService.getPostsHeaders().whenComplete(this::showResponseOrError).join();
    }

    private void showPostsAccessOptions() {
        apiService.getPostsAccessOptions().whenComplete(this::showResponseOrError).join();
    }

    private void showPost(int postId) {
        apiService.getPost(postId).whenComplete(this::showResponseOrError).join();
    }

    private void createPost(String jsonPost) {
        apiService.createPost(jsonPost).whenComplete(this::showResponseOrError).join();
    }

    private void updatePost(int postId, String jsonPost) {
        apiService.updatePost(postId, jsonPost).whenComplete(this::showResponseOrError).join();
    }

    private void patchPost(int postId, String jsonPost) {
        apiService.patchPost(postId, jsonPost).whenComplete(this::showResponseOrError).join();
    }

    private void deletePost(int postId) {
        apiService.deletePost(postId).whenComplete(this::showResponseOrError).join();
    }

    private void showUserPosts(int userId) {
        apiService.getUserPosts(userId).whenComplete(this::showResponseOrError).join();
    }

    private void showResponseOrError(HttpResponse<String> httpResponse, Throwable throwable) {
        if (throwable != null) {
            showError(throwable);
        } else {
            showResponse(httpResponse);
        }
    }

}
