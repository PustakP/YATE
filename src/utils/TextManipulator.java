package utils;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class TextManipulator {

    int sent = 0;
    private JTextPane textPane;
    
    public TextManipulator(JTextPane textPane) {
        this.textPane = textPane;
    }
    
    // text manipulation methods
    public void changeFont(String fontName, int size) {
        textPane.setFont(new Font(fontName, Font.PLAIN, size));
    }
    
    public void toUpperCase() {
        String selectedText = textPane.getSelectedText();
        if (selectedText != null) {
            textPane.replaceSelection(selectedText.toUpperCase());
        }
    }
    
    public void toLowerCase() {
        String selectedText = textPane.getSelectedText();
        if (selectedText != null) {
            textPane.replaceSelection(selectedText.toLowerCase());
        }
    }
    
    public void findAndReplace(String find, String replace, boolean replaceAll) {
        // bail if search term empty
        
        if (find.isEmpty()) return;
        
        String text = textPane.getText();
        if (replaceAll) {
            // impl replace all - keep original working code
            text = text.replaceAll(find, replace);
            textPane.setText(text);
        } else {
            // handle single find/replace
            int caretPos = textPane.getCaretPosition();
            int pos = text.indexOf(find, caretPos); // subtract 1 from search position
            
            // System.out.println("pos: in find " + pos);
            
            // wrap search to beginning if not found
            if (pos < 0) {
                pos = text.indexOf(find);
                sent = 0;
                // System.out.println("pos: wrap " + pos);
            }
            
            if (pos >= 0) {
                // highlight found text
                textPane.setSelectionStart(pos-sent);
                textPane.setSelectionEnd((pos-sent) + find.length());
                sent = sent + 1;
                // System.out.println("pos: replace " + pos + " sent: " + sent);
                
                // if replace string provided, do replacement
                if (!replace.isEmpty()) {
                    textPane.replaceSelection(replace);
                }
            }
        }
    }
    
    public String getWordAndCharCount() {
        String text = textPane.getSelectedText();
        if (text == null) text = textPane.getText();
        
        int chars = text.length();
        int words = (chars == 0) ? 0 : text.split("\\s+").length;
        
        return String.format("Words: %d, Characters: %d", words, chars);
    }
    
    public void toggleBold() {
        StyledDocument doc = textPane.getStyledDocument();
        MutableAttributeSet attr = textPane.getInputAttributes();
        boolean isBold = StyleConstants.isBold(attr);
        StyleConstants.setBold(attr, !isBold);
        doc.setCharacterAttributes(textPane.getSelectionStart(), 
            textPane.getSelectionEnd() - textPane.getSelectionStart(), attr, false);
    }
    
    public void toggleItalic() {
        StyledDocument doc = textPane.getStyledDocument();
        MutableAttributeSet attr = textPane.getInputAttributes();
        boolean isItalic = StyleConstants.isItalic(attr);
        StyleConstants.setItalic(attr, !isItalic);
        doc.setCharacterAttributes(textPane.getSelectionStart(), 
            textPane.getSelectionEnd() - textPane.getSelectionStart(), attr, false);
    }
    
    public void toggleUnderline() {
        StyledDocument doc = textPane.getStyledDocument();
        MutableAttributeSet attr = textPane.getInputAttributes();
        boolean isUnderline = StyleConstants.isUnderline(attr);
        StyleConstants.setUnderline(attr, !isUnderline);
        doc.setCharacterAttributes(textPane.getSelectionStart(), 
            textPane.getSelectionEnd() - textPane.getSelectionStart(), attr, false);
    }
    
    public void setUnderline(boolean underline) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setUnderline(attrs, underline);
        
        // apply underline to sel text or all text
        int start = textPane.getSelectionStart();
        int length = textPane.getSelectionEnd() - start;
        if (length == 0) {
            length = doc.getLength();
            start = 0;
        }
        
        doc.setCharacterAttributes(start, length, attrs, false);
    }
    
    public int getCurrentFontSize() {
        // get curr font size from textpane
        return textPane.getFont().getSize();
    }
    
    public void adjustFontSize(boolean increase) {
        // get curr font and adjust size
        Font currentFont = textPane.getFont();
        int newSize = currentFont.getSize() + (increase ? 2 : -2);
        // min font size = 8
        newSize = Math.max(8, newSize);
        textPane.setFont(new Font(currentFont.getName(), currentFont.getStyle(), newSize));
    }
} 