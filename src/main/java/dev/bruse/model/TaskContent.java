package dev.bruse.model;

public class TaskContent {

    private final TaskBodyType taskBodyType;
    private final String body;

    private TaskContent(final TaskBodyType taskBodyType,
                        final String body) {
        this.taskBodyType = taskBodyType;
        this.body = body;

    }

    public TaskContentDto convertToDto() {
        return new TaskContentDto().bodyType(TaskBodyTypeDto.fromValue(taskBodyType.name().toLowerCase()))
                                   .body(body);
    }

    public static class Builder {
        private final TaskBodyType taskBodyType;
        private String body;

        public Builder(final TaskBodyType taskBodyType) {
            this.taskBodyType = taskBodyType;
        }

        public Builder body(final String body) {
            this.body = body;
            return this;
        }


        public TaskContent build() {
            return new TaskContent(taskBodyType,
                                   body);
        }
    }

}
