package com.trackstudio.viewer.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.trackstudio.viewer.models.FilterItem;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Filters service updater.
 */
public class FiltersUpdater implements ItemParser<FilterItem> {
    /**
     * TAG for logger.
     */
    private final static String TAG = FiltersUpdater.class.getSimpleName();

    private final static String URL_PATTER = "%s/task/filter/%s?login=%s&password=%s";

    /**
     * Storage of filter item.
     */
    private final List<FilterItem> storage;

    /**
     * Filter adapter.
     */
    private final ArrayAdapter adapter;

    /**
     * Default constructor.
     * @param storage List of filters
     */
    public FiltersUpdater(Activity activity, ArrayAdapter adapter, List<FilterItem> storage) {
        this.adapter = adapter;
        this.storage = storage;
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        new Fetcher<>(
            String.format(
                URL_PATTER,
                prefs.getString("url", ""),
                prefs.getString("parent_task", ""),
                prefs.getString("username", ""),
                prefs.getString("password", "")
            ),
            this
        ).fetchForUI();
    }

    @Override
    public List<FilterItem> parse(String json) {
        final List<FilterItem> list = new ArrayList<>();
        try {
            final JSONArray array = new JSONArray(json);
            for (int index=0;index!=array.length();++index) {
                list.add(new FilterItem("", array.getString(index)));
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
