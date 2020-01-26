package com.rebuslop.repository.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "treasure-hunt-games")
public class TreasureHuntGameItem {

    private String huntId;
    private List<String> taskIds;


    @DynamoDBHashKey(attributeName = "hunt_id")
    public String getHuntId() {
        return huntId;
    }

    public void setHuntId(final String huntId) {
        this.huntId = huntId;
    }

    @DynamoDBAttribute(attributeName = "tasks")
    public List<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(final List<String> taskIds) {
        this.taskIds = taskIds;
    }

    @Override
    public String toString() {
        return "TreasureHuntGameItem{" +
                "huntId='" + huntId + '\'' +
                ", taskIds=" + taskIds +
                '}';
    }
}
