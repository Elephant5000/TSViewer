package com.trackstudio.viewer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.trackstudio.viewer.R;

/**
 * The details of task - fragment.
 * It shows the tasks name, description
 */
public class DetailsFragment extends Fragment {
    private TextView name;
    private TextView description;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(
            R.layout.details_fragment, null, false
        );
        this.name = (TextView) view.findViewById(R.id.task_name);
        this.description = (TextView) view.findViewById(R.id.description);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateView(1L);
    }

    /**
     * Updates the views.
     * @param id selected task
     */
    public void updateView(long id) {
        this.name.setText("Task #1");
        this.description.setText("Description of task");
    }
}
