package es.iessaladillo.pedrojoya.retrofit.data.api;

import es.iessaladillo.pedrojoya.retrofit.data.api.entity.FilmResponse;
import es.iessaladillo.pedrojoya.retrofit.data.api.entity.PersonResponse;
import es.iessaladillo.pedrojoya.retrofit.data.api.entity.PlanetResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

import java.util.concurrent.CompletableFuture;

public interface ApiService {

    @GET("planets/{planetId}")
    CompletableFuture<PlanetResponse> getPlanet(@Path("planetId") int planetId);

    @GET("people/{personId}")
    CompletableFuture<PersonResponse> getPerson(@Path("personId") int personId);

    @GET
    CompletableFuture<PersonResponse> getPersonByUrl(@Url String url);

    @GET("films/{filmId}")
    CompletableFuture<FilmResponse> getFilm(@Path("filmId") int filmId);

    @GET
    CompletableFuture<FilmResponse> getFilmByUrl(@Url String url);

}
