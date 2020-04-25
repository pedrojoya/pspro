package es.iessaladillo.pedrojoya.httpurlconnection;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final int OPTION_EXIT = 10;

    private final Scanner scanner = new Scanner(System.in);

    Main() {
        int selectedOption;
        do {
            selectedOption = showMenu();
            try {
                processOption(selectedOption);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } while (selectedOption != OPTION_EXIT);
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
        System.out.println("10. Exit");
        System.out.print("Select an option: ");
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return 0;
        }
    }

    private void processOption(int selectedOption) throws IOException {
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

    private void showPosts() throws IOException {
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
