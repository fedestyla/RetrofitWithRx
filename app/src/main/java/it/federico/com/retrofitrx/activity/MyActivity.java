package it.federico.com.retrofitrx.activity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;
import it.federico.com.retrofitrx.Model.Repository;
import it.federico.com.retrofitrx.R;
import it.federico.com.retrofitrx.application.CustomApplication;
import it.federico.com.retrofitrx.fragment.MainFragment;
import it.federico.com.retrofitrx.fragment.ProgressDialogFragment;
import it.federico.com.retrofitrx.network.controller.RepositoriesController;
import org.json.JSONArray;
import org.json.JSONObject;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, MainFragment.newInstance())
                    .commit();
        }

    }

}
