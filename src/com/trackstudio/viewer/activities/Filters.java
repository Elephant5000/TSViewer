package com.trackstudio.viewer.activities;

import android.app.Activity;
import android.os.Bundle;
import com.trackstudio.viewer.fragments.FiltersFragment;

/**
 * Filters activity.
 */
public class Filters extends Activity {
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            FiltersFragment fragment = new FiltersFragment();
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
