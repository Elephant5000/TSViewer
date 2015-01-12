package com.trackstudio.viewer.models;

/**
 * Item describes the task fields
 */
public class TaskItem {
    /**
     * Submitter.
     */
    private final String submitter;

    /**
     * Task name.
     */
    private final String taskName;

    /**
     * Submit date.
     */
    private final String submitDate;

    /**
     * Default constructor.
     * @param submitter Submitter
     * @param taskName Task name
     * @param submitDate Submit date
     */
    public TaskItem(String submitter, String taskName, String submitDate) {
        this.submitter = submitter;
        this.taskName = taskName;
        this.submitDate = submitDate;
    }

    /**
     * Get submitter.
     * @return Submitter
     */
    public String getSubmitter() {
        return this.submitter;
    }

    /**
     * Get task name.
     * @return Task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Get submit date.
     * @return Submit date
     */
    public String getSubmitDate() {
        return this.submitDate;
    }
}
