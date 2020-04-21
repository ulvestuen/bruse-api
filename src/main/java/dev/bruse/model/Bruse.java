package dev.bruse.model;

import java.util.List;


/**
 * The Bruse class specifies contents and methods for a treasure hunt game. When initialized, it reads data from an
 * external JSON-file and store the game information in its applicable fields. The class also provide methods for
 * accessing data from the Bruse instance.
 */
public class Bruse {

    private final String huntId;
    private final List<Task> tasks;


    /**
     * Constructor
     */
    private Bruse(final String gamePin, final List<Task> tasks) {
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

        public Bruse build() {
            return new Bruse(gamePin, tasks);
        }

    }
}
