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
     * Number.
     */
    private final String number;

    /**
     * Default constructor.
     * @param submitter Submitter
     * @param taskName Task name
     * @param submitDate Submit date
     * @param number Number
     */
    public TaskItem(String submitter, String taskName, String submitDate, String number) {
        this.submitter = submitter;
        this.taskName = taskName;
        this.submitDate = submitDate;
        this.number = number;
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

    /**
     * Get Number.
     * @return Number
     */
    public String getNumber() {
        return this.number;
    }
}
