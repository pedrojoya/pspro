package es.iessaladillo.pedrojoya.retrofit.data.swapi;

import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.SwapiService;
import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.responses.*;
import es.iessaladillo.pedrojoya.retrofit.data.swapi.model.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefaultDto implements Dto {

    private final SwapiService swapiService;

    public DefaultDto(SwapiService swapiService) {
        this.swapiService = swapiService;
    }

    // ALL

    @Override
    public CompletableFuture<List<Film>> getFilms(int pageNumber, String format) {
        return swapiService.getFilms(pageNumber, format)
                .thenCompose(filmsResponse -> {
                    List<CompletableFuture<Film>> responsesList = filmsResponse.results.stream()
                            .map(filmResponse -> getFilmResponseSubData(filmResponse, format))
                            .collect(Collectors.toList());
                    return CompletableFuture.allOf(responsesList.toArray(new CompletableFuture[0]))
                            .thenApply(nothing ->
                                    responsesList.stream().map(CompletableFuture::join).collect(Collectors.toList())
                            );
                });
    }

    private CompletableFuture<Film> getFilmResponseSubData(FilmResponse filmResponse, String format) {
        CompletableFuture<List<PersonResponse>> characters = getResponsesByUrl(filmResponse.getCharacters(), url -> swapiService.getPersonByUrl(url, format));
        CompletableFuture<List<PlanetResponse>> planets = getResponsesByUrl(filmResponse.getPlanets(), url -> swapiService.getPlanetByUrl(url, format));
        CompletableFuture<List<StarshipResponse>> starships = getResponsesByUrl(filmResponse.getStarships(), url -> swapiService.getStarshipByUrl(url, format));
        CompletableFuture<List<VehicleResponse>> vehicles = getResponsesByUrl(filmResponse.getVehicles(), url -> swapiService.getVehicleByUrl(url, format));
        CompletableFuture<List<SpecieResponse>> species = getResponsesByUrl(filmResponse.getSpecies(), url -> swapiService.getSpecieByUrl(url, format));
        return CompletableFuture.allOf(characters, planets, starships, vehicles, species)
                .thenApply(nothing -> filmDto(filmResponse, characters.join(), planets.join(), starships.join(), vehicles.join(), species.join()));
    }


    // BY ID

    @Override
    public CompletableFuture<Planet> getPlanetById(int planetId, String format) {
        return swapiService.getPlanet(planetId, format)
                .thenCompose(planetResponse -> {
                    CompletableFuture<List<PersonResponse>> residents =
                            getResponsesByUrl(planetResponse.getResidents(), url -> swapiService.getPersonByUrl(url, format));
                    CompletableFuture<List<FilmResponse>> films =
                            getResponsesByUrl(planetResponse.getFilms(), url -> swapiService.getFilmByUrl(url, format));
                    return CompletableFuture.allOf(residents, films)
                            .thenApply(nothing -> planetDto(planetResponse, residents.join(), films.join()));
                });
    }

    @Override
    public CompletableFuture<Vehicle> getVehicleById(int vehicleId, String format) {
        return swapiService.getVehicle(vehicleId, format)
                .thenCompose(vehicleResponse -> {
                    CompletableFuture<List<PersonResponse>> pilots = getResponsesByUrl(vehicleResponse.getPilots(), url -> swapiService.getPersonByUrl(url, format));
                    CompletableFuture<List<FilmResponse>> films = getResponsesByUrl(vehicleResponse.getFilms(), url -> swapiService.getFilmByUrl(url, format));
                    return CompletableFuture.allOf(pilots, films)
                            .thenApply(nothing -> vehicleDto(vehicleResponse, pilots.join(), films.join()));
                });
    }

    @Override
    public CompletableFuture<Starship> getStarshipById(int starshipId, String format) {
        return swapiService.getStarship(starshipId, format)
                .thenCompose(starship -> {
                    CompletableFuture<List<PersonResponse>> pilots = getResponsesByUrl(starship.getPilots(), url -> swapiService.getPersonByUrl(url, format));
                    CompletableFuture<List<FilmResponse>> films = getResponsesByUrl(starship.getFilms(), url -> swapiService.getFilmByUrl(url, format));
                    return CompletableFuture.allOf(pilots, films)
                            .thenApply(nothing -> starshipDto(starship, pilots.join(), films.join()));
                });
    }

    @Override
    public CompletableFuture<Specie> getSpecieById(int specieId, String format) {
        return swapiService.getSpecie(specieId, format)
                .thenCompose(specieResponse -> {
                    CompletableFuture<PlanetResponse> homeWorld = swapiService.getPlanetByUrl(specieResponse.getHomeworld(), format);
                    CompletableFuture<List<PersonResponse>> people = getResponsesByUrl(specieResponse.getPeople(), url -> swapiService.getPersonByUrl(url, format));
                    CompletableFuture<List<FilmResponse>> films = getResponsesByUrl(specieResponse.getFilms(), url -> swapiService.getFilmByUrl(url, format));
                    return CompletableFuture.allOf(homeWorld, people, films)
                            .thenApply(nothing -> specieDto(specieResponse, homeWorld.join(), people.join(), films.join()));
                });
    }

    @Override
    public CompletableFuture<Person> getPersonById(int personId, String format) {
        return swapiService.getPerson(personId, format)
                .thenCompose(personResponse -> {
                    CompletableFuture<PlanetResponse> homeWorld = swapiService.getPlanetByUrl(personResponse.getHomeworld(), format);
                    CompletableFuture<List<FilmResponse>> films = getResponsesByUrl(personResponse.getFilms(), url -> swapiService.getFilmByUrl(url, format));
                    CompletableFuture<List<SpecieResponse>> species = getResponsesByUrl(personResponse.getSpecies(), url -> swapiService.getSpecieByUrl(url, format));
                    CompletableFuture<List<VehicleResponse>> vehicles = getResponsesByUrl(personResponse.getVehicles(), url -> swapiService.getVehicleByUrl(url, format));
                    CompletableFuture<List<StarshipResponse>> starships = getResponsesByUrl(personResponse.getStarships(), url -> swapiService.getStarshipByUrl(url, format));
                    return CompletableFuture.allOf(homeWorld, films, species, vehicles, starships)
                            .thenApply(nothing -> personDto(personResponse, homeWorld.join(), films.join(), species.join(), vehicles.join(), starships.join()));
                });
    }

    @Override
    public CompletableFuture<Film> getFilmById(int filmId, String format) {
        return swapiService.getFilm(filmId, format)
                .thenCompose(filmResponse -> getFilmResponseSubData(filmResponse, format));
    }

    // BY URL

    private <T> CompletableFuture<List<T>> getResponsesByUrl(List<String> urls, Function<String, CompletableFuture<T>> mapper) {
        if (urls == null || urls.isEmpty()) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }
        List<CompletableFuture<T>> list = urls.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return CompletableFuture.allOf(list.toArray(new CompletableFuture[0]))
                .thenApply(nothing ->
                        list.stream().map(CompletableFuture::join).collect(Collectors.toList())
                );
    }

    // DTO

    @NotNull
    private Planet planetDto(PlanetResponse planetResponse, List<PersonResponse> personResponses, List<FilmResponse> filmResponses) {
        return new Planet(
                planetResponse.getName(),
                planetResponse.getRotationPeriod(),
                planetResponse.getOrbitalPeriod(),
                planetResponse.getDiameter(),
                planetResponse.getClimate(),
                planetResponse.getGravity(),
                planetResponse.getTerrain(),
                planetResponse.getSurfaceWater(),
                planetResponse.getPopulation(),
                personResponses,
                filmResponses,
                planetResponse.getCreated(),
                planetResponse.getEdited(),
                planetResponse.getUrl()
        );
    }

    @NotNull
    private Vehicle vehicleDto(VehicleResponse vehicleResponse, List<PersonResponse> personResponses, List<FilmResponse> filmResponses) {
        return new Vehicle(
                vehicleResponse.getName(),
                vehicleResponse.getModel(),
                vehicleResponse.getManufacturer(),
                vehicleResponse.getCostInCredits(),
                vehicleResponse.getLength(),
                vehicleResponse.getMaxAtmospheringSpeed(),
                vehicleResponse.getCrew(),
                vehicleResponse.getPassengers(),
                vehicleResponse.getCargoCapacity(),
                vehicleResponse.getConsumables(),
                vehicleResponse.getVehicleClass(),
                personResponses,
                filmResponses,
                vehicleResponse.getCreated(),
                vehicleResponse.getEdited(),
                vehicleResponse.getUrl()
        );
    }

    @NotNull
    private Starship starshipDto(StarshipResponse starshipResponse, List<PersonResponse> personResponses, List<FilmResponse> filmResponses) {
        return new Starship(
                starshipResponse.getName(),
                starshipResponse.getModel(),
                starshipResponse.getManufacturer(),
                starshipResponse.getCostInCredits(),
                starshipResponse.getLength(),
                starshipResponse.getMaxAtmospheringSpeed(),
                starshipResponse.getCrew(),
                starshipResponse.getPassengers(),
                starshipResponse.getCargoCapacity(),
                starshipResponse.getConsumables(),
                starshipResponse.getHyperdriveRating(),
                starshipResponse.getMGLT(),
                starshipResponse.getStarshipClass(),
                personResponses,
                filmResponses,
                starshipResponse.getCreated(),
                starshipResponse.getEdited(),
                starshipResponse.getUrl()
        );
    }

    @NotNull
    private Specie specieDto(SpecieResponse specieResponse, PlanetResponse planetResponse, List<PersonResponse> personResponses, List<FilmResponse> filmResponses) {
        return new Specie(
                specieResponse.getName(),
                specieResponse.getClassification(),
                specieResponse.getDesignation(),
                specieResponse.getAverageHeight(),
                specieResponse.getSkinColors(),
                specieResponse.getHairColors(),
                specieResponse.getEyeColors(),
                specieResponse.getAverageLifespan(),
                planetResponse,
                specieResponse.getLanguage(),
                personResponses,
                filmResponses,
                specieResponse.getCreated(),
                specieResponse.getEdited(),
                specieResponse.getUrl()
        );
    }

    @NotNull
    private Person personDto(PersonResponse personResponse, PlanetResponse planetResponse, List<FilmResponse> filmResponses,
                             List<SpecieResponse> specieResponses, List<VehicleResponse> vehicleResponses,
                             List<StarshipResponse> starshipResponses) {
        return new Person(
                personResponse.getName(),
                personResponse.getHeight(),
                personResponse.getMass(),
                personResponse.getHairColor(),
                personResponse.getSkinColor(),
                personResponse.getEyeColor(),
                personResponse.getBirthYear(),
                personResponse.getGender(),
                planetResponse,
                filmResponses,
                specieResponses,
                vehicleResponses,
                starshipResponses,
                personResponse.getCreated(),
                personResponse.getEdited(),
                personResponse.getUrl()
        );
    }

    @NotNull
    private Film filmDto(FilmResponse filmResponse, List<PersonResponse> personResponses, List<PlanetResponse> planetResponses,
                         List<StarshipResponse> starshipResponses, List<VehicleResponse> vehicleResponses,
                         List<SpecieResponse> specieResponses) {
        return new Film(
                filmResponse.getTitle(),
                filmResponse.getEpisodeId(),
                filmResponse.getOpeningCrawl(),
                filmResponse.getDirector(),
                filmResponse.getProducer(),
                filmResponse.getReleaseDate(),
                personResponses,
                planetResponses,
                starshipResponses,
                vehicleResponses,
                specieResponses,
                filmResponse.getCreated(),
                filmResponse.getEdited(),
                filmResponse.getUrl()
        );
    }

}
