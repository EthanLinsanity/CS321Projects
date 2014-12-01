/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
 */

package controller;

import java.awt.SplashScreen;
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
    /**
     * Controls all the views and presents a image to look at while loading
     * @pre none
     * @post all the view are loaded and the Main view is displayed
     */
    public OverallController()
    {
        SplashScreen mySplash = SplashScreen.getSplashScreen();
        
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
            mySplash.close();
            theMainView.setVisibility(true);
        });
    }
    /**
     * Creates and displays the workout selection view and hides the main view
     * @pre main view on display
     * @post the workout selection view is displayed
     */
    private void startExerciseSelection()
    {
        theSelectionView = new WorkoutSelectionView(this);
        theMainView.setVisibility(false);
    }
     /**
     * Displays the progress view 
     * @pre progress view already made but hidden
     * @post the progress view is displayed
     */
    
    private void showProgressGoal()
    {
        Platform.runLater(()->
        {
            theProgressView.rePopulateData();
        });
        theProgressView.setVisibility(true);
    }
     /**
     * Creates and displays the workout selection view and hides the main view
     * @pre main view on display
     * @post the workout selection view is displayed
     */
    private void startDescriptionView(String inName)
    {
        Exercises exerToDisp = curExerHolder.getExercise(inName);
        theDescriptionView = new ExerciseDescriptionView(this, exerToDisp);
    }
    
     /**
     * Displays the main view
     * This method is called by the OverallControllerCallback
     * @pre main view already created
     * @post the main view is displayed
     */
    @Override
    public void showMainView() {
        theMainView.setVisibility(true);
    }
     /**
     * Returns all the user names entered by the users
     * This method is called by the OverallControllerCallback
     * @pre the name holder must have at least one user we called guest
     * @post name holder contains all the users
     * @return user names
     */
    @Override
    public List<String> getAllTraineeNames() {
        return allNameHolder.getTraineeNames();
    }
     /**
     * Adds a user to the holder and updates the main view
     * This method is called by the OverallControllerCallback
     * @pre user types a name
     * @post the combo box in main view will use this to populate it names
     * @param response the name of the user that is typed in
     */
    @Override
    public void addATrainee(String response) {
        allNameHolder.addATrainee(response);
        theMainView.update(response);
    }
    /**
     * Displays the main view
     * This method is called by the OverallControllerCallback
     * @pre main view already created
     * @post the main view is displayed
     */
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
            nameToReturn = curExerHolder.getAllNames().get(randomInd);
        }
        return curExerHolder.getExercise(nameToReturn);
    }

    @Override
    public void closingProgram() {
        StoreOp.saveAll(allNameHolder);
    }

    @Override
    public void removeThisUser(String cboNameSelected) {
        allNameHolder.removeThisTrainee(cboNameSelected);
    }
}
