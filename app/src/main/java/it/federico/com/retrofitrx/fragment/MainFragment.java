package it.federico.com.retrofitrx.fragment;



import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.federico.com.retrofitrx.Model.Repository;
import it.federico.com.retrofitrx.R;
import it.federico.com.retrofitrx.adapter.CustomAdapter;
import it.federico.com.retrofitrx.application.CustomApplication;
import it.federico.com.retrofitrx.network.controller.RepositoriesController;
import it.federico.com.retrofitrx.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getCanonicalName();

    @InjectView(R.id.listview)
    ListView listView;
    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;

    private ArrayList<Repository> organizationRepositories = new ArrayList<Repository>();
    private CustomAdapter customAdapter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = createView(inflater, container, savedInstanceState);
        buildViews();
        return root;
    }

    private void buildViews() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {

            }
        });
        customAdapter = new CustomAdapter(organizationRepositories, getActivity());
        listView.setAdapter(customAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Start loading
        ViewUtils.setViewVisible(progressBar);
        ViewUtils.setViewGone(listView);
        CustomApplication.getInstance().getGithubApi().getListOfRepositories(new RepositoriesController() {
            @Override
            public void onEnd() {
                Log.d(TAG, "onEnd Called");
                ViewUtils.setViewGone(progressBar);
                ViewUtils.setViewVisible(listView);
            }

            @Override
            public void onGetRepositoriesError(final Throwable throwable) {
                Log.d(TAG, "onGetRepositoriesError Called");
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onGetRepositories(final Repository[] repositories) {
                Log.d(TAG, "onGetRepositories Called");
                refreshListView(repositories);
            }
        }, "octokit");
    }

    private void refreshListView(Repository[] repositories) {
        organizationRepositories.clear();
        organizationRepositories.addAll(Arrays.asList(repositories));
        customAdapter.notifyDataSetChanged();
    }

    private View createView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


}
