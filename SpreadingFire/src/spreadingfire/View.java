/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadingfire;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author NuPLoY
 */
public class View extends JPanel {
    Cell cell[][];
    int width, height;
    private int step;
    
    /**
     * Paint the cell
     */
    @Override
    public void paintComponent(Graphics g) {
        // Fill the background color with black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 400);
        
        // Calculate the border width
        int border = (400 - (width * cell.length)) / 2;
        
        // Do not paint if there is no cell
        if (cell == null) return;
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                // Paint the cell
                g.setColor(cell[i][j].getColor());
                g.fillRect(border + j * width, border + i * height, width, height);
                // Paint the cell Border
                g.setColor(Color.BLACK);
                g.drawRect(border + j * width, border + i * height, width, height);
            }
        }
        
        // Paint the "Step" label
        g.fillRect(0, 400, 400, 25);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("Step : " + step, 170, 420);
    }
    
    /**
     * Constructor - create the view panel
     * @param width
     * @param height
     */
    public View(int width, int height) {
        this.width = width;
        this.height = height;
        cell = null;
    }
    
    /**
     * Set the block size
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    /**
     * Update the cell
     * @param cell
     */
    public void update(Cell cell[][]) {
        this.cell = cell;
        repaint();
    }
    
    public void updateStep(int step) {
        this.step = step;
    }
}
