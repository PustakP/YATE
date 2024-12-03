package shapes;

import java.awt.*;

public class Circle extends Shape {
    public Circle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        // draw circle using oval w/ equal w&h
        g2d.setColor(color);
        int size = Math.min(Math.abs(width), Math.abs(height));
        g2d.drawOval(x, y, size, size);
    }
} 