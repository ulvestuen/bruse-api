package dev.bruse.model;

public class TaskRequest {

    private final String gamePin;
    private final double lat;
    private final double lon;

    public TaskRequest(final String gamePin, final double lat, final double lon) {
        this.gamePin = gamePin;
        this.lat = lat;
        this.lon = lon;
    }

    public TaskRequest(final TaskRequestDto taskRequestDto) {
        gamePin = taskRequestDto.getGamepin();
        lat = taskRequestDto.getLat();
        lon = taskRequestDto.getLon();
    }

    public String getGamePin() {
        return gamePin;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
