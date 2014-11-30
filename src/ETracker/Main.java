/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ETracker;

import controller.OverallController;
import store.CreateXML;

/**
 *
 * @author Rawsome
 */
public class Main{
    
    public static void main(String[] args){
        OverallController theController = new OverallController();
        
        CreateXML createXml = new CreateXML();
        //createXml.createXML();
//        createXml.getTrainees(nameHolder.getTraineeSet());
        createXml.receiveData(nameHolder);
        
        //modify xml
        //ModifyXML modXml = new ModifyXML();
        //modXml.receiveData(nameHolder);
        //modXml.modifyXmlFile();       
        
        
    }   
}
