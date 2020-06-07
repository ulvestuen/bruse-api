package dev.bruse.api;

import dev.bruse.model.TaskRequestDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskRequestDtoValidatorTest {

    @Test
    void isValid_allowedValues_returnsTrue() {

        assertTrue(List.of("123", "a1b23c", "A1B23C", "a1B23c").stream()
                       .map(it -> new TaskRequestDto().gamepin(it)
                                                      .lat(Math.random() * 180 - 90)
                                                      .lon(Math.random() * 180 - 90))
                       .allMatch(TaskRequestDtoValidator::isValid));

    }

    @Test
    void isNotValid_illegalGamePin_returnsFalse() {

        assertTrue(List.of("123_", "*", "{A1B23C}", "<>'").stream()
                       .map(it -> new TaskRequestDto().gamepin(it)
                                                      .lat(Math.random() * 180 - 90)
                                                      .lon(Math.random() * 180 - 90))
                       .noneMatch(TaskRequestDtoValidator::isValid));

    }

    @Test
    void isNotValid_coordinatesOutOfRange_returnsFalse() {

        assertTrue(List.of("123", "a1b23c", "A1B23C", "a1B23c").stream()
                       .map(it -> new TaskRequestDto().gamepin(it)
                                                      .lat(Math.random() * 180 + 90.1)
                                                      .lon(Math.random() * 180 - 270.1))
                       .noneMatch(TaskRequestDtoValidator::isValid));

    }
}