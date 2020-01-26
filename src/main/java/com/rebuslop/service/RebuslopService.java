package com.rebuslop.service;

import com.rebuslop.model.Rebuslop;
import com.rebuslop.model.Task;
import com.rebuslop.model.TaskRequest;
import com.rebuslop.repository.RebuslopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RebuslopService {

    final RebuslopRepository rebuslopRepository;

    @Autowired
    public RebuslopService(final RebuslopRepository rebuslopRepository) {
        this.rebuslopRepository = rebuslopRepository;
    }

    /**
     * The method getTask returns the first task in the task list of the Rebuslop instance
     * as long as the lat, lon values provided are found to lie within the acceptance radius of a task. If not, the
     * method returns an empty Optional<Task>.
     *
     * @param taskRequest, instance of type {@link TaskRequest}
     * @return object of type {@link Task} wrapped in an Optional.
     */
    public Optional<Task> getAvailableTask(final TaskRequest taskRequest) {
        final Rebuslop rebuslop = rebuslopRepository.getRebuslop(taskRequest.getGamePin());
        return rebuslop.getTasks().stream()
                .filter(task -> calculateDistance(taskRequest.getLat(),
                                                  taskRequest.getLon(),
                                                  task.getLat(),
                                                  task.getLon()) < task.getAcceptanceRadius())
                .findFirst();
    }

    /**
     * The method calculateDistance returns the distance in meters between geographical points given by the pairs
     * (dLatA,dLonA) and
     * (dLatB,dLonB)
     *
     * @param dLatA, latitude coordinate of point A.
     * @param dLonA, latitude coordinate of point A.
     * @param dLatB, latitude coordinate of point B.
     * @param dLonB, latitude coordinate of point B.
     */
    private double calculateDistance(final double dLatA, final double dLonA, final double dLatB, final double dLonB) {

        final double rLatA = dLatA * (Math.PI / 180);
        final double rLatB = dLatB * (Math.PI / 180);
        final double rLonA = dLonA * (Math.PI / 180);
        final double rLonB = dLonB * (Math.PI / 180);

        return 2 * 6371000 * Math.asin(Math.sqrt(Math.pow(Math.sin((rLatA - rLatB) / 2), 2) + Math.cos(rLatA) * Math.cos(rLatB) * Math.pow(Math.sin((rLonA - rLonB) / 2), 2)));

    }

}
