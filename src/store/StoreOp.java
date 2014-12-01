/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
 */

package store;

import model.TraineeHolder;

/**
 *
 * @author Rawsome
 */
public class StoreOp {
    public static boolean saveAll(TraineeHolder inTrainHolder)
    {
        return SaveToXML.saveThis(inTrainHolder);
    }
    
    public static TraineeHolder loadSaved()
    {
        return LoadFromXML.loadXMLNow();
    }
}
