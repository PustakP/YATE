package ui;

import javax.swing.*;
import java.awt.*;
import utils.TextManipulator;

public class ToolbarBuilder {
    private TextManipulator textManipulator;
    private JToolBar toolbar;
    private String[] fontSizes = {"8", "10", "12", "14", "16", "18", "20", "24", "28", "32", "36", "48", "72"};
    private String[] fontNames;
    
    public ToolbarBuilder(TextManipulator textManipulator) {
        this.textManipulator = textManipulator;
        // get available system fonts
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontNames = ge.getAvailableFontFamilyNames();
    }
    
    public JToolBar build() {
        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        
        // font family combo
        JComboBox<String> fontFamily = new JComboBox<>(fontNames);
        fontFamily.setMaximumSize(new Dimension(200, 30));
        fontFamily.addActionListener(e -> updateFont());
        
        // font size combo
        JComboBox<String> fontSize = new JComboBox<>(fontSizes);
        fontSize.setMaximumSize(new Dimension(70, 30));
        fontSize.setSelectedItem("12");
        fontSize.addActionListener(e -> updateFont());
        
        // case buttons
        JButton upperCase = new JButton("ABC");
        JButton lowerCase = new JButton("abc");
        
        upperCase.addActionListener(e -> textManipulator.toUpperCase());
        lowerCase.addActionListener(e -> textManipulator.toLowerCase());
        
        // add components
        toolbar.add(new JLabel(" Font: "));
        toolbar.add(fontFamily);
        toolbar.add(new JLabel(" Size: "));
        toolbar.add(fontSize);
        toolbar.addSeparator();
        toolbar.add(upperCase);
        toolbar.add(lowerCase);
        
        return toolbar;
    }
    
    private void updateFont() {
        // impl font update logic
        JComboBox<String> fontBox = (JComboBox<String>) toolbar.getComponent(1);
        JComboBox<String> sizeBox = (JComboBox<String>) toolbar.getComponent(3);
        
        String fontName = (String)fontBox.getSelectedItem();
        int fontSize = Integer.parseInt((String)sizeBox.getSelectedItem());
        
        textManipulator.changeFont(fontName, fontSize);
    }
} 