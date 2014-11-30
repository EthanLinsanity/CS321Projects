
package model;

import java.util.ArrayList;

/**
 *
 * @author Rawsome
 */

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
//******************************************************************************  
//****************************************************************************** 
//    not necessary, it can be extracted from above method
//******************************************************************************
//    public String getNameAtIndex(int Index)
//    {
//        return allExercises.get(Index).getExerName();
//    }
//******************************************************************************  
//******************************************************************************
    public int getSize()
    {
        return size;
    }
    
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************
//    Not necessary, all info can be extracted from existing methods.
//    
//    //code added by jared-------------------------------------------
//    ModifyXML foo = new ModifyXML();
//    CreateXML pooh = new CreateXML();
//    public void updateExerciseHolder(String name, int sets, int reps)
//    {
//        getExercise(name).setActualSets(sets);
//        getExercise(name).setActualReps(reps);
//        //this sysout works
//        //System.out.println("This is EHolderSets: "+ getExercise(name).getActualSets());
//        //System.out.println("------------testing allExercises: "+allExercises.get(1).getActualSets()+"-------------");
//        
//        foo.updateModifyXML(allExercises);
//        foo.getIndex(name);
//        foo.modifyXmlFile(); 
//        pooh.updateCreateXML(allExercises);
//    }
//    
////    public int getIndexAtName(String name)
////    {
////        //int index=allExercises;
////        System.out.println("===========THis is index: "+ index+"==================");
////        return index;
////    }
//       
//    /* The method pulls from the class Exercises the last sets
//    * @parm index <-- this index is the location of the Exercise in
//    * an arraylist
//    * @returns an integer to the caller
//    */
//    
//    public int getSetsAtIndex(int index)
//    {
//       //System.out.println("exercise holder: "+getExercise(getNameAtIndex(index)).getActualSets());
//   
//        return getExercise(getNameAtIndex(index)).getActualSets();
//    }
//        
//    
//    public int getRepsAtIndex(int index)
//    {
//        return getExercise(getNameAtIndex(index)).getActualReps();
//    }
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************
    
    
    
    //---------------------------------------------------------------
    
}
