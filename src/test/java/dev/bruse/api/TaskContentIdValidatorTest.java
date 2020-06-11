package dev.bruse.api;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskContentIdValidatorTest {

    @Test
    void isValid_allowedContentId_returnsTrue() {

        assertTrue(List.of(UUID.randomUUID(),
                           UUID.randomUUID(),
                           UUID.randomUUID(),
                           UUID.randomUUID()).stream()
                       .map(UUID::toString)
                       .allMatch(TaskContentIdValidator::isValid));

    }

    @Test
    void isValid_notAllowedContentId_returnsFalse() {

        assertTrue(List.of("abc",
                           "",
                           UUID.randomUUID().toString().substring(1),
                           "e<c7dcf2-5ea6-4031-a8ea-38f8af2329c1").stream()
                       .noneMatch(TaskContentIdValidator::isValid));

    }
}