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
    BENTOVERBARBELROW   ("Bentover Barbell Row","ExerciseImages/BentoverBarbellRow.jpg","ExerciseDescriptions/BentoverBarbellRow.txt"),
    CALFRAISE           ("Calf Raises","ExerciseImages/CalfRaise.jpg","ExerciseDescriptions/CalfRaise.txt"),
    CRUNCHES            ("Crunches","ExerciseImages/Crunches.jpg","ExerciseDescriptions/Crunches.txt"),
    FRONTDUMBBELLRAISE  ("Front Dumbbell Raises","ExerciseImages/FrontDumbbellRaise.jpg","ExerciseDescriptions/FrontDumbbellRaise.txt"),
    FULLSQUAT           ("Full Squats","ExerciseImages/FullSquat.jpg","ExerciseDescriptions/FullSquat.txt"),
    ONEARMTRICEP        ("Triceps","ExerciseImages/OneArmTricep.jpg","ExerciseDescriptions/OneArmTricep.txt"),
    SHOULDERPRESS       ("Shoulder Press","ExerciseImages/ShoulderPress.jpg","ExerciseDescriptions/ShoulderPress.txt"),
    SITUP               ("Situps","ExerciseImages/SitUp.jpg","ExerciseDescriptions/SitUp.txt"),
    WALKINGLUNGE        ("Walking Lunges","ExerciseImages/WalkingLunge.jpg","ExerciseDescriptions/WalkingLunge.txt");
    
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
