package dev.bruse.repository.dynamodb.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "bruse-tasks")
public class BruseTaskItem {

    private String id;
    private String taskDesc;
    private double lat;
    private double lon;
    private int acceptanceRadius;
    private String taskContentId;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "task_desc")
    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(final String taskDesc) {
        this.taskDesc = taskDesc;
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

    @DynamoDBAttribute(attributeName = "task_content_id")
    public String getTaskContentId() {
        return taskContentId;
    }

    public void setTaskContentId(final String taskContentId) {
        this.taskContentId = taskContentId;
    }

    @Override
    public String toString() {
        return "BruseTaskItem{" +
                "taskId='" + id + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", acceptanceRadius=" + acceptanceRadius +
                ", taskContentId=" + taskContentId +
                '}';
    }
}
