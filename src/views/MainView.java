/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import controller.OverallControllerCallback;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Rawsome
 */
public class MainView {
    private final JFrame mainFrame;
    private final JComboBox<String> nameComboBox;
    private final JButton startExerciseButton;
    private final JButton progressAndGoalViewButton;
    private final JButton createNameButton;
    private final JLabel lblName;
    private OverallControllerCallback myController;

    public MainView()
    {

        JPanel traineePanel = new JPanel();
        traineePanel.setLayout(new GridLayout(1,3));
        nameComboBox = new JComboBox<>();
        createNameButton = new JButton("Create New User");
        lblName = new JLabel("Name: ");
        traineePanel.add(lblName);
        traineePanel.add(nameComboBox);
        traineePanel.add(createNameButton);
        
        startExerciseButton = new JButton("Start Exercise");
        progressAndGoalViewButton = new JButton("Progress and Goal");
        
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(traineePanel,BorderLayout.NORTH);
        mainFrame.add(startExerciseButton, BorderLayout.CENTER);
        mainFrame.add(progressAndGoalViewButton, BorderLayout.SOUTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        
    }
    
    public void setController(OverallControllerCallback inController)
    {
        myController = inController;
    }
    
    public void update()
    {
        populateNameComboBox();
    }
    
    /**
     * get all existing user names from the model and set it to the NameComboBox
     *
     */
    private void populateNameComboBox()
    {
        List<String> allNames = myController.getAllTraineeNames();
        allNames.stream().forEach((String name) -> {
            nameComboBox.addItem(name);
        });
        
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
     *
     * @return the selected name from the name selection combo box.
     */     
    public String cboNameSelected()
    {
        return nameComboBox.getSelectedItem().toString();
    }
    
   /**
     * Add a listener to the "Name" Selection ComboBox
     *
     * @param listener as an ActionListener.
     */     
    public void cboNameSelectionListener(ActionListener listener)
    {
        nameComboBox.addActionListener(listener);
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
            
    }
    
    /**
     * Add a listener to the "new user" button.
     *
     * @param listener as an ActionListener.
     */    
    public void btnNewUserListener(ActionListener listener){
        createNameButton.addActionListener(listener);
    }
    
    /**
     * Setting Visibility of the mainFrame.
     *
     * @param inputVisBool as an boolean.
     */    
    public void setVisibility(boolean inputVisBool)
    {
        mainFrame.setVisible(inputVisBool);
    }
    
}
