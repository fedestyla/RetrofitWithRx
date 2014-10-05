package it.federico.com.retrofitrx.network.api;


import android.util.Log;
import it.federico.com.retrofitrx.Model.Repository;
import it.federico.com.retrofitrx.network.RetrofitInterface;
import retrofit.RestAdapter;
import rx.Observable;

public final class GithubApi {

    private static final String BASE_URL = "https://api.github.com";
    private RetrofitInterface retrofitInterface;

    public GithubApi() {
        RestAdapter restAdapter = buildRestAdapter();
        retrofitInterface = restAdapter.create(RetrofitInterface.class);
    }

    private RestAdapter buildRestAdapter() {
        return new RestAdapter.Builder().setEndpoint(BASE_URL).setLog(new RestAdapter.Log() {
            @Override
            public void log(final String message) {
                Log.d("TAG", message);
            }
        }).setLogLevel(RestAdapter.LogLevel.FULL).build();
    }

    public Observable<Repository[]> getListOfRepositories(final String organization) {
        return retrofitInterface.getListOfRepos(organization);
    }
}
