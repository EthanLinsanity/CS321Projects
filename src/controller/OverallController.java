/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javafx.application.Platform;
import views.JavaFXTableView;
import views.MainView;

/**
 *
 * @author Rawsome
 */
public class OverallController {
    
    MainView theMainView;
    JavaFXTableView theProgressView;
    
    public OverallController(MainView inputMainView, JavaFXTableView inputProgressView)
    {
        theMainView = inputMainView;
        theProgressView = inputProgressView;
        theMainView.btnProgressAndGoalListener( clicked -> Platform.runLater(()->ProgressGoalClicked()));

       
    }
    
    private void StartExerciseClicked()
    {
        
    }
    private void ProgressGoalClicked()
    {
        theProgressView.showProgressView();
    }
    //Individaul Workout View----------------------
    private void SaveSetRepClicked()
    {
        
    }
    //Progress and Goals View----------------------
    private void BackToMainClicked()
    {
        
        
    }
}
