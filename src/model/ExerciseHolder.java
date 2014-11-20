
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
    private int size;
    public ExerciseHolder()
    {
        startComponents();
    }
    
    //this method initializes all exercies and puts them in an array list
    private void startComponents(){
        
        size = ExerciseNames.getNumEl();
        allExercises = new ArrayList<>(size);
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
            if(e.getExerName().equals(exerciseName))
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
    
    public String getNameAtIndex(int Index)
    {
        return allExercises.get(Index).getExerName();
    }
    
    public int getSize()
    {
        return size;
    }
}
