package es.iessaladillo.pedrojoya.okhttp;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ApiService {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final OkHttpClient okHttpClient;

    public ApiService(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public Response getPostsSync() throws IOException {
        // 2. Create http request.
        Request request = new Request.Builder()
                // 2.1. URL
                .url(BASE_URL + "posts")
                // 2.2. Request method.
                .get()
                .build();
        // 3. Create a new Call with that request.
        Call call = okHttpClient.newCall(request);
        // 4. Execute call and obtain a response (blocking)
        return call.execute();
    }

    public void getPostsWithCallback(Callback callback) {
        Request request = new Request.Builder()
                .url(BASE_URL + "posts")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public CompletableFuture<Response> getPosts() {
        Request request = new Request.Builder()
                .url(BASE_URL + "posts")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> getPostsAccessOptions() {
        Request request = new Request.Builder()
                .url(BASE_URL + "posts")
                .method("OPTIONS", null)
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> getPostsHeaders() {
        Request request = new Request.Builder()
                .url(BASE_URL + "posts")
                .head()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> getPost(int postId) {
        Request request = new Request.Builder()
                .url(BASE_URL + "posts/" + postId)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> createPost(String jsonPost) {
        RequestBody requestBody = RequestBody.create(jsonPost, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(BASE_URL + "posts")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> updatePost(int postId, String jsonPost) {
        RequestBody requestBody = RequestBody.create(jsonPost, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(BASE_URL + "posts/" + postId)
                .put(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> patchPost(int postId, String jsonPost) {
        RequestBody requestBody = RequestBody.create(jsonPost, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(BASE_URL + "posts/" + postId)
                .post(requestBody)
                .addHeader("X-HTTP-Method-Override", "PATCH")
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> deletePost(int postId) {
        Request request = new Request.Builder()
                .url(BASE_URL + "posts/" + postId)
                .delete()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    public CompletableFuture<Response> getUserPosts(int userId) {
        Request request = new Request.Builder()
                .url(BASE_URL + "users/" + userId + "/posts")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        return executeAsync(call);
    }

    // ---------------------------------------------------------

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
