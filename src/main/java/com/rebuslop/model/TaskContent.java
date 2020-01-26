package com.rebuslop.model;

import java.util.List;

public class TaskContent {

    private final TaskBodyType taskBodyType;
    private final String body;
    private final List<String> images;
    private final List<String> videos;
    private final List<String> sounds;


    private TaskContent(final TaskBodyType taskBodyType,
                        final String body,
                        final List<String> images,
                        final List<String> videos,
                        final List<String> sounds) {
        this.taskBodyType = taskBodyType;
        this.body = body;
        this.images = images;
        this.videos = videos;
        this.sounds = sounds;

    }

    public TaskContentDto convertToDto() {
        return new TaskContentDto().bodyType(TaskBodyTypeDto.fromValue(taskBodyType.name().toLowerCase()))
                                   .body(body)
                                   .images(images)
                                   .videos(videos)
                                   .sounds(sounds);
    }

    public static class Builder {
        private final TaskBodyType taskBodyType;
        private String body;
        private List<String> images;
        private List<String> videos;
        private List<String> sounds;

        public Builder(final TaskBodyType taskBodyType) {
            this.taskBodyType = taskBodyType;
        }

        public Builder body(final String body) {
            this.body = body;
            return this;
        }

        public Builder images(final List<String> images) {
            this.images = images;
            return this;
        }

        public Builder videos(final List<String> videos) {
            this.videos = videos;
            return this;
        }

        public Builder sounds(final List<String> sounds) {
            this.sounds = sounds;
            return this;
        }

        public TaskContent build() {
            return new TaskContent(taskBodyType,
                                   body,
                                   images,
                                   videos,
                                   sounds);
        }
    }

}
