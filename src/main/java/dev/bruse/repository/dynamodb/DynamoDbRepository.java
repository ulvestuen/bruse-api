package dev.bruse.repository.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dev.bruse.model.Bruse;
import dev.bruse.repository.BruseRepository;
import dev.bruse.repository.dynamodb.domain.Db2ModelMapper;
import dev.bruse.repository.dynamodb.domain.BruseGameItem;
import dev.bruse.repository.dynamodb.domain.BruseTaskItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class DynamoDbRepository implements BruseRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);

    private final DynamoDBMapper dynamoDBMapper;

    public DynamoDbRepository(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;

    }

    public Bruse getBruse(final String gamePin) {
        final var gameItem = dynamoDBMapper.load(BruseGameItem.class, gamePin);
        LOGGER.debug("Request for game with gamePin {}, resulted in:\n{}", gamePin, gameItem);
        final var taskItems = gameItem.getTaskIds().stream()
                                      .map(taskId -> dynamoDBMapper.load(BruseTaskItem.class,
                                                                         taskId))
                                      .collect(Collectors.toList());
        LOGGER.debug("Request for tasks with taskIds {}, resulted in:\n{}",
                     gameItem.getTaskIds(),
                     taskItems);

        return new Bruse.Builder(gamePin)
                .tasks(taskItems.stream()
                                .map(Db2ModelMapper::dbTaskItem2Task)
                                .collect(Collectors.toList()))
                .build();
    }
}
