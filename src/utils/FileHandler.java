package utils;

import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileHandler {
    private JTextPane textPane;
    private File currentFile;
    
    public FileHandler(JTextPane textPane) {
        this.textPane = textPane;
    }
    
    public void saveFile() {
        // init jfc w/ txt filter
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            
            // add .txt if not present
            if (!currentFile.getName().toLowerCase().endsWith(".txt")) {
                currentFile = new File(currentFile.getAbsolutePath() + ".txt");
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                writer.write(textPane.getText());
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
                textPane.setText("");
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textPane.setText(content.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error loading file: " + e.getMessage());
            }
        }
    }
    
    public void newFile() {
        textPane.setText("");
        currentFile = null;
    }
} 