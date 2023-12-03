package event;

public class PathUpdatedEvent implements Event {

    private String path;

    public PathUpdatedEvent(String newPath) {
        this.path = newPath;
    }

    public String getPath() {
        return path;
    }

}
