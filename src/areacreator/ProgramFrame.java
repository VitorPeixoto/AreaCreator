package areacreator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Peixoto
 */

public class ProgramFrame extends JFrame {
    private MapPanel mapPanel;
    private ArrayList<TilesPanel> tilesPanelList;
    private ArrayList<JScrollPane> scrollPaneList;
    private ToolsPanel toolsPanel;
    private JTabbedPane tilesTabbedPanel;
    private final Thread atualize;
    private JScrollPane jsp;
    private int currentTab = 0;
    
    private Listener L;
    
    ProgramFrame() throws IOException {
        this.setLayout(null);
        this.tilesPanelList = new ArrayList<>();
        scrollPaneList = new ArrayList<>();
        
        L = new Listener();
        this.addMouseMotionListener(L);
        
        jsp = new JScrollPane();
        this.mapPanel = new MapPanel(32, 32, 20, 20);
        mapPanel.setPreferredSize(new Dimension(mapPanel.getWidth(), mapPanel.getHeight()));
        jsp.setViewportView(mapPanel);
        add(jsp);
        jsp.setBounds(10, 60, 670, 490);
        
        toolsPanel = new ToolsPanel();
        toolsPanel.setBounds(10, 10, 980, 40);
        
        for(int i = 0; i < 2; i++) {
            tilesPanelList.add(new TilesPanel(i));            
            //tilesPanelList.get(i).setPreferredSize(new Dimension(300, 300));
            scrollPaneList.add(new JScrollPane(tilesPanelList.get(i)));
            //scrollPaneList.get(i).setViewportView(tilesPanelList.get(i));
            //scrollPaneList.get(i).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
            scrollPaneList.get(i).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }    
        
        //tilesPanel.setBounds(690, 60, 300, 200);      
        tilesTabbedPanel = new JTabbedPane();
        tilesTabbedPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i = 0; i < tilesTabbedPanel.getTabCount(); i++) {                    
                    if(tilesTabbedPanel.getSelectedIndex() == i) {                      
                        currentTab = i;
                    }    
                    else tilesPanelList.get(i).setAllSelected(false);
                }    
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        tilesTabbedPanel.setBounds(690, 60, 300, 200);      
        tilesTabbedPanel.addTab("Basic Tiles", (scrollPaneList.get(0)));
        tilesTabbedPanel.addTab("Other Tiles", (scrollPaneList.get(1)));
        
        atualize = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    try {
                        mapPanel.setClickeds(tilesPanelList.get(currentTab).getActualTile(), tilesPanelList.get(currentTab).getActualTileIcon());
                        mapPanel.setGrid(toolsPanel.getToolState(1));
                        mapPanel.printProperty(toolsPanel.getToolState(0));
                        repaint();
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProgramFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        }); atualize.start();
        
        this.add(tilesTabbedPanel);
        this.add(toolsPanel);
    }
    
    public void paint(Graphics g) {
        if(!L.isDragNull())  {
            Rectangle dragged = L.getDragged();
            g.fillRect((int)dragged.getX(), (int)dragged.getY(), (int)dragged.getWidth(), (int)dragged.getHeight());
        }
        //g.drawLine(0, 0, 200, 200);
    }
        
}
