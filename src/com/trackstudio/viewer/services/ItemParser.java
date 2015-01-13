package com.trackstudio.viewer.services;

import org.json.JSONObject;

import java.util.List;

/**
 *
 */
public interface ItemParser<T> {
    /**
     * Parses the input JSONObject to list of items.
     * @param json JSONObject
     * @return List of items
     */
    List<T> parse(JSONObject json);
}
