/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadingfire;

import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author NuPLoY
 */
public class SpreadingFire {
    static int width, height;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //JFrame project = new GUI();
        TUI();
    }
    
    public static void TUI() {
        System.out.println("Input the size of the forest : ");
        System.out.print("Width = ");
        Scanner w = new Scanner(System.in);
        int width = w.nextInt();
        System.out.print("Height = ");
        Scanner h = new Scanner(System.in);
        int height = h.nextInt();
        
        System.out.println("Set the probability of tree in the cell catching fire (0 - 100) : ");
        Scanner probFireTree = new Scanner(System.in);
        int probC = probFireTree.nextInt();
        
        // System.out.println("Set the density of forest : (0 - 1) ");
        // Scanner density = new Scanner(System.in);
        // int probT = density.nextInt();
        
        Forest grid = new Forest(width, height, probC, 0, 100);
        
        System.out.println("Initial grid");
        grid.printForest();
        int step = 1;
        while (!grid.alreadyBurn()) {
            grid.spreading();
            System.out.println("Step = " + step);
            grid.printForest();
            step++;
            grid.checkFire();
        }
        System.out.println("Total step = " + (step - 1));
    }
}
