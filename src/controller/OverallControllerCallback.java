/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.List;
import model.Exercises;
import model.Trainee;

/**
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
