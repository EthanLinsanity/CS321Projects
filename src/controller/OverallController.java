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
     * Closes the exercise selection view and 
     * presents the exercise description view
     * This method is called by the OverallControllerCallback
     * @pre main view already created
     * @post the main view is displayed
     * @param exerName name of the exercise to be presented
     */
    @Override
    public void exerSelectionComplete(String exerName) {
        startDescriptionView(exerName);
        theSelectionView.closeThisView();
    }
    /**
     * Return method to get current user
     * This method is called by the OverallControllerCallback
     * @pre name in combo box is selected
     * @post current user is returned to caller
     */
    @Override
    public Trainee getCurTrainee() {
        return allNameHolder.getThisTrainee(theMainView.cboNameSelected());
    }
     /**
     * Updates the progress view if the main user is changed
     * This method is called by the OverallControllerCallback
     * @pre the progress view must be visible
     * @post the progress view re animates 
     */
    
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
    /**
     * Used to direct the recommend next exercise button
     * Randomizes the next exercise to be displayed
     * This method is called by the OverallControllerCallback
     * @pre exercise description view must be displayed
     * @post the next exercise is presented
     * @param inName string used to generated another random exercise
     * @return name of exercise so that the caller can populate next exercise
     * 
     */
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
     /**
     * Closes the program by saving the users to the xml file
     * This method is called by the OverallControllerCallback
     * @pre program must be running
     * @post users are saved to xml
     */
    @Override
    public void closingProgram() {
        StoreOp.saveAll(allNameHolder);
    }
     /**
     * Deletes the user from the combo box and from the xml file
     * This method is called by the OverallControllerCallback
     * @pre name must be found in the combo box found in main view
     * @post user name is removed from the combo box
     * @param cboNameSelected name selected from combo box
     */
    @Override
    public void removeThisUser(String cboNameSelected) {
        allNameHolder.removeThisTrainee(cboNameSelected);
    }
}
