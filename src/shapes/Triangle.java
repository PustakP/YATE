package shapes;

import java.awt.*;

public class Triangle extends Shape {
    public Triangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        // draw triangle using polygon
        g2d.setColor(color);
        int[] xPoints = {x, x + width/2, x + width};
        int[] yPoints = {y + height, y, y + height};
        g2d.drawPolygon(xPoints, yPoints, 3);
    }
} 