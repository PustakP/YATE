package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import utils.TextManipulator;
import utils.ThemeColors;

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
        toolbar.setBackground(ThemeColors.TOOLBAR_BG);
        
        // create title label
        JLabel titleLabel = new JLabel("YATE");
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 48));
        titleLabel.setForeground(ThemeColors.TEXT);
        JLabel titleDesc = new JLabel("Yet Another Text Editor");
        titleDesc.setFont(new Font("Courier New", Font.PLAIN, 12));
        titleDesc.setForeground(ThemeColors.TEXT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // add title label first
        toolbar.add(titleLabel);
        toolbar.add(titleDesc);
        toolbar.addSeparator(new Dimension(0, 20)); // add some spacing
        toolbar.addSeparator();
        toolbar.addSeparator(new Dimension(0, 20)); 
        // font family combo
        JComboBox<String> fontFamily = new JComboBox<>(fontNames);
        fontFamily.setMaximumSize(new Dimension(200, 30));
        fontFamily.addActionListener(e -> updateFont());
        
        // font size controls
        JButton decreaseSize = new JButton("-");
        JLabel fontSizeLabel = new JLabel("24");
        JButton increaseSize = new JButton("+");
        
        // btn dims and font for all buttons
        Dimension btnSize = new Dimension(40, 40);
        Font buttonFont = new Font("Courier New", Font.BOLD, 16);
        
        // size buttons dimensions - use same btnSize as other buttons
        decreaseSize.setPreferredSize(btnSize);
        increaseSize.setPreferredSize(btnSize);
        fontSizeLabel.setPreferredSize(new Dimension(40, 30));
        
        // style size buttons same as other buttons
        decreaseSize.setFont(buttonFont);
        increaseSize.setFont(buttonFont);
        fontSizeLabel.setForeground(ThemeColors.TEXT);
        // increase font size label size
        fontSizeLabel.setFont(new Font("Courier New", Font.BOLD, 18));
        
        // apply consistent styling
        styleButton(decreaseSize);
        styleButton(increaseSize);
        
        // add hover effects
        addHoverEffect(decreaseSize);
        addHoverEffect(increaseSize);
        
        // init default font size
        textManipulator.changeFont(fontNames[0], 24);
        
        // size button actions
        decreaseSize.addActionListener(e -> {
            textManipulator.adjustFontSize(false);
            fontSizeLabel.setText(String.valueOf(textManipulator.getCurrentFontSize()));
        });
        increaseSize.addActionListener(e -> {
            textManipulator.adjustFontSize(true);
            fontSizeLabel.setText(String.valueOf(textManipulator.getCurrentFontSize()));
        });
        
        // case buttons with styling
        JButton upperCase = new JButton("ABC");
        JButton lowerCase = new JButton("abc");
        
        upperCase.setPreferredSize(btnSize);
        lowerCase.setPreferredSize(btnSize);
        upperCase.setFont(buttonFont);
        lowerCase.setFont(buttonFont);
        
        // add tooltips
        upperCase.setToolTipText("Uppercase");
        lowerCase.setToolTipText("Lowercase");
        
        // style buttons
        styleButton(upperCase);
        styleButton(lowerCase);
        
        // add hover effect
        addHoverEffect(upperCase);
        addHoverEffect(lowerCase);
        
        upperCase.addActionListener(e -> textManipulator.toUpperCase());
        lowerCase.addActionListener(e -> textManipulator.toLowerCase());
        
        // create and style font label
        JLabel fontLabel = new JLabel(" Font: ");
        fontLabel.setForeground(ThemeColors.TEXT);
        fontLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        toolbar.add(fontLabel);
        toolbar.add(fontFamily);
        toolbar.addSeparator(new Dimension(0, 20));
        
        // add components to toolbar
        toolbar.add(decreaseSize);
        toolbar.add(fontSizeLabel);
        toolbar.add(increaseSize);
        toolbar.addSeparator();
        toolbar.add(upperCase);
        toolbar.add(lowerCase);
        
        // create bigger style buttons with icons
        JButton boldBtn = new JButton("B");
        JButton italicBtn = new JButton("I");
        JButton underlineBtn = new JButton("U");
        
        // make buttons bigger - reuse existing btnSize
        boldBtn.setPreferredSize(btnSize);
        italicBtn.setPreferredSize(btnSize);
        underlineBtn.setPreferredSize(btnSize);
        
        // style the buttons - reuse existing buttonFont
        boldBtn.setFont(buttonFont);
        italicBtn.setFont(new Font("Times New Roman", Font.ITALIC, 16));
        underlineBtn.setFont(buttonFont);
        
        // add tooltips
        boldBtn.setToolTipText("Bold (Ctrl+B)");
        italicBtn.setToolTipText("Italic (Ctrl+I)");
        underlineBtn.setToolTipText("Underline (Ctrl+U)");
        
        // style buttons
        styleButton(boldBtn);
        styleButton(italicBtn);
        styleButton(underlineBtn);
        
        // add hover effect
        addHoverEffect(boldBtn);
        addHoverEffect(italicBtn);
        addHoverEffect(underlineBtn);
        
        // add actions
        boldBtn.addActionListener(e -> textManipulator.toggleBold());
        italicBtn.addActionListener(e -> textManipulator.toggleItalic());
        underlineBtn.addActionListener(e -> textManipulator.toggleUnderline());
        
        // add keyboard shortcuts
        boldBtn.registerKeyboardAction(
            e -> textManipulator.toggleBold(),
            KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        italicBtn.registerKeyboardAction(
            e -> textManipulator.toggleItalic(),
            KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        underlineBtn.registerKeyboardAction(
            e -> textManipulator.toggleUnderline(),
            KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        // add style buttons with more spacing
        toolbar.addSeparator(new Dimension(10, 0));
        toolbar.add(boldBtn);
        toolbar.addSeparator(new Dimension(5, 0));
        toolbar.add(italicBtn);
        toolbar.addSeparator(new Dimension(5, 0));
        toolbar.add(underlineBtn);
        
        return toolbar;
    }
    
    private void updateFont() {
        // get font family combo box (it's the second component after the label)
        JComboBox<String> fontBox = (JComboBox<String>)toolbar.getComponentAtIndex(1);
        String fontName = (String)fontBox.getSelectedItem();
        
        // get current font size from text manipulator
        int fontSize = textManipulator.getCurrentFontSize();
        
        textManipulator.changeFont(fontName, fontSize);
    }
    
    private void addHoverEffect(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(ThemeColors.MENU_HOVER);
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(ThemeColors.SECONDARY_BG);
            }
        });
    }
    
    // helper method to style buttons
    private void styleButton(JButton btn) {
        btn.setBackground(ThemeColors.SECONDARY_BG);
        btn.setForeground(ThemeColors.TEXT);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ThemeColors.BORDER),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
} 