package event;

public class FilesChangedEvent implements Event {

    private String path;

    public FilesChangedEvent(String newPath) {
        this.path = newPath;
    }

    public String getPath() {
        return path;
    }

}
