package entity;

import json.LogJson;

public class LogEntity {

    private String id;
    private Long startTimestamp;
    private Integer duration;
    private String type;
    private String host;
    private boolean longEvent;

    public LogEntity() {}

    public LogEntity(LogJson startLog, LogJson finishedLog) {
        this.setId(startLog.getId());
        this.setStartTimestamp(startLog.getTimestamp());
        Long duration = finishedLog.getTimestamp() - startLog.getTimestamp();
        this.setDuration(duration.intValue());
        this.setLongEvent(this.duration > 4);
        this.setType(startLog.getType());
        this.setHost(startLog.getHost());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isLongEvent() {
        return longEvent;
    }

    public void setLongEvent(boolean longEvent) {
        this.longEvent = longEvent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id='" + id + '\'' +
                ", startTimestamp=" + startTimestamp +
                ", duration=" + duration +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", longEvent=" + longEvent +
                "}\n";
    }
}
