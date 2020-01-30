package es.iessaladillo.pedrojoya.retrofit.data.swapi.model;

import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses.*;

import java.util.List;

public class Film {

    private String title;
    private Integer episodeId;
    private String openingCrawl;
    private String director;
    private String producer;
    private String releaseDate;
    private List<PersonResponse> characters;
    private List<PlanetResponse> planets;
    private List<StarshipResponse> starships;
    private List<VehicleResponse> vehicles;
    private List<SpecieResponse> species;
    private String created;
    private String edited;
    private String url;

    public Film(String title, Integer episodeId, String openingCrawl, String director, String producer,
                String releaseDate, List<PersonResponse> characters, List<PlanetResponse> planets, List<StarshipResponse> starships,
                List<VehicleResponse> vehicles, List<SpecieResponse> species, String created, String edited, String url) {
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

    public List<PersonResponse> getCharacters() {
        return characters;
    }

    public List<PlanetResponse> getPlanets() {
        return planets;
    }

    public List<StarshipResponse> getStarships() {
        return starships;
    }

    public List<VehicleResponse> getVehicles() {
        return vehicles;
    }

    public List<SpecieResponse> getSpecies() {
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
        return "Film{" +
                "title='" + title + '\'' +
                ", episodeId=" + episodeId +
                ", openingCrawl='" + openingCrawl + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", characters=" + characters +
                ", planets=" + planets +
                ", starships=" + starships +
                ", vehicles=" + vehicles +
                ", species=" + species +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
