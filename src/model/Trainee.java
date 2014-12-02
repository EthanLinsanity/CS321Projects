/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
 */
package model;

/**
 *
 * @author Rawsome
 */
public class Trainee {
    private final ExerciseHolder myExerHolder;
    private final String myName;
    
    /**
     * Creates a trainee(user)
     * @pre user must enter in a name
     * @post trainee is formed
     * @param inName name of the trainee(user)
     */
    public Trainee(String inName)
    {
        myExerHolder = new ExerciseHolder();
        myName = inName;
    }
    /**
     * Returns the name of the trainee(user)
     * @pre name of the trainee(user) must exist
     * @post name is returned to caller
     * @return name of the trainee
     */
    public String getName() {return myName;}
    /**
     * Returns the exercise holder that contains all the exercises
     * assigned to the user
     * @pre the exercise holder is created
     * @post exercise holder is sent to caller
     * @return the exercise holder
     */
    public ExerciseHolder getExerciseHolder() {return myExerHolder;}
    
    /**
     * Creates a hash code
     * @pre variable myName must be assigned
     * @post name is returned to caller
     * @return name of the trainee
     */
    @Override
    public int hashCode()
    {
        return myName.toLowerCase().hashCode();
    }
    /**
     * To see if an object is equal to another
     * @pre object must exist
     * @post the object tested is either true or false
     * @return true or false
     */
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
