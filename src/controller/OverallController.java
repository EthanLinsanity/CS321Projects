/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.List;
import java.util.Random;
import javafx.application.Platform;
import javax.swing.SwingUtilities;
import model.ExerciseHolder;
import model.Exercises;
import model.Trainee;
import model.TraineeHolder;
import store.StoreOp;
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
    
    public OverallController()
    {
        allNameHolder = StoreOp.loadSaved();

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
        Platform.runLater(()->
        {
            theProgressView.rePopulateData();
        });
        theProgressView.setVisibility(true);
    }
    
    private void startDescriptionView(String inName)
    {
        Exercises exerToDisp = curExerHolder.getExercise(inName);
        theDescriptionView = new ExerciseDescriptionView(this, exerToDisp);
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

    @Override
    public void mainUserChanged() {
        if(theProgressView.isVisible())
        {
            Platform.runLater(()->
            {
                theProgressView.rePopulateData();
            });
        }
    }

    @Override
    public Exercises recommendNext(String inName) {
        List<String> namePool = curExerHolder.getAllNames();
        String nameToReturn = inName;
        Random randomGenerator = new Random();
        int containerSize = curExerHolder.getSize();
        while(nameToReturn.equalsIgnoreCase(inName))
        {
            int randomInd = randomGenerator.nextInt(containerSize);
            nameToReturn = curExerHolder.getNameAtIndex(randomInd);
        }
        return curExerHolder.getExercise(nameToReturn);
    }

    @Override
    public void closingProgram() {
        StoreOp.saveAll(allNameHolder);
    }
}
