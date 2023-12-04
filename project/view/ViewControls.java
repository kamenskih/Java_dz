package view;

import event.Event;
import event.EventListener;
import event.EventManager;
import event.PathUpdatedEvent;
import event.ViewUpdatedEvent;
import service.PathContextHolder;
import service.ViewTypeContextHolder;

import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewControls implements EventListener {

    private final JPanel wrapper = new JPanel();
    private final JButton listButton = new JButton("List");
    private final JButton gridButton = new JButton("Grid");

    public ViewControls() {
        JLabel label = new JLabel("View type:");

        listButton.addMouseListener(viewTypeChangeListener(ViewType.LIST));
        gridButton.addMouseListener(viewTypeChangeListener(ViewType.GRID));

        if (PathContextHolder.getCurrentPath().isEmpty()) {
            listButton.setEnabled(false);
            gridButton.setEnabled(false);
        } else {
            listButton.setEnabled(ViewTypeContextHolder.getViewType() != ViewType.LIST);
            gridButton.setEnabled(ViewTypeContextHolder.getViewType() != ViewType.GRID);
        }

        wrapper.add(label);
        wrapper.add(listButton);
        wrapper.add(gridButton);

        EventManager.subscribe(ViewUpdatedEvent.class, this);
        EventManager.subscribe(PathUpdatedEvent.class, this);
    }

    public JPanel pack() {
        return wrapper;
    }

    @Override
    public void update(Event eventType) {
        if (eventType instanceof ViewUpdatedEvent) {
            listButton.setEnabled(ViewTypeContextHolder.getViewType() != ViewType.LIST);
            gridButton.setEnabled(ViewTypeContextHolder.getViewType() != ViewType.GRID);
            wrapper.repaint();
        }
        if (eventType instanceof PathUpdatedEvent) {
            if (PathContextHolder.getCurrentPath().isEmpty()) {
                listButton.setEnabled(false);
                gridButton.setEnabled(false);
            } else {
                listButton.setEnabled(ViewTypeContextHolder.getViewType() != ViewType.LIST);
                gridButton.setEnabled(ViewTypeContextHolder.getViewType() != ViewType.GRID);
            }
            wrapper.repaint();
        }
    }

    private MouseAdapter viewTypeChangeListener(ViewType viewType) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                super.mouseClicked(evt);
                ViewTypeContextHolder.setViewType(viewType);
            }
        };
    }

}
