package shapes;

// base class for all shapes
import java.awt.*;

public abstract class Shape {
    protected int x, y, width, height;
    protected Color color;
    
    public Shape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = Color.WHITE;
    }
    
    public abstract void draw(Graphics2D g2d);
    
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }
} 