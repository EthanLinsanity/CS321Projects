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
    /**
     * A function that saves to the xml file
     * @pre xml file already exists
     * @post xml file is updated
     * @return true if the trainee holder is saved and false if it isn't
     * @param inTrainHolder the trainee holder to be saved
     */
    public static boolean saveAll(TraineeHolder inTrainHolder)
    {
        return SaveToXML.saveThis(inTrainHolder);
    }
     /**
     * A function that loads from the xml file
     * @pre xml file already exists
     * @post data from xml file is loaded to a trainee holder
     * @return the trainee holder from xml
     */
    public static TraineeHolder loadSaved()
    {
        return LoadFromXML.loadXMLNow();
    }
}
