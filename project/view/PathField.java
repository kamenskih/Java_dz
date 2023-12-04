package view;

import event.Event;
import event.EventListener;
import event.EventManager;
import event.PathUpdatedEvent;
import service.PathContextHolder;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class PathField implements EventListener {

    private final JTextField pathField = new JTextField();

    private JComponent wrapper;

    public PathField(JComponent wrapper) {
        this.wrapper = wrapper;

        pathField.setPreferredSize(new Dimension(300, 25));
        pathField.setText(PathContextHolder.getCurrentPath());
        pathField.addKeyListener(enterKeyListener());
        pathField.addFocusListener(focusOutListener());

        EventManager.subscribe(PathUpdatedEvent.class, this);
    }

    public JTextField pack() {
        return pathField;
    }

    @Override
    public void update(Event eventType) {
        if (eventType instanceof PathUpdatedEvent) {
            pathField.setText(PathContextHolder.getCurrentPath());
            wrapper.repaint();
        }
    }

    private KeyAdapter enterKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    PathContextHolder.addNewPath(pathField.getText());
                }
            }
        };
    }

    private FocusAdapter focusOutListener() {
        return new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                PathContextHolder.addNewPath(pathField.getText());
            }
        };
    }

}
