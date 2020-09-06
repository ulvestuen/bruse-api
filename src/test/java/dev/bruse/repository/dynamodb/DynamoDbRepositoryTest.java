package dev.bruse.repository.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dev.bruse.repository.dynamodb.domain.BruseGameItem;
import dev.bruse.repository.dynamodb.domain.BruseTaskItem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DynamoDbRepositoryTest {

    private final DynamoDBMapper dynamoDBMapperMock = mock(DynamoDBMapper.class);
    private final DynamoDbRepository dynamoDbRepository = new DynamoDbRepository(dynamoDBMapperMock);

    @Test
    public void getBruse_gamePin_returnsBruse() {
        final var taskId1 = UUID.randomUUID().toString();
        final var taskId2 = UUID.randomUUID().toString();

        final var bruseTaskItem1 = new BruseTaskItem();
        bruseTaskItem1.setId(taskId1);
        final var bruseTaskItem2 = new BruseTaskItem();
        bruseTaskItem2.setId(taskId2);

        final var bruseGameItem = new BruseGameItem();
        bruseGameItem.setId("1");
        bruseGameItem.setTaskIds(List.of(taskId1, taskId2));


        when(dynamoDBMapperMock.load(eq(BruseGameItem.class), anyString()))
                .thenReturn(bruseGameItem);
        when(dynamoDBMapperMock.load(eq(BruseTaskItem.class), anyString()))
                .thenReturn(bruseTaskItem1, bruseTaskItem2);

        final var bruse = dynamoDbRepository.getBruse("1");

        assertEquals(bruseGameItem.getId(), bruse.getId());
        IntStream.range(0, bruseGameItem.getTaskIds().size())
                 .forEach(i -> assertEquals(bruseGameItem.getTaskIds().get(i),
                                            bruse.getTasks().get(i).getTaskId()));

    }

}