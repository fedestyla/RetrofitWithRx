package it.federico.com.retrofitrx.network.controller;

import it.federico.com.retrofitrx.Model.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by federico on 27/07/2014.
 */
public interface RepositoriesController {

    public abstract void onEnd();

    public abstract void onGetRepositoriesError(Throwable throwable);

    public abstract void onGetRepositories(Repository[] repositories);
}
