/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
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
    /**
     * Creates a Trainee holder-holds all the users
     * @pre overall name holder is created 
     * @post a user called Guest is added the the name holder
     */
    public TraineeHolder()
    {
        overallNameHolder = new HashSet<>();
        overallNameHolder.add(new Trainee("Guest"));
    }
    /**
     * Retrieves all the names of the users
     * @pre the overall name holder has names within it
     * @post 
     * @return an array list of names
     */
    public ArrayList<String> getTraineeNames()
    {
        ArrayList<String> output = new ArrayList<>(overallNameHolder.size());
        overallNameHolder.stream().forEach((trainee) -> 
        {
            output.add(trainee.getName());
        });
        return output;
    }
    
    public Trainee getThisTrainee(String inName)
    {
        Trainee output = null;
        for(Trainee e : overallNameHolder)
        {
            if(e.getName().equals(inName)) {output = e;}
        }
        return output;
    }
    
    public void removeThisTrainee(String trainName)
    {
        overallNameHolder.remove(this.getThisTrainee(trainName));
        if(overallNameHolder.size() < 1)
        {
            overallNameHolder.add(new Trainee("Guest"));
        }
    }

    public void addATrainee(String inTraineeName) {
        overallNameHolder.add(new Trainee(inTraineeName));
    }
    
    public Set<Trainee> getTraineeSet()
    {
        return overallNameHolder;
    }
    
   
}
