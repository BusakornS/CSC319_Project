/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadingfire;

/**
 *
 * @author NuPLoY
 */

import java.util.*;
import java.awt.Color;

public class Forest {
    private Cell[][] cell;
    private int width, height, delay;
    private double probCatch, probBurn, probTree;
    private boolean checkCellCannotFire[][];
    
    static int size = 7; // input size using Scanner
    static double[][] probBurning = new double[size][size];
    int[][] fired = new int[size][size];
    
    /**
     * Constructor for create the forest
     */
    public Forest() {
        this(30, 30, 50, 0, 100, 100);
    }
    
    public Forest(int width, int height) {
        this(width, height, 50, 0, 100, 100);
    }
    
    /**
     * Constructor for create the grid
     * @param width
     * @param height
     * @param probC
     * @param probB
     * @param probT
     * @param delay
     */
    public Forest(int width, int height, int probC, int probB, int probT, int delay) {
        this.width = width;
        this.height = height;
        this.probCatch = probC;
        this.probBurn = probB;
        this.probTree = probT;
        this.delay = delay;
        createForest();
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
        createForest();
    }
    
    /**
     * Get the RGB color of cell at (x, y)
     * @param x
     * @param y
     * @return 
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
     * @return 
     */
    private int getStatus(int x, int y) {
        try {
            return cell[x][y].getStatus();
        } catch (Exception e) {
            // If (x, y) is out of bound, set as empty
            return Cell.YELLOW;
        }
    }
    
    public void createForest() {
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
                    cell[i][j] = new Cell(0);
                } else {
                    cell[i][j] = new Cell(1);
                }
            }
        }
        cell[width / 2][height / 2] = new Cell(2);
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
     * @param c
     * @return boolean of random number
     */
    public boolean randomCatch(double c) {
        double rand = Math.random() * 100;
        if (rand < c) {
            return true;
        }
        return false;
    }
    
    public void fireBurn(String direction, int x, int y) {
        if (direction.equals("North") && getStatus(x - 1, y) == Cell.GREEN /*&& randomCatch(probCatch) == true*/) { // North
            cell[x - 1][y].setStatus(2);
            //checkCellCannotFire[x - 1][y] = true;
        }
        if (direction.equals("South") && getStatus(x + 1, y) == Cell.GREEN /*&& randomCatch(probCatch) == true*/) { // South
            cell[x + 1][y].setStatus(2);
            //checkCellCannotFire[x + 1][y] = true;
        }
        if (direction.equals("West") && getStatus(x, y - 1) == Cell.GREEN /*&& randomCatch(probCatch) == true*/) { // West
            cell[x][y - 1].setStatus(2);
            //checkCellCannotFire[x][y - 1] = true;
        }
        if (direction.equals("East") && getStatus(x, y + 1) == Cell.GREEN /*&& randomCatch(probCatch) == true*/) { // East
            cell[x][y + 1].setStatus(2);
            //cell[x][y + 1].setStatus(2);
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
    
    public void spreading() {
        
        /*try {
            if (!allGone()) {
                //fireBurn();
            }
            createForest();
            Thread.sleep(getDelay());
        } catch (InterruptedException e) {
            
        }*/
        for (int i = 0; i < cell.length - 1; i++) {
            for (int j = 0; j < cell[0].length - 1; j++) {
                if (getStatus(i, j) == Cell.RED /*&& checkCellCannotFire[i][j] == false*/) {
                    cell[i][j].setStatus(0);
                    fireBurn("North", i, j);
                    fireBurn("South", i, j);
                    fireBurn("West", i, j);
                    fireBurn("East", i, j);
                }
            }
        }
    }

    /*
     * Set the delay time
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    /*
     * Get the delay time
     */
    public int getDelay() {
        return this.delay;
    }
}
    /*
        //if(x < 4 && y < 4) {
        for(int i = 0 ; i < 6 ; i++){
            for(int j = 0 ; j < 6 ; j++){
                if(Jungle[i][j] == 2){
                    Jungle[i][j] = 0;
                }   
            }
        }
        if(Jungle[x][y+1] == 1){
            Jungle[x][y+1] = 2;
        }
            //if(y+1 < 6){
                //fireBurn(x, y+1);
            //}
            //fired[x][y+1] = 1;
            //fireBurn(x, y+1);
        if(Jungle[x][y-1] == 1){
            Jungle[x][y-1] = 2;
        }
            //if(y-1 > 0){
              //  fireBurn(x, y-1);
            //}
            //fired[x][y-1] = 1;
            //fireBurn(x, y-1);
        if(Jungle[x-1][y] == 1){
            Jungle[x-1][y] = 2;
        }
            //if(x-1 > 0){
              //  fireBurn(x-1, y);
            //}
            //fired[x-1][y] = 1;
            //fireBurn(x-1, y);
        if(Jungle[x+1][y] == 1){
            Jungle[x+1][y] = 2;
        }
            //if(x+1 < 6){
              //  fireBurn(x+1, y);
            //}
            //fired[x+1][y] = 1;
            //fireBurn(x+1, y);
            //Jungle[x][y]=0;
            printForest();
        //}
    }
}
* */
