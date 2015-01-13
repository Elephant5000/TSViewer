package com.trackstudio.viewer.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.adapters.TaskList;
import com.trackstudio.viewer.activities.DetailsTask;
import com.trackstudio.viewer.models.Constrains;
import com.trackstudio.viewer.models.TaskItem;
import com.trackstudio.viewer.services.TasksUpdater;
import java.util.ArrayList;
import java.util.List;

/**
 * The list of tasks fragments.
 */
public class TasksFragment extends ListFragment {
    /**
     * TAG Logger.
     */
    private final static String TAG = TasksFragment.class.getSimpleName();

    /**
     * Storage tasks
     */
    private final List<TaskItem> list = new ArrayList<TaskItem>();

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setListAdapter(
            new TaskList(this.getActivity(), this.list)
        );
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        final Button refresh = new Button(getActivity());
        refresh.setText("Refresh");
        refresh.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TasksFragment.this.updateUI();
                }
            }
        );
        getListView().addHeaderView(refresh);
        this.updateUI();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        DetailsFragment fragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details_fragment);
        final String number = this.list.get(position-1).getNumber();
        if (fragment != null && fragment.isVisible()) {
            fragment.updateView(number);
        } else {
            startActivity(
                new Intent(getActivity(), DetailsTask.class)
                    .putExtra(
                        Constrains.TASK_NUMBER,
                        number
                    )
            );
        }
    }

    /**
     * Reload the list of tasks by selected filter.
     * @param filterId Selected filter
     */
    public void update(final long filterId) {
        Log.d(TAG, String.format("Update %s", filterId));
    }

    /**
     * Updates UI.
     */
    public void updateUI() {
        new TasksUpdater(
            getActivity(),
            (ArrayAdapter) this.getListAdapter(),
            this.list
        ).execute();
    }
}
