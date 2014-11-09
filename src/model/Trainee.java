/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Rawsome
 */
public class Trainee {
    private ExerciseHolder myExerHolder;
    private String myName;
    public Trainee(String inName)
    {
        myExerHolder = new ExerciseHolder();
        myName = inName;
    }
    
    public String getName() {return myName;}
    public ExerciseHolder getExerciseHolder() {return myExerHolder;}
    
}
