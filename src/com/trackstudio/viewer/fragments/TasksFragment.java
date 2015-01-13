package com.trackstudio.viewer.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.adapters.TaskList;
import com.trackstudio.viewer.activities.DetailsTask;
import com.trackstudio.viewer.models.TaskItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The list of tasks fragments.
 */
public class TasksFragment extends ListFragment {

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setListAdapter(
            new TaskList(this.getActivity(), this.load())
        );
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        DetailsFragment fragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details_fragment);
        if (fragment != null && fragment.isVisible()) {
            fragment.updateView(id);
        } else {
            startActivity(
                new Intent(getActivity(), DetailsTask.class)
            );
        }
    }

    /**
     * The stub for tasks.
     * @return list of tasks
     */
    private List<TaskItem> load() {
        final List<TaskItem> list = new ArrayList<TaskItem>();
        final String submitDate = new Date().toString();
        list.add(new TaskItem("Peter Arsentev", "Task #1", submitDate));
        list.add(new TaskItem("Peter Arsentev", "Task #2", submitDate));
        list.add(new TaskItem("Peter Arsentev", "Task #3", submitDate));
        return list;
    }

    /**
     * Reload the list of tasks by selected filter.
     * @param filterId Selected filter
     */
    public void update(final long filterId) {
    }
}
