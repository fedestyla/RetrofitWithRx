package it.federico.com.retrofitrx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import it.federico.com.retrofitrx.Model.Repository;
import it.federico.com.retrofitrx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico on 27/07/2014.
 */
public class CustomAdapter extends BaseAdapter {

    List<Repository> repositoryList;
    Context context;

    public CustomAdapter(ArrayList<Repository> repositories, Context context) {
        repositoryList = repositories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return repositoryList.size();
    }

    @Override
    public Repository getItem(final int position) {
        return repositoryList.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view;
        final Model.ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = createView(inflater, parent);
        } else {
            // Recycle the old view.
            view = convertView;
        }
        viewHolder = (Model.ViewHolder) view.getTag();
        bindView(viewHolder, getItem(position));
        return view;
    }

    private void bindView(final Model.ViewHolder viewHolder, final Repository item) {
        viewHolder.repositoryName.setText(item.fullName);
    }

    private View createView(final LayoutInflater inflater, final ViewGroup parent) {
        View view = inflater.inflate(R.layout.custom_adapter_item, parent, false);
        Model.ViewHolder viewHolder = new Model.ViewHolder();
        viewHolder.repositoryName = ButterKnife.findById(view, R.id.repository_name_label);
        view.setTag(viewHolder);
        return view;
    }

    public static class Model {

        static final class ViewHolder {
            TextView repositoryName;
        }

    }
}
