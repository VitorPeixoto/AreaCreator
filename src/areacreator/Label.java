package areacreator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Peixoto
 */
public class Label extends JLabel {
    private boolean property = false;
    private boolean wall = false, printProperty;
    private Image propertyImage;
    private int constantOfDivision = 1;
    private ArrayList<JLabel> labels;
    
    Label(){}
    
    Label(int constantOfDivision, int width, int height) {
        this.constantOfDivision = constantOfDivision;                
        this.setLayout(new GridLayout(constantOfDivision, constantOfDivision));
        if(!((width/constantOfDivision) <= 1) || ((height/constantOfDivision) <= 1)) {
        //else {
            labels = new ArrayList<>();
            for(int i = 0; i < (constantOfDivision*constantOfDivision); i++) {
                labels.add(new JLabel());
                labels.get(i).setSize((width/constantOfDivision), (height/constantOfDivision));
                labels.get(i).setBorder(BorderFactory.createLineBorder(Color.GRAY));
                add(labels.get(i));
                labels.get(i).setEnabled(false);
                labels.get(i).setVisible(false);
            }
        }
    }
    
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
    
    public boolean isPrintProperty() {
        return printProperty;
    }

    public boolean isWall() {
        return wall;
    }     
}
