/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.List;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import model.ExerciseHolder;
import model.Exercises;
import model.TraineeHolder;
import views.ExerciseDescriptionView;
import views.JavaFXTableView;
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
    
    public OverallController(TraineeHolder inNameHolder)
    {
        allNameHolder = inNameHolder;

        SwingUtilities.invokeLater(() -> {
            theMainView = new MainView(this);
            theMainView.btnProgressAndGoalListener( clicked ->showProgressGoal());
            theMainView.btnStartExerciseListener(clicked ->
            {
                    startExerciseSelection();
                    curExerHolder = allNameHolder.getThisTrainee(theMainView.cboNameSelected()).getExerciseHolder();
            });
            theProgressView = new JFrame("Progress and Goal View");
            final JFXPanel fxPanel = new JFXPanel();
            theProgressView.add(fxPanel);
            theProgressView.setSize(760,450);
            theProgressView.setVisible(true);
            theProgressView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            Platform.runLater(() -> {
                JavaFXTableView progressView = new JavaFXTableView();
                progressView.initialize(fxPanel);
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
