/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package store;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.ExerciseHolder;
import model.Exercises;
import model.TraineeHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Rawsome
 */
class LoadFromXML {

    static TraineeHolder loadXMLNow() {
        TraineeHolder tHolderToRtn = new TraineeHolder(); 
        try {
            String place = System.getProperty("user.dir") + "/resources/userData.xml";
            File xmlFile = new File(place);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList allTraineeNodes = doc.getElementsByTagName("Trainee");
            for (int trainCtr = 0; trainCtr < allTraineeNodes.getLength(); trainCtr++)
            {
                Node curTrainNode = allTraineeNodes.item(trainCtr);
                Element curTrainElement = (Element) curTrainNode;
                String traineeName = curTrainElement.getAttribute("name");
                tHolderToRtn.addATrainee(traineeName);
                
                NodeList exerNameList = curTrainElement.getElementsByTagName("Exercises");
                ExerciseHolder eHolder = tHolderToRtn.getThisTrainee(traineeName).getExerciseHolder();
                for (int exerCtr = 0; exerCtr < exerNameList.getLength(); exerCtr++)
                {
                    Node curExerNode = exerNameList.item(exerCtr);
                    Element curExerElement = (Element) curExerNode;
                    String curExerName = curExerElement.getAttribute("name");
                    Exercises curExercise = eHolder.getExercise(curExerName);
                    curExercise.setGoalReps(Integer.parseInt(curExerElement.getElementsByTagName("GoalRep").item(0).getTextContent()));
                    curExercise.setGoalSets(Integer.parseInt(curExerElement.getElementsByTagName("GoalSet").item(0).getTextContent()));
                    curExercise.setLastReps(Integer.parseInt(curExerElement.getElementsByTagName("LastRep").item(0).getTextContent()));
                    curExercise.setLastSets(Integer.parseInt(curExerElement.getElementsByTagName("LastSet").item(0).getTextContent()));
                }
            }
        } 
        catch (ParserConfigurationException | SAXException | IOException ex) 
        {
//            Logger.getLogger(LoadFromXML.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
            return tHolderToRtn;
        }
        return tHolderToRtn;
    }
    
}
