package es.iessaladillo.pedrojoya.httpclient;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

@SuppressWarnings("SameParameterValue")
public class Main {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    private final ApiService apiService = new ApiService(httpClient);
    private final Scanner scanner = new Scanner(System.in);

    Main() {
        int selectedOption = showMenu();
        processOption(selectedOption);
    }

    public static void main(String[] args) {
        new Main();
    }

    private int showMenu() {
        System.out.println("\nMENU");
        System.out.println("1. Show all posts synchronously");
        System.out.println("2. Show all posts");
        System.out.println("3. Show post");
        System.out.println("4. Show user's posts");
        System.out.println("5. Create post");
        System.out.println("6. Update post");
        System.out.println("7. Update post partially");
        System.out.println("8. Delete post");
        System.out.println("9. Show only headers");
        System.out.println("10. Show access options");
        System.out.print("Select an option: ");
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return 0;
        }
    }

    private void processOption(int selectedOption) {
        switch (selectedOption) {
            case 1:
                showPostsSync();
                break;
            case 2:
                showPosts();
                break;
            case 3:
                showPost(1);
                break;
            case 4:
                showUserPosts(1);
                break;
            case 5:
                createPost("{\"title\": \"Baldomero\", \"body\": \"Llegate Ligero\", \"userId\": 1}");
                break;
            case 6:
                updatePost(1, "{\"id\": 1, \"title\": \"Baldomero\", \"body\": \"Llegate Ligero\", \"userId\": 1}");
                break;
            case 7:
                patchPost(1, "{\"title\": \"Baldomero\"}");
                break;
            case 8:
                deletePost(1);
                break;
            case 9:
                showPostsHeaders();
                break;
            case 10:
                showPostsAccessOptions();
                break;
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

    private void showError(Throwable e) {
        System.out.println(e.toString());
    }

    private void showResponse(HttpResponse<String> response) {
        System.out.println(response.statusCode());
        response.headers().map().forEach((key, value) -> System.out.println(key + ": " + String.join(";", value)));
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            String responseBody = response.body();
            if (responseBody != null) {
                System.out.println("\n" + responseBody);
            }
        }
    }

}
