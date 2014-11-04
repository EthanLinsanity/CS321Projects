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
        theMainView.btnProgressAndGoalListener( clicked ->progressGoalClicked());
        theMainView.btnStartExerciseListener(clicked ->startExerciseClicked());
    }
    
    public void selectionViewClosing()
    {
        backToMainClicked();
    }
    
    private void startExerciseClicked()
    {
        WorkoutSelectionView theSelectionView = new WorkoutSelectionView(this);
        theMainView.setVisibility(false);
    }
    private void progressGoalClicked()
    {
        theProgressView.setVisible(true);
    }
    //Individaul Workout View----------------------
    private void saveSetRepClicked()
    {
        
    }
    //Progress and Goals View----------------------
    private void backToMainClicked()
    {
        theMainView.setVisibility(true);
        
    }
    
    
}
