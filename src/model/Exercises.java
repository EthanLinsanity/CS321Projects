/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
	private int goalSets;
	private int actualSets;
	private int goalReps;
	private int actualReps;
        private int lastSets;
        private int lastReps;
        
        private final StringProperty pExerName;
        private IntegerProperty pGoalReps;
        private IntegerProperty pGoalSets;
        
        public String getName() {return exerciseName.getName();}
        public String getPicturePath() {return exerciseName.getPicPath();}
        public String getDesciption() {return exerciseName.getDesPath();}
        
        public Exercises(ExerciseNames inExerName)
        {
            exerciseName = inExerName;
            this.pExerName = new SimpleStringProperty(inExerName.getName());
            initDefault();
        }
        private void initDefault()
        {
            goalSets = 0;
            actualSets = 0;
            goalReps = 0;
            actualReps = 0;
            lastSets = 0;
            lastReps = 0;
            
            this.pGoalReps = new SimpleIntegerProperty(0);
            this.pGoalSets = new SimpleIntegerProperty(0);
        }
        
        public void setGoalSet(int inNum) { goalSets = inNum;}
        public int getGoalSet() { return goalSets;}
        public void setActualSet(int inNum) {actualSets = inNum;}
        public int getActualSet() { return actualSets;}
        public void setGoalRep(int inNum) {goalReps = inNum;}
        public int getGoalRep() {return goalReps;}
        public void setActualRep(int inNum) {actualReps = inNum;}
        public int getActualRep() {return actualReps;}
        public void setLastSet(int inNum) {lastSets = inNum;}
        public int getLastSet() {return lastSets;}
        public void setLastRep(int inNum) {lastReps = inNum;}
        public int getLastRep() {return lastReps;}
        
        public String getPExerName() { return pExerName.get(); }
        public void setPExerName(String inName) { this.pExerName.set(inName); }
        public StringProperty pExerNameProperty() { return pExerName; }
        public int getPGoalReps() { return pGoalReps.get(); }
        public void setPGoalReps(int inGoalNum) { this.pGoalReps.set(inGoalNum); }
        public IntegerProperty pGoalRepsProperty() { return pGoalReps; }
        public int getPGoalSets() { return pGoalSets.get(); }
        public void setPGoalSets(int inGoalNum) { this.pGoalSets.set(inGoalNum); }
        public IntegerProperty pGoalSetsProperty() { return pGoalSets; }
        
        
}
