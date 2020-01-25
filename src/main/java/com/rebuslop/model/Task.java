package com.rebuslop.model;

import java.util.List;

public class Task {

    private final String taskId;
    private final String taskDesc;
    private final String taskType;
    private final double lat;
    private final double lon;
    private final int acceptanceRadius;
    private final TaskContent taskContent;

    public Task(final String taskId,
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

    public TaskDto convertToDto() {
        return new TaskDto().taskId(taskId)
                            .taskType(taskType)
                            .latlonCoordinates(List.of(lat, lon))
                            .acceptanceRadius(acceptanceRadius)
                            .taskContent(taskContent.convertToDto());
    }
}
