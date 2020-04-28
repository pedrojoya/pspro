package es.iessaladillo.pedrojoya.httpurlconnection;

import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final int TIMEOUT_MILLIS = 5000;

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
        System.out.println("1. Show all posts");
        System.out.println("2. Show post");
        System.out.println("3. Show user's posts");
        System.out.println("4. Create post");
        System.out.println("5. Update post");
        System.out.println("6. Update post partially");
        System.out.println("7. Delete post");
        System.out.println("8. Show only headers");
        System.out.println("9. Show access options");
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
                showPosts();
                break;
            case 2:
                showPost(1);
                break;
            case 3:
                showUserPosts(1);
                break;
            case 4:
                createPost("{\"title\": \"Baldomero\", \"body\": \"Llegate Ligero\", \"userId\": 1}");
                break;
            case 5:
                updatePost(1, "{\"id\": 1, \"title\": \"Baldomero\", \"body\": \"Llegate Ligero\", \"userId\": 1}");
                break;
            case 6:
                patchPost(1, "{\"title\": \"Baldomero\"}");
                break;
            case 7:
                deletePost(1);
                break;
            case 8:
                showPostsHeaders();
                break;
            case 9:
                showPostsAccessOptions();
                break;
        }
    }

    private void showPosts() {
        HttpUtils.doHttpRequestAsync("https://jsonplaceholder.typicode.com/posts", "GET",
                null, null, TIMEOUT_MILLIS).join();
    }

    private void showPost(int postId) {
        HttpUtils.doHttpRequestAsync("https://jsonplaceholder.typicode.com/posts/" + postId, "GET",
                null, null, TIMEOUT_MILLIS).join();
    }

    private void createPost(String jsonPost) {
        HttpUtils.doHttpRequestAsync("https://jsonplaceholder.typicode.com/posts", "POST",
                Map.of("Content-type", "application/json; charset=UTF-8"),
                jsonPost.getBytes(), TIMEOUT_MILLIS).join();
    }

    private void updatePost(int postId, String jsonPost) {
        HttpUtils.doHttpRequestAsync("https://jsonplaceholder.typicode.com/posts/" + postId, "PUT",
                Map.of("Content-type", "application/json; charset=UTF-8"),
                jsonPost.getBytes(), TIMEOUT_MILLIS).join();
    }

    private void patchPost(int postId, String jsonPost) {
        HttpUtils.doHttpRequestAsync("https://jsonplaceholder.typicode.com/posts/" + postId, "PATCH",
                Map.of("Content-type", "application/json; charset=UTF-8"),
                jsonPost.getBytes(), TIMEOUT_MILLIS).join();
    }

    private void deletePost(int postId) {
        HttpUtils.doHttpRequestAsync("https://jsonplaceholder.typicode.com/posts/" + postId, "DELETE",
                null, null, TIMEOUT_MILLIS).join();
    }

    private void showUserPosts(int userId) {
        HttpUtils.doHttpRequestAsync("https://jsonplaceholder.typicode.com/users/" + userId + "/posts", "GET",
                null, null, TIMEOUT_MILLIS).join();
    }

    private void showPostsHeaders() {
        HttpUtils.doHttpRequestAsync("https://jsonplaceholder.typicode.com/posts", "HEAD",
                null, null, TIMEOUT_MILLIS)
                .exceptionally(this::showError)
                .join();
    }

    private void showPostsAccessOptions() {
        HttpUtils.doHttpRequestAsync("https://jsonplaceholder.typicode.com/posts", "OPTIONS",
                null, null, TIMEOUT_MILLIS).join();
    }

    private Void showError(Throwable e) {
        System.out.println(e.toString());
        return null;
    }


}
