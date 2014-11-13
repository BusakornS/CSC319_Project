/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadingfire;

import java.util.*;
import java.awt.Color;

/**
 *
 * @author NuPLoY
 */
public class Forest {
    private Cell[][] cell;
    private View observer;
    private boolean checkCellCannotFire[][];
    public int width, height, probCatch, probBurn, probTree;
    private int step;

    /**
     * Constructor for create the forest
     */
    public Forest() {
        this(30, 30, 50, 0, 100);
    }
    
    public Forest(int width, int height) {
        this(width, height, 50, 0, 100);
    }
    
    /**
     * Constructor for create the grid
     * @param width
     * @param height
     * @param probC
     * @param probB
     * @param probT
     */
    public Forest(int width, int height, int probC, int probB, int probT) {
        this.width = width;
        this.height = height;
        this.probCatch = probC;
        this.probBurn = probB;
        this.probTree = probT;
        // Reset the field
        resetForest();
    }
    
    /**
     * Set the fire probability of the forest
     * @param probCatch
     */
    public void setProbCatch(int probC) {
        this.probCatch = probC;
    }
    
    /**
     * Set the burning probability of each tree
     * @param probBurn
     */
    public void setProbBurn(int probB) {
        this.probBurn = probB;
    }
    
    /**
     * Set the density of tree in the forest
     * @param probTree
     */
    public void setProbTree(int probT) {
        this.probTree = probT;
    } 
    
    /**
     * Set the size of the forest
     * @param width
     * @param height 
     */
    public void setForestSize(int width, int height) {
        this.width = width;
        this.height = height;
        // Reset the field after set
        resetForest();
    }
    
    /**
     * Get the RGB color of cell at (x, y)
     * @param x
     * @param y
     * @return RGB Color
     */
    public Color getColor(int x, int y) {
        try {
            return cell[x][y].getColor();
        } catch (Exception e) {
            // If (x, y) is out of bound, set as empty
            return Color.WHITE;
        }
    }
    
    /**
     * Get the status of cell at(x, y)
     * @param x
     * @param y
     * @return Cell Status
     */
    private int getStatus(int x, int y) {
        try {
            return cell[x][y].getStatus();
        } catch (Exception e) {
            // If (x, y) is out of bound, set as empty
            return Cell.YELLOW;
        }
    }
    
    public void resetForest() {
        // Create new grid with current size
        // 0 : Empty space
        // 1 : Tree
        // 2 : Fire burning
        cell =  new Cell[width][height];
        checkCellCannotFire = new boolean[width][height];
        checkFire();
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                // If it is the border of the forest, there is an empty space
                if (i == 0 || j == 0 || i == cell.length - 1 || j == cell[0].length - 1) {
                    cell[i][j] = new Cell(Cell.YELLOW);
                } else {
                    if (random(probTree)) {
                        if (random(probBurn)) {
                            cell[i][j] = new Cell(Cell.RED);
                        } else {
                            cell[i][j] = new Cell(Cell.GREEN);
                        }
                    } else {
                        cell[i][j] = new Cell(Cell.YELLOW);
                    }
                }
            }
        }
        // Set the middle to be the starting point
        cell[width / 2][height / 2] = new Cell(Cell.RED);
        
        // Reset step
        step = 0;
        
        // Update the field
        update();
    }
    
    /**
     * Set the boolean of every cell to false
     */
    public void checkFire() {
        for (int i = 1; i < checkCellCannotFire.length - 1; i++) {
            for (int j = 1; j < checkCellCannotFire.length - 1; j++) {
                checkCellCannotFire[i][j] = false;
            }
        }
    }
    
    /**
     * Random number
     * @param r
     * @return boolean of random number
     */
    public boolean random(double r) {
        double rand = Math.random() * 100;
        if (rand < r) {
            return true;
        }
        return false;
    }
    
    /**
     * Spread the fire from (x, y)
     * @param direction
     * @param x
     * @param y 
     */
    public void fireBurn(String direction, int x, int y) {
        if (direction.equals("West") && getStatus(x - 1, y) == Cell.GREEN && random(probCatch) == true) { // West
            cell[x - 1][y].setStatus(Cell.RED);
            checkCellCannotFire[x - 1][y] = true;
        }
        if (direction.equals("East") && getStatus(x + 1, y) == Cell.GREEN && random(probCatch) == true) { // East
            cell[x + 1][y].setStatus(Cell.RED);
            checkCellCannotFire[x + 1][y] = true;
        }
        if (direction.equals("North") && getStatus(x, y - 1) == Cell.GREEN && random(probCatch) == true) { // North
            cell[x][y - 1].setStatus(Cell.RED);
            checkCellCannotFire[x][y - 1] = true;
        }
        if (direction.equals("South") && getStatus(x, y + 1) == Cell.GREEN && random(probCatch) == true) { // South
            cell[x][y + 1].setStatus(Cell.RED);
            checkCellCannotFire[x][y + 1] = true; 
        }
    }
    
     
    /**
     * Check if there is no burning anymore
     * @return true if there is burning tree in the forest
     */
    public boolean alreadyBurn() {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (getStatus(i, j) == Cell.RED) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Print forest
     */
    public void printForest() {
         for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                System.out.printf("%2s ", "");
                if (cell[i][j].getStatus() == Cell.GREEN) {
                    System.out.print(1);
                } else if (cell[i][j].getStatus() == Cell.RED) {
                    System.out.print(2);
                } else if (cell[i][j].getStatus() == Cell.YELLOW) {
                    System.out.print(0);
                }
            }
            System.out.println();
         }
    }
    
    /**
     * Search for the fire
     */
    public void spreading() {
        step++;
        update();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        for (int i = 0; i < cell.length - 1; i++) {
            for (int j = 0; j < cell[0].length - 1; j++) {
                if (getStatus(i, j) == Cell.RED && checkCellCannotFire[i][j] == false) {
                    cell[i][j].setStatus(Cell.YELLOW);
                    fireBurn("North", i, j);
                    fireBurn("South", i, j);
                    fireBurn("West", i, j);
                    fireBurn("East", i, j);
                }
            }
        }
    }
   
    /**
     * Add the observer for this model
     * @param view
     */
    public void addObserver(View view){
        observer = view;
        update();
    }
     
    /**
     * Update this field
     */ 
    public void update() {
        if (observer != null){
            observer.update(cell);
            if(!alreadyBurn()) {
                observer.updateStep(step);
            }
        }
    }
}