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
import java.util.stream.Collectors;

public class Main {

    private final ApiService apiService = createApiService();
    private final ApiService swapiService = createSwapiService();

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        showPostsSync();
        showPostsWithCallback();
        showPosts();
        showPostsHeaders();
        showPostsAccessOptions();
        showPost(1);
        createPost(new PostResponse(1, 0, "Baldomero", "Llégate Ligero"));
        updatePost(1, new PostResponse(1, 1, "Baldomero", "Llégate Ligero"));
        patchPost(1, new PostResponse(3, 1, "Baldomero", "Llégate Ligero"));
        deletePost(1);
        showUserPosts(1);
        showPostsByUser(1);
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
