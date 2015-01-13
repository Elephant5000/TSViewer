package com.trackstudio.viewer.services;

import java.util.List;

/**
 *  Parser.
 */
public interface ItemParser<T> {
    /**
     * Parses the input JSONObject to list of items.
     * @param json JSONArray
     * @return List of items
     */
    List<T> parse(String json);
}
