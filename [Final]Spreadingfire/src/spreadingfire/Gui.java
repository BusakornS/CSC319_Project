/**
 *
 * @author JuniorOSK
 */
package spreadingfire;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

//declare button
public class Gui {
    private static JButton reset = new JButton("Reset");
    private static JButton exit = new JButton("Exit");
    private static JButton manual = new JButton("Manual Spread");
    private static JButton auto = new JButton("Auto Spread");
    private static JRadioButton numberOn = new JRadioButton("Number On");
    private static JRadioButton numberOff = new JRadioButton("Number Off");
    private static JButton stop = new JButton("Stop");
    private static JButton set = new JButton("Seting");
    private static Forest forest;
    private static JLabel text = new JLabel("Count : ");
    private static int setsize = 20;
    private static int probC = 50;
    private static int probB = 0;
    private static int probT = 100;
    private static int step = 0;
    static Thread autoThread;
    
    
    public static void main(String[] args) {
        // Get inputing size from user
         
        final JFrame frame = new JFrame("OOP : Fire Spreading Group2");
        frame.setSize(960,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        JPanel menu = new JPanel();
        menu.setBackground(Color.PINK);
        JPanel btm = new JPanel();
        btm.setBackground(Color.decode("#00EBFF"));
        
        ButtonGroup group = new ButtonGroup();
        
        group.add(numberOn);
        group.add(numberOff);
        menu.add(numberOn);
        menu.add(numberOff);
        menu.add(manual);
        menu.add(auto);   
        menu.add(stop);
        menu.add(reset);
        menu.add(set);
        menu.add(exit);
        btm.add(text);
        
        numberOff.setSelected(true);    
        
        forest = new Forest(setsize, setsize, probC , probB , probT);
        frame.add(menu, BorderLayout.NORTH);
        frame.add(forest, BorderLayout.CENTER);     
        frame.add(btm, BorderLayout.SOUTH);     
            
        frame.setVisible(true);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forest.resetForest();
                step=0;
                text.setText("Count: " + step);
                forest.validate();
                forest.repaint();
                frame.validate();
                frame.repaint();
            }
        });
        
        auto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoThread = new Thread() {
                    public void run() {
                        loop:
                        while (!forest.alreadyBurn()) {
                            forest.spreading();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            //System.out.println("Step = " + step);
                            step++;
                            text.setText("Count " + step);
                            forest.printForest();
                            
                            forest.checkFire();
                            forest.validate();
                            forest.repaint();
                            frame.validate();
                            frame.repaint();
                        }
                    }
                };
                autoThread.start();
            }
        }); 
        
        manual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forest.printForest();
                    forest.spreading();
                    if (!forest.alreadyBurn()) {
                        step++;
                        text.setText("Count " + step);
                    }
                    //System.out.println("Step = " + step);
                    forest.printForest();
                    //step++;
                    forest.checkFire();
                    frame.validate();
                    frame.repaint();
                }
        }); 
        
        numberOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forest.numberOff();
            }
        });
        
        numberOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forest.numberOn();
            }
        });
        
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    autoThread.stop();
                return;

            }
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });  
        
        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
               setsize = Integer.parseInt(JOptionPane.showInputDialog(null, "Input forest size(side^2)"));
      
               probC = Integer.parseInt(JOptionPane.showInputDialog(null, "Input probCatch(%)"));
               probB = Integer.parseInt(JOptionPane.showInputDialog(null, "Input probBurn(%)"));
               probT = Integer.parseInt(JOptionPane.showInputDialog(null, "Input probTree(%)"));
               
               
               
               frame.remove(forest);
               forest = new Forest(setsize, setsize, probC, probB, probT);
               frame.add(forest);
               frame.validate();
               frame.repaint();
             
            }
        });
    }
}

