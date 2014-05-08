package areacreator;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Peixoto
 */
public class Label_ extends JLabel {
    private boolean wall = false, printProperty;
    private Image propertyImage;
    private final int width, height, x, y;
    private static int offset;

    public Label_(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;        
    }        
    
    public void paint(Graphics g) {
        this.setBounds(x+g.getClipBounds().x, y, width, height);
        super.paint(g);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if(getIcon() != null)
            g.drawImage(((ImageIcon)this.getIcon()).getImage(), 0, 0, null);
        if(printProperty) 
            g.drawImage(propertyImage, 7, 7, this.getWidth()-14, this.getHeight()-14, null);
    }
    
    public void printProperty(boolean print) {
        propertyImage = new javax.swing.ImageIcon(getClass().getResource("/Others/"+(wall ? 'X' : 'O')+".png")).getImage();
        printProperty = print;
        repaint();
    }
    
    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public static void setOffset(int offset) {
        Label_.offset = offset;
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getX()+offset, this.getY(), this.width, this.height);
    }
    
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x+offset, y, width, height);
    }
    
    public boolean isPrintProperty() {
        return printProperty;
    }

    public boolean isWall() {
        return wall;
    }        
    
}
