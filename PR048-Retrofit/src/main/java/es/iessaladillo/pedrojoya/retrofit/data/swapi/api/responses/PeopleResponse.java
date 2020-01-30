package es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses;

import java.util.List;

public class PeopleResponse extends ResultsResponse<PeopleResponse> {
    public PeopleResponse(Integer count, String next, String previous, List<PeopleResponse> results) {
        super(count, next, previous, results);
    }
}
