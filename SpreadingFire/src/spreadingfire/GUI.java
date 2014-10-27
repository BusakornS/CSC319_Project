/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadingfire;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author NuPLoY
 */
public class GUI extends JFrame {
    JButton startButton, stopButton, resetButton;
    Forest forest;
    
    /**
     * Create the GUI of project
     */
    public GUI() {
        // Create the main frame
        super("CSC319 Spread Of Fire (Group 2)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
                
        // Create the controller panel
        JPanel controller = new JPanel();
        controller.setLayout(new GridLayout(6, 1));
        
        // Add the controller panel
        add(controller);
        
        // Create and add the first row to controller panel
        JPanel controller1 = new JPanel();
        controller.add(controller1);
        {
            // Create and add the startButton
            startButton = new JButton("Start");
            controller1.add(startButton);
            
            // Create and add the stopButton
            stopButton = new JButton("Stop");
            controller1.add(stopButton);
            
            // Create and add the resetButton
            resetButton = new JButton("Reset");
            controller1.add(resetButton);
        }
        
        // Set the frame visible
        setVisible(true);
    }
}
