/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
 */

package controller;

import java.util.List;
import model.Exercises;
import model.Trainee;

/**
 * This interface is main controller for the entire program
 * All function calls are sent to the overall controller class
 * 
 * @author Rawsome
 */
public interface OverallControllerCallback {
    public void showMainView();
    public List<String> getAllTraineeNames();
    public void addATrainee(String response);
    public void exerSelectionComplete(String toString);
    public Trainee getCurTrainee();
    public void mainUserChanged();
    public Exercises recommendNext(String inName);
    public void closingProgram();
    public void removeThisUser(String cboNameSelected);
}
