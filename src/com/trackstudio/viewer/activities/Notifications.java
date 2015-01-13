package com.trackstudio.viewer.activities;

import android.app.Activity;
import android.os.Bundle;
import com.trackstudio.viewer.fragments.NotificationsFragment;

/**
 * List of notifications
 */
public class Notifications extends Activity {
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            NotificationsFragment fragment = new NotificationsFragment();
            this.getFragmentManager()
                .beginTransaction()
                .add(
                    android.R.id.content, fragment,
                    fragment.getClass().getSimpleName()
                )
                .commit();
        }
    }
}
