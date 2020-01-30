package es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsResponse<T> {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("next")
    @Expose
    public String next;
    @SerializedName("previous")
    @Expose
    public String previous;
    @SerializedName("results")
    @Expose
    public List<T> results = null;

    public ResultsResponse(Integer count, String next, String previous, List<T> results) {
        super();
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<T> getResults() {
        return results;
    }

}