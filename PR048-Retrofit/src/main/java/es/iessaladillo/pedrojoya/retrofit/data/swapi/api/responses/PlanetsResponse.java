package es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses;

import java.util.List;

public class PlanetsResponse extends ResultsResponse<PlanetResponse> {
    public PlanetsResponse(Integer count, String next, String previous, List<PlanetResponse> results) {
        super(count, next, previous, results);
    }
}
