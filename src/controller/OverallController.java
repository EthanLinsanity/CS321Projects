/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.List;
import javax.swing.SwingUtilities;
import model.ExerciseHolder;
import model.Exercises;
import model.Trainee;
import model.TraineeHolder;
import views.ExerciseDescriptionView;
import views.ProgressAndGoalView;
import views.MainView;
import views.WorkoutSelectionView;

/**
 *
 * @author Rawsome
 */
public class OverallController implements OverallControllerCallback {
    
    private MainView theMainView;
    private WorkoutSelectionView theSelectionView;
    private ExerciseDescriptionView theDescriptionView;
    private ExerciseHolder curExerHolder;
    private TraineeHolder allNameHolder;
    private ProgressAndGoalView theProgressView;
    
    public OverallController(TraineeHolder inNameHolder)
    {
        allNameHolder = inNameHolder;

        SwingUtilities.invokeLater(() -> {
            theMainView = new MainView(this);
            theProgressView = new ProgressAndGoalView(this);
            theMainView.btnProgressAndGoalListener( clicked -> this.showProgressGoal());
            theMainView.btnStartExerciseListener(clicked ->
            {
                    startExerciseSelection();
                    curExerHolder = allNameHolder.getThisTrainee(theMainView.cboNameSelected()).getExerciseHolder();
            });
        });
    }
    
    private void startExerciseSelection()
    {
        theSelectionView = new WorkoutSelectionView(this);
        theMainView.setVisibility(false);
    }
    private void showProgressGoal()
    {
        theProgressView.populateData();
        theProgressView.setVisibility(true);
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

    @Override
    public Trainee getCurTrainee() {
        return allNameHolder.getThisTrainee(theMainView.cboNameSelected());
    }
    
}
