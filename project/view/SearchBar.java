package view;

import event.EventManager;
import event.SearchPerfomedEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchBar {

    private static final int ICON_SIZE = 20;

    private final JPanel wrapper = new JPanel();
    private final JTextField searchBar = new JTextField();

    public JPanel pack() {
        JLabel nameLabel = new JLabel("Search");

        searchBar.setPreferredSize(new Dimension(150, 25));
        searchBar.getDocument().addDocumentListener(getSearchBarListener());
        wrapper.add(nameLabel);
        wrapper.add(searchBar);
        wrapper.add(getIcon());
        return wrapper;
    }

    private JLabel getIcon() {
        ImageIcon img = new ImageIcon("C:\\дз прога 3 курс\\Java_dz\\Java_dz\\project\\icons\\icon-magnifying-glass.png");

        Image image = img.getImage(); // transform it
        Image newimg = image.getScaledInstance(ICON_SIZE,
                                               ICON_SIZE,
                                               Image.SCALE_SMOOTH); // scale it the smooth way
        img = new ImageIcon(newimg); // transform it back
        return new JLabel(img);
    }

    public DocumentListener getSearchBarListener() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                EventManager.notify(new SearchPerfomedEvent(searchBar.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                EventManager.notify(new SearchPerfomedEvent(searchBar.getText()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                EventManager.notify(new SearchPerfomedEvent(searchBar.getText()));
            }
        };
    }

}
