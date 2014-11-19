/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadingfire;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author NuPLoY
 */
public class Cell extends JButton {
    public static final int YELLOW = 0, GREEN = 1, RED = 2;
    private int status;
    private boolean showNum;
    
    /**
     * Constructor for create the empty cell
     */
    public Cell() {
        this.status = 0;
        this.showNum = false;
        this.setText("" + this.status);
        this.setBackground(Color.YELLOW);
        this.setEnabled(false);
    }
    
    /*
     * Create the cell by using status
     * @param status
     */
    public Cell(int status) {
        this.status = status;
        this.showNum = true;
        this.setEnabled(false);
        this.setStatus(status);
        this.setOff();
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
            switch (status) {
                case 0:
                    this.setBackground(Color.yellow);
                    break;
                case 1:
                    this.setBackground(Color.green);
                    break;
                case 2:
                    this.setBackground(Color.red);
                    break;
            }
            if(this.showNum){
                this.setOn();
            } 

            
    }
    
    public void setOff(){
        this.setText("");
        this.showNum = false;
        validate();
        repaint();
    }
    
    public void setOn(){
        this.setText("" + this.status);
        this.showNum = true;
        validate();
        repaint();
    }
    /**
     * Check if the cell is empty
     * @return true if the cell is empty
     */
    public boolean isEmpty() {
        return status == YELLOW;
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
