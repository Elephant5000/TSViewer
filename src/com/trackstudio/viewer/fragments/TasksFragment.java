package com.trackstudio.viewer.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import com.trackstudio.viewer.adapters.TaskList;
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
}
