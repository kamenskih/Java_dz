package service;

import event.EventManager;
import event.FilesChangedEvent;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class FileService {

    public static List<File> getFilesByPath(String path) {
        File[] files = new File(path).listFiles();

        if (files == null) {
            return List.of();
        }
        return Stream.of(files)
                .sorted((o1, o2) -> {
                    if (o1.isDirectory()) {
                        if (o2.isDirectory()) {
                            return o1.getName().compareTo(o2.getName());
                        }
                        return -1;
                    }
                    if (o2.isDirectory()) {
                        return 1;
                    }
                    return o1.getName().compareTo(o2.getName());
                })
                .toList();
    }

    public static void renameFile(String filePath, String newName) throws Exception {
        File file = new File(filePath);
        File newFile = new File(file.getParent() + File.separator + newName);

        if (newFile.exists()) {
            throw new Exception("File with this name already exists");
        }
        boolean success = file.renameTo(newFile);
        if (!success) {
            throw new Exception("File was not renamed");
        }
    }

    public static List<File> search(String path, String pattern) {
        List<File> files = getFilesByPath(path);

        if (pattern == null || pattern.isBlank()) {
            return files;
        }
        // # - one or more any symbols

        String searchPattern = pattern.replaceAll("#", ".+");

        return files.stream()
                .filter(file -> file.getName().toLowerCase().matches(searchPattern.toLowerCase()))
                .toList();
    }

    public static void copyAllToClipboard(String path) {
        List<File> files = getFilesByPath(path);

        if (files.isEmpty()) {
            System.err.println("No files to copy");
            return;
        }
        double totalSize = countFilesSize(path);

        System.out.println("Total size of files to copy: " + totalSize);

        if (totalSize > 100_000_000) {
            System.err.println("Files are too big to copy");
            return;
        }
        FileTransferable ft = new FileTransferable(files);

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(ft, (clipboard, contents) ->
                        System.err.println("Lost ownership"));

        System.out.println("Copied " + files.size() + " files to clipboard");
    }

    public static void pasteFromClipboard(String path) {
        Transferable tr = Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .getContents(null);

        if (tr == null) {
            return;
        }
        List<File> files = null;
        try {
            files = (List<File>) tr.getTransferData(DataFlavor.javaFileListFlavor);
        } catch (UnsupportedFlavorException e) {
            System.err.println("Unsupported flavor" + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("IO error" + e.getMessage());
            return;
        }
        files.forEach(file -> {
            try {
                pasteFile(file, path);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });

        System.out.println("Pasted " + files.size() + " files from clipboard");
    }

    private static void pasteFile(File file, String path) throws Exception {
        File newFile = new File(path + File.separator + file.getName());

        if (newFile.exists()) {
            throw new Exception("File with this name already exists");
        }
        if (file.isDirectory()) {
            newFile.mkdir();
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    pasteFile(f, newFile.getAbsolutePath());
                }
            }
        } else {
            Files.copy(file.toPath(), newFile.toPath());
        }
        EventManager.notify(new FilesChangedEvent(path));
    }

    private static double countFilesSize(String path) {
        List<File> files = getFilesByPath(path);

        double filesSize = 0;

        for (File file : files) {
            if (file.isDirectory()) {
                countFilesSize(file.getAbsolutePath());
            }
            filesSize += file.length();
        }
        return filesSize;
    }

}
