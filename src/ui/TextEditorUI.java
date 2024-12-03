package ui;

import javax.swing.*;
import java.awt.*;
import utils.TextManipulator;
import utils.FileHandler;
import utils.ThemeColors;

public class TextEditorUI extends JFrame {
    private JTextPane textArea;
    private TextManipulator textManipulator;
    private FileHandler fileHandler;
    private ShapesPanel shapesPanel;
    
    public TextEditorUI() {
        // set default look and feel decorated
        JFrame.setDefaultLookAndFeelDecorated(true);

        // init main frame with dark theme
        setTitle("YATE: Yet Another Text Editor (CSD211 Project - Pustak Pathak 2310110564)");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // remove undecorated setting and just use dark theme - NOOOOO WORKEYY
        //setUndecorated(true); // removing this line
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        
         // init components
        textArea = new JTextPane();
        textManipulator = new TextManipulator(textArea);
        fileHandler = new FileHandler(textArea);
        shapesPanel = new ShapesPanel();

        
        // setup ui
        setupUI();
    }
    
    private void setupUI() {
        // main layout setup
        setLayout(new BorderLayout());
        
        // apply dark theme
        getContentPane().setBackground(ThemeColors.BACKGROUND);
        textArea.setBackground(ThemeColors.SECONDARY_BG);
        textArea.setForeground(ThemeColors.TEXT);
        textArea.setCaretColor(ThemeColors.TEXT);
        
        // add menu bar
        setJMenuBar(new MenuBarBuilder(fileHandler, textManipulator).build());
        
        // add toolbar
        add(new ToolbarBuilder(textManipulator).build(), BorderLayout.NORTH);
        
        // add main text area with scroll
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // add shapes panel
        add(shapesPanel, BorderLayout.EAST);
        
        // style the root pane for dark title bar
        JRootPane rootPane = getRootPane();
        rootPane.putClientProperty("JRootPane.titleBarBackground", ThemeColors.TITLE_BAR);
        rootPane.putClientProperty("JRootPane.titleBarForeground", ThemeColors.TEXT);
        
        // style the scroll bars
        UIManager.put("ScrollBar.thumb", ThemeColors.SECONDARY_BG);
        UIManager.put("ScrollBar.track", ThemeColors.BACKGROUND);
        UIManager.put("ScrollBar.thumbDarkShadow", ThemeColors.BORDER);
        UIManager.put("ScrollBar.thumbHighlight", ThemeColors.BORDER);
        UIManager.put("ScrollBar.thumbShadow", ThemeColors.BORDER);
        UIManager.put("ScrollBar.background", ThemeColors.BACKGROUND);

        // change title bar colors attmpt 2
        UIDefaults uiDefaults = UIManager.getDefaults(); // get ui defaults
        uiDefaults.put("activeCaption", ThemeColors.TITLE_BAR); // set active caption color
        uiDefaults.put("activeCaptionText", ThemeColors.TEXT); // set active caption text color
    }
} 