package es.iessaladillo.pedrojoya.retrofit.data.swapi.api;

import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses.*;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

import java.util.concurrent.CompletableFuture;

public interface SwapiService {

    // ALL

    @GET("planets")
    CompletableFuture<PlanetsResponse> getPlanets(@Query("page") int pageNumber, @Query("format") String format);

    @GET("people")
    CompletableFuture<PeopleResponse> getPersons(@Query("page") int pageNumber, @Query("format") String format);

    @GET("vehicles")
    CompletableFuture<VehiclesResponse> getVehicles(@Query("page") int pageNumber, @Query("format") String format);

    @GET("starships")
    CompletableFuture<StarshipsResponse> getStarships(@Query("page") int pageNumber, @Query("format") String format);

    @GET("species")
    CompletableFuture<SpeciesResponse> getSpecies(@Query("page") int pageNumber, @Query("format") String format);

    @GET("films")
    CompletableFuture<FilmsResponse> getFilms(@Query("page") int pageNumber, @Query("format") String format);

    // SEARCH

    @GET("planets")
    CompletableFuture<PlanetsResponse> searchPlanets(@Query("page") int pageNumber, @Query("format") String format, @Query("search") String search);

    @GET("people")
    CompletableFuture<PeopleResponse> searchPersons(@Query("page") int pageNumber, @Query("format") String format, @Query("search") String search);

    @GET("vehicles")
    CompletableFuture<VehiclesResponse> searchVehicles(@Query("page") int pageNumber, @Query("format") String format, @Query("search") String search);

    @GET("starships")
    CompletableFuture<StarshipsResponse> searchStarships(@Query("page") int pageNumber, @Query("format") String format, @Query("search") String search);

    @GET("species")
    CompletableFuture<SpeciesResponse> searchSpecies(@Query("page") int pageNumber, @Query("format") String format, @Query("search") String search);

    @GET("films")
    CompletableFuture<FilmsResponse> searchFilms(@Query("page") int pageNumber, @Query("format") String format, @Query("search") String search);

    // BY ID

    @GET("planets/{planetId}")
    CompletableFuture<PlanetResponse> getPlanet(@Path("planetId") int planetId, @Query("format") String format);

    @GET("people/{personId}")
    CompletableFuture<PersonResponse> getPerson(@Path("personId") int personId, @Query("format") String format);

    @GET("vehicles/{vehicleId}")
    CompletableFuture<VehicleResponse> getVehicle(@Path("vehicleId") int vehicleId, @Query("format") String format);

    @GET("starships/{starshipId}")
    CompletableFuture<StarshipResponse> getStarship(@Path("starshipId") int starshipId, @Query("format") String format);

    @GET("species/{specieId}")
    CompletableFuture<SpecieResponse> getSpecie(@Path("specieId") int specieId, @Query("format") String format);

    @GET("films/{filmId}")
    CompletableFuture<FilmResponse> getFilm(@Path("filmId") int filmId, @Query("format") String format);

    // BY URL

    @GET
    CompletableFuture<PersonResponse> getPersonByUrl(@Url String url, @Query("format") String format);

    @GET
    CompletableFuture<FilmResponse> getFilmByUrl(@Url String url, @Query("format") String format);

    @GET
    CompletableFuture<StarshipResponse> getStarshipByUrl(@Url String url, @Query("format") String format);

    @GET
    CompletableFuture<PlanetResponse> getPlanetByUrl(@Url String url, @Query("format") String format);

    @GET
    CompletableFuture<SpecieResponse> getSpecieByUrl(@Url String url, @Query("format") String format);

    @GET
    CompletableFuture<VehicleResponse> getVehicleByUrl(@Url String url, @Query("format") String format);

}
