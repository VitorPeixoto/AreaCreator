package areacreator;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Peixoto
 */
public class TilesPanel extends javax.swing.JScrollPane {
    private int numberOfTiles, index;
    private final int maxRows, maxCols, cols = 7, rows;
    
    private ArrayList<ImageIcon> images;
    private Tile actualTile = null;
    private ImageIcon actualTileIcon = null;
    
    public TilesPanel(int tilesImage) throws IOException {
        BufferedImage a = ImageIO.read(new java.io.File(getClass().getResource("/Tilesets/Tiles"+tilesImage+".png").getPath()));
        
        maxCols = (a.getWidth()/32);
        maxRows = (a.getHeight()/32);
        
        numberOfTiles = (maxCols*maxRows);
        rows = numberOfTiles / cols;
        initComponents(tilesImage, a);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(int tilesImage, BufferedImage image) throws IOException {
        BufferedImage aux;
        
        labelsArray = new ArrayList<>();        
        images = new java.util.ArrayList<>();                
        
        javax.swing.JOptionPane.showMessageDialog(null, numberOfTiles);
        int x1 = 0, y1 = 0;
        
        for(int i = 0; i < (numberOfTiles); i++) {
            try {
                aux = image.getSubimage(x1, y1, 32, 32);
                images.add(new ImageIcon(createImage(aux.getSource())));            
                x1 += 32;
            } catch(java.awt.image.RasterFormatException ex) {
                x1 = 0;
                y1 += 32;
                try {
                    aux = image.getSubimage(x1, y1, 32, 32);
                    images.add(new ImageIcon(createImage(aux.getSource())));
                } catch(java.awt.image.RasterFormatException ex2) { break; }             
            }                       
            
        }    
        
        Collections.reverse(images);
        //rows = (numberOfTiles/cols);
        this.setLayout(null);
        //this.setLayout(new GridLayout(maxRows, maxCols, 2, 2));
        
        
        //Listener L = new Listener();

        //this.addMouseListener(L);
        
        int x2 = -25, y2 = 4;
        for(int i = 0; i < numberOfTiles; i++) {
            index = i;
            labelsArray.add(new javax.swing.JLabel());
            labelsArray.get(i).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            labelsArray.get(i).setIcon((images.size() > 0 ? this.images.remove((images.size()-1)) : null));

            labelsArray.get(i).addMouseListener(new MouseListener() {
                private final int thisIndex = index;
                @Override
                public void mouseClicked(MouseEvent e) {
                    labelsArray.get(index).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));                    
                    actualTile = Tile.getTile(thisIndex);
                    labelsArray.get(thisIndex).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 2));
                    actualTileIcon = (ImageIcon) labelsArray.get(thisIndex).getIcon();
                    index = thisIndex;
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
            
            x2 += ((i%(cols) == 0 && i != 0) ? ((-35*((cols)-1))) : 35);
            y2 += ((i%(cols) == 0 && i != 0) ? 35 : 0);
            //labelsList.get(i).setBounds(x2, y2, 32, 32);            
            
            
            labelsArray.get(i).setBounds(x2, y2, 32, 32);
            /*x2 += ((i%7 == 0 && i != 0) ? (8*-33) : 33);
            y2 += ((i%7 == 0 && i != 0) ? 33 : 0);*/
            this.add(labelsArray.get(i));            
            labelsArray.get(i).setSize(32, 32);
        }            
        this.setPreferredSize(new Dimension(cols*32, (rows*35)+6));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));        
        
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private ArrayList<javax.swing.JLabel> labelsArray;
    private ArrayList<javax.swing.GroupLayout.SequentialGroup> sequentialGroups;
    private ArrayList<javax.swing.GroupLayout.ParallelGroup> parallelGroups;
    private javax.swing.JScrollBar jScrollBar1;

    // End of variables declaration
    public Tile getActualTile() {
        return actualTile;
    }
    
    public ImageIcon getActualTileIcon() {
        return actualTileIcon;
    }

    void setAllSelected(boolean b) {
        for(int i = 0; i < labelsArray.size(); i++) {
            labelsArray.get(i).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            actualTile = null;
            actualTileIcon = null;
        }    
    }
    
}
