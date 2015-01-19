package com.trackstudio.viewer.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.trackstudio.viewer.models.Constrains;
import com.trackstudio.viewer.models.TaskItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Tasks service updater.
 */
public class TasksUpdater implements ItemParser<TaskItem> {
    /**
     * TAG for logger.
     */
    private final static String TAG = TasksUpdater.class.getSimpleName();

    /**
     * URL Pattern.
     */
    private final static String URL_PATTERN =  "%s/task/tasks/%s?filter=%s&login=%s&password=%s";

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
        this.storage = storage;
        this.adapter = adapter;
        try {
            new Fetcher<>(
                String.format(
                    URL_PATTERN,
                    prefs.getString("url", ""),
                    prefs.getString("parent_task", ""),
                    URLEncoder.encode(filter, "UTF-8"),
                    prefs.getString("username", ""),
                    prefs.getString("password", "")
                ),
                this
            ).fetchForUI();
        } catch (final UnsupportedEncodingException ex) {
            Log.e(TAG, "Error encoding", ex);
        }
    }

    @Override
    public List<TaskItem> parse(String json) {
        final List<TaskItem> list = new ArrayList<>();
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

    @Override
    public void updateUI(final String json) {
        storage.clear();
        storage.addAll(this.parse(json));
        adapter.notifyDataSetChanged();
    }

    @Override
    public Context context() {
        return this.adapter.getContext();
    }
}
