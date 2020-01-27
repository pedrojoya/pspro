package es.iessaladillo.pedrojoya.retrofit.data;

import com.sun.jndi.toolkit.url.Uri;
import es.iessaladillo.pedrojoya.retrofit.data.api.ApiService;
import es.iessaladillo.pedrojoya.retrofit.data.api.entity.FilmResponse;
import es.iessaladillo.pedrojoya.retrofit.data.api.entity.PersonResponse;
import es.iessaladillo.pedrojoya.retrofit.data.api.entity.PlanetResponse;
import es.iessaladillo.pedrojoya.retrofit.data.model.Person;
import es.iessaladillo.pedrojoya.retrofit.data.model.Planet;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class DefaultRepository implements Repository {

    private final ApiService apiService;

    public DefaultRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public CompletableFuture<Planet> getPlanet(int planetId) {
        return apiService.getPlanet(planetId)
                .thenCompose(planetResponse -> {
                    CompletableFuture<List<PersonResponse>> residents = getResidentsOfPlanet(planetResponse);
                    CompletableFuture<List<FilmResponse>> films = getFilmsOfPlanet(planetResponse);
                    return CompletableFuture.allOf(residents, films)
                            .thenApply(nothing -> new Planet(planetResponse, residents.join(), films.join()));
                });
    }

    private CompletableFuture<List<PersonResponse>> getResidentsOfPlanet(PlanetResponse planetResponse) {
        List<CompletableFuture<PersonResponse>> list = planetResponse.getResidents().stream()
                .map(apiService::getPersonByUrl)
                .collect(Collectors.toList());
        return CompletableFuture.allOf(list.toArray(new CompletableFuture[0]))
                .thenApply(nothing ->
                        list.stream().map(CompletableFuture::join).collect(Collectors.toList())
                );
    }

    private CompletableFuture<List<FilmResponse>> getFilmsOfPlanet(PlanetResponse planetResponse) {
        List<CompletableFuture<FilmResponse>> list = planetResponse.getFilms().stream()
                .map(apiService::getFilmByUrl)
                .collect(Collectors.toList());
        return CompletableFuture.allOf(list.toArray(new CompletableFuture[0]))
                .thenApply(nothing ->
                        list.stream().map(CompletableFuture::join).collect(Collectors.toList())
                );
    }

}
