package es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses;

import java.util.List;

public class FilmsResponse extends ResultsResponse<FilmResponse> {
    public FilmsResponse(Integer count, String next, String previous, List<FilmResponse> results) {
        super(count, next, previous, results);
    }
}
