package com.trackstudio.viewer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.models.Constrains;
import com.trackstudio.viewer.services.DetailsUpdater;

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
            R.layout.details_fragment, container, false
        );
        this.name = (TextView) view.findViewById(R.id.task_name);
        this.description = (TextView) view.findViewById(R.id.description);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateView(
            getActivity()
                .getIntent()
                .getStringExtra(Constrains.TASK_NUMBER)
        );
    }

    /**
     * Updates the views.
     * @param number selected task
     */
    public void updateView(final String number) {
        if (number != null && !number.isEmpty()) {
            new DetailsUpdater(
                getActivity(),
                this.name,
                this.description,
                number
            );
        } else {
            Toast.makeText(
                getActivity(),
                "Please select task!",
                Toast.LENGTH_SHORT
            ).show();
        }
    }
}
