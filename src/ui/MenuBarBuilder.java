package ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import utils.*;
import java.awt.Color;

public class MenuBarBuilder {
    private FileHandler fileHandler;
    private TextManipulator textManipulator;
    
    public MenuBarBuilder(FileHandler fileHandler, TextManipulator textManipulator) {
        this.fileHandler = fileHandler;
        this.textManipulator = textManipulator;
    }
    
    public JMenuBar build() {
        // init main menu bar w/ theme colors
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(ThemeColors.TOOLBAR_BG);
        menuBar.setOpaque(true);
        
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
        JMenu editMenu = new JMenu("Tools");
        editMenu.setMnemonic(KeyEvent.VK_E);
        
        JMenuItem findItem = new JMenuItem("Find/Replace", KeyEvent.VK_F);
        JMenuItem countItem = new JMenuItem("Word Count", KeyEvent.VK_W);
        
        findItem.addActionListener(e -> showFindReplaceDialog());
        countItem.addActionListener(e -> showWordCount());
        
        editMenu.add(findItem);
        editMenu.add(countItem);

        // about me :3
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_P);

        JMenuItem aboutItem = new JMenuItem("About Me", KeyEvent.VK_A);
//        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Made by: Pustak Pathak\n2310110564", "CSD213 Project Submission for Pustak Pathak"));
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Made by: Pustak Pathak\n2310110564", "CSD213 Project Submission for Pustak Pathak", JOptionPane.INFORMATION_MESSAGE));
        aboutMenu.add(aboutItem);


        // add menus to bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(aboutMenu);

        // style all menus and items
        styleMenu(fileMenu);
        styleMenu(editMenu);
        styleMenu(aboutMenu);

        return menuBar;
    }
    
    private void styleMenu(JMenu menu) {
        menu.setBackground(ThemeColors.MENU_BG);
        menu.setOpaque(true);
        menu.setForeground(ThemeColors.TEXT);
        menu.setBorderPainted(false);
        menu.getPopupMenu().setBackground(ThemeColors.MENU_BG);
        menu.getPopupMenu().setBorder(BorderFactory.createLineBorder(ThemeColors.BORDER));
        
        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem item = menu.getItem(i);
            if (item != null) {
                item.setBackground(ThemeColors.MENU_BG);
                item.setForeground(ThemeColors.TEXT);
                item.setOpaque(true);
                
                item.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        item.setBackground(ThemeColors.MENU_HOVER);
                    }
                    public void mouseExited(MouseEvent e) {
                        item.setBackground(ThemeColors.TITLE_BAR);
                    }
                });
            }
        }
    }
    
    private void showFindReplaceDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Find and Replace");
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        
        JTextField findField = new JTextField(20);
        JTextField replaceField = new JTextField(20);
        
        JButton findButton = new JButton("Find");
        findButton.setToolTipText("Find next occurrence");
        
        JButton replaceButton = new JButton("Replace");
        replaceButton.setToolTipText("Replace current selection");
        
        
        JButton replaceAllButton = new JButton("Replace All");
        replaceAllButton.setToolTipText("Replace all occurrences");
        
        // find next occurrence
        findButton.addActionListener(e -> 
            textManipulator.findAndReplace(findField.getText(), "", false));
        
        // replace current selection
        replaceButton.addActionListener(e -> 
            textManipulator.findAndReplace(findField.getText(), replaceField.getText(), false));
        
        // replace all occurrences
        replaceAllButton.addActionListener(e -> 
            textManipulator.findAndReplace(findField.getText(), replaceField.getText(), true));
        
        // add labels and fields
        JLabel findLabel = new JLabel("Find:");
        findLabel.setForeground(ThemeColors.TEXT);
        dialog.add(findLabel);
        dialog.add(findField);
        JLabel replaceLabel = new JLabel("Replace with:");
        replaceLabel.setForeground(ThemeColors.TEXT);
        dialog.add(replaceLabel);
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