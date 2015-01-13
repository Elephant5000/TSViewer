package com.trackstudio.viewer.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import com.trackstudio.viewer.adapters.NotificationList;
import com.trackstudio.viewer.models.NotificationItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Notifications fragment.
 */
public class NotificationsFragment extends ListFragment {

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        this.setListAdapter(
            new NotificationList(this.getActivity(), this.load())
        );
    }

    /**
     * The stub for notifications.
     * @return list of notifications
     */
    private List<NotificationItem> load() {
        final List<NotificationItem> list = new ArrayList<NotificationItem>();
        final String submitDate = new Date().toString();
        list.add(new NotificationItem("1", submitDate, "Notification #1"));
        list.add(new NotificationItem("2", submitDate, "Notification #2"));
        list.add(new NotificationItem("3", submitDate, "Notification #3"));
        return list;
    }
}
