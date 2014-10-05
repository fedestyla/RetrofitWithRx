package it.federico.com.retrofitrx.network;


import it.federico.com.retrofitrx.Model.Repository;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface RetrofitInterface {

    @GET("/orgs/{organization}/repos")
    Observable<Repository[]> getListOfRepos(@Path("organization") String organization);

}
