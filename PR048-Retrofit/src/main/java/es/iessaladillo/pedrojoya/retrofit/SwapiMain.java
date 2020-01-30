package es.iessaladillo.pedrojoya.retrofit;

import es.iessaladillo.pedrojoya.retrofit.data.swapi.DefaultDto;
import es.iessaladillo.pedrojoya.retrofit.data.swapi.Dto;
import es.iessaladillo.pedrojoya.retrofit.data.swapi.api.SwapiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SwapiMain {

    private final Dto dto = new DefaultDto(createSwapiService());

    public SwapiMain() {
        showPlanet(1);
        showVehicle(4);
        showStarship(9);
        showSpecie(3);
        showPerson(1);
        showFilm(1);
    }

    public static void main(String[] args) {
        new SwapiMain();
    }

    private void showPlanet(int planetId) {
        dto.getPlanetById(planetId, "json").whenComplete(this::showErrorOrModel);
    }

    private void showVehicle(int vehicleId) {
        dto.getVehicleById(vehicleId, "json").whenComplete(this::showErrorOrModel);
    }

    private void showStarship(int starshipId) {
        dto.getStarshipById(starshipId, "json").whenComplete(this::showErrorOrModel);
    }

    private void showSpecie(int specieId) {
        dto.getSpecieById(specieId, "json").whenComplete(this::showErrorOrModel);
    }

    private void showPerson(int personId) {
        dto.getPersonById(personId, "json").whenComplete(this::showErrorOrModel);
    }

    private void showFilm(int filmId) {
        dto.getFilmById(filmId, "json").whenComplete(this::showErrorOrModel);
    }

    // -------------------------

    @NotNull
    private SwapiService createSwapiService() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SwapiService.class);
    }

    private <T> void showErrorOrModel(T model, Throwable throwable) {
        if (throwable != null) {
            showError(throwable);
        } else {
            showModel(model);
        }
    }

    private void showError(Throwable e) {
        System.out.println(e.toString());
    }

    private <T> void showModel(T model) {
        System.out.println(model);
    }

}
