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
    private final ExerciseHolder myExerHolder;
    private final String myName;
    public Trainee(String inName)
    {
        myExerHolder = new ExerciseHolder();
        myName = inName;
    }
    
    public String getName() {return myName;}
    public ExerciseHolder getExerciseHolder() {return myExerHolder;}
    
    @Override
    public int hashCode()
    {
        return myName.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trainee other = (Trainee) obj;
        return this.myName.equalsIgnoreCase(other.myName);
    }
}
