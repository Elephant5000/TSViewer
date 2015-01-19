package com.trackstudio.viewer.services;

import android.content.Context;
import android.test.mock.MockContext;

import java.util.Collections;
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

    /**
     * Updates UI. 
     * @param json JSONArray*
     */
    void updateUI(String json);

    /**
     * Returns context. 
     * @return Context
     */
    Context context();
    
    /**
     * Dummy parser.
     */
    ItemParser<String> DUMMY = new ItemParser<String>() {
        @Override
        public List<String> parse(String json) {
            return Collections.emptyList();
        }

        @Override
        public void updateUI(String json) {
        }

        @Override
        public Context context() {
            return new MockContext();
        }
    };
}
