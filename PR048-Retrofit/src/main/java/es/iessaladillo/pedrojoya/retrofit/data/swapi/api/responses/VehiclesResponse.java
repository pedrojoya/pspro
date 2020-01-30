package es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses;

import java.util.List;

public class VehiclesResponse extends ResultsResponse<VehicleResponse> {
    public VehiclesResponse(Integer count, String next, String previous, List<VehicleResponse> results) {
        super(count, next, previous, results);
    }
}
