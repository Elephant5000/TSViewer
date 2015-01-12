package com.trackstudio.viewer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The main activity.
 */
public class TSViewer extends Activity {
    @Override
    protected void onCreate(final Bundle bundle) {
        super. onCreate(bundle);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        return true;
    }
}
