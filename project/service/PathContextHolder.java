package service;

import event.EventManager;
import event.PathUpdatedEvent;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class PathContextHolder {

    private static final String DEFAULT_PATH = ""; //Root
    private static final int MAX_HISTORY_SIZE = 10;

    private static String currentPath;

    //Save history of paths with max size of 10

    private static String[] history = new String[MAX_HISTORY_SIZE];
    private static int historyIndex = 0;

    static {
        currentPath = DEFAULT_PATH;
        history[historyIndex] = DEFAULT_PATH;
        historyIndex++;
    }

    public static String getCurrentPath() {
        return currentPath;
    }

    public static void goBack() {
        if (historyIndex > 1) {
            historyIndex--;
            currentPath = history[historyIndex - 1];
            EventManager.notify(new PathUpdatedEvent(currentPath));
        }
    }

    public static void goForward() {
        if (historyIndex < history.length && history[historyIndex] != null) {
            currentPath = history[historyIndex];
            historyIndex++;
            EventManager.notify(new PathUpdatedEvent(currentPath));
        }
    }

    public static void addNewPath(String newPath) {
        String validatedPath = validatePath(newPath);

        if (validatedPath == null) {
            return;
        }

        if (historyIndex < history.length) {
            history[historyIndex] = newPath;
            historyIndex++;
        } else {
            history = Arrays.copyOfRange(history, 1, history.length);
            history[history.length - 1] = newPath;
        }
        currentPath = newPath;
        EventManager.notify(new PathUpdatedEvent(currentPath));
    }

    public static boolean canGoBack() {
        return historyIndex > 1;
    }

    public static boolean canGoForward() {
        return historyIndex < history.length && history[historyIndex] != null;
    }

    private static String validatePath(String path) {
        String processedPath = path.trim();

        String newPath = currentPath;

        if (processedPath.equals(newPath)) {
            return null;
        }
        if (!processedPath.isEmpty() && !processedPath.endsWith(File.separator)) {
            processedPath = processedPath + File.separator;
        }
        if (!processedPath.isEmpty() && Files.exists(Path.of(processedPath))) {
            newPath = processedPath;
        } else {
            System.err.println("Path " + processedPath + " does not exist");
            return null;
        }
        return newPath;
    }

}
