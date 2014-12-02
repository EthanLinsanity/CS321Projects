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
     * @post an array list is created and sent
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
    /**
     * A getter that pulls from the overall name holder
     * @pre overall name holder has names in it
     * @post trainee(user) is pulled sent to caller
     * @return a trainee(user) 
     * @param inName name of the user that the caller wants
     */
    public Trainee getThisTrainee(String inName)
    {
        Trainee output = null;
        for(Trainee e : overallNameHolder)
        {
            if(e.getName().equals(inName)) {output = e;}
        }
        return output;
    }
    /**
     * Removes the trainee(user) from the overall name holder
     * if the last user is removed it add a "Guest" user
     * @pre trainee(user) must exist in name holder
     * @post trainee(user) is deleted
     * @param trainName name to be removed
     */
    public void removeThisTrainee(String trainName)
    {
        overallNameHolder.remove(this.getThisTrainee(trainName));
        if(overallNameHolder.size() < 1)
        {
            overallNameHolder.add(new Trainee("Guest"));
        }
    }
    /**
     * Adds the trainee(user) to the overall name holder
     * @pre overall name holder exists
     * @post name is added to the holder
     * @param inTraineeName name of trainee(user)added
     */
    
    public void addATrainee(String inTraineeName) {
        overallNameHolder.add(new Trainee(inTraineeName));
    }
    /**
     * Returns the overall name holder to the caller
     * @pre the overall name holder has names within it
     * @post overall name holder is sent to caller
     * @return a set of objects called Trainee(users)
     */
    public Set<Trainee> getTraineeSet()
    {
        return overallNameHolder;
    }
    
   
}
