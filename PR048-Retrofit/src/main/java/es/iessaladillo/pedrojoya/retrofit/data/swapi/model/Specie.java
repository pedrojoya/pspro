package es.iessaladillo.pedrojoya.retrofit.data.swapi.model;

import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses.FilmResponse;
import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses.PersonResponse;
import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses.PlanetResponse;

import java.util.List;

public class Specie {

    private String name;
    private String classification;
    private String designation;
    private String averageHeight;
    private String skinColors;
    private String hairColors;
    private String eyeColors;
    private String averageLifespan;
    private PlanetResponse homeworld;
    private String language;
    private List<PersonResponse> people;
    private List<FilmResponse> films;
    private String created;
    private String edited;
    private String url;

    public Specie(String name, String classification, String designation, String averageHeight, String skinColors,
                  String hairColors, String eyeColors, String averageLifespan, PlanetResponse homeworld, String language,
                  List<PersonResponse> people, List<FilmResponse> films, String created, String edited, String url) {
        this.name = name;
        this.classification = classification;
        this.designation = designation;
        this.averageHeight = averageHeight;
        this.skinColors = skinColors;
        this.hairColors = hairColors;
        this.eyeColors = eyeColors;
        this.averageLifespan = averageLifespan;
        this.homeworld = homeworld;
        this.language = language;
        this.people = people;
        this.films = films;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getClassification() {
        return classification;
    }

    public String getDesignation() {
        return designation;
    }

    public String getAverageHeight() {
        return averageHeight;
    }

    public String getSkinColors() {
        return skinColors;
    }

    public String getHairColors() {
        return hairColors;
    }

    public String getEyeColors() {
        return eyeColors;
    }

    public String getAverageLifespan() {
        return averageLifespan;
    }

    public PlanetResponse getHomeworld() {
        return homeworld;
    }

    public String getLanguage() {
        return language;
    }

    public List<PersonResponse> getPeople() {
        return people;
    }

    public List<FilmResponse> getFilms() {
        return films;
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
        return "Specie{" +
                "name='" + name + '\'' +
                ", classification='" + classification + '\'' +
                ", designation='" + designation + '\'' +
                ", averageHeight='" + averageHeight + '\'' +
                ", skinColors='" + skinColors + '\'' +
                ", hairColors='" + hairColors + '\'' +
                ", eyeColors='" + eyeColors + '\'' +
                ", averageLifespan='" + averageLifespan + '\'' +
                ", homeworld=" + homeworld +
                ", language='" + language + '\'' +
                ", people=" + people +
                ", films=" + films +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}