package com.trackstudio.viewer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.fragments.NotificationsFragment;

/**
 * List of notifications
 */
public class Notifications extends Activity {
    /**
     *  Notification fragment.
     */
    private final NotificationsFragment fragment = new NotificationsFragment();
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.getFragmentManager()
                .beginTransaction()
                .add(
                    android.R.id.content, fragment,
                    fragment.getClass().getSimpleName()
                )
                .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                this.fragment.updateUI();
                return true;
        }
        return false;
    }
}
