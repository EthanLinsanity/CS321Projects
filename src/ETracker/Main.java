/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ETracker;

import controller.OverallController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import views.ExerciseDescriptionView;
import views.JavaFXTableView;
import views.MainView;

/**
 *
 * @author Rawsome
 */
public class Main{
    
    
    private static JFrame progressFrame;
    
    private static void initFXGUI()
    {
        progressFrame = new JFrame("Progress and Goal");
        final JFXPanel fxPanel = new JFXPanel();
        progressFrame.add(fxPanel);
        progressFrame.setSize(760,450);
        progressFrame.setVisible(false);
        progressFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        Platform.runLater(() -> {
            JavaFXTableView progressView = new JavaFXTableView();
            progressView.initialize(fxPanel);
        });
    }
    
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            initFXGUI();
        });
        MainView theView = new MainView();
        OverallController theController = new OverallController(theView,progressFrame);
    }
    
    
}
