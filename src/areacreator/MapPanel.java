package areacreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Peixoto
 */
public class MapPanel extends JPanel {
    private final int singleTileWidth,
                      singleTileHeight,
                      numberOfCols,
                      numberOfRols;
    
    private int labelsIndex;
    
    private ImageIcon clickedTileIcon = null;
    private Tile clickedTile = null;    
    
    private ArrayList<Label> arrayOfLabels;
    
    public MapPanel(int singleTileWidth, int singleTileHeight, int numberOfCols, int numberOfRows) {
        this.singleTileWidth = singleTileWidth;
        this.singleTileHeight = singleTileHeight;
        this.numberOfCols = numberOfCols;
        this.numberOfRols = numberOfRows;
        
        arrayOfLabels = new ArrayList<>();

        this.setLayout(new GridLayout(numberOfRows, numberOfCols, 2, 2));
        this.setSize((numberOfCols*singleTileWidth), (numberOfRows*singleTileHeight));        
        
        for(int i = 0; i < (numberOfCols*numberOfRows); i++) {
            arrayOfLabels.add(new Label(3, 32, 32));
            arrayOfLabels.get(i).setBorder(BorderFactory.createLineBorder(Color.black));
            arrayOfLabels.get(i).setPreferredSize(new Dimension(singleTileWidth, singleTileHeight));
            
            labelsIndex = i;
            arrayOfLabels.get(i).addMouseListener(new MouseListener() {
                final int index = labelsIndex;
                private ImageIcon tileIcon;
                private Tile tile;
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(arrayOfLabels.get(index).isPrintProperty()) {
                         arrayOfLabels.get(index).setWall(!arrayOfLabels.get(index).isWall());
                     }
                     else {
                        tileIcon = clickedTileIcon;
                        tile = clickedTile;                     
                        arrayOfLabels.get(index).setIcon(tileIcon);
                     }
                }
                
                public void mousePressed(MouseEvent e) {}
                public void mouseReleased(MouseEvent e) {} 
                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
            });
            add(arrayOfLabels.get(i));
        }
    }
    
    public void setClickeds(Tile clickedTile, ImageIcon clickedTileIcon) {
        this.clickedTile = clickedTile;
        this.clickedTileIcon = clickedTileIcon;
    }    
    
    public void printProperty(boolean print) {
        for(int i = 0; i < arrayOfLabels.size(); i++)
            arrayOfLabels.get(i).printProperty(print);
    }

    void setGrid(boolean toolState) {
        if(toolState) {
            for(int i = 0; i < arrayOfLabels.size(); i++)
                arrayOfLabels.get(i).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(137, 137, 137)));
        }
        else {
            for(int i = 0; i < arrayOfLabels.size(); i++)
                arrayOfLabels.get(i).setBorder(null);
        }
    }
    
}
