package com.trackstudio.viewer.services;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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

    private static final String URL_PATTERN = "%s/task/info/%s?login=%s&password=%s";

    /**
     * Details fetcher.
     */
    private final Fetcher<DetailsItem> fetcher;

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
     * @param number Number
     */
    public DetailsUpdater(Activity activity, TextView name, TextView description, String number) {
        this.name = name;
        this.description = description;
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        this.fetcher = new Fetcher<DetailsItem>(
            String.format(
                URL_PATTERN,
                prefs.getString("url", ""),
                number,
                prefs.getString("username", ""),
                prefs.getString("password", "")
                ),
            this
        );
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
