package dev.bruse.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dev.bruse.model.Bruse;
import dev.bruse.repository.domain.Db2ModelMapper;
import dev.bruse.repository.domain.TreasureHuntGameItem;
import dev.bruse.repository.domain.TreasureHuntTaskItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class BruseRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BruseRepository.class);

    private final DynamoDBMapper dynamoDBMapper;

    public BruseRepository(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;

    }

    public Bruse getBruse(final String gamePin) {
        final var gameItem = dynamoDBMapper.load(TreasureHuntGameItem.class, gamePin);
        LOGGER.info("Request for game with gamePin {}, resulted in:\n{}", gamePin, gameItem);
        final var taskItems = gameItem.getTaskIds().stream()
                                      .map(taskId -> dynamoDBMapper.load(TreasureHuntTaskItem.class, taskId))
                                      .collect(Collectors.toList());
        LOGGER.info("Request for tasks with taskIds {}, resulted in:\n{}",
                    gameItem.getTaskIds(),
                    taskItems);

        return new Bruse.Builder(gamePin)
                .tasks(taskItems.stream()
                                .map(Db2ModelMapper::dbTaskItem2Task)
                                .collect(Collectors.toList()))
                .build();
    }
}
