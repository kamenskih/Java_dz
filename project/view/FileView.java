package view;

import service.FileService;
import service.PathContextHolder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FileView {

    private static final String[] IMAGE_EXTENSIONS = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};

    private static final int VIEW_HEIGHT = 22;
    private static final float FONT_SIZE = 12.0f;
    private static final int ICON_SIZE = 20;

    private final JPanel wrapper = new JPanel();

    private String name;
    private String path;
    private boolean isDirectory;

    public FileView(File file) {
        this.name = file.getName();
        this.path = file.getPath();
        this.isDirectory = file.isDirectory();

        wrapper.setLayout(new FlowLayout());
        wrapper.setPreferredSize(new Dimension(200, VIEW_HEIGHT));
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, VIEW_HEIGHT));
        wrapper.setLayout(new FlowLayout(FlowLayout.LEFT));
        wrapper.addMouseListener(doubleClickWrapperMouseListener());
    }

    public JPanel pack() {
        //Icon
        wrapper.add(getFileIcon());

        //Name
        JLabel nameLabel = new JLabel(this.name);
        nameLabel.setFont(nameLabel.getFont().deriveFont(FONT_SIZE));
        nameLabel.addMouseListener(startRenameMouseListener(nameLabel));
        wrapper.add(nameLabel);
        return wrapper;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    private JLabel getFileIcon() {
        ImageIcon img = new ImageIcon("C:\\дз прога 3 курс\\Java_dz\\Java_dz\\project\\icons\\icon-unknown.png");

        if (this.isDirectory) {
            img = new ImageIcon("C:\\дз прога 3 курс\\Java_dz\\Java_dz\\project\\icons\\icon-folder.png");
        }
        if (Arrays.stream(IMAGE_EXTENSIONS).anyMatch(name::endsWith)) {
            img = new ImageIcon("C:\\дз прога 3 курс\\Java_dz\\Java_dz\\project\\icons\\icon-image.png");
        }
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
                    System.out.println("Double clicked on: " + name);
                    if (isDirectory) {
                        PathContextHolder.addNewPath(path);
                    }
                    if (Arrays.stream(IMAGE_EXTENSIONS).anyMatch(name::endsWith)) {
                        openImage();
                    }
                }
            }
        };
    }

    private void openImage() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        frame.setLocationRelativeTo(null);

        ImageIcon img = new ImageIcon(path);
        Image image = img.getImage(); // transform it
        double ratio = (double) image.getWidth(null) / image.getHeight(null);

        if (image.getWidth(null) > screenSize.getWidth() * 0.8) {
            image = image.getScaledInstance((int) (screenSize.getWidth() * 0.8),
                                            (int) (screenSize.getWidth() * 0.8 / ratio),
                                            Image.SCALE_SMOOTH);
        } else if (image.getHeight(null) > screenSize.getHeight() * 0.8) {
            image = image.getScaledInstance((int) (screenSize.getHeight() * 0.8 * ratio),
                                            (int) (screenSize.getHeight() * 0.8),
                                            Image.SCALE_SMOOTH);
        } else {
            image = image.getScaledInstance((int) (image.getWidth(null) * 0.8),
                                            (int) (image.getHeight(null) * 0.8),
                                            Image.SCALE_SMOOTH);
        }
        img = new ImageIcon(image); // transform it back
        JLabel imageLabel = new JLabel(img);
        JLabel nameLabel = new JLabel(name);
        frame.add(nameLabel, BorderLayout.NORTH);
        frame.add(imageLabel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void startRename(JLabel nameLabel) {
        System.out.println("Clicked on " + name);

        wrapper.remove(nameLabel);

        JTextField textField = new JTextField();
        textField.setText(name);
        wrapper.add(textField);
        textField.grabFocus();
        textField.selectAll();
        wrapper.revalidate();

        textField.addActionListener(e1 -> performRename(nameLabel, textField));
        textField.addKeyListener(cancelRenameKeyListener(nameLabel, textField));
        textField.addFocusListener(focusOutListener(nameLabel, textField));
    }

    private void performRename(JLabel nameLabel, JTextField textField) {
        String newName = textField.getText();
        if (newName == null || newName.isEmpty()) {
            replaceInputToLabel(nameLabel, textField);
        }
        try {
            FileService.renameFile(path, newName);
            System.out.println("Renaming " + name + " to " + newName);
            this.name = newName;
            nameLabel.setText(name);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            replaceInputToLabel(nameLabel, textField);
        }
    }

    private void replaceInputToLabel(JLabel nameLabel, JTextField textField) {
        wrapper.remove(textField);
        wrapper.add(nameLabel);
        wrapper.revalidate();
        wrapper.repaint();
    }

    private MouseAdapter startRenameMouseListener(JLabel nameLabel) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                startRename(nameLabel);
            }
        };
    }

    private KeyAdapter cancelRenameKeyListener(JLabel nameLabel, JTextField textField) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    replaceInputToLabel(nameLabel, textField);
                }
            }
        };
    }

    private FocusAdapter focusOutListener(JLabel nameLabel, JTextField textField) {
        return new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                performRename(nameLabel, textField);
            }
        };
    }

}
