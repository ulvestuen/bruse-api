package dev.bruse.model;

public class TaskContentInfo {

    private final String taskContentId;
    private final String taskContentUrl;
    private final String taskContentType;


    public TaskContentInfo(final String taskContentId,
                           final String taskContentUrl,
                           final String taskContentType) {
        this.taskContentId = taskContentId;
        this.taskContentUrl = taskContentUrl;
        this.taskContentType = taskContentType;
    }

    public static TaskContentInfo fromDto(final TaskContentInfoDto taskContentInfoDto) {
        return new TaskContentInfo(taskContentInfoDto.getTaskContentId(),
                                   taskContentInfoDto.getTaskContentUrl(),
                                   taskContentInfoDto.getTaskContentType());

    }

    public String getTaskContentId() {
        return taskContentId;
    }

    public String getTaskContentUrl() {
        return taskContentUrl;
    }

    public String getTaskContentType() {
        return taskContentType;
    }
}
