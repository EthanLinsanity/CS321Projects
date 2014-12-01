/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
 */

package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Exercises is the smallest unit in our program
 * It contains all the set and reps for each particular exercise
 * @author Rawsome
 */
public class Exercises {
        private final ExerciseNames exerciseName;  
        private final StringProperty exerName;
        private IntegerProperty goalReps;
        private IntegerProperty goalSets;
        private IntegerProperty lastSets;
        private IntegerProperty lastReps;
        
        /**
         * Creates all the exercises
         * @pre none
         * @post the exercise is created 
         * @param inExerName name of each exercise
         * 
         */
        public Exercises(ExerciseNames inExerName)
        {
            exerciseName = inExerName;
            this.exerName = new SimpleStringProperty(inExerName.getName());
            initDefault();
        }
        /**
         * The initial goals and sets presented in every description view 
         * and the progress/goal view
         * @pre exercise object must be created to set initial values
         * @post sets and reps are set to default values
         */
        private void initDefault()
        {
            this.goalReps = new SimpleIntegerProperty(10);
            this.goalSets = new SimpleIntegerProperty(5);
            this.lastSets = new SimpleIntegerProperty(0);
            this.lastReps = new SimpleIntegerProperty(0);
        }
        /**
         * Retrieves the picture path
         * @pre picture path must exist/picture must exist
         * @post path gets returned
         * @return string that contains the path of the picture
         */
        public String getPicturePath() {return exerciseName.getPicPath();}
        /**
         * Retrieves the description of the exercise 
         * @pre description path must exist
         * @post description path gets returned
         * @return string that contains the path of the description
         */
        public String getDesciption() {return exerciseName.getDesPath();}
        /**
         * Retrieves the name of the exercise 
         * @pre name of exercise must exist
         * @post name gets sent to caller
         * @return string that contains the exercise name
         */
        public String getExerName() { return exerName.get(); }
        /**
         * Changes the name of the exercise
         * @pre name of exercise must exist
         * @post exercise name is changed
         * @param inName name of the exercise to be changed to
         */
        public void setExerName(String inName) { this.exerName.set(inName); }
        /**
         * Used to talk to the javaFX bar graph
         * @pre name of exercise must exist
         * @post exercise name is associated with this property 
         * @return name of the exercise
         */
        public StringProperty exerNameProperty() { return exerName; }
         /**
         * Retrieves the goal of the exercise 
         * @pre the exercise must exist
         * @post goal is sent to caller
         * @return int that contains the goal of reps
         */
        public int getGoalReps() { return goalReps.get(); }
        /**
         * Changes the goal of reps of the exercise
         * @pre exercise object must exist
         * @post the goal for exercise reps is changed
         * @param inGoalNum integer to be set to the goal variable
         */
        public void setGoalReps(int inGoalNum) { this.goalReps.set(inGoalNum); }
        /**
         * Used to talk to the javaFX bar graph
         * @pre goal reps of exercise must exist
         * @post exercise goal is associated with this property 
         * @return goal of the exercise
         */
        public IntegerProperty goalRepsProperty() { return goalReps; }
        /**
         * Retrieves the goal of sets of the exercise 
         * @pre the exercise must exist
         * @post goal is sent to caller
         * @return int that contains the goal of sets
         */
        public int getGoalSets() { return goalSets.get(); }
         /**
         * Changes the goal of sets of the exercise
         * @pre exercise object must exist
         * @post the goal for exercise sets is changed
         * @param inGoalNum integer to be set to the goal variable
         */
        public void setGoalSets(int inGoalNum) { this.goalSets.set(inGoalNum); }
        /**
         * Used to talk to the javaFX bar graph
         * @pre goal sets of exercise must exist
         * @post exercise goal is associated with this property 
         * @return goal sets of the exercise
         */
        public IntegerProperty goalSetsProperty() { return goalSets; }
         /**
         * Retrieves the last number of sets  
         * @pre the user has logged out and back into the program
         * @post sets is sent to caller
         * @return int that contains the number 
         */  
        public int getLastSets() { return lastSets.get(); }
         /**
         * Changes the last sets/updates the last sets
         * @pre exercise object must exist
         * @post the last exercise sets is changed
         * @param inGoalNum integer to be set to the goal variable
         */
        public void setLastSets(int inGoalNum) { this.lastSets.set(inGoalNum); }
        /**
         * Used to talk to the javaFX bar graph
         * @pre lasts sets of exercise must exist
         * @post exercise last number is associated with this property 
         * @return lasts sets of the exercise
         */
        public IntegerProperty lastSetsProperty() { return lastSets; }
        /**
         * Retrieves the last number of reps  
         * @pre the user has logged out and back into the program
         * @post reps is sent to caller
         * @return int that contains the number 
         */  
        public int getLastReps() { return lastReps.get(); }
        /**
         * Changes the last reps/updates the last reps
         * @pre exercise object must exist
         * @post the last exercise reps is changed
         * @param inGoalNum integer to be reps to the goal variable
         */
        public void setLastReps(int inGoalNum) { this.lastReps.set(inGoalNum); }
        /**
         * Used to talk to the javaFX bar graph
         * @pre lasts reps of exercise must exist
         * @post exercise last number is associated with this property 
         * @return lasts reps of the exercise
         */
        public IntegerProperty lastRepsProperty() { return lastReps; }
        
}
