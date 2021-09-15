package json;

public class LogJson {

    private String id;
    private LogStateEnum state;
    private Long timestamp;
    private String type;
    private String host;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LogStateEnum getState() {
        return state;
    }

    public void setState(LogStateEnum state) {
        this.state = state;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isValid() {
        return this.id != null &&
                this.state != null &&
                this.timestamp != null;
    }

    @Override
    public String toString() {
        return "LogJson{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

}
