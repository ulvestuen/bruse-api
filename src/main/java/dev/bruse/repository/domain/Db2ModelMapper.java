package dev.bruse.repository.domain;

import dev.bruse.model.Task;
import dev.bruse.model.TaskBodyType;
import dev.bruse.model.TaskContent;

public final class Db2ModelMapper {

    public static Task dbTaskItem2Task(final BruseTaskItem bruseTaskItem) {
        return new Task.Builder(bruseTaskItem.getId())
                .taskDesc(bruseTaskItem.getTaskDesc())
                .taskType(bruseTaskItem.getTaskType())
                .lat(bruseTaskItem.getLat())
                .lon(bruseTaskItem.getLon())
                .acceptanceRadius(bruseTaskItem.getAcceptanceRadius())
                .taskContent(dbTaskContentItem2TaskContent(bruseTaskItem.getTaskContent()))
                .build();
    }

    public static TaskContent dbTaskContentItem2TaskContent(final BruseTaskItem.TaskContent taskContent) {
        return new TaskContent.Builder(dbTaskBody2TaskBody(taskContent.getTaskBodyType()))
                .body(taskContent.getBody())
                .build();
    }

    public static TaskBodyType dbTaskBody2TaskBody(final String taskBodyType) {
        return TaskBodyType.valueOf(taskBodyType.toUpperCase());
    }

}
