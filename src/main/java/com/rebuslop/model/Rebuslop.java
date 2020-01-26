package com.rebuslop.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * The Rebuslop class specifies contents and methods for a treasure hunt game. When initialized, it reads data from an
 * external JSON-file and store the game information in its applicable fields. The class also provide methods for
 * accessing data from the Rebuslop instance.
 */
public class Rebuslop {

    private final String huntId;
    private final List<Task> tasks;


    /**
     * Constructor
     */
    private Rebuslop(final String gamePin, final List<Task> tasks) {
        huntId = gamePin;
        this.tasks = tasks;
    }

    /**
     * Get task at index i.
     */
    public Task getTask(final int i) {
        return tasks.get(i);
    }

    /**
     * Get a list of all tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    public static class Builder {
        private final String gamePin;
        private List<Task> tasks;

        public Builder(final String gamePin) {
            this.gamePin = gamePin;
        }

        public Builder tasks(final List<Task> tasks) {
            this.tasks = tasks;
            return this;
        }

        public Rebuslop build() {
            return new Rebuslop(gamePin, tasks);
        }

    }
}
