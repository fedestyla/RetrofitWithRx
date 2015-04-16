package it.federico.com.retrofitrx.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import it.federico.com.retrofitrx.Model.Repository;
import it.federico.com.retrofitrx.R;
import it.federico.com.retrofitrx.adapter.CustomAdapter;
import it.federico.com.retrofitrx.application.CustomApplication;
import it.federico.com.retrofitrx.network.observer.EndObserver;
import it.federico.com.retrofitrx.utils.ViewUtils;
import rx.Observable;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        Observable<Repository[]> observable = CustomApplication.getInstance().getGithubApi()
                .getListOfRepositories("octokit");
        // let's bind the observable to the fragment so It follows the Fragment lifecycle
        AndroidObservable.bindFragment(MainFragment.this, observable).
        // do we want to cache the response so if anyone else subscribe again to observable after it's finished will
        // receive the same result?
        //        observable.cache()
        // subscribe the observable in a scheduler
        subscribeOn(Schedulers.io()).
        // observe in the mainThread
        observeOn(AndroidSchedulers.mainThread()).
        // subscribe the observable,
        subscribe(new EndObserver<Repository[]>() {
            @Override
            public void onCompleted() {
                super.onCompleted();
                // do whatever you want in the onComplete, keep in mind that if an error occurs this is never called
            }

            @Override
            public void onNext(final Repository[] repositories) {
                super.onNext(repositories);
                // item emitted, use it
                refreshListView(repositories);
            }

            @Override
            public void onError(final Throwable throwable) {
                super.onError(throwable);
                // an error occured during the operation, implements your logic here!
            }
        });
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
