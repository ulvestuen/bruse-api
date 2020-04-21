package dev.bruse.repository.domain;

import dev.bruse.model.Task;
import dev.bruse.model.TaskBodyType;
import dev.bruse.model.TaskContent;

public final class Db2ModelMapper {

    public static Task dbTaskItem2Task(final TreasureHuntTaskItem treasureHuntTaskItem) {
        return new Task.Builder(treasureHuntTaskItem.getTaskId())
                .taskDesc(treasureHuntTaskItem.getTaskDesc())
                .taskType(treasureHuntTaskItem.getTaskType())
                .lat(treasureHuntTaskItem.getLat())
                .lon(treasureHuntTaskItem.getLon())
                .acceptanceRadius(treasureHuntTaskItem.getAcceptanceRadius())
                .taskContent(dbTaskContentItem2TaskContent(treasureHuntTaskItem.getTaskContent()))
                .build();
    }

    public static TaskContent dbTaskContentItem2TaskContent(final TreasureHuntTaskItem.TaskContent taskContent) {
        return new TaskContent.Builder(dbTaskBody2TaskBody(taskContent.getTaskBodyType()))
                .body(taskContent.getBody())
                .images(taskContent.getImages())
                .sounds(taskContent.getSounds())
                .videos(taskContent.getVideos())
                .build();
    }

    public static TaskBodyType dbTaskBody2TaskBody(final String taskBodyType) {
        return TaskBodyType.valueOf(taskBodyType.toUpperCase());
    }

}
