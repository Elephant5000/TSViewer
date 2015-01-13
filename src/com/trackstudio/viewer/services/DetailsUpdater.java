package com.trackstudio.viewer.services;

import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import com.trackstudio.viewer.models.DetailsItem;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Updater for details of task.
 */
public class DetailsUpdater extends AsyncTask<String, Void, DetailsItem> implements ItemParser<DetailsItem> {
    /**
     * TAG Logger.
     */
    private static final String TAG = DetailsUpdater.class.getSimpleName();

    /**
     * Details fetcher.
     */
    private final Fetcher<DetailsItem> fetcher = new Fetcher<DetailsItem>(
        "http://192.168.0.100:8888/TrackStudio/rest/task/info/120?login=root&password=root",
        this
    );

    /**
     * Name view.
     */
    private final TextView name;

    /**
     * Description view.
     */
    private final TextView description;

    /**
     * Default constructor.
     * @param name Name
     * @param description Description
     */
    public DetailsUpdater(TextView name, TextView description) {
        this.name = name;
        this.description = description;
    }

    @Override
    protected DetailsItem doInBackground(String... params) {
        return this.fetcher.fetch().items().iterator().next();
    }

    @Override
    protected void onPostExecute(DetailsItem detailsItem) {
        super.onPostExecute(detailsItem);
        this.name.setText(detailsItem.getName());
        this.description.setText(Html.fromHtml(detailsItem.getDescription()));
    }

    @Override
    public List<DetailsItem> parse(String json) {
        final List<DetailsItem> list = new ArrayList<DetailsItem>();
        try {
            JSONObject task = new JSONObject(json);
            list.add(
                new DetailsItem(
                    String.format("%s [#%s]", task.getString("name"), task.getString("number")),
                    task.getString("description")
                )
            );
        } catch (final JSONException ex) {
            Log.e(TAG, "Error parse", ex);
        }
        return list;
    }
}
