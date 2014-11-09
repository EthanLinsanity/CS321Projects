/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

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
        
        public String getName() {return exerciseName.getName();}
        public String getPicturePath() {return exerciseName.getPicPath();}
        public String getDesciption() {return exerciseName.getDesPath();}
        
        public Exercises(ExerciseNames inExerName)
        {
            exerciseName = inExerName;
            initDefault();
        }
        private void initDefault()
        {
            goalSets = 0;
            actualSets = 0;
            goalReps = 0;
            actualReps = 0;
        }
        
        public void setGoalSet(int inNum) { goalSets = inNum;}
        public int getGoalSet() { return goalSets;}
        public void setActualSet(int inNum) {actualSets = inNum;}
        public int getActualSet() { return actualSets;}
        public void setGoalRep(int inNum) {goalReps = inNum;}
        public int getGoalRep() {return goalReps;}
        public void setActualRep(int inNum) {actualReps = inNum;}
        public int getActualRep() {return actualReps;}
        
	


}
