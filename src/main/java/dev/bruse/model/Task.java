package dev.bruse.model;

import java.util.List;

public class Task {

    private final String taskId;
    private final String taskDesc;
    private final String taskType;
    private final double lat;
    private final double lon;
    private final int acceptanceRadius;
    private final TaskContent taskContent;

    private Task(final String taskId,
                 final String taskDesc,
                 final String taskType,
                 final double lat,
                 final double lon,
                 final int acceptanceRadius,
                 final TaskContent taskContent) {
        this.taskId = taskId;
        this.taskDesc = taskDesc;
        this.taskType = taskType;
        this.lat = lat;
        this.lon = lon;
        this.acceptanceRadius = acceptanceRadius;
        this.taskContent = taskContent;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public String getTaskType() {
        return taskType;
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

    public TaskContent getTaskContent() {
        return taskContent;
    }

    public TaskDto convertToDto() {
        return new TaskDto().taskId(taskId)
                            .taskType(taskType)
                            .latlonCoordinates(List.of(lat, lon))
                            .acceptanceRadius(acceptanceRadius)
                            .taskContent(taskContent.convertToDto());
    }

    public static class Builder {
        private final String taskId;
        private String taskDesc;
        private String taskType;
        private double lat;
        private double lon;
        private int acceptanceRadius;
        private TaskContent taskContent;

        public Builder(final String taskId) {
            this.taskId = taskId;
        }

        public Builder taskDesc(final String taskDesc) {
            this.taskDesc = taskDesc;
            return this;
        }

        public Builder taskType(final String taskType) {
            this.taskType = taskType;
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

        public Builder taskContent(final TaskContent taskContent) {
            this.taskContent = taskContent;
            return this;
        }

        public Task build() {
            return new Task(taskId,
                            taskDesc,
                            taskType,
                            lat,
                            lon,
                            acceptanceRadius,
                            taskContent);
        }
    }
}
