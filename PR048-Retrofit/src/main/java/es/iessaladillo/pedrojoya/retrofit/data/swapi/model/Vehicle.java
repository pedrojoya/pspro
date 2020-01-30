package es.iessaladillo.pedrojoya.retrofit.data.swapi.model;

import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses.FilmResponse;
import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses.PersonResponse;

import java.util.List;

public class Vehicle {

    private String name;
    private String model;
    private String manufacturer;
    private String costInCredits;
    private String length;
    private String maxAtmospheringSpeed;
    private String crew;
    private String passengers;
    private String cargoCapacity;
    private String consumables;
    private String vehicleClass;
    private List<PersonResponse> pilots;
    private List<FilmResponse> films;
    private String created;
    private String edited;
    private String url;

    public Vehicle(String name, String model, String manufacturer, String costInCredits, String length,
                   String maxAtmospheringSpeed, String crew, String passengers, String cargoCapacity,
                   String consumables, String vehicleClass, List<PersonResponse> pilots, List<FilmResponse> films, String created,
                   String edited, String url) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.costInCredits = costInCredits;
        this.length = length;
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
        this.crew = crew;
        this.passengers = passengers;
        this.cargoCapacity = cargoCapacity;
        this.consumables = consumables;
        this.vehicleClass = vehicleClass;
        this.pilots = pilots;
        this.films = films;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCostInCredits() {
        return costInCredits;
    }

    public String getLength() {
        return length;
    }

    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public String getCrew() {
        return crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public List<PersonResponse> getPilots() {
        return pilots;
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
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", costInCredits='" + costInCredits + '\'' +
                ", length='" + length + '\'' +
                ", maxAtmospheringSpeed='" + maxAtmospheringSpeed + '\'' +
                ", crew='" + crew + '\'' +
                ", passengers='" + passengers + '\'' +
                ", cargoCapacity='" + cargoCapacity + '\'' +
                ", consumables='" + consumables + '\'' +
                ", vehicleClass='" + vehicleClass + '\'' +
                ", pilots=" + pilots +
                ", films=" + films +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}