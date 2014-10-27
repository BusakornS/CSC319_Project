/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadingfire;

/**
 *
 * @author NuPLoY
 */

import java.io.*;

public class Forest {
    static int size = 7; // input size using Scanner
    static int[][] Jungle = new int[size][size];
    static double[][] probBurn = new double[size][size];
    int[][] fired = new int[size][size];
    
    /**
     * Constructor for create the forest
     */
    public Forest() {
        createForest();
        printForest();
        //fired[3][3] = 1;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                fireBurn(3+i,3+j); 
                fireBurn(3-i,3-j);
            }
        }

        //printForest();
    }

    public void createForest() {
        for (int i = 0; i < Jungle.length; i++) {
            for (int j = 0; j < Jungle.length; j++) {
                // If it is the border of the forest, there is an empty space
                if (i == 0 || j == 0 || i == Jungle.length - 1 || j == Jungle.length - 1) {
                    Jungle[i][j] = 0;
                } else {
                    Jungle[i][j] = 2;
                }
            }
        }
        Jungle[size/2][size/2] = 1;
    }

    public void randomProb() {
        for (int i = 0; i < probBurn.length; i++) {
            for (int j = 0; j < probBurn.length; j++) {
                probBurn[i][j] = Math.random();
            }
        }
    }
    
    public void printForest() {
         for (int i = 0; i < Jungle.length; i++) {
            for (int j = 0; j < Jungle.length; j++) {
                System.out.print(Jungle[i][j] + " ");
            }
            System.out.println();
         }
         System.out.println();
    }
    
    public void fireBurn(int x, int y) {
        
        /* Debug booleam 
        boolean isBurn = false;
        */
        /*
        for (int i = 0; i < Jungle.length; i++) {
            for (int j = 0; j < Jungle.length; j++) {
                
                if (Jungle[i][j] == 1) {
                    Jungle[i][j] = 0;
                    if (Jungle[i - 1][j] == 2) { // North
                        Jungle[i - 1][j] = 1;
                        printForest();
                    }
                    if (Jungle[i][j + 1] == 2) { // East
                        Jungle[i][j + 1] = 1;
                        printForest();
                    }
                    if (Jungle[i + 1][j] == 2) { // South
                        Jungle[i + 1][j] = 1;
                        printForest();
                    }
                    if (Jungle[i][j - 1] == 2) { // West
                        Jungle[i][j - 1] = 1;
                        printForest();
                    }
                    i = 0; j = 0;
                    //isBurn = true;
                }
                /* Debug
                if(isBurn)
                break;
                
                
                
            }
        }*/
        //if(x < 4 && y < 4) {
        for(int i = 0 ; i < 6 ; i++){
            for(int j = 0 ; j < 6 ; j++){
                if(Jungle[i][j] == 1){
                    Jungle[i][j] = 0;
                }   
            }
        }
        if(Jungle[x][y+1] == 2){
            Jungle[x][y+1] = 1;
        }
            //if(y+1 < 6){
                //fireBurn(x, y+1);
            //}
            //fired[x][y+1] = 1;
            //fireBurn(x, y+1);
        if(Jungle[x][y-1] == 2){
            Jungle[x][y-1] = 1;
        }
            //if(y-1 > 0){
              //  fireBurn(x, y-1);
            //}
            //fired[x][y-1] = 1;
            //fireBurn(x, y-1);
        if(Jungle[x-1][y] == 2){
            Jungle[x-1][y] = 1;
        }
            //if(x-1 > 0){
              //  fireBurn(x-1, y);
            //}
            //fired[x-1][y] = 1;
            //fireBurn(x-1, y);
        if(Jungle[x+1][y] == 2){
            Jungle[x+1][y] = 1;
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
