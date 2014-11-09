/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Rawsome
 */
public class TraineeHolder {
    Set<Trainee> overallNameHolder;
    public TraineeHolder()
    {
        overallNameHolder = new HashSet<>();
        overallNameHolder.add(new Trainee("Ethan"));
    }
    
    public ArrayList<String> getTraineeNames()
    {
        ArrayList<String> output = new ArrayList<>(overallNameHolder.size());
        overallNameHolder.stream().forEach((trainee) -> 
        {
            output.add(trainee.getName());
        });
        return output;
    }
}
