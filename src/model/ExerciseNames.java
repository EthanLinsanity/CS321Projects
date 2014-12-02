/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
 */
package model;

import java.util.ArrayList;

/**
 * A enumeration of the names of the exercises used by the trainee
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
    /**
     * Creates the exercise
     * @pre the paths have been stored in a file that can be reached
     * @post all the exercise are initialized 
     * @param inName name of the exercise
     * @param inPicPath path to the image
     * @param inDesPath path to the description of the exercise
     */
    ExerciseNames(String inName, String inPicPath, String inDesPath)
    {
        this.exerciseName = inName;
        this.picturePath = inPicPath;
        this.descriptionPath = inDesPath;
    }
    /**
     * A get function to get the name of the exercise
     * @pre name of the exercise must exists
     * @post exercise name is passed
     * @return name of the exercise
     */
    public String getName(){ return exerciseName;}
    /**
     * A get function to get the picture of the exercise
     * @pre picture of the exercise must exists
     * @post exercise picture is passed
     * @return picture of the exercise
     */
    public String getPicPath() {return picturePath;}
    /**
     * A get function to get the description of the exercise
     * @pre description of the exercise must exists
     * @post exercise description is passed
     * @return description of the exercise
     */
    public String getDesPath() {return descriptionPath;}
    /**
     * A get function to get the number of exercises
     * @pre exercises object must exist
     * @post size of exercises is passed to caller
     * @return number of exercises
     */
    public static int getNumEl() {return size;}
    /**
     * A get function to get all the names of the exercises
     * @pre exercises object must exist
     * @post array list of strings is formed
     * @return the array list of all the names
     */
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
