/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
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
        theMainView = inputMainView;
        theProgressView = inputProgressView;
        allNameHolder = inNameHolder;
        theMainView.btnProgressAndGoalListener( clicked ->showProgressGoal());
        theMainView.btnStartExerciseListener(clicked ->startExerciseSelection());
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
    
    
}
