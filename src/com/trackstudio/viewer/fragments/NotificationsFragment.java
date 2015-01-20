package com.trackstudio.viewer.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.trackstudio.viewer.adapters.NotificationList;
import com.trackstudio.viewer.models.NotificationItem;
import com.trackstudio.viewer.services.NotificationsUpdater;

import java.util.ArrayList;
import java.util.List;

/**
 * Notifications fragment.
 */
public class NotificationsFragment extends ListFragment {
    /**
     * Storage filters
     */
    private final List<NotificationItem> list = new ArrayList<>();

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        this.setListAdapter(
            new NotificationList(this.getActivity(), this.list)
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateUI();
    }

    /**
     * Updates UI.
     */
    public void updateUI() {
        new NotificationsUpdater(
            getActivity(),
            (ArrayAdapter) this.getListAdapter(),
            this.list
        );
    }
}
