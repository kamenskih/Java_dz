package view;

import event.Event;
import event.EventListener;
import event.EventManager;
import event.FilesChangedEvent;
import event.PathUpdatedEvent;
import event.SearchPerfomedEvent;
import event.ViewUpdatedEvent;
import service.FileService;
import service.PathContextHolder;
import service.ViewTypeContextHolder;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class FilesView implements EventListener {

    private boolean isStateUpdated = true;

    private String searchQuery = "";

    private final JPanel content = new JPanel() {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            displayFiles();
            isStateUpdated = false;
        }
    };

    public FilesView() {
        content.setBorder(new EmptyBorder(10, 10, 0, 10));
        content.setAutoscrolls(true);

        EventManager.subscribe(PathUpdatedEvent.class, this);
        EventManager.subscribe(SearchPerfomedEvent.class, this);
        EventManager.subscribe(FilesChangedEvent.class, this);
        EventManager.subscribe(ViewUpdatedEvent.class, this);
    }

    @Override
    public void update(Event eventType) {
        if (eventType instanceof PathUpdatedEvent) {
            isStateUpdated = true;
            content.repaint();
        }
        if (eventType instanceof SearchPerfomedEvent) {
            SearchPerfomedEvent event = (SearchPerfomedEvent) eventType;
            searchQuery = event.getSearchValue();
            isStateUpdated = true;
            content.repaint();
        }
        if (eventType instanceof FilesChangedEvent) {
            FilesChangedEvent event = (FilesChangedEvent) eventType;
            if (event.getPath().equals(PathContextHolder.getCurrentPath())) {
                isStateUpdated = true;
                content.repaint();
            }
        }
        if (eventType instanceof ViewUpdatedEvent) {
            isStateUpdated = true;
            content.repaint();
        }
    }

    public JScrollPane pack() {
        // Make it scrollable
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new EmptyBorder(0, 0, 10, 0));

        return scrollPane;
    }

    private void displayFiles() {
        if (!isStateUpdated) {
            return;
        }
        content.removeAll();
        String currentPath = PathContextHolder.getCurrentPath();

        if (currentPath.isEmpty()) {
            content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
            displayDrives();
            return;
        }
        if (ViewTypeContextHolder.getViewType() == ViewType.LIST) {
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        } else if (ViewTypeContextHolder.getViewType() == ViewType.GRID) {
            content.setLayout(new GridLayout(0, 4, 10, 10));
        }
        //        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
//        content.setLayout(new GridLayout(0, 4, 10, 10));

        List<File> files = FileService.search(currentPath, searchQuery);
        System.out.println("Files in " + currentPath + " " + files.toString());

        if (files.isEmpty()) {
            JLabel noFilesLabel = new JLabel("No files here");
            noFilesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            content.add(noFilesLabel);
            content.revalidate();
            return;
        }
        files.stream()
                .map(FileView::new)
                .forEach(fileView -> content.add(fileView.pack()));

        content.revalidate();
    }

    private void displayDrives() {
        if (!isStateUpdated) {
            return;
        }
        content.removeAll();

        Arrays.stream(File.listRoots())
                .map(file -> new DriveView(file.getAbsolutePath()))
                .forEach(driveView -> content.add(driveView.pack()));

        content.revalidate();
    }



}
