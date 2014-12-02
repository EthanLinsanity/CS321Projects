/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
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
    /**
     * A function that creates the main view
     * @pre none
     * @post the main view is populated
     * @param inputController used to display and hide the main view
     */
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
    }
    /**
     * Updates the main view by populating the combo box
     * @pre main view is created
     * @post the combo box in main view is updated
     * @param focusName name that becomes highlighted
     */
    public void update(String focusName)
    {
        populateNameComboBox();
        focusNameComboBox(focusName);
    }
    
    /**
     * Gets all existing user names from the model and set it to the NameComboBox
     * @pre the combo box exists/main view is populated
     * @post the combo box is filled with user names
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
     * Focuses to a specific name
     * @pre combo box is selected
     * @post name is highlighted
     * @param inFocusName as a string.
     */  
    public void focusNameComboBox(String inFocusName)
    {
        if(inFocusName!=null)
        {
            nameComboBox.setSelectedItem(inFocusName);
        }
    }
    /**
     * Closes the combo box once a user is selected
     * @pre nameComboBox cannot be empty
     * @post name selected is on display in main view
     * @return the selected name from the name selection combo box.
     */     
    public final String cboNameSelected()
    {
        if(nameComboBox.getSelectedIndex() < 0)
        {
            nameComboBox.setSelectedIndex(0);
        }
        return nameComboBox.getSelectedItem().toString();
        
    }
    /**
     * Add a listener to the "Start Exercise" button.
     * @pre user clicks start exercise button
     * @post the caller will be notified when clicked
     * @param listener as an ActionListener.
     */    
    public void btnStartExerciseListener(ActionListener listener){
        startExerciseButton.addActionListener(listener);
    }
    /**
     * Add a listener to the "Progress and Goal" button.
     * @pre user clicks progress and goal button
     * @post the caller will be notified when clicked
     * @param listener as an ActionListener.
     */    
    public void btnProgressAndGoalListener(ActionListener listener){
        progressAndGoalViewButton.addActionListener(listener);    
    }
    
    /**
     * Setting Visibility of the mainFrame.
     * @pre the program has started
     * @post the main frame is presented
     * @param inputVisBool as an boolean.
     */    
    public void setVisibility(boolean inputVisBool)
    {
        mainFrame.setVisible(inputVisBool);
    }
    
}
