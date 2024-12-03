package shapes;

import java.awt.*;

public class Pentagon extends Shape {
    public Pentagon(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        // calc pentagon points
        g2d.setColor(color);
        int[] xPoints = new int[5];
        int[] yPoints = new int[5];
        
        // center point for calcs
        int centerX = x + width/2;
        int centerY = y + height/2;
        int radius = Math.min(Math.abs(width), Math.abs(height))/2;
        
        // create 5 points using trig
        for (int i = 0; i < 5; i++) {
            double angle = i * 2 * Math.PI / 5 - Math.PI/2;
            xPoints[i] = (int)(centerX + radius * Math.cos(angle));
            yPoints[i] = (int)(centerY + radius * Math.sin(angle));
        }
        
        g2d.drawPolygon(xPoints, yPoints, 5);
    }
} 