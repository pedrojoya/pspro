package es.iessaladillo.pedrojoya.retrofit.data.model;

import es.iessaladillo.pedrojoya.retrofit.data.api.entity.FilmResponse;
import es.iessaladillo.pedrojoya.retrofit.data.api.entity.PersonResponse;
import es.iessaladillo.pedrojoya.retrofit.data.api.entity.PlanetResponse;

import java.util.List;

public class Planet {

    private String name;
    private String rotationPeriod;
    private String orbitalPeriod;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surfaceWater;
    private String population;
    private List<PersonResponse> residents = null;
    private List<FilmResponse> films = null;
    private String created;
    private String edited;
    private String url;

    public Planet(String name, String rotationPeriod, String orbitalPeriod, String diameter, String climate,
                          String gravity, String terrain, String surfaceWater, String population, List<PersonResponse> residents,
                          List<FilmResponse> films, String created, String edited, String url) {
        this.name = name;
        this.rotationPeriod = rotationPeriod;
        this.orbitalPeriod = orbitalPeriod;
        this.diameter = diameter;
        this.climate = climate;
        this.gravity = gravity;
        this.terrain = terrain;
        this.surfaceWater = surfaceWater;
        this.population = population;
        this.residents = residents;
        this.films = films;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public Planet(PlanetResponse planetResponse, List<PersonResponse> residentList, List<FilmResponse> filmList) {
        this.name = planetResponse.getName();
        this.rotationPeriod = planetResponse.getRotationPeriod();
        this.orbitalPeriod = planetResponse.getOrbitalPeriod();
        this.diameter = planetResponse.getDiameter();
        this.climate = planetResponse.getClimate();
        this.gravity = planetResponse.getGravity();
        this.terrain =  planetResponse.getTerrain();
        this.surfaceWater = planetResponse.getSurfaceWater();
        this.population = planetResponse.getPopulation();
        this.residents = residentList;
        this.films = filmList;
        this.created = planetResponse.getCreated();
        this.edited = planetResponse.getEdited();
        this.url = planetResponse.getUrl();
    }

    public String getName() {
        return name;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getClimate() {
        return climate;
    }

    public String getGravity() {
        return gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public String getPopulation() {
        return population;
    }

    public List<PersonResponse> getResidents() {
        return residents;
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
        return "Planet{" +
                "name='" + name + '\'' +
                ", rotationPeriod='" + rotationPeriod + '\'' +
                ", orbitalPeriod='" + orbitalPeriod + '\'' +
                ", diameter='" + diameter + '\'' +
                ", climate='" + climate + '\'' +
                ", gravity='" + gravity + '\'' +
                ", terrain='" + terrain + '\'' +
                ", surfaceWater='" + surfaceWater + '\'' +
                ", population='" + population + '\'' +
                ", residents=" + residents +
                ", films=" + films +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


}
