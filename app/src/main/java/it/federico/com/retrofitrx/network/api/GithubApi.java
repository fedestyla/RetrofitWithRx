package it.federico.com.retrofitrx.network.api;


import android.content.Context;
import android.util.Log;
import it.federico.com.retrofitrx.Model.Repository;
import it.federico.com.retrofitrx.network.RetrofitInterface;
import it.federico.com.retrofitrx.network.controller.RepositoriesController;
import it.federico.com.retrofitrx.network.observer.EndObserver;
import org.json.JSONArray;
import org.json.JSONObject;
import retrofit.RestAdapter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public Subscription getListOfRepositories(final RepositoriesController repositoriesController, final String organization) {
        Observable<Repository[]> observable = retrofitInterface.getListOfRepos(organization);
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EndObserver<Repository[]>() {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        repositoriesController.onEnd();
                    }

                    @Override
                    public void onError(final Throwable throwable) {
                        super.onError(throwable);
                        repositoriesController.onGetRepositoriesError(throwable);
                        repositoriesController.onEnd();
                    }

                    @Override
                    public void onNext(final Repository[] o) {
                        super.onNext(o);
                        repositoriesController.onGetRepositories(o);
                    }
                });
    }
}
