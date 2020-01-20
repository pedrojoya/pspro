package es.iessaladillo.pedrojoya.okhttp;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("SameParameterValue")
public class Main {

    // 1. Create Http client
    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            // 1.1. Request timeouts.
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .build();

    public static void main(String[] args) throws IOException {
//        showPosts();
        showPostsWithCallback();
        showPostsAsync();
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
        // 2. Create http request
        Request request = new Request.Builder()
                // 2.1. URL
                .url(url)
                // 2.2. Request method.
                .get()
                .build();
        // 3. Create a new Call with that request.
        Call call = okHttpClient.newCall(request);
        // 4. Execute call and obtain a response (blocking)
        try (Response response = call.execute()) {
            // 5. Process response
            showResponse(response);
        } catch (IOException e) {
            // 6. Process failure.
            showError(e);
            throw e;
        }
    }

    private static void showPostsWithCallback() throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                showError(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                showResponse(response);
            }
        });
    }

    private static void showPostsAsync() throws MalformedURLException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        showResponseOrError(call);
    }

    private static void showPostsAccessOptions() throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        Request request = new Request.Builder()
                .url(url)
                .method("OPTIONS", null)
                .build();
        Call call = okHttpClient.newCall(request);
        showResponseOrError(call);
    }

    private static void showPostsHeaders() throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        Request request = new Request.Builder()
                .url(url)
                .head()
                .build();
        Call call = okHttpClient.newCall(request);
        showResponseOrError(call);
    }

    private static void showPost(int postId) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + postId);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        showResponseOrError(call);
    }

    private static void createPost(String jsonPost) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        RequestBody requestBody = RequestBody.create(jsonPost, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        showResponseOrError(call);
    }

    private static void updatePost(int postId, String jsonPost) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + postId);
        RequestBody requestBody = RequestBody.create(jsonPost, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        showResponseOrError(call);
    }

    private static void patchPost(int postId, String jsonPost) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + postId);
        RequestBody requestBody = RequestBody.create(jsonPost, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("X-HTTP-Method-Override", "PATCH")
                .build();
        Call call = okHttpClient.newCall(request);
        showResponseOrError(call);
    }

    private static void deletePost(int postId) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + postId);
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        Call call = okHttpClient.newCall(request);
        showResponseOrError(call);
    }

    private static void showUserPosts(int userId) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/users/" + userId + "/posts");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        showResponseOrError(call);
    }

    // ---------------------------------------------------------

    private static void showResponseOrError(Call call) {
        executeAsync(call).whenComplete((response, throwable) -> {
            if (throwable != null) {
                showError(throwable);
            }
        }).thenAccept((Main::showResponse));
    }

    private static CompletableFuture<Response> executeAsync(Call call) {
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

    private static void showResponse(Response response) {
        // 4.1. Response code
        System.out.print(response.code());
        // 4.2. Response message
        System.out.println(" " + response.message());
        // 4.2. Response headers.
        response.headers().forEach(header -> System.out.println(header.component1() + ": " + header.component2()));
        if (response.isSuccessful()) {
            // 4.3 Response body
            try {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    System.out.println("\n" + responseBody.string());
                }
            } catch (IOException ignored) {
            }
        }
    }

    private static void showError(Throwable e) {
        System.out.println(e.toString());
    }

}
