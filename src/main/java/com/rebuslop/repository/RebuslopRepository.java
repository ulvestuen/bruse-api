package com.rebuslop.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.rebuslop.model.Rebuslop;
import com.rebuslop.repository.domain.Db2ModelMapper;
import com.rebuslop.repository.domain.TreasureHuntGameItem;
import com.rebuslop.repository.domain.TreasureHuntTaskItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class RebuslopRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RebuslopRepository.class);

    private final DynamoDBMapper dynamoDBMapper;

    public RebuslopRepository(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;

    }

    public Rebuslop getRebuslop(final String gamePin) {
        final var gameItem = dynamoDBMapper.load(TreasureHuntGameItem.class, gamePin);
        LOGGER.info("Request for game with gamePin {}, resulted in:\n{}", gamePin, gameItem);
        final var taskItems = gameItem.getTaskIds().stream()
                                      .map(taskId -> dynamoDBMapper.load(TreasureHuntTaskItem.class, taskId))
                                      .collect(Collectors.toList());
        LOGGER.info("Request for tasks with taskIds {}, resulted in:\n{}",
                    gameItem.getTaskIds(),
                    taskItems);

        return new Rebuslop.Builder(gamePin)
                .tasks(taskItems.stream()
                                .map(Db2ModelMapper::dbTaskItem2Task)
                                .collect(Collectors.toList()))
                .build();
    }
}
