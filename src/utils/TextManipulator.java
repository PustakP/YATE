package utils;

import javax.swing.*;
import java.awt.*;

public class TextManipulator {
    private JTextArea textArea;
    
    public TextManipulator(JTextArea textArea) {
        this.textArea = textArea;
    }
    
    // text manipulation methods
    public void changeFont(String fontName, int size) {
        textArea.setFont(new Font(fontName, Font.PLAIN, size));
    }
    
    public void toUpperCase() {
        String selectedText = textArea.getSelectedText();
        if (selectedText != null) {
            textArea.replaceSelection(selectedText.toUpperCase());
        }
    }
    
    public void toLowerCase() {
        String selectedText = textArea.getSelectedText();
        if (selectedText != null) {
            textArea.replaceSelection(selectedText.toLowerCase());
        }
    }
    
    public void findAndReplace(String find, String replace, boolean replaceAll) {
        // impl find/replace logic
        String text = textArea.getText();
        if (replaceAll) {
            text = text.replaceAll(find, replace);
            textArea.setText(text);
        } else {
            // impl single replace
            int pos = text.indexOf(find);
            if (pos >= 0) {
                textArea.select(pos, pos + find.length());
                textArea.replaceSelection(replace);
            }
        }
    }
    
    public String getWordAndCharCount() {
        String text = textArea.getSelectedText();
        if (text == null) text = textArea.getText();
        
        int chars = text.length();
        int words = text.split("\\s+").length;
        
        return String.format("Words: %d, Characters: %d", words, chars);
    }
} 