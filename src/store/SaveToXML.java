/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package store;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.ExerciseHolder;
import model.TraineeHolder;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Rawsome
 */
public class SaveToXML {

    static boolean saveThis(TraineeHolder inTrainHolder) {
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
            //root
            Element rootEle = doc.createElement("AllTrainee");
            doc.appendChild(rootEle);
            
            inTrainHolder.getTraineeSet().stream().forEach(trainee -> {
                
                Element traineeNode = doc.createElement("Trainee");
                rootEle.appendChild(traineeNode);
                
                Attr traineeAttribute = doc.createAttribute("name");
                traineeAttribute.setValue(trainee.getName());
                traineeNode.setAttributeNode(traineeAttribute);
                
                ExerciseHolder exerHolder = trainee.getExerciseHolder();
                exerHolder.getAllExercises().stream().forEach(exercise -> {
                    Element exerNameNode = doc.createElement("Exercises");
                    traineeNode.appendChild(exerNameNode);
                    
                    Attr exerAttribute = doc.createAttribute("name");
                    exerAttribute.setValue(exercise.getExerName());
                    exerNameNode.setAttributeNode(exerAttribute);
                    
                    Element lastRepNode = doc.createElement("LastRep");
                    lastRepNode.appendChild(doc.createTextNode(Integer.toString(exercise.getLastReps())));
                    Element lastSetNode = doc.createElement("LastSet");
                    lastSetNode.appendChild(doc.createTextNode(Integer.toString(exercise.getLastSets())));
                    Element goalRepNode = doc.createElement("GoalRep");
                    goalRepNode.appendChild(doc.createTextNode(Integer.toString(exercise.getGoalReps())));
                    Element goalSetNode = doc.createElement("GoalSet");
                    goalSetNode.appendChild(doc.createTextNode(Integer.toString(exercise.getGoalSets())));
                    
                    exerNameNode.appendChild(lastRepNode);
                    exerNameNode.appendChild(lastSetNode);
                    exerNameNode.appendChild(goalRepNode);
                    exerNameNode.appendChild(goalSetNode); 
                }); 
            }); //end traineeSet stream foreach
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            
            String place = System.getProperty("user.dir") + "storage.xml";
            File filePlace = new File(place);
            StreamResult streamResult = new StreamResult(filePlace);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, streamResult);
            
            
        }
        catch (ParserConfigurationException pce) 
        {
            pce.printStackTrace();
            return false;
        }
        catch (TransformerException tfe) 
        {
            tfe.printStackTrace();
            return false;
        }
        return true;
    }

    
}
