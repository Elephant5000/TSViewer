package com.trackstudio.viewer.services;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.trackstudio.viewer.models.TaskItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Tasks service updater.
 */
public class TasksUpdater extends AsyncTask<String, Void, List<TaskItem>> implements ItemParser<TaskItem> {
    /**
     * TAG for logger.
     */
    private final static String TAG = TasksUpdater.class.getSimpleName();

    /**
     * Tasks fetcher.
     */
    private final Fetcher<TaskItem> fetcher = new Fetcher<TaskItem>(
        "http://192.168.0.100:8888/TrackStudio/rest/task/tasks/95?filter=All%20tasks&login=root&password=root",
        this
    );

    /**
     * Storage of tasks item.
     */
    private final List<TaskItem> storage;

    /**
     * Task adapter.
     */
    private final ArrayAdapter adapter;

    /**
     * Default constructor.
     * @param adapter Adapter
     * @param storage Storage
     */
    public TasksUpdater(ArrayAdapter adapter, List<TaskItem> storage) {
        this.storage = storage;
        this.adapter = adapter;
    }

    @Override
    protected List<TaskItem> doInBackground(String... params) {
        return this.fetcher.fetch().items();
    }

    @Override
    protected void onPostExecute(List<TaskItem> items) {
        super.onPostExecute(items);
        storage.clear();
        storage.addAll(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public List<TaskItem> parse(String json) {
        final List<TaskItem> list = new ArrayList<TaskItem>();
        try {
            final JSONArray array = new JSONArray(json);
            for (int index=0;index!=array.length();++index) {
                final JSONObject task = array.getJSONObject(index);
                list.add(
                    new TaskItem(
                        task.getString("submitterName"),
                        task.getString("name"),
                        new Date(task.getLong("submitdate")).toString(),
                        task.getString("number")
                    )
                );
            }
        } catch (final JSONException ex) {
            Log.e(TAG, "Error parse", ex);
        }
        return list;
    }
}
