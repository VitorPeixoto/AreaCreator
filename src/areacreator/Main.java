/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package areacreator;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Peixoto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ProgramFrame pf = new ProgramFrame();
        pf.setSize(1000, 600);
        pf.setResizable(false);
        pf.setDefaultCloseOperation(3);
        pf.setVisible(true);
        /*
        JFrame jf = new JFrame();
        GridPanel gp = new GridPanel(32, 32, 100, 10);
        jf.add(gp);
        jf.setVisible(true);*/
    }
    
}
