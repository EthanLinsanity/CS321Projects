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
 *
 * @author Rawsome
 */
public class Exercises {
        private final ExerciseNames exerciseName;        
        public String getPicturePath() {return exerciseName.getPicPath();}
        public String getDesciption() {return exerciseName.getDesPath();}
        
        private final StringProperty exerName;
        private IntegerProperty goalReps;
        private IntegerProperty goalSets;
        private IntegerProperty lastSets;
        private IntegerProperty lastReps;
        
        public Exercises(ExerciseNames inExerName)
        {
            exerciseName = inExerName;
            this.exerName = new SimpleStringProperty(inExerName.getName());
            initDefault();
        }
        private void initDefault()
        {
            this.goalReps = new SimpleIntegerProperty(10);
            this.goalSets = new SimpleIntegerProperty(5);
            this.lastSets = new SimpleIntegerProperty(0);
            this.lastReps = new SimpleIntegerProperty(0);
        }
        
        public String getExerName() { return exerName.get(); }
        public void setExerName(String inName) { this.exerName.set(inName); }
        public StringProperty exerNameProperty() { return exerName; }
        
        public int getGoalReps() { return goalReps.get(); }
        public void setGoalReps(int inGoalNum) { this.goalReps.set(inGoalNum); }
        public IntegerProperty goalRepsProperty() { return goalReps; }
        
        public int getGoalSets() { return goalSets.get(); }
        public void setGoalSets(int inGoalNum) { this.goalSets.set(inGoalNum); }
        public IntegerProperty goalSetsProperty() { return goalSets; }
  
        
        public int getLastSets() { return lastSets.get(); }
        public void setLastSets(int inGoalNum) { this.lastSets.set(inGoalNum); }
        public IntegerProperty lastSetsProperty() { return lastSets; }
                
        public int getLastReps() { return lastReps.get(); }
        public void setLastReps(int inGoalNum) { this.lastReps.set(inGoalNum); }
        public IntegerProperty lastRepsProperty() { return lastReps; }
        
}
