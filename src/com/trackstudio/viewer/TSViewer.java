package com.trackstudio.viewer;

import android.app.Activity;
import android.os.Bundle;

/**
 * The main activity.
 */
public class TSViewer extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
