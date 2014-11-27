/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public static TraineeHolder loading()
    {
        return LoadFromXML.loadXMLNow();
    }
}
