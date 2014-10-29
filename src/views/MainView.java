/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Rawsome
 */
public class MainView {
    
    private final JComboBox<String> nameComboBox;
    private final JButton startExerciseButton;
    private final JButton progressAndGoalViewButton;
    private final JButton createNameButton;
    /**
     * MainView constructor.
     *
     */
    public MainView()
    {
        JPanel traineePanel = new JPanel();
        traineePanel.setLayout(new GridLayout(1,2));
        nameComboBox = new JComboBox<>();
        nameComboBox.addItem("Name?");
        createNameButton = new JButton("Create New User");
        traineePanel.add(nameComboBox);
        traineePanel.add(createNameButton);
        
        startExerciseButton = new JButton("Start Exercise");
        progressAndGoalViewButton = new JButton("Progress and Goal");
        
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(traineePanel,BorderLayout.NORTH);
        mainFrame.add(startExerciseButton, BorderLayout.CENTER);
        mainFrame.add(progressAndGoalViewButton, BorderLayout.SOUTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        
    }
    
    /**
     * get all existing user names from the model and set it to the NameComboBox
     *
     */
    public void populateNameComboBox()
    {
        
    }
    
    /**
     * focus to a specific name
     *
     * @param name as an String.
     */  
    public void focusNameComboBox(String name)
    {
        
    }
    
    /**
     * Add a listener to the "Start Exercise" button.
     *
     * @param listener as an ActionListener.
     */    
    public void btnStartExerciseListener(ActionListener listener){
        startExerciseButton.addActionListener(listener);
    }
    
    /**
     * Add a listener to the "Progress and Goal" button.
     *
     * @param listener as an ActionListener.
     */    
    public void btnProgressAndGoalListener(ActionListener listener){
        progressAndGoalViewButton.addActionListener(listener);
        //call JavaFX_TableView 
            
    }
    
    /**
     * Add a listener to the "new user" button.
     *
     * @param listener as an ActionListener.
     */    
    public void btnNewUserListener(ActionListener listener){
        createNameButton.addActionListener(listener);
    }
    
    
}
