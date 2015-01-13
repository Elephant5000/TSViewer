package com.trackstudio.viewer.services;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;

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
     * Result.
     */
    private final StringBuilder sb = new StringBuilder();

    /**
     * Parser.
     */
    private final ItemParser<T> parser;

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
     * @return Fetcher.
     */
    public Fetcher<T> fetch() {
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
        return this;
    }

    /**
     * Get raw text from URL.
     * @return RAW Content
     */
    public String raw() {
        return this.sb.toString();
    }

    /**
     * Returns the parsed item from content.
     * @return Items
     */
    public List<T> items() {
        return this.parser.parse(this.sb.toString());
    }
}
