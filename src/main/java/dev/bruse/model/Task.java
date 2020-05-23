package dev.bruse.model;

import java.util.List;

public class Task {

    private final String taskId;
    private final String taskDesc;
    private final double lat;
    private final double lon;
    private final int acceptanceRadius;
    private final String taskContentId;

    private Task(final String taskId,
                 final String taskDesc,
                 final double lat,
                 final double lon,
                 final int acceptanceRadius,
                 final String taskContentId) {
        this.taskId = taskId;
        this.taskDesc = taskDesc;
        this.lat = lat;
        this.lon = lon;
        this.acceptanceRadius = acceptanceRadius;
        this.taskContentId = taskContentId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getAcceptanceRadius() {
        return acceptanceRadius;
    }

    public String getTaskContentId() {
        return taskContentId;
    }

    public TaskDto convertToDto() {
        return new TaskDto().taskId(taskId)
                            .latlonCoordinates(List.of(lat, lon))
                            .acceptanceRadius(acceptanceRadius)
                            .taskContentId(taskContentId);
    }

    public static class Builder {
        private final String taskId;
        private String taskDesc;
        private double lat;
        private double lon;
        private int acceptanceRadius;
        private String taskContentId;

        public Builder(final String taskId) {
            this.taskId = taskId;
        }

        public Builder taskDesc(final String taskDesc) {
            this.taskDesc = taskDesc;
            return this;
        }

        public Builder lat(final double lat) {
            this.lat = lat;
            return this;
        }

        public Builder lon(final double lon) {
            this.lon = lon;
            return this;
        }

        public Builder acceptanceRadius(final int acceptanceRadius) {
            this.acceptanceRadius = acceptanceRadius;
            return this;
        }

        public Builder taskContentId(final String taskContentId) {
            this.taskContentId = taskContentId;
            return this;
        }

        public Task build() {
            return new Task(taskId,
                            taskDesc,
                            lat,
                            lon,
                            acceptanceRadius,
                            taskContentId);
        }
    }
}
