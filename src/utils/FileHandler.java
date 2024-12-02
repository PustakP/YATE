package utils;

import javax.swing.*;
import java.io.*;

public class FileHandler {
    private JTextArea textArea;
    private File currentFile;
    
    public FileHandler(JTextArea textArea) {
        this.textArea = textArea;
    }
    
    public void saveFile() {
        // impl file save logic w/ jfilechooser
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage());
            }
        }
    }
    
    public void loadFile() {
        // impl file load logic
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error loading file: " + e.getMessage());
            }
        }
    }
    
    public void newFile() {
        textArea.setText("");
        currentFile = null;
    }
} 