package com.rebuslop.service;

import com.rebuslop.model.Rebuslop;
import com.rebuslop.model.Task;
import com.rebuslop.model.TaskRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RebuslopService {

    public Task getTask(final TaskRequest taskRequest) {
        return null;
    }

    /**
     * The method findIndexOfAvailableTask returns the index of the first task in the task list of the Rebuslop instance
     * as long as the lat, lon values provided are found to lie within the acceptance radius of a task. If not, the
     * method
     * returns -1.
     *
     * @param rebuslop, active instance of type {@link Rebuslop}
     * @param lat, latitude coordinate value of user measured in degrees.
     * @param lon, longitude coordinate value of user measured in degrees.
     */
    private int findIndexOfAvailableTask(final Rebuslop rebuslop, final double lat, final double lon) {

        return rebuslop.getTaskCoordinatesAndRadius().stream()
                       .map(x -> calculateDistance(lat, lon, x[0], x[1]) < x[2])
                       .collect(ArrayList<Boolean>::new, ArrayList::add, ArrayList::addAll)
                       .indexOf(true);

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
