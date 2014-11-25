package store;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.Exercises;
import model.Trainee;
import model.TraineeHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Jared
 */
public class CreateXML 
{
    
    public void docBuilder()
    {
        try {
            // Set up the document builder
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
             // define root elements
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("AllUsers");
            document.appendChild(rootElement);
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreateXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  
    
    public void createXML() 
    {
        // this should be improved. there is duplicated code with writeStudentsToXml
        // refactoring would help. the file is hard coded and the method should
        // be refactored to use a parameter.
        // TODO Provide a parameter for the file and refactor into common methos with writeStudentsToXml
        try 
        {
            //docBuilder();
            // Set up the document builder
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            // define root elements
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("AllUsers");
            document.appendChild(rootElement);

            // add the data
            TraineeHolder tmp = new TraineeHolder();
            receiveData(tmp);
            
            
           
            for(int i = 0; i < 1; i++)
            {
                rootElement.appendChild(getUser(document,Integer.toString(i), name, exerHolder,arrayNames,sets, reps));
            }
          
            //TraineeHolder.getTraineeNames();
             
            // creating and writing to xml file
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            String place = System.getProperty("user.dir") + "\\src\\store\\user.xml";
            System.out.println("place = " + place);
            File filePlace = new File(place);
            StreamResult streamResult = new StreamResult(filePlace);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);

            System.out.println("File saved to specified path!");

        
        }   
        catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    
    /**
     * Write a list of students to the xml file.
     *
     * @param users as an ArrayList of Student
     */
    public void writeStudentsToXml(ArrayList<Trainee> users) {
        // this should be improved. there is duplicated code with createXml.
        // refactoring would help. the file is hard coded and the method should
        // be refactored to use a parameter.
        // TODO Provide a parameter for the file and refactor into commonly with createXml
        try {
        // Set up the document builder
           DocumentBuilderFactory documentFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentFactory
                   .newDocumentBuilder();

          // define root elements
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("AllUsers");
            document.appendChild(rootElement);
            
            //docBuilder();
            // need to generate an id
            int id = 1;

            // loop through the student list 
            for (Trainee us : users) {
                // check to make sure that the blank element of the list 
                // is not processed
                if (null == us.getName()) {
                } else {
                    // Append the element making sure that the id is converted
                    // to a string
                    for(int i=0;i<us.getExerciseHolder().getSize();i++)
                    {
                    rootElement.appendChild(getUser(document, Integer.toString(id),
                            us.getName(),
                            us.getExerciseHolder(),
                            us.getExerciseHolder().getAllNames(),
                            us.getExerciseHolder().getRepsAtIndex(id),
                            us.getExerciseHolder().getRepsAtIndex(id)));
                    }
                   
                    id++;
                }
            }

            // creating and writing to xml file
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            // change this so that it will send back information to the user
            // interface about the location of the file
            String place = System.getProperty("user.dir") + "\\src\\model\\user.xml";
           
            System.out.println("place = " + place);
            // Make a file
            File filePlace = new File(place);
            // make a stream result for output
            StreamResult streamResult = new StreamResult(filePlace);
            // use indentation in the file
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // transform to xmal and write
            transformer.transform(domSource, streamResult);
            // change this so that it will send back information to the 
            // user interface about the success of the save
            System.out.println("File saved to specified path!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    /**
     * Create a student xml node element
     *
     * @param doc the xml document
     * @param id the generated id as a string
     * @param firstname the first name as a string
     * @param email the email address as a string with no validation
     * @param phone the phone numb er as a string with no validation
     * @return an xml node element
     */
    private static Node getUser(Document doc, String id, String firstname,  ExerciseHolder workout, ArrayList<String>arrayNames, int sets, int reps) {
        Element user = doc.createElement("User");
        //set id attribute
        user.setAttribute("id", id);
        //create firstname element
        user.appendChild(getUserElements(doc, user, "name", firstname));
        //create workout routine
        user.appendChild(getUserElements(doc, user, "dataModelName", workout.toString()));
        
        
        //prints out all the exercise names-----------------------------
        for(int i=0;i<arrayNames.size();i++)
        {
            user.appendChild(getUserElements(doc, user, "exercies", arrayNames.get(i)));
            user.appendChild(getUserElements(doc, user, "Sets", Integer.toString(sets)));
            user.appendChild(getUserElements(doc, user, "Reps", Integer.toString(reps)));
        }
        
        

        return user;
    }


    /**
     * Get the student elements to be appended and return an xml node
     *
     * @param doc the xml document
     * @param element the element to be used as the node
     * @param name the string for the name of the element
     * @param value the string for the value to append
     * @return a xml node
     */
    private static Node getUserElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        
        
        return node;
    }
  
    
    
 
    /**
     * Receive all the data from Trainee Holder to populate the xml database
     * 
     * @param tmp a TraineeHolder will all the users and all their workouts
     * @return holder that can be accessed
     */
    String name;
    ExerciseHolder exerHolder;
    ArrayList<String>arrayNames;
    int arraySets[];
    int sets;
    int reps;
    
    
    public TraineeHolder receiveData(TraineeHolder tmp)
    {
        TraineeHolder holder = new TraineeHolder();
        tmp.getTraineeSet().stream().forEach(t ->
            {
                name=t.getName();
                exerHolder=t.getExerciseHolder();
                arrayNames=t.getExerciseHolder().getAllNames();
                
                //---------------------------------------------------------------------------------
                
                t.getExerciseHolder().getAllExercises();
                //---------------------------------------------------------------------------------
                //sets=t.getExerciseHolder().getSetsAtIndex(1);
                reps=t.getExerciseHolder().getRepsAtIndex(1);
                
                System.out.println(t.getExerciseHolder().getNameAtIndex(5));
                
                System.out.println(t.getExerciseHolder().getSetsAtIndex(1));
                System.out.println(t.getExerciseHolder().getRepsAtIndex(1));
                
                //t.getExerciseHolder().getExercise(t.getExerciseHolder().getNameAtIndex(1));
                for(int i=0; i<t.getExerciseHolder().getSize();i++)
                {
                    sets=t.getExerciseHolder().getSetsAtIndex(i);
                }
                
                
                
            });
        
        
        return holder;    
    }
    
    
    //testing purposes
    public void print(ArrayList<String>exerNames)
    {
        System.out.println("Name of Exercises: " + exerNames );  //testing purposes
        
        
    }
    

}

