package es.iessaladillo.pedrojoya.okhttp;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("SameParameterValue")
public class Main {

    public static void main(String[] args) {
        new Main();
    }

    // 1. Create Http client.
    private final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            // 1.1. Request timeouts.
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .build();
    private final ApiService apiService = new ApiService(okHttpClient);
    private final Scanner scanner = new Scanner(System.in);

    Main() {
        int selectedOption = showMenu();
        processOption(selectedOption);
        okHttpClient.connectionPool().evictAll();
        okHttpClient.dispatcher().executorService().shutdownNow();
    }

    private int showMenu() {
        System.out.println("\nMENU");
        System.out.println("1. Show all posts synchronously");
        System.out.println("2. Show all posts with callback");
        System.out.println("3. Show all posts");
        System.out.println("4. Show post");
        System.out.println("5. Show user's posts");
        System.out.println("6. Create post");
        System.out.println("7. Update post");
        System.out.println("8. Update post partially");
        System.out.println("9. Delete post");
        System.out.println("10. Show only headers");
        System.out.println("11. Show access options");
        System.out.print("Select an option: ");
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return 0;
        }
    }

    private static void showResponse(Response response) {
        // 4.1. Response code.
        System.out.print(response.code());
        // 4.2. Response message.
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

    private void processOption(int selectedOption) {
        switch (selectedOption) {
            case 1:
                showPostsSync();
                break;
            case 2:
                showPostsWithCallback();
                break;
            case 3:
                showPosts();
                break;
            case 4:
                showPost(1);
                break;
            case 5:
                showUserPosts(1);
                break;
            case 6:
                createPost("{\"title\": \"Baldomero\", \"body\": \"Llegate Ligero\", \"userId\": 1}");
                break;
            case 7:
                updatePost(1, "{\"id\": 1, \"title\": \"Baldomero\", \"body\": \"Llegate Ligero\", \"userId\": 1}");
                break;
            case 8:
                patchPost(1, "{\"title\": \"Baldomero\"}");
                break;
            case 9:
                deletePost(1);
                break;
            case 10:
                showPostsHeaders();
                break;
            case 11:
                showPostsAccessOptions();
                break;
        }
    }

    private void showPostsSync() {
        try {
            Response response = apiService.getPostsSync();
            showResponse(response);
        } catch (IOException e) {
            showError(e);
        }
    }

    private void showPostsWithCallback() {
        apiService.getPostsWithCallback(new Callback() {
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

    private void showPosts() {
        apiService.getPosts().whenComplete(this::showResponseOrError).join();
    }

    private void showPostsAccessOptions() {
        apiService.getPostsAccessOptions().whenComplete(this::showResponseOrError).join();
    }

    private void showPostsHeaders() {
        apiService.getPostsHeaders().whenComplete(this::showResponseOrError).join();
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

    // ---------------------------------------------------------

    private void showResponseOrError(Response response, Throwable throwable) {
        if (throwable != null) {
            showError(throwable);
        } else {
            showResponse(response);
        }
    }

    private void showError(Throwable e) {
        System.out.println(e.toString());
    }

}
