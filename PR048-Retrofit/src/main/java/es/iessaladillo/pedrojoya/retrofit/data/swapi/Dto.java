package es.iessaladillo.pedrojoya.retrofit.data.swapi;

import es.iessaladillo.pedrojoya.retrofit.data.swapi.model.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Dto {

    CompletableFuture<Planet> getPlanetById(int planetId, String format);

    CompletableFuture<Vehicle> getVehicleById(int vehicleId, String format);

    CompletableFuture<Starship> getStarshipById(int starshipId, String format);

    CompletableFuture<Specie> getSpecieById(int specieId, String format);

    CompletableFuture<Person> getPersonById(int personId, String format);

    CompletableFuture<Film> getFilmById(int filmId, String format);

    CompletableFuture<List<Film>> getFilms(int pageNumber, String format);

}
