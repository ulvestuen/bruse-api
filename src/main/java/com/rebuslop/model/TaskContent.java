package com.rebuslop.model;

import java.util.List;

public class TaskContent {

    private final TaskBodyType taskBodyType;
    private final String body;
    private final List<String> images;
    private final List<String> videos;
    private final List<String> sounds;


    public TaskContent(final TaskBodyType taskBodyType,
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

}
