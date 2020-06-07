package dev.bruse.api;

import dev.bruse.model.TaskRequestDto;

public class TaskRequestDtoValidator {

    public static boolean isValid(final TaskRequestDto taskRequestDto) {
        return taskRequestDto.getGamepin().matches("^[a-zA-Z0-9]+$")
                && taskRequestDto.getLat() >= -90.0
                && taskRequestDto.getLat() <= 90.0
                && taskRequestDto.getLon() >= -90.0
                && taskRequestDto.getLon() <= 90.0;
    }

}
