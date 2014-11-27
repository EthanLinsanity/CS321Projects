/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import model.TraineeHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Jared
 */
public class ModifyXML 
{
    ArrayList<Exercises>booyah;    
    ExerciseHolder fight;
//    public void receiveData(TraineeHolder tmp)
//    {
//        //TraineeHolder holder = new TraineeHolder();
//        tmp.getTraineeSet().stream().forEach(t ->
//        {
//                fight=t.getExerciseHolder().getAllExercises();
//        });
//        
//        //System.out.println("This is receiveData: ");
//    }   
    public void updateModifyXML(ArrayList<Exercises> watchThis)
    {

        //(ArrayList<Object>)myTempObject.clone();
       booyah=(ArrayList<Exercises>)watchThis.clone();
       System.out.println("===============updateModifyXML: "+watchThis.get(1).getActualSets()+"========================");
       
        
    }
    
    
    public void printModify()
    {
        System.out.println("This is printModify: ");
//        System.out.println("sets: "+fight.getSetsAtIndex(1));
//        System.out.println("reps: "+fight.getRepsAtIndex(1));
        
    }
    
    
    
    public void modifyXmlFile() {
        
        String place = System.getProperty("user.dir") + "\\src\\store\\user.xml";
        
        System.out.println("read place of original = " + place);
        File xmlFile = new File(place);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            
            //updateFight();
            //update attribute value
            updateReps(doc);

            //update sets value
            //param one doc, index of array that holds an exercise object
            updateSets(doc, 1);
            System.out.println("===============updateModifyXMLbooyah: "+booyah.get(1).getActualSets()+"========================");
            //printModify();
            //System.out.println("This is updateSets: "+Integer.toString(fight.get(2).getActualSets()));

            //delete element
            //deleteElement(doc);

            //add new element
            //addElement(doc);

            //write the updated document to file or console
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            String placeOut = System.getProperty("user.dir") + "\\src\\store\\usrsMod.xml";
            System.out.println("read place out = " + placeOut);
            File xmlFileMod = new File(placeOut);
            StreamResult result = new StreamResult(xmlFileMod);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    private static void addElement(Document doc) {
        NodeList students = doc.getElementsByTagName("Student");
        Element stu = null;

        //loop for each student
        for (int i = 0; i < students.getLength(); i++) {
            stu = (Element) students.item(i);
            Element roleElement = doc.createElement("role");
            roleElement.appendChild(doc.createTextNode("willing participant"));
            stu.appendChild(roleElement);
        }
    }

    private static void deleteElement(Document doc) {
        NodeList students = doc.getElementsByTagName("Student");
        Element stu = null;
        //loop for each employee
        for (int i = 0; i < students.getLength(); i++) {
            stu = (Element) students.item(i);
            Node phoneElement = stu.getElementsByTagName("phone").item(0);
            stu.removeChild(phoneElement);
        }

    }

    public void updateSets(Document doc, int index)
    {
        NodeList user = doc.getElementsByTagName("User");
        Element ptr = null;
        //loop for each employee
        for (int i = 0; i < user.getLength(); i++) {
            ptr = (Element) user.item(i);
            Node setsElement = ptr.getElementsByTagName("Sets").item(index).getFirstChild();//item is location
            setsElement.setNodeValue(Integer.toString(booyah.get(index).getActualSets()));     //text works
            //System.out.println("This is updateSets: "+Integer.toString(fight.get(index).getActualSets()));
        }
    }
    
    
    private static void updateReps(Document doc) {
        NodeList user = doc.getElementsByTagName("User");
        Element ptr = null;
        //loop for each employee
        for (int i = 0; i < user.getLength(); i++) {
            ptr = (Element) user.item(i);
            Node repsElement = ptr.getElementsByTagName("Reps").item(9).getFirstChild();
            repsElement.setNodeValue("Work those Reps out!");   //this works
        }
    }
    
    public void updateXml(int sets)
    {
        
        
    }
    

    private static void updateAttributeValue(Document doc) {
        NodeList students = doc.getElementsByTagName("Student");
        Element stu = null;
        //loop for each employee
        for (int i = 0; i < students.getLength(); i++) {
            stu = (Element) students.item(i);
            String fname = stu.getElementsByTagName("firstname").item(0).getFirstChild().getNodeValue();
            if (fname.equalsIgnoreCase("will")) {
                //prefix id attribute with M
                stu.setAttribute("id", "M" + stu.getAttribute("id"));
            } else {
                //prefix id attribute with F
                stu.setAttribute("id", "F" + stu.getAttribute("id"));
            }
        }
    }

}
