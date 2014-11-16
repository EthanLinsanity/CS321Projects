
package model;

import java.util.ArrayList;

/**
 *
 * @author Ben
 */
//String exerices;

//This class holds the exercies
public class ExerciseHolder {
       
    private ArrayList<Exercises> allExercises;
    
    public ExerciseHolder()
    {
        startComponents();
    }
    
    //this method initializes all exercies and puts them in an array list
    private void startComponents(){
        
        allExercises = new ArrayList<>(ExerciseNames.getNumEl());
        for(ExerciseNames n : ExerciseNames.values())
        {
            Exercises newExer= new Exercises(n);
            allExercises.add(newExer);
        }
    }
        //this method returns the Exercise object to the caller
    public Exercises getExercise(String exerciseName){
        Exercises output = null;
        for(Exercises e : allExercises)
        {
            if(e.getName().equals(exerciseName))
            {
                output = e;
                break;
            }
        }
        return output;
    }
    
    public ArrayList<String> getAllNames()
    {
        return ExerciseNames.getAllNames();
    }
    
    public ArrayList<Exercises> getAllExercises()
    {
        return allExercises;
    }
}
