package ui;

import javax.swing.*;
import java.awt.*;
import utils.TextManipulator;
import utils.FileHandler;

public class TextEditorUI extends JFrame {
    private JTextArea textArea;
    private TextManipulator textManipulator;
    private FileHandler fileHandler;
    private ShapesPanel shapesPanel;
    
    public TextEditorUI() {
        // init main frame
        setTitle("YATE:YetAnotherTextEditor");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // init components
        textArea = new JTextArea();
        textManipulator = new TextManipulator(textArea);
        fileHandler = new FileHandler(textArea);
        shapesPanel = new ShapesPanel();
        
        // setup ui
        setupUI();
    }
    
    private void setupUI() {
        // main layout setup
        setLayout(new BorderLayout());
        
        // add menu bar
        setJMenuBar(new MenuBarBuilder(fileHandler, textManipulator).build());
        
        // add toolbar
        add(new ToolbarBuilder(textManipulator).build(), BorderLayout.NORTH);
        
        // add main text area with scroll
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // add shapes panel
        add(shapesPanel, BorderLayout.EAST);
    }
} 