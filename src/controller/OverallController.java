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
public class OverallController implements ActionListener, OverallControllerCallback {
    
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
    
    private void startExerciseClicked()
    {
        theSelectionView = new WorkoutSelectionView(this);
        theSelectionView.addListener( selected -> {
            startDescriptionView();
            theSelectionView.closeThisView();
                });
        theMainView.setVisibility(false);
    }
    private void progressGoalClicked()
    {
        theProgressView.setVisible(true);
    }
    
    private void startDescriptionView()
    {
        theDescriptionView = new ExerciseDescriptionView(this);
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
