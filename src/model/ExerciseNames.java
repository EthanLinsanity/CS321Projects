/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author Rawsome
 */
public enum ExerciseNames {
    BARBELLCURL         ("Barbell Curl","ExerciseImages/barbellCurl.jpg","ExerciseDescriptions/barbellCurl.txt"),
    BARBELLDEADLIFT     ("Barbell Deadlift","ExerciseImages/BarbellDeadlift.jpg","ExerciseDescriptions/BarbellDeadlift.txt"),
    BENTOVERBARBELROW   ("BentOver Barbell Row","ExerciseImages/BentoverBarbellRow.jpg","ExerciseDescriptions/BentoverBarbellRow.txt");
//    CALFRAISE           ("")
//    CRUNCHES
//    FRONTDUMBELLRAISE
//    FULLSQUAT
//    ONEARMTRICEP
//    SHOULDERPRESS
//    SITUP
//    WALKINGLUNGE
    private final String exerciseName;
    private final String picturePath;
    private final String descriptionPath;
    private static final int size = ExerciseNames.values().length;
    
    ExerciseNames(String inName, String inPicPath, String inDesPath)
    {
        this.exerciseName = inName;
        this.picturePath = inPicPath;
        this.descriptionPath = inDesPath;
    }
    public String getName(){ return exerciseName;}
    public String getPicPath() {return picturePath;}
    public String getDesPath() {return descriptionPath;}
    public static int getNumEl() {return size;}
    public static ArrayList<String> getAllNames()
    {
        ArrayList<String> names = new ArrayList<>(size);
        for(ExerciseNames n : ExerciseNames.values())
        {
            names.add(n.getName());
        }
        return names;
    }
}
