package dev.bruse.repository.dynamodb.domain;

import dev.bruse.model.Task;

public final class Db2ModelMapper {

    public static Task dbTaskItem2Task(final BruseTaskItem bruseTaskItem) {
        return new Task.Builder(bruseTaskItem.getId())
                .taskDesc(bruseTaskItem.getTaskDesc())
                .lat(bruseTaskItem.getLat())
                .lon(bruseTaskItem.getLon())
                .acceptanceRadius(bruseTaskItem.getAcceptanceRadius())
                .taskContentId(bruseTaskItem.getTaskContentId())
                .build();
    }

}
