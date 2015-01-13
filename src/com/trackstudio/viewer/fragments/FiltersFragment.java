package com.trackstudio.viewer.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.activities.TSViewer;
import com.trackstudio.viewer.adapters.FilterList;
import com.trackstudio.viewer.models.FilterItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Filters fragments.
 */
public class FiltersFragment extends ListFragment {

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        this.setListAdapter(
            new FilterList(this.getActivity(), this.load())
        );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TasksFragment fragment = (TasksFragment) this.getFragmentManager().findFragmentById(R.id.fragment_tasks);
        if (fragment != null && fragment.isVisible()) {
            fragment.update(id);
        } else {
            this.startActivity(
                new Intent(this.getActivity(), TSViewer.class)
            );
        }
    }

    /**
     * The stub for filters.
     * @return list of filters
     */
    private List<FilterItem> load() {
        final List<FilterItem> list = new ArrayList<FilterItem>();
        list.add(new FilterItem("1", "Filter #1"));
        list.add(new FilterItem("2", "Filter #2"));
        list.add(new FilterItem("3", "Filter #3"));
        return list;
    }
}
