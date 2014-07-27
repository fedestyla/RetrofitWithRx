package it.federico.com.retrofitrx.application;

import android.app.Application;
import it.federico.com.retrofitrx.network.api.GithubApi;

public class CustomApplication extends Application {

    private static CustomApplication instance;
    private GithubApi githubApi;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        githubApi = new GithubApi();
    }

    public static CustomApplication getInstance() {
        return instance;
    }

    public GithubApi getGithubApi() {
        return githubApi;
    }
}
