package es.iessaladillo.pedrojoya.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ApiService {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final HttpClient httpClient;

    public ApiService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse<String> getPostsSync() throws IOException, InterruptedException {
        URI uri = URI.create(BASE_URL + "posts");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.send(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getPosts() {
        URI uri = URI.create(BASE_URL + "posts");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getPostsHeaders() {
        URI uri = URI.create(BASE_URL + "posts");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .method("HEAD", null)
                .uri(uri)
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getPostsAccessOptions() {
        URI uri = URI.create(BASE_URL + "posts");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .method("OPTIONS", null)
                .uri(uri)
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getPost(int postId) {
        URI uri = URI.create(BASE_URL + "posts/" + postId);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> createPost(String jsonPost) {
        URI uri = URI.create(BASE_URL + "posts");
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(jsonPost);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(bodyPublisher)
                .uri(uri)
                .header("Content-Type", "application/json; charset=utf-8")
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> updatePost(int postId, String jsonPost) {
        URI uri = URI.create(BASE_URL + "posts/" + postId);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(jsonPost);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .PUT(bodyPublisher)
                .uri(uri)
                .header("Content-Type", "application/json; charset=utf-8")
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> patchPost(int postId, String jsonPost) {
        URI uri = URI.create(BASE_URL + "posts/" + postId);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(jsonPost);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .method("PATCH", bodyPublisher)
                .uri(uri)
                .header("Content-Type", "application/json; charset=utf-8")
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> deletePost(int postId) {
        URI uri = URI.create(BASE_URL + "posts/" + postId);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .DELETE()
                .uri(uri)
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getUserPosts(int userId) {
        URI uri = URI.create(BASE_URL + "users/" + userId + "/posts");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

}
