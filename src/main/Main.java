package main;

import javax.swing.*;
import ui.TextEditorUI;
import utils.ThemeColors;

public class Main{
    public static void main(String[] args) {
        // set system look and feel for dark theme
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Panel.background", ThemeColors.BACKGROUND);
            UIManager.put("OptionPane.background", ThemeColors.BACKGROUND);
            UIManager.put("OptionPane.messageForeground", ThemeColors.TEXT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // init swing components on edt (event dispatch thread)
        SwingUtilities.invokeLater(() -> {
            TextEditorUI editor = new TextEditorUI();
            editor.setVisible(true);
        });
    }
} 