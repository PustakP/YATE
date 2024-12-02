package ui;


import shapes.Shape;
import shapes.Rectangle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ShapesPanel extends JPanel {
    private List<Shape> shapes;
    private Shape currentShape;
    private Point startPoint;
    
    public ShapesPanel() {
        shapes = new ArrayList<>();
        setPreferredSize(new Dimension(300, 0));
        setupMouseListeners();
    }
    
    private void setupMouseListeners() {
        // impl mouse listeners for shape manipulation
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                // create new shape based on selected type
                currentShape = new Rectangle(startPoint.x, startPoint.y, 0, 0);
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
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // draw all shapes
        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }
} 