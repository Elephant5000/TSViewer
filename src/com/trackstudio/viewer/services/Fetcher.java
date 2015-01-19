package com.trackstudio.viewer.services;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

/**
 * This class is used for fetching data from URL.
 */
public class Fetcher<T> {
    /**
     *  TAG for logger.
     */
    private final static String TAG = Fetcher.class.getSimpleName();

    /**
     * URL.
     */
    private final String url;

    /**
     * Parser.
     */
    private final ItemParser<T> parser;

    /**
     * Constructor for fetching text
     * @param url URL
     */
    public Fetcher(final String url) {
        this.url = url;
        this.parser = null;
    }

    /**
     * Default constructor.
     * @param url URL
     * @param parser Parser
     */
    public Fetcher(String url, ItemParser<T> parser) {
        this.url = url;
        this.parser = parser;
    }

    /**
     * Fetches the data from URL.
     */
    public void fetchForUI() {
        RequestQueue queue = Volley.newRequestQueue(this.parser.context());
        StringRequest stringRequest = new StringRequest(
            Request.Method.GET, this.url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String json) {
                    Fetcher.this.parser.updateUI(json);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(
                        TAG,
                        String.format("Error in fetching : %s", Fetcher.this.url),
                        error
                    );
                }
            }
        );
        queue.add(stringRequest);
    }

    /**
     * Fetch the pure text.
     * @return String
     */
    public String fetchForText() {
        final StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = null;
            try {
                URLConnection yc = new URL(
                    URLDecoder.decode(this.url, "UTF-8")
                ).openConnection();
                in = new BufferedReader(
                    new InputStreamReader(yc.getInputStream())
                );
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } catch (final Exception ex) {
            Log.e(
                TAG,
                String.format("Error in fetching : %s", this.url),
                ex
            );
        }
        return sb.toString();
    }
}
