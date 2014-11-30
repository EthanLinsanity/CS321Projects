package store;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Exercises;
import model.Trainee;
import model.TraineeHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This file is used to created an xml file. Used dmr's code and modified it
 * 
 * Date: 29 Nov 2014
 * @author Jared and dmr
 */
public class CreateXML 
{
    ArrayList<Exercises> fight;
    ArrayList<String>arrayNames;
    String name;
    Set<Trainee> traineeSet;
    /**
     * An update function called from the updateExerciseHolder function
     * 
     * @param watchThis the user received from main view selection combo box
     * @pre the array list of exercises called watchThis must exists
     * @post the xml file get updated
    */
    public void updateCreateXML(ArrayList<Exercises> watchThis)
    {
        fight=(ArrayList<Exercises>)watchThis.clone();
    }
    /**
     * Sets the name of the user to the variable name
     * 
     * @param nameIN the user received from main view selection combo box
     * @pre a user must be created
     * @post the name of the user is stuck to local variable name
    */ 
    public void getNewUser(String nameIN)
    {
        name=nameIN;
    }

    /**
     * Creates the xml document that stores all the information in between
     * program shut down and start up.
     * 
     * @pre none
     * @post the xml document is created
    */
    
    public void createXML() 
    {
        try 
        {
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
          
            //# of users in the xml
            addUser(rootElement,document);
            
            // creating and writing to xml file
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            String place = System.getProperty("user.dir") + "\\src\\store\\user2.xml";
            //System.out.println("place = " + place);
            File filePlace = new File(place);
            StreamResult streamResult = new StreamResult(filePlace);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);
            //System.out.println("File saved to specified path!");
        }   
        catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
    /**
     * Adds a user to the xml document and the option to add more users
     * 
     * @param thisElement is the root element that is added to the xml file
     * @param doc we need a document to be added to 
     * @pre doc and element must be created
     * @post a user gets added to the xml file
    */
    private void addUser(Element thisElemet,Document doc)
    {
        for(int i=0;i<10;i++)
        {
           thisElemet.appendChild(getUser(doc,Integer.toString(i),name, arrayNames,fight));
        }
    }
    /**
     * Create a user xml node element
     *
     * @param doc the xml document
     * @param id the generated id as a string
     * @param firstname the first name as a string
     * @param arrayNames is the array of workout names
     * @param fight is an array list of objects called Exercises
     * @return an xml node element
     */
    private static Node getUser(Document doc, String id, String firstname, 
            ArrayList<String>arrayNames, ArrayList<Exercises> fight)
    {
        Element user = doc.createElement("User");
        //set id attribute
        user.setAttribute("id", id);
        //create firstname element
        user.appendChild(getUserElements(doc, user, "name", firstname));
                
        //prints out all the exercise names-----------------------------
        for(int i=0;i<10;i++)
        {
            user.appendChild(getUserElements(doc, user, "exercies", arrayNames.get(i)));
            user.appendChild(getUserElements(doc, user, "Sets","0"));
            user.appendChild(getUserElements(doc, user, "Reps","0"));
        }
        return user;
    }


    /**
     * Get the user elements to be appended and return an xml node
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
     * @pre a TraineeHolder must exists
     * @post the name of user, names of exercises, and the sets/gets for each exercise
     * @param tmp a TraineeHolder will all the users and all their workouts
     * @return holder that can be accessed
     */
   
    String nameD;
    public void receiveData(TraineeHolder tmp)
    {
        tmp.getTraineeSet().stream().forEach(t ->
            {
                nameD=t.getName();
                arrayNames=t.getExerciseHolder().getAllNames();
                fight=t.getExerciseHolder().getAllExercises();
            });
    }
}

