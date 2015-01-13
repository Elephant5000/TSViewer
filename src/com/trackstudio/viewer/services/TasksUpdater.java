package com.trackstudio.viewer.services;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.trackstudio.viewer.models.Constrains;
import com.trackstudio.viewer.models.TaskItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Tasks service updater.
 */
public class TasksUpdater extends AsyncTask<String, Void, List<TaskItem>> implements ItemParser<TaskItem> {
    /**
     * TAG for logger.
     */
    private final static String TAG = TasksUpdater.class.getSimpleName();

    /**
     * URL Pattern.
     */
    private final static String URL_PATTERN =  "%s/task/tasks/%s?filter=%s&login=%s&password=%s";

    /**
     * Tasks fetcher.
     */
    private final Fetcher<TaskItem> fetcher;

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
     * @param activity Activity
     * @param adapter Adapter
     * @param storage Storage
     * @param filter Filter
     */
    public TasksUpdater(Activity activity, ArrayAdapter adapter, List<TaskItem> storage, String filter) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        this.fetcher = new Fetcher<TaskItem>(
            String.format(
                URL_PATTERN,
                prefs.getString("url", ""),
                prefs.getString("parent_task", ""),
                filter,
                prefs.getString("username", ""),
                prefs.getString("password", "")
            ),
            this
        );
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
            final SimpleDateFormat sdf = new SimpleDateFormat(Constrains.TIME_FORMAT, Locale.US);
            for (int index=0;index!=array.length();++index) {
                final JSONObject task = array.getJSONObject(index);
                list.add(
                    new TaskItem(
                        task.getString("submitterName"),
                        task.getString("name"),
                        sdf.format(new Date(task.getLong("submitdate"))),
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
