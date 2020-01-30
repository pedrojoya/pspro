package es.iessaladillo.pedrojoya.retrofit.data.swapi.model;

import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses.*;

import java.util.List;

public class Person {

    private String name;
    private String height;
    private String mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;
    private PlanetResponse homeworld;
    private List<FilmResponse> films;
    private List<SpecieResponse> species;
    private List<VehicleResponse> vehicles;
    private List<StarshipResponse> starships;
    private String created;
    private String edited;
    private String url;

    public Person(String name, String height, String mass, String hairColor, String skinColor, String eyeColor,
                  String birthYear, String gender, PlanetResponse homeworldUrl, List<FilmResponse> films, List<SpecieResponse> species,
                  List<VehicleResponse> vehicles, List<StarshipResponse> starships, String created, String edited, String url) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.birthYear = birthYear;
        this.gender = gender;
        this.homeworld = homeworldUrl;
        this.films = films;
        this.species = species;
        this.vehicles = vehicles;
        this.starships = starships;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }

    public PlanetResponse getHomeworld() {
        return homeworld;
    }

    public List<FilmResponse> getFilms() {
        return films;
    }

    public List<SpecieResponse> getSpecies() {
        return species;
    }

    public List<VehicleResponse> getVehicles() {
        return vehicles;
    }

    public List<StarshipResponse> getStarships() {
        return starships;
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
        return "Person{" +
                "name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", mass='" + mass + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", gender='" + gender + '\'' +
                ", homeworld=" + homeworld +
                ", films=" + films +
                ", species=" + species +
                ", vehicles=" + vehicles +
                ", starships=" + starships +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}