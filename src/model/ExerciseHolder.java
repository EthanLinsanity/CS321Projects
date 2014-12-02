/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
 */
package model;

import java.util.ArrayList;

/**
 * This class holds the exercises
 * @author Rawsome
 */
public class ExerciseHolder {
       
    private ArrayList<Exercises> allExercises;
    private int size;
    /**
     * Public function to get things started
     * @pre method start Components has been created
     * @post ?
     */
    public ExerciseHolder()
    {
        startComponents();
    }
    /**
     * Initializes all exercises and puts them in an array list
     * @pre array list all exercises formed
     * @post all exercises array list is populated
     */
    private void startComponents(){
        
        size = ExerciseNames.getNumEl();
        allExercises = new ArrayList<>(size);
        for(ExerciseNames n : ExerciseNames.values())
        {
            Exercises newExer= new Exercises(n);
            allExercises.add(newExer);
        }
    }
    /**
     * A get function to get the exercise object 
     * @pre exercises object must exist, name of exercise must be present
     * @post exercise object is passed to the caller
     * @return exercise object that matches the name of the parameter
     * @param exerciseName name of the exercise to be sent to caller
     */ 
    public Exercises getExercise(String exerciseName){
        Exercises output = null;
        for(Exercises e : allExercises)
        {
            if(e.getExerName().equals(exerciseName))
            {
                output = e;
                break;
            }
        }
        return output;
    }
    /**
     * A get function to get all the exercise names
     * @pre exercise names must be populated
     * @post all the exercise names passed to caller
     * @return an array list of exercise names
     */
    public ArrayList<String> getAllNames()
    {
        return ExerciseNames.getAllNames();
    }
    /**
     * A get function to get all the exercises
     * @pre exercises must exists
     * @post all the exercises passed to caller
     * @return an array list of exercise objects
     */
    public ArrayList<Exercises> getAllExercises()
    {
        return allExercises;
    }
    /**
     * A get function to get the size of the array list of exercises
     * @pre array list of exercises must be populated
     * @post number of exercises get sent to caller
     * @return an integer that is the size of the array list
     */
    public int getSize()
    {
        return size;
    }
      
}
