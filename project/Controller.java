import service.FileService;
import service.PathContextHolder;
import view.FilesView;
import view.NavigationButtons;
import view.PathField;
import view.SearchBar;
import view.ViewControls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.WindowConstants;


public class Controller extends JFrame {

    public Controller() throws HeadlessException {
        setTitle("Explorer");
        setSize(800, 600);
        setMinimumSize(new Dimension(800, 600));
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JPanel topHeader = new JPanel();
        topHeader.setLayout(new FlowLayout(FlowLayout.CENTER));
        topHeader.add(new NavigationButtons().pack());
        topHeader.add(new JLabel("Path:"));
        topHeader.add(new PathField(header).pack());
        topHeader.add(new SearchBar().pack());

        JPanel bottomHeader = new JPanel();

        JButton copyAllButton = new JButton("Copy all");
        copyAllButton.setBackground(Color.GREEN);
        copyAllButton.addMouseListener(copyAllButtonMouseListener());

        JButton pasteButton = new JButton("Paste");
        pasteButton.setBackground(Color.YELLOW);
        pasteButton.addMouseListener(pasteButtonMouseListener());

        bottomHeader.add(copyAllButton);
        bottomHeader.add(pasteButton);
        bottomHeader.add(new ViewControls().pack());

        header.add(topHeader);
        header.add(bottomHeader);

        FilesView filesView = new FilesView();

        add(header, BorderLayout.NORTH);
        add(filesView.pack(), BorderLayout.CENTER);
        setVisible(true);
    }

    private MouseAdapter copyAllButtonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FileService.copyAllToClipboard(PathContextHolder.getCurrentPath());
            }
        };
    }

    private MouseAdapter pasteButtonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FileService.pasteFromClipboard(PathContextHolder.getCurrentPath());
            }
        };
    }

}
