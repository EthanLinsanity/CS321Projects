/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ETracker;

import controller.OverallController;
import model.TraineeHolder;

/**
 *
 * @author Rawsome
 */
public class Main{
    
    public static void main(String[] args){
        TraineeHolder nameHolder = new TraineeHolder();
        OverallController theController = new OverallController(nameHolder);
    }   
}
