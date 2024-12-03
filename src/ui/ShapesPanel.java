package ui;

import shapes.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ShapesPanel extends JPanel {
    private List<shapes.Shape> shapes;
    private shapes.Shape currentShape;
    private Point startPoint;
    private JComboBox<String> shapeSelector;
    private String[] shapeTypes = {"Rectangle", "Circle", "Triangle", "Square", "Pentagon"};
    
    public ShapesPanel() {
        shapes = new ArrayList<>();
        setPreferredSize(new Dimension(300, 0));
        
        // init shape selector
        shapeSelector = new JComboBox<>(shapeTypes);
        shapeSelector.setMaximumSize(new Dimension(150, 30));

        // shape selector label
        JLabel shapeLabel = new JLabel("Shape: ");
        shapeLabel.setFont(new java.awt.Font("Courier New", java.awt.Font.BOLD, 16));
        shapeLabel.setForeground(java.awt.Color.WHITE);

        // create wrapper panel for dropdown
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(shapeLabel);
        topPanel.add(shapeSelector);
        
        // use border layout to place dropdown at top
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        
        setupMouseListeners();
    }
    
    private void setupMouseListeners() {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                // create shape based on selection
                String selectedShape = (String)shapeSelector.getSelectedItem();
                currentShape = createShape(selectedShape, startPoint.x, startPoint.y);
                shapes.add(currentShape);
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentShape != null) {
                    int width = e.getX() - startPoint.x;
                    int height = e.getY() - startPoint.y;
                    currentShape.resize(width, height);
                    repaint();
                }
            }
        };
        
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
    }
    
    private shapes.Shape createShape(String type, int x, int y) {
        // factory method for creating shapes
        return switch (type) {
            case "Circle" -> new Circle(x, y, 0, 0);
            case "Triangle" -> new Triangle(x, y, 0, 0);
            case "Square" -> new Square(x, y, 0, 0);
            case "Pentagon" -> new Pentagon(x, y, 0, 0);
            default -> new Rectangle(x, y, 0, 0);
        };
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // draw all shapes
        for (shapes.Shape shape : shapes) {
            shape.draw(g2d);
        }
    }
} 