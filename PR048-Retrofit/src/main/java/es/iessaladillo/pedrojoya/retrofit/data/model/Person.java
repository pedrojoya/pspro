package es.iessaladillo.pedrojoya.retrofit.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import es.iessaladillo.pedrojoya.retrofit.data.api.entity.FilmResponse;

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
    private String homeworldUrl;
    private List<FilmResponse> films = null;
    private List<String> species = null;
    private List<String> vehicles = null;
    private List<String> starships = null;
    private String created;
    private String edited;
    private String url;

    public Person(String name, String height, String mass, String hairColor, String skinColor, String eyeColor,
                  String birthYear, String gender, String homeworldUrl, List<FilmResponse> films, List<String> species,
                  List<String> vehicles, List<String> starships, String created, String edited, String url) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.birthYear = birthYear;
        this.gender = gender;
        this.homeworldUrl = homeworldUrl;
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

    public String getHomeworldUrl() {
        return homeworldUrl;
    }

    public List<FilmResponse> getFilms() {
        return films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public List<String> getStarships() {
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

}