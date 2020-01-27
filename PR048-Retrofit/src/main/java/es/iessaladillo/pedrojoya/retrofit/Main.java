package es.iessaladillo.pedrojoya.retrofit;

import es.iessaladillo.pedrojoya.retrofit.data.DefaultRepository;
import es.iessaladillo.pedrojoya.retrofit.data.Repository;
import es.iessaladillo.pedrojoya.retrofit.data.api.ApiService;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Repository repository = new DefaultRepository(apiService);
        repository.getPlanet(1)
                .whenComplete(this::showErrorOrModel);
    }

//    private <Person> CompletionStage<Person> composePersonWithPlanet(Response<Person> personResponse) {
//    }

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
