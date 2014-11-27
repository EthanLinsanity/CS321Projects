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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

            //update attribute value
            updateAttributeValue(doc);

            //update sets value
            updateSets(doc);

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

    private static void updateSets(Document doc) {
        NodeList user = doc.getElementsByTagName("User");
        Element stu = null;
        //loop for each employee
        for (int i = 0; i < user.getLength(); i++) {
            stu = (Element) user.item(i);
            Node emailElement = stu.getElementsByTagName("Sets").item(0).getFirstChild();
            emailElement.setNodeValue("This is how we do it!");
        }
//        stu = (Element) user.item(1);
//        Node workoutFirst = stu.getElementsByTagName("sets").item(0).getFirstChild();
//        workoutFirst.setNodeValue(workoutFirst.getNodeValue());
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
