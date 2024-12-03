package shapes;

import java.awt.*;

public class Square extends Shape {
    public Square(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        // draw square using equal w&h
        g2d.setColor(color);
        int size = Math.min(Math.abs(width), Math.abs(height));
        g2d.drawRect(x, y, size, size);
    }
} 