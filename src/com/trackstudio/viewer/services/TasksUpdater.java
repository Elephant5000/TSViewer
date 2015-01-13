package com.trackstudio.viewer.services;

import android.os.AsyncTask;
import android.util.Log;
import com.trackstudio.viewer.models.TaskItem;
import java.util.Collections;
import java.util.List;

/**
 * Tasks service updater.
 */
public class TasksUpdater extends AsyncTask<String, Void, String> implements ItemParser<TaskItem> {
    /**
     * TAG for logger.
     */
    private final static String TAG = TasksUpdater.class.getSimpleName();

    @Override
    protected String doInBackground(String... params) {
        final String result = new Fetcher<TaskItem>(
            "http://192.168.0.100:8888/TrackStudio/rest/task/filter/95?login=root&password=root",
            this
        ).fetch().raw();
        Log.d(TAG, result);
        return result;
    }

    @Override
    public List<TaskItem> parse(String json) {
        return Collections.emptyList();
    }
}
