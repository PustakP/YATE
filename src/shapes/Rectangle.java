package shapes;

import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.drawRect(x, y, width, height);
    }
} 