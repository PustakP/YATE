package main;

import javax.swing.*;
import ui.TextEditorUI;

public class TextEditorMain {
    public static void main(String[] args) {
        // init swing components on edt (event dispatch thread)
        SwingUtilities.invokeLater(() -> {
            TextEditorUI editor = new TextEditorUI();
            editor.setVisible(true);
        });
    }
} 