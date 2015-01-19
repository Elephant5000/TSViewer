package com.trackstudio.viewer.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.trackstudio.viewer.models.Constrains;
import com.trackstudio.viewer.models.NotificationItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Notifications updater
 */
public class NotificationsUpdater implements ItemParser<NotificationItem> {
    /**
     * TAG Logger.
     */
    private static final String TAG = NotificationsUpdater.class.getSimpleName();

    /**
     * URL for auth
     */
    private static final String AUTH_URL = "%s/user/auth?login=%s&password=%s";

    /**
     * User service URL.
     */
    private static final String USER_URL = "%s/user/info/%s?login=%s&password=%s";

    /**
     * Messenger service URL.
     */
    private static final String MESSENGER_URL = "%s/messenger/messages/%s?sessionId=%s";


    /**
     * Storage of notification item.
     */
    private final List<NotificationItem> storage;

    /**
     * Notification adapter.
     */
    private final ArrayAdapter adapter;

    /**
     * Default constructor.
     * @param activity Activity
     * @param adapter Adapter
     * @param storage Storage
     */
    public NotificationsUpdater(Activity activity, ArrayAdapter adapter, List<NotificationItem> storage) {
        this.storage = storage;
        this.adapter = adapter;
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        final String url = prefs.getString("url", "");
        final String login = prefs.getString("username", "");
        final String password = prefs.getString("password", "");
        new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    final String sessionId = new Fetcher<String>(
                        String.format(AUTH_URL, url, login, password)
                    ).fetchForText();
                    final String userJson = new Fetcher<>(
                        String.format(USER_URL, url, login, login, password),
                        ItemParser.DUMMY
                    ).fetchForText();
                    final String userId = new JSONObject(userJson).getString("id");
                    new Fetcher<>(
                        String.format(MESSENGER_URL, url, userId, sessionId),
                        NotificationsUpdater.this
                    ).fetchForUI();
                } catch (JSONException ex) {
                    Log.e(TAG, "Convert json", ex);
                }
            }
        }.start();
    }

    @Override
    public List<NotificationItem> parse(String json) {
        final List<NotificationItem> list = new ArrayList<>();
        try {
            final JSONArray array = new JSONArray(json);
            final SimpleDateFormat sdf = new SimpleDateFormat(Constrains.TIME_FORMAT, Locale.US);
            for (int index=0;index!=array.length();++index) {
                final JSONObject message = array.getJSONObject(index);
                list.add(
                    new NotificationItem(
                        message.getString("id"),
                        sdf.format(new Date(message.getLong("time"))),
                        message.getString("text")
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
        this.storage.clear();
        this.storage.addAll(this.parse(json));
        this.adapter.notifyDataSetChanged(); 
    }

    @Override
    public Context context() {
        return this.adapter.getContext();
    }
}
