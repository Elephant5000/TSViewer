package com.trackstudio.viewer.models;

/**
 * DetailsItem of task
 */
public class DetailsItem {
    /**
     * Name.
     */
    private final String name;

    /**
     * Description.
     */
    private final String description;

    /**
     * Default constructor.
     * @param name Name
     * @param description description
     */
    public DetailsItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Get name.
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Get description.
     * @return Description
     */
    public String getDescription() {
        return description;
    }
}
