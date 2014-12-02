/*
 * CS 321 Team 7 Project
 * Team Members: Ethan Lin, Jared Paul, Ian Matteson, Ben Mwangi
 * Date: 1 Dec 2014
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
  * Class that saves to the xml file located in the resource package
  * @pre xml exists
  * @post data from the trainee holder is pulled to populate the xml file
  * @return true if data was saved and false with exception thrown if data
  * wasn't saved
  * @author Rawsome
  */
class SaveToXML {

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
            
            String place = System.getProperty("user.dir") + "/resources/userData.xml";
            File filePlace = new File(place);
            StreamResult streamResult = new StreamResult(filePlace);
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, streamResult);
        }
        catch (ParserConfigurationException | TransformerException pce) 
        {
            return false;
        }
        return true;
    }

    
}
