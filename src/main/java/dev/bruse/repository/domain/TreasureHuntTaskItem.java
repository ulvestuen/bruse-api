package dev.bruse.repository.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "treasure-hunt-tasks")
public class TreasureHuntTaskItem {

    private String taskId;
    private String taskDesc;
    private String taskType;
    private double lat;
    private double lon;
    private int acceptanceRadius;
    private TaskContent taskContent;

    @DynamoDBHashKey(attributeName = "task_id")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(final String taskId) {
        this.taskId = taskId;
    }

    @DynamoDBAttribute(attributeName = "task_desc")
    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(final String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @DynamoDBAttribute(attributeName = "task_type")
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(final String taskType) {
        this.taskType = taskType;
    }

    @DynamoDBAttribute(attributeName = "lat")
    public double getLat() {
        return lat;
    }

    public void setLat(final double lat) {
        this.lat = lat;
    }

    @DynamoDBAttribute(attributeName = "lon")
    public double getLon() {
        return lon;
    }

    public void setLon(final double lon) {
        this.lon = lon;
    }

    @DynamoDBAttribute(attributeName = "acceptance_radius")
    public int getAcceptanceRadius() {
        return acceptanceRadius;
    }

    public void setAcceptanceRadius(final int acceptanceRadius) {
        this.acceptanceRadius = acceptanceRadius;
    }

    @DynamoDBAttribute(attributeName = "task_content")
    public TaskContent getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(final TaskContent taskContent) {
        this.taskContent = taskContent;
    }

    @Override
    public String toString() {
        return "TreasureHuntTaskItem{" +
                "taskId='" + taskId + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskType='" + taskType + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", acceptanceRadius=" + acceptanceRadius +
                ", taskContent=" + taskContent +
                '}';
    }

    @DynamoDBDocument
    public static class TaskContent {

        private String taskBodyType;
        private String body;
        private List<String> images;
        private List<String> sounds;
        private List<String> videos;

        @DynamoDBAttribute(attributeName = "task_body_type")
        public String getTaskBodyType() {
            return taskBodyType;
        }

        public void setTaskBodyType(final String taskBodyType) {
            this.taskBodyType = taskBodyType;
        }

        @DynamoDBAttribute(attributeName = "body")
        public String getBody() {
            return body;
        }

        public void setBody(final String body) {
            this.body = body;
        }

        @DynamoDBAttribute(attributeName = "images")
        public List<String> getImages() {
            return images;
        }

        public void setImages(final List<String> images) {
            this.images = images;
        }

        @DynamoDBAttribute(attributeName = "sounds")
        public List<String> getSounds() {
            return sounds;
        }

        public void setSounds(final List<String> sounds) {
            this.sounds = sounds;
        }

        @DynamoDBAttribute(attributeName = "videos")
        public List<String> getVideos() {
            return videos;
        }

        public void setVideos(final List<String> videos) {
            this.videos = videos;
        }

        @Override
        public String toString() {
            return "TaskContent{" +
                    "taskBodyType='" + taskBodyType + '\'' +
                    ", body='" + body + '\'' +
                    ", images=" + images +
                    ", sounds=" + sounds +
                    ", videos=" + videos +
                    '}';
        }
    }
}
