package es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses;

import java.util.List;

public class StarshipsResponse extends ResultsResponse<StarshipResponse> {
    public StarshipsResponse(Integer count, String next, String previous, List<StarshipResponse> results) {
        super(count, next, previous, results);
    }
}
