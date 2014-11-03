/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ETracker;

import controller.OverallController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.JavaFXTableView;
import views.MainView;

/**
 *
 * @author Rawsome
 */
public class Main extends Application{
    private static JavaFXTableView progressView = new JavaFXTableView();
    
    public static void main(String[] args){
        MainView theView = new MainView();
        OverallController theController = new OverallController(theView,progressView);
        launch(args);
    }
    

    @Override
    public void start(Stage primaryStage){
        
        progressView.initialize(primaryStage);
    }
}
