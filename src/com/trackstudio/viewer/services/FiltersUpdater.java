package com.trackstudio.viewer.services;

import android.os.AsyncTask;
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
public class FiltersUpdater extends AsyncTask<String, Void, List<FilterItem>> implements ItemParser<FilterItem> {
    /**
     * TAG for logger.
     */
    private final static String TAG = FiltersUpdater.class.getSimpleName();

    /**
     * Filter fetcher.
     */
    private final Fetcher<FilterItem> fetcher = new Fetcher<FilterItem>(
        "http://192.168.0.100:8888/TrackStudio/rest/task/filter/95?login=root&password=root",
        this
    );

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
    public FiltersUpdater(ArrayAdapter adapter, List<FilterItem> storage) {
        this.adapter = adapter;
        this.storage = storage;
    }

    @Override
    protected List<FilterItem> doInBackground(String... params) {
        return fetcher.fetch().items();
    }

    @Override
    protected void onPostExecute(final List<FilterItem> items) {
        storage.clear();
        storage.addAll(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public List<FilterItem> parse(String json) {
        final List<FilterItem> list = new ArrayList<FilterItem>();
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
}
