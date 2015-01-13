package com.trackstudio.viewer.activities;

import android.app.Activity;
import android.os.Bundle;
import com.trackstudio.viewer.fragments.DetailsFragment;

/**
 * The details of task activity
 */
public class DetailsTask extends Activity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            DetailsFragment fragment = new DetailsFragment();
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