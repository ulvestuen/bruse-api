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

    public TaskContentInfo(final TaskContentInfoDto taskContentInfoDto) {
        taskContentId = taskContentInfoDto.getTaskContentId();
        taskContentUrl = taskContentInfoDto.getTaskContentUrl();
        taskContentType = taskContentInfoDto.getTaskContentType();
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
