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
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + "posts"))
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.send(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getPosts() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + "posts"))
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getPostsHeaders() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(BASE_URL + "posts"))
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getPostsAccessOptions() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .method("OPTIONS", HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(BASE_URL + "posts"))
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getPost(int postId) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + "posts/" + postId))
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> createPost(String jsonPost) {
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(jsonPost);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(bodyPublisher)
                .uri(URI.create(BASE_URL + "posts"))
                .header("Content-Type", "application/json; charset=utf-8")
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> updatePost(int postId, String jsonPost) {
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(jsonPost);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .PUT(bodyPublisher)
                .uri(URI.create(BASE_URL + "posts/" + postId))
                .header("Content-Type", "application/json; charset=utf-8")
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> patchPost(int postId, String jsonPost) {
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(jsonPost);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .method("PATCH", bodyPublisher)
                .uri(URI.create(BASE_URL + "posts/" + postId))
                .header("Content-Type", "application/json; charset=utf-8")
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> deletePost(int postId) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(BASE_URL + "posts/" + postId))
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

    public CompletableFuture<HttpResponse<String>> getUserPosts(int userId) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + "users/" + userId + "/posts"))
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        return httpClient.sendAsync(httpRequest, bodyHandler);
    }

}
