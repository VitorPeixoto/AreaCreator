package areacreator;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Peixoto
 */

public class Listener implements MouseMotionListener {
    private int x, y;
    private Point initialPoint = null;
    private Dimension finalPoint;
    private Rectangle dragged;
    private Thread print;            

    @Override
    public void mouseDragged(MouseEvent e) {
        if(initialPoint == null) {
            initialPoint = new Point(e.getX(), e.getY());
            finalPoint = new Dimension(e.getX(), e.getY());
        }
        finalPoint = new Dimension(e.getX(), e.getY());
        dragged = new Rectangle(initialPoint, finalPoint);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //if(initialPoint != null) finalPoint = new Dimension(e.getX(), e.getY());
    }
    
    public Rectangle getDragged() {        
        dragged = new Rectangle(initialPoint, finalPoint);
        return dragged;
    }
    
    public boolean isDragNull() {
        return (dragged == null);
    }
}
