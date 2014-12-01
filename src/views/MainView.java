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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private final JButton removeNameButton;
    private final JLabel lblName;
    private OverallControllerCallback myController;
//    CreateXML createXml = new CreateXML();
       
    

    public MainView(OverallControllerCallback inputController)
    {
        myController = inputController;
        JPanel traineePanel = new JPanel();
        traineePanel.setLayout(new GridLayout(0,4));
        nameComboBox = new JComboBox<>();
        this.populateNameComboBox();
        createNameButton = new JButton("Create New User");
        createNameButton.addActionListener(clicked ->
        {
            String response = JOptionPane.showInputDialog(null,
                                "What is your name?", 
                                "Enter your name",
                                JOptionPane.QUESTION_MESSAGE);
            if(response != null)
            {
                myController.addATrainee(response);
//                createXml.getNewUser(response);  
            } 
        });
        
        removeNameButton = new JButton("Remove This User");
        removeNameButton.addActionListener(clicked ->{
            if(nameComboBox.getSelectedIndex() >= 0)
            {
                myController.removeThisUser(this.cboNameSelected());
                this.populateNameComboBox();
            }
        });
        
        nameComboBox.addActionListener(selected->
        {
            myController.mainUserChanged();
        });
        
        lblName = new JLabel("Name: ");
        lblName.setHorizontalTextPosition(JLabel.RIGHT);
        traineePanel.add(lblName);
        traineePanel.add(nameComboBox);
        traineePanel.add(createNameButton);
        traineePanel.add(removeNameButton);
        
        startExerciseButton = new JButton("Start Exercise");
        progressAndGoalViewButton = new JButton("Progress and Goal");
        
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent windowEvent)
            {
                myController.closingProgram();
            }
        });
        
        mainFrame.add(traineePanel,BorderLayout.NORTH);
        mainFrame.add(startExerciseButton, BorderLayout.CENTER);
        mainFrame.add(progressAndGoalViewButton, BorderLayout.SOUTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    
    public void update(String focusName)
    {
        populateNameComboBox();
        focusNameComboBox(focusName);
    }
    
    /**
     * get all existing user names from the model and set it to the NameComboBox
     *
     */
    private void populateNameComboBox()
    {
        nameComboBox.removeAllItems();
        List<String> allNames = myController.getAllTraineeNames();
        allNames.stream().forEach((String name) -> {
            nameComboBox.addItem(name);
        });
    }
    
    /**
     * focus to a specific name
     *
     * @param inFocusName as an String.
     */  
    public void focusNameComboBox(String inFocusName)
    {
        if(inFocusName!=null)
        {
            nameComboBox.setSelectedItem(inFocusName);
        }
    }
   /**
     * @precondition nameComboBox cannot be empty
     * @return the selected name from the name selection combo box.
     */     
    public final String cboNameSelected()
    {
//        CreateXML why = new CreateXML();
//        why.getNewUser(nameComboBox.getSelectedItem().toString());
//        System.out.println("============comboSelected: "+nameComboBox.getSelectedItem().toString()+"====================");
        if(nameComboBox.getSelectedIndex() < 0)
        {
            nameComboBox.setSelectedIndex(0);
        }
        return nameComboBox.getSelectedItem().toString();
        
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
     * Setting Visibility of the mainFrame.
     *
     * @param inputVisBool as an boolean.
     */    
    public void setVisibility(boolean inputVisBool)
    {
        mainFrame.setVisible(inputVisBool);
    }
    
}
