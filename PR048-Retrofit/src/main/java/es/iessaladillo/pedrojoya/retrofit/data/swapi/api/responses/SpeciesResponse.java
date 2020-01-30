package es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses;

import java.util.List;

public class SpeciesResponse extends ResultsResponse<SpecieResponse> {
    public SpeciesResponse(Integer count, String next, String previous, List<SpecieResponse> results) {
        super(count, next, previous, results);
    }
}
