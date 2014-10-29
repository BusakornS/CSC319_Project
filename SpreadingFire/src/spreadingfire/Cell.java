/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadingfire;

import java.awt.Color;

/**
 *
 * @author NuPLoY
 */
public class Cell {
    public static final int YELLOW = 0, GREEN = 1, RED = 2;
    private int status;
    
    public Cell() {
        status = Cell.GREEN;
    }
    
    /*
     * Create the cell by using status
     * @param status
     */
    public Cell(int status) {
        this.status = status;
    }
    
    /*
     * Get the status of cell
     */
    public int getStatus() {
        return status;
    }
    
    /*
     * Set the status of cell
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    /**
     * Get the RGB color of each cell
     * @return RGB color
     */
    public Color getColor() {
        if (status == GREEN) {
            return Color.GREEN;
        }
        if (status == RED) {
            return Color.RED;
        }
        return Color.YELLOW;
    }
}
