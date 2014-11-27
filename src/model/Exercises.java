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
        public String getPicturePath() {return exerciseName.getPicPath();}
        public String getDesciption() {return exerciseName.getDesPath();}
        
        private final StringProperty exerName;
        private IntegerProperty goalReps;
        private IntegerProperty goalSets;
        private IntegerProperty actualSets;
        private IntegerProperty actualReps;
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
            this.actualSets = new SimpleIntegerProperty(0);
            this.actualReps = new SimpleIntegerProperty(0);
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
                
        public int getActualSets() 
        { 
           //this is working!
            int sets=actualSets.get();
            System.out.println("This is GETACTUAL in exercises: "+actualSets.get());
            
            //System.out.println("exersceClass: "+sets);
            return sets; 
            
        }
        public void setActualSets(int inGoalNum) 
        { 
            //System.out.println("This is SET : "+inGoalNum);
            this.actualSets.set(inGoalNum); 
        }
        public IntegerProperty actualSetsProperty() { return actualSets; }
                
        public int getActualReps() { return actualReps.get(); }
        public void setActualReps(int inGoalNum) { this.actualReps.set(inGoalNum); }
        public IntegerProperty actualRepsProperty() { return actualReps; }
                
        public int getLastSets() { return lastSets.get(); }
        public void setLastSets(int inGoalNum) { this.lastSets.set(inGoalNum); }
        public IntegerProperty lastSetsProperty() { return lastSets; }
                
        public int getLastReps() { return lastReps.get(); }
        public void setLastReps(int inGoalNum) { this.lastReps.set(inGoalNum); }
        public IntegerProperty lastRepsProperty() { return lastReps; }
        
}
