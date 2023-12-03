package view;

import event.Event;
import event.EventListener;
import event.EventManager;
import event.PathUpdatedEvent;
import service.PathContextHolder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationButtons implements EventListener {

    private final JButton previousButton = new JButton("Previous");
    private final JButton nextButton = new JButton("Next");

    private final JPanel wrapper = new JPanel();

    public NavigationButtons() {
        previousButton.setEnabled(false);
        nextButton.setEnabled(false);

        previousButton.addMouseListener(previousButtonMouseListener());
        nextButton.addMouseListener(nextButtonMouseListener());

        EventManager.subscribe(PathUpdatedEvent.class, this);
    }

    public JPanel pack() {
        wrapper.add(previousButton);
        wrapper.add(nextButton);
        return wrapper;
    }

    private MouseAdapter previousButtonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                PathContextHolder.goBack();
            }
        };
    }

    private MouseAdapter nextButtonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                PathContextHolder.goForward();
            }
        };
    }

    @Override
    public void update(Event eventType) {
        if (eventType instanceof PathUpdatedEvent) {
            previousButton.setEnabled(PathContextHolder.canGoBack());
            nextButton.setEnabled(PathContextHolder.canGoForward());
            wrapper.repaint();
        }
    }

}
