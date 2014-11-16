/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.List;
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
public class OverallController implements OverallControllerCallback {
    
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
    }
    
    private void startExerciseSelection()
    {
        theSelectionView = new WorkoutSelectionView(this);
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
    public void showMainView() {
        theMainView.setVisibility(true);
    }

    @Override
    public List<String> getAllTraineeNames() {
        return allNameHolder.getTraineeNames();
    }

    @Override
    public void addATrainee(String response) {
        allNameHolder.addATrainee(response);
        theMainView.update(response);
    }

    @Override
    public void exerSelectionComplete(String exerName) {
        startDescriptionView(exerName);
        theSelectionView.closeThisView();
    }
    
    
}
