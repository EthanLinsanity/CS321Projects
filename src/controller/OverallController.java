/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.ExerciseHolder;
import model.Exercises;
import model.TraineeHolder;
import views.ExerciseDescriptionView;
import views.MainView;
import views.WorkoutSelectionView;

/**
 *
 * @author Rawsome
 */
public class OverallController implements ActionListener, OverallControllerCallback {
    
    MainView theMainView;
    JFrame theProgressView;
    WorkoutSelectionView theSelectionView;
    ExerciseDescriptionView theDescriptionView;
    ExerciseHolder curExerHolder;
    TraineeHolder allNameHolder;
    
    public OverallController(MainView inputMainView, JFrame inputProgressView, TraineeHolder inNameHolder)
    {
        theProgressView = inputProgressView;
        allNameHolder = inNameHolder;
        theMainView = inputMainView;
        theMainView.btnProgressAndGoalListener( clicked ->showProgressGoal());
        theMainView.btnStartExerciseListener(clicked ->
        {
                startExerciseSelection();
                curExerHolder = allNameHolder.getThisTrainee(theMainView.cboNameSelected()).getExerciseHolder();
        });
        theMainView.btnNewUserListener(clicked -> 
        {
            String response = JOptionPane.showInputDialog(null,
            "What is your name?", 
            "Enter your name",
            JOptionPane.QUESTION_MESSAGE);
            if(response != null)
            {
                allNameHolder.addATrainee(response);
                theMainView.update(response);
            } 
        });
    }
    
    private void startExerciseSelection()
    {
        theSelectionView = new WorkoutSelectionView(this);
        theSelectionView.addListener( selected -> {
            String exerName = theSelectionView.whichComboSelected();
            startDescriptionView(exerName);
            theSelectionView.closeThisView();
                });
        theMainView.setVisibility(false);
    }
    private void showProgressGoal()
    {
        theProgressView.setVisible(true);
    }
    
    private void startDescriptionView(String inName)
    {
        Exercises exerToDisp = curExerHolder.getExercise(inName);
        theDescriptionView = new ExerciseDescriptionView(this, exerToDisp);
    }
    
    //Individaul Workout View----------------------
    private void saveSetRepClicked()
    {
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void showMainView() {
        theMainView.setVisibility(true);
    }

    @Override
    public List<String> getAllTraineeNames() {
        return allNameHolder.getTraineeNames();
    }
    
    
}
