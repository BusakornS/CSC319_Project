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
    Forest myForest;
    View myView;
    Thread startThread;
    JButton startButton, stopButton, resetButton, moveButton;
    
    /**
     * Create the GUI of project
     */
    public GUI() {
        // Create the main frame
        super("CSC319 Spread Of Fire (Group 2)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 450);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        
        //Create the model -- The main process of project
        myForest = new Forest();
        
        //Create the view -- The output of main process of project
        int boxWidth = (int) (400 / myForest.width);
        int boxHeight = (int) (400 / myForest.height);
        myView = new View(boxWidth, boxHeight);
        
        // Add the myView panel to the left
        add(myView);
        
        // Let myView be the observer of myForest
        myForest.addObserver(myView);
        
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
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // If no thread or thread is dead, create the new thread and start
                    if (startThread == null || startThread.isAlive()) {
                        startThread = new Thread() {
                            public void run() {
                                while (!myForest.alreadyBurn()) {
                                    myForest.spreading();
                                    myForest.resetForest();
                                }
                            }
                        };
                        startThread.start();
                    }
                }
            });
            controller1.add(startButton);
            
            // Create and add the moveButton
            moveButton = new JButton("Move");
            moveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // If no thread or thread is dead, create the new thread and start
                    if (startThread == null || startThread.isAlive()) {
                        startThread = new Thread() {
                            public void run() {
                                myForest.spreading();
                                myForest.resetForest();
                            }
                        };
                        startThread.start();
                    }
                }
            });
            controller1.add(moveButton);
            
            // Create and add the stopButton
            stopButton = new JButton("Stop");
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // If there is an alive thread, stop it
                    if (startThread == null && startThread.isAlive()) {
                        startThread.stop();
                    }
                }
            });
            controller1.add(stopButton);
            
            // Create and add the resetButton
            resetButton = new JButton("Reset");
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // If there is an alive thread, stop it
                    if (startThread != null && startThread.isAlive()) {
                        startThread.stop();
                    }
                    // Reset the main process
                    myForest.resetForest();
                    myForest.checkFire();
                }
            });
            controller1.add(resetButton);
        }
        
        // Set the frame visible
        setVisible(true);
    }
}
