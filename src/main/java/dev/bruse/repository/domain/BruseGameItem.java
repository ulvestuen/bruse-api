package dev.bruse.repository.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "bruse-games")
public class BruseGameItem {

    private String id;
    private List<String> taskIds;


    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
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
        return "BruseGameItem{" +
                "id='" + id + '\'' +
                ", taskIds=" + taskIds +
                '}';
    }
}
