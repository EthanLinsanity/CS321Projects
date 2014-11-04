/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.swing.JFrame;
import views.MainView;
import views.WorkoutSelectionView;

/**
 *
 * @author Rawsome
 */
public class OverallController {
    
    MainView theMainView;
    JFrame theProgressView;
    
    public OverallController(MainView inputMainView, JFrame inputProgressView)
    {
        theMainView = inputMainView;
        theProgressView = inputProgressView;
        theMainView.btnProgressAndGoalListener( clicked ->ProgressGoalClicked());
        theMainView.btnStartExerciseListener(clicked ->StartExerciseClicked());
    }
    
    private void StartExerciseClicked()
    {
        WorkoutSelectionView theSelectionView = new WorkoutSelectionView();
    }
    private void ProgressGoalClicked()
    {
        theProgressView.setVisible(true);
    }
    //Individaul Workout View----------------------
    private void SaveSetRepClicked()
    {
        
    }
    //Progress and Goals View----------------------
    private void BackToMainClicked()
    {
        
        
    }
}
