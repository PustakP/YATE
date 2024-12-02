package ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import utils.*;

public class MenuBarBuilder {
    private FileHandler fileHandler;
    private TextManipulator textManipulator;
    
    public MenuBarBuilder(FileHandler fileHandler, TextManipulator textManipulator) {
        this.fileHandler = fileHandler;
        this.textManipulator = textManipulator;
    }
    
    public JMenuBar build() {
        // init main menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // file menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem newItem = new JMenuItem("New", KeyEvent.VK_N);
        JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_O);
        JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
        JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
        
        newItem.addActionListener(e -> fileHandler.newFile());
        openItem.addActionListener(e -> fileHandler.loadFile());
        saveItem.addActionListener(e -> fileHandler.saveFile());
        exitItem.addActionListener(e -> System.exit(0));
        
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // edit menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        
        JMenuItem findItem = new JMenuItem("Find/Replace", KeyEvent.VK_F);
        JMenuItem countItem = new JMenuItem("Word Count", KeyEvent.VK_W);
        
        findItem.addActionListener(e -> showFindReplaceDialog());
        countItem.addActionListener(e -> showWordCount());
        
        editMenu.add(findItem);
        editMenu.add(countItem);
        
        // add menus to bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        return menuBar;
    }
    
    private void showFindReplaceDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Find and Replace");
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        
        JTextField findField = new JTextField(20);
        JTextField replaceField = new JTextField(20);
        
        JButton findButton = new JButton("Find");
        JButton replaceButton = new JButton("Replace");
        JButton replaceAllButton = new JButton("Replace All");
        
        findButton.addActionListener(e -> 
            textManipulator.findAndReplace(findField.getText(), replaceField.getText(), false));
        replaceAllButton.addActionListener(e -> 
            textManipulator.findAndReplace(findField.getText(), replaceField.getText(), true));
        
        dialog.add(new JLabel("Find:"));
        dialog.add(findField);
        dialog.add(new JLabel("Replace with:"));
        dialog.add(replaceField);
        dialog.add(findButton);
        dialog.add(replaceButton);
        dialog.add(replaceAllButton);
        
        dialog.pack();
        dialog.setVisible(true);
    }
    
    private void showWordCount() {
        JOptionPane.showMessageDialog(null, textManipulator.getWordAndCharCount());
    }
} 