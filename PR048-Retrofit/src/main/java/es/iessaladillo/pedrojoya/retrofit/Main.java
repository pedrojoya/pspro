package es.iessaladillo.pedrojoya.retrofit;

import es.iessaladillo.pedrojoya.retrofit.data.api.ApiService;
import es.iessaladillo.pedrojoya.retrofit.data.api.response.PostResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private final ApiService apiService = createApiService();
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
        System.out.println("2. Show all posts with callback");
        System.out.println("3. Show all posts");
        System.out.println("4. Show post");
        System.out.println("5. Show user's posts");
        System.out.println("6. Show posts by user");
        System.out.println("7. Create post");
        System.out.println("8. Update post");
        System.out.println("9. Update post partially");
        System.out.println("10. Delete post");
        System.out.println("11. Show only headers");
        System.out.println("12. Show access options");
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
                showPostsByUser(1);
                break;
            case 7:
                createPost(new PostResponse(1, 0, "Baldomero", "Llégate Ligero"));
                break;
            case 8:
                updatePost(1, new PostResponse(1, 1, "Baldomero", "Llégate Ligero"));
                break;
            case 9:
                patchPost(1, new PostResponse(3, 1, "Baldomero", "Llégate Ligero"));
                break;
            case 10:
                deletePost(1);
                break;
            case 11:
                showPostsHeaders();
                break;
            case 12:
                showPostsAccessOptions();
                break;
        }
    }

    private void showPostsSync() {
        try {
            Response<List<PostResponse>> response = apiService.getPostsCall().execute();
            if (response.isSuccessful() && response.body() != null) {
                showModel(response.body());
            }
        } catch (IOException e) {
            showError(e);
        }
    }

    private void showPostsWithCallback() {
        apiService.getPostsCall().enqueue(new Callback<List<PostResponse>>() {
            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    showModel(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }

    private void showPosts() {
        apiService.getPosts().whenComplete(this::showErrorOrModel);
    }

    private void showPostsAccessOptions() {
        apiService.getPostsAccessOptions()
                .thenApply(response ->
                        String.join(";", response.headers().values("access-control-allow-methods")))
                .whenComplete(this::showErrorOrModel);
    }

    private void showPostsHeaders() {
        apiService.getPostsHeaders()
                .thenApply(response -> response.headers().toMultimap().entrySet().stream()
                        .map(entry -> entry.getKey() + ": " + String.join(";", entry.getValue()))
                        .collect(Collectors.joining("\n"))
                )
                .whenComplete(this::showErrorOrModel);
    }

    private void showPost(int postId) {
        apiService.getPost(1).whenComplete(this::showErrorOrModel);
    }

    private void createPost(PostResponse postResponse) {
        apiService.createPost(postResponse).whenComplete(this::showErrorOrModel);
    }

    private void updatePost(int postId, PostResponse postResponse) {
        apiService.updatePost(postId, postResponse).whenComplete(this::showErrorOrModel);
    }

    private void patchPost(int postId, PostResponse postResponse) {
        apiService.patchPost(postId, postResponse).whenComplete(this::showErrorOrModel);
    }

    private void deletePost(int postId) {
        apiService.deletePost(postId).whenComplete(this::showErrorOrModel).join();
    }

    private void showUserPosts(int userId) {
        apiService.getUserPosts(userId).whenComplete(this::showErrorOrModel).join();
    }

    private void showPostsByUser(int userId) {
        apiService.getPostsByUser(userId).whenComplete(this::showErrorOrModel).join();
    }

    // -------------------------

    @NotNull
    private ApiService createApiService() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    @NotNull
    private ApiService createSwapiService() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    private <T> void showErrorOrModel(T model, Throwable throwable) {
        if (throwable != null) {
            showError(throwable);
        } else {
            showModel(model);
        }
    }

    private void showError(Throwable e) {
        System.out.println(e.toString());
    }

    private <T> void showModel(T model) {
        System.out.println(model);
    }

}
