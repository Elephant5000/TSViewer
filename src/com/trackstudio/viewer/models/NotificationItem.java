package com.trackstudio.viewer.models;

/**
 * Notification model.
 */
public class NotificationItem {
    /**
     * Notification id.
     */
    private final String id;

    /**
     * Notification date.
     */
    private final String date;

    /**
     * Notification text.
     */
    private final String text;

    /**
     * Default constructor.
     * @param id Id
     * @param date Date
     * @param text Text
     */
    public NotificationItem(String id, String date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    /**
     * Get notification id.
     * @return Notification id
     */
    public String getId() {
        return id;
    }

    /**
     * Get notification date.
     * @return Notification date
     */
    public String getDate() {
        return date;
    }

    /**
     * Get notification text.
     * @return Notification text
     */
    public String getText() {
        return text;
    }
}
