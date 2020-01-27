package es.iessaladillo.pedrojoya.retrofit.data;

import es.iessaladillo.pedrojoya.retrofit.data.api.entity.PlanetResponse;
import es.iessaladillo.pedrojoya.retrofit.data.model.Planet;

import java.util.concurrent.CompletableFuture;

public interface Repository {

    CompletableFuture<Planet> getPlanet(int planetId);

}
