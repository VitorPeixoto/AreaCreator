/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package areacreator;

import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollBar;

/**
 *
 * @author Peixoto
 */
public class MapPanel_ extends JComponent {
    private JScrollBar horizontalBar, verticalBar;
    private ArrayList<Tile> tilesList;
    private ArrayList<Label_> labelsList;
    private ImageIcon clickedTileIcon = null;
    private Tile clickedTile = null;
    private int index = 0, offsetX;
    private final int maxTilesOnScreenX;
    private final int maxTilesOnScreenY;
    private int horizontalTilesCount;
    
    public MapPanel_(int singleTileWidth, int singleTileHeight, int horizontalTilesCount, int verticalTilesCount) {
        maxTilesOnScreenX = getWidth()/singleTileWidth;
        maxTilesOnScreenY = getHeight()/singleTileHeight;
        this.labelsList = new ArrayList<>();
        this.horizontalTilesCount = horizontalTilesCount;
        
        this.setLayout(null);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        horizontalBar = new JScrollBar(JScrollBar.HORIZONTAL);
        verticalBar = new JScrollBar(JScrollBar.VERTICAL);                        
        
                setSize(670, 490);     
        add(horizontalBar);
        horizontalBar.setBounds(1, getHeight()-21, getWidth()-22, 20);
        horizontalBar.setMaximum(horizontalTilesCount);
        
        horizontalBar.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if(e.getAdjustmentType() == AdjustmentEvent.BLOCK_INCREMENT) offsetX = (e.getValue());
                else offsetX = (e.getValue()*-1);
                Label_.setOffset(offsetX*32);
            }
        });
        horizontalBar.addMouseListener(new MouseListener() {            
            
            @Override
            public void mouseClicked(MouseEvent e) {                                               
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        
        add(verticalBar);
        verticalBar.setBounds(getWidth()-21, 1, 20, getHeight()-21);                                 
        
        int x = 10, y = 10, horizontalCount = 0;
        for(int i = 0; i < (horizontalTilesCount*verticalTilesCount); i++) {
            index = i;
            labelsList.add(new Label_(x, y, singleTileWidth, singleTileHeight));
            labelsList.get(i).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(137, 137, 137)));
            labelsList.get(i).setSize(singleTileWidth, singleTileHeight);
            labelsList.get(i).addMouseListener(new MouseListener() {
                private ImageIcon tileIcon;
                private Tile tile;
                private final int thisIndex = index;
                @Override
                public void mouseClicked(MouseEvent e) {
                     if(labelsList.get(thisIndex).isPrintProperty()) {
                         labelsList.get(thisIndex).setWall(!labelsList.get(thisIndex).isWall());
                     }
                     else {
                        tileIcon = clickedTileIcon;
                        tile = clickedTile;                     
                        labelsList.get(thisIndex).setIcon(tileIcon);
                     }           
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }
                
                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    
                }
            });
            labelsList.get(i).setLocation(x, y);

            horizontalCount++;
            if(horizontalCount < horizontalTilesCount) {
                x += singleTileWidth;
            }
            else {
                horizontalCount = 0;
                x = 10;
                y += singleTileHeight;
            }
            this.add(labelsList.get(i));          
        }                  
    }
    
    
    public void paintChildren(Graphics g) {        
        g.setClip(offsetX*32, 0, getWidth(), getHeight());
        super.paintChildren(g);
    }    
    
    public void setClickeds(Tile clickedTile, ImageIcon clickedTileIcon) {
        this.clickedTile = clickedTile;
        this.clickedTileIcon = clickedTileIcon;
    }    
    
    public void printProperty(boolean print) {
        for(int i = 0; i < labelsList.size(); i++)
            labelsList.get(i).printProperty(print);
    }

    void setGrid(boolean toolState) {
        if(toolState) {
            for(int i = 0; i < labelsList.size(); i++)
                labelsList.get(i).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(137, 137, 137)));
        }
        else {
            for(int i = 0; i < labelsList.size(); i++)
                labelsList.get(i).setBorder(null);
        }
    }
}
