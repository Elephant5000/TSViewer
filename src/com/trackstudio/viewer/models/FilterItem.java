package com.trackstudio.viewer.models;

/**
 * FilterItem model.
 */
public class FilterItem {
    /**
     * Filter id.
     */
    private final String id;

    /**
     * Filter name.
     */
    private final String name;

    /**
     * Default constructor.
     * @param id Filter id
     * @param name Filter name
     */
    public FilterItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get filter id.
     * @return Filter id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get filter name.
     * @return Filter name
     */
    public String getName() {
        return this.name;
    }
}
