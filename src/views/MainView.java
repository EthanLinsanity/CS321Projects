/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Rawsome
 */
public class MainView {
    
    public MainView()
    {
        JPanel traineePanel = new JPanel();
        traineePanel.setLayout(new GridLayout(1,2));
        JComboBox<String> nameComboBox = new JComboBox<String>();
        nameComboBox.addItem("Name?");
        JButton createNameButton = new JButton("Create New User");
        traineePanel.add(nameComboBox);
        traineePanel.add(createNameButton);
        
        JButton startExerciseButton = new JButton("Start Exercise");
        JButton progressAndGoalViewButton = new JButton("Progress and Goal");
        
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(traineePanel,BorderLayout.NORTH);
        mainFrame.add(startExerciseButton, BorderLayout.CENTER);
        mainFrame.add(progressAndGoalViewButton, BorderLayout.SOUTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        
    }
    
}
