package com.trackstudio.viewer.activities;

import android.app.Activity;
import android.os.Bundle;
import com.trackstudio.viewer.fragments.SettingsFragment;

/**
 * Setting activity.
 */
public class Settings extends Activity {

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            SettingsFragment fragment = new SettingsFragment();
            getFragmentManager()
                .beginTransaction()
                .add(
                    android.R.id.content, fragment,
                    fragment.getClass().getSimpleName()
                )
                .commit();
        }
    }
}
