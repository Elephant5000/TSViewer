package com.trackstudio.viewer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.fragments.TasksFragment;

/**
 * The main activity.
 */
public class TSViewer extends Activity {
    @Override
    protected void onCreate(final Bundle bundle) {
        super. onCreate(bundle);
        if (bundle == null) {
            TasksFragment fragment = new TasksFragment();
            getFragmentManager()
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, Settings.class));
                return true;
            default:
                return false;
        }
    }
}
