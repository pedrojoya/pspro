package es.iessaladillo.pedrojoya.retrofit.data.api;

import es.iessaladillo.pedrojoya.retrofit.data.api.response.PostResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ApiService {

    @GET("posts")
    Call<List<PostResponse>> getPostsCall();

    @GET("posts")
    CompletableFuture<List<PostResponse>> getPosts();

    @OPTIONS("posts")
    CompletableFuture<Response<PostResponse>> getPostsAccessOptions();

    @HEAD("posts")
    CompletableFuture<Response<Void>> getPostsHeaders();

    @GET("posts/{postId}")
    CompletableFuture<PostResponse> getPost(@Path("postId") int postId);

    @POST("posts")
    CompletableFuture<PostResponse> createPost(@Body PostResponse postResponse);

    @PUT("posts/{postId}")
    CompletableFuture<PostResponse> updatePost(@Path("postId") int postId, @Body PostResponse postResponse);

    @PATCH("posts/{postId}")
    CompletableFuture<PostResponse> patchPost(@Path("postId") int postId, @Body PostResponse postResponse);

    @DELETE("posts/{postId}")
    CompletableFuture<Void> deletePost(@Path("postId") int postId);

    @GET("users/{userId}/posts")
    CompletableFuture<List<PostResponse>> getUserPosts(@Path("userId") int userId);

    @GET("posts")
    CompletableFuture<List<PostResponse>> getPostsByUser(@Query("userId") int userId);

}
