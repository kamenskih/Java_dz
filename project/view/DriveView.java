package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import service.PathContextHolder;

public class DriveView {

    private static final int ICON_SIZE = 20;

    private final String name;
    private final String path;

    private final JPanel wrapper = new JPanel();

    public DriveView(String path) {
        this.name = "Drive \"" + path.replace("\\", "").replace(":", "") + "\"";
        this.path = path;

        wrapper.setPreferredSize(new Dimension(200, 40));
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        wrapper.setBackground(Color.lightGray);
        wrapper.setBorder(BorderFactory.createLineBorder(Color.black));
        wrapper.addMouseListener(doubleClickWrapperMouseListener());
    }

    public JPanel pack() {
        JLabel nameLabel = new JLabel(this.name);
        wrapper.add(getDriveIcon());
        wrapper.add(nameLabel);
        return wrapper;
    }

    private JLabel getDriveIcon() {
        ImageIcon img = new ImageIcon("C:\\дз прога 3 курс\\Java_dz\\Java_dz\\project\\icons\\icon-drive.png");

        Image image = img.getImage(); // transform it
        Image newimg = image.getScaledInstance(ICON_SIZE,
                                               ICON_SIZE,
                                               Image.SCALE_SMOOTH); // scale it the smooth way
        img = new ImageIcon(newimg); // transform it back
        return new JLabel(img);
    }

    private MouseAdapter doubleClickWrapperMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    PathContextHolder.addNewPath(path);
                }
            }
        };
    }

}
