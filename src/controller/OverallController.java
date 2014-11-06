/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import views.ExerciseDescriptionView;
import views.MainView;
import views.WorkoutSelectionView;

/**
 *
 * @author Rawsome
 */
public class OverallController implements ActionListener {
    
    MainView theMainView;
    JFrame theProgressView;
    WorkoutSelectionView theSelectionView;
    ExerciseDescriptionView theDescriptionView;
    
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
        theSelectionView = new WorkoutSelectionView(this);
        theSelectionView.addListener( selected -> startDescriptionView());
        theMainView.setVisibility(false);
    }
    private void progressGoalClicked()
    {
        theProgressView.setVisible(true);
    }
    
    private void startDescriptionView()
    {
        theDescriptionView = new ExerciseDescriptionView();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
}
