package es.iessaladillo.pedrojoya.okhttp;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class ApiService {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final OkHttpClient okHttpClient;

    public ApiService(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public Response getPostsSync() throws IOException {
        URL url = buildUrl(BASE_URL + "posts");
        // 2. Create http request.
        Request request = new Request.Builder()
                // 2.1. URL
                .url(url)
                // 2.2. Request method.
                .get()
                .build();
        // 3. Create a new Call with that request.
        Call call = okHttpClient.newCall(request);
        // 4. Execute call and obtain a response (blocking)
        return call.execute();
    }

    public void getPostsWithCallback(Callback callback) {
        URL url = buildUrl(BASE_URL + "posts");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public CompletableFuture<Response> getPosts() {
        URL url = buildUrl(BASE_URL + "posts");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> getPostsAccessOptions() {
        URL url = buildUrl(BASE_URL + "posts");
        Request request = new Request.Builder()
                .url(url)
                .method("OPTIONS", null)
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> getPostsHeaders() {
        URL url = buildUrl(BASE_URL + "posts");
        Request request = new Request.Builder()
                .url(url)
                .head()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> getPost(int postId) {
        URL url = buildUrl(BASE_URL + "posts/" + postId);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> createPost(String jsonPost) {
        URL url = buildUrl(BASE_URL + "posts");
        RequestBody requestBody = RequestBody.create(jsonPost, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> updatePost(int postId, String jsonPost) {
        URL url = buildUrl(BASE_URL + "posts/" + postId);
        RequestBody requestBody = RequestBody.create(jsonPost, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> patchPost(int postId, String jsonPost) {
        URL url = buildUrl(BASE_URL + "posts/" + postId);
        RequestBody requestBody = RequestBody.create(jsonPost, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("X-HTTP-Method-Override", "PATCH")
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> deletePost(int postId) {
        URL url = buildUrl(BASE_URL + "posts/" + postId);
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> getUserPosts(int userId) {
        URL url = buildUrl(BASE_URL + "users/" + userId + "/posts");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    // ---------------------------------------------------------

    @NotNull
    private URL buildUrl(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed URL");
        }
        return url;
    }

    private CompletableFuture<Response> executeAsync(Call call) {
        CompletableFuture<Response> cf = new CompletableFuture<>();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                cf.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                cf.complete(response);
            }
        });
        return cf;
    }

}
