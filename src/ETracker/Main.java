/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ETracker;

import controller.OverallController;
import model.TraineeHolder;
import store.CreateXML;
import store.ModifyXML;

/**
 *
 * @author Rawsome
 */
public class Main{
    
    public static void main(String[] args){
        TraineeHolder nameHolder = new TraineeHolder();
        OverallController theController = new OverallController(nameHolder);
        
        CreateXML createXml = new CreateXML();
        createXml.createXML();
        createXml.receiveData(nameHolder);
        
        //modify xml
        ModifyXML modXml = new ModifyXML();
        modXml.modifyXmlFile();
               
        
        
    }   
}
