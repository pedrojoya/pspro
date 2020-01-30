package es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmResponse {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("episode_id")
    @Expose
    private Integer episodeId;
    @SerializedName("opening_crawl")
    @Expose
    private String openingCrawl;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("producer")
    @Expose
    private String producer;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("characters")
    @Expose
    private List<String> characters = null;
    @SerializedName("planets")
    @Expose
    private List<String> planets = null;
    @SerializedName("starships")
    @Expose
    private List<String> starships = null;
    @SerializedName("vehicles")
    @Expose
    private List<String> vehicles = null;
    @SerializedName("species")
    @Expose
    private List<String> species = null;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("edited")
    @Expose
    private String edited;
    @SerializedName("url")
    @Expose
    private String url;

    public FilmResponse(String title, Integer episodeId, String openingCrawl, String director, String producer, String releaseDate, List<String> characters, List<String> planets, List<String> starships, List<String> vehicles, List<String> species, String created, String edited, String url) {
        this.title = title;
        this.episodeId = episodeId;
        this.openingCrawl = openingCrawl;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.characters = characters;
        this.planets = planets;
        this.starships = starships;
        this.vehicles = vehicles;
        this.species = species;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public List<String> getStarships() {
        return starships;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public List<String> getSpecies() {
        return species;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return title;
    }
}
