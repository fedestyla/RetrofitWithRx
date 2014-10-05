package it.federico.com.retrofitrx.activity;

import android.app.Activity;
import android.os.Bundle;
import it.federico.com.retrofitrx.R;
import it.federico.com.retrofitrx.fragment.MainFragment;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, MainFragment.newInstance()).commit();
        }

    }

}
