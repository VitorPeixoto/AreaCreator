package areacreator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Peixoto
 */

public class ToolsPanel extends JPanel {
    private ArrayList<JButton> buttonsList;
    private int index;
    private boolean isToolSelected[];
    
    public ToolsPanel() {
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));        
        buttonsList = new ArrayList<>();
        isToolSelected = new boolean[]{false, true};
        
        this.setLayout(null);
        
        int x1 = 5, y1 = 5;
        for(int i = 0; i < 2; i++) {
            index = i;
            buttonsList.add(new JButton(new ImageIcon(getClass().getResource("/tools/tool"+i+".png"))));
            buttonsList.get(i).addMouseListener(new MouseListener() {
                private final int thisIndex = index;
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonsList.get(thisIndex).setSelected(!buttonsList.get(thisIndex).isSelected());
                    isToolSelected[thisIndex] = !isToolSelected[thisIndex];
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
            
            this.add(buttonsList.get(i));            
            buttonsList.get(i).setBounds(x1, y1, 30, 30);
            x1 += 35;
        }
            
    }
    
    public boolean getToolState(int tool) {
        return isToolSelected[tool];
    }
}
