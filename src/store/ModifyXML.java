//package store;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import model.ExerciseHolder;
//import model.Exercises;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//

//******************************************************************************
//******************************************************************************
//******************************************************************************

//// This whole class will be removed.. Sorry Jared.
//******************************************************************************
//******************************************************************************
//******************************************************************************

///**
// *
// * @author Jared
// */
//public class ModifyXML 
//{
//    ArrayList<Exercises>booyah;    
//    ExerciseHolder fight;
//  
//    public void updateModifyXML(ArrayList<Exercises> watchThis)
//    {
//       booyah=(ArrayList<Exercises>)watchThis.clone();
//    }
//    
//    public String inHouseName(int index)
//    {
//        return booyah.get(index).getExerName();
//    }
//    int index;
//    public int getIndex(String name)
//    {
//        for (int i = 0; i < booyah.size(); i++)
//        {
//            Exercises thisName = booyah.get(i);
//            if (name.equals(thisName.getExerName()))
//            {
//                index=i;
//                return i;
//            }
//        } 
//
//        return -1;
//    }
//       
//    
//    
//    public void modifyXmlFile() {
//        
//        String place = System.getProperty("user.dir") + "\\src\\store\\usrsMod.xml";
//        
//        System.out.println("read place of original = " + place);
//        File xmlFile = new File(place);
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder;
//        try {
//            dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(xmlFile);
//            doc.getDocumentElement().normalize();
//
//            updateSets(doc,index);
//            updateReps(doc,index);
//            
//            //write the updated document to file or console
//            doc.getDocumentElement().normalize();
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(doc);
//            String placeOut = System.getProperty("user.dir") + "\\src\\store\\usrsMod.xml";
//            System.out.println("read place out = " + placeOut);
//            File xmlFileMod = new File(placeOut);
//            StreamResult result = new StreamResult(xmlFileMod);
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.transform(source, result);
//            System.out.println("XML file updated successfully");
//
//        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
//            e1.printStackTrace();
//        }
//    }
//    
//    public void updateSets(Document doc, int index)
//    {
//        NodeList user = doc.getElementsByTagName("User");
//        Element ptr = null;
//        //loop for each employee
//        for (int i = 0; i < user.getLength(); i++) {
//            ptr = (Element) user.item(i);
//            Node setsElement = ptr.getElementsByTagName("Sets").item(index).getFirstChild();//item is location
//            setsElement.setNodeValue(Integer.toString(booyah.get(index).getActualSets()));     //text works
//            System.out.println("---------------updateSets: "+Integer.toString(booyah.get(index).getActualSets())+"------------");
//        }
//    }
//    
//    private void updateReps(Document doc,int index)
//    {
//        NodeList user = doc.getElementsByTagName("User");
//        Element ptr = null;
//        //loop for each employee
//        for (int i = 0; i < user.getLength(); i++) {
//            ptr = (Element) user.item(i);
//            Node repsElement = ptr.getElementsByTagName("Reps").item(index).getFirstChild();
//            repsElement.setNodeValue(Integer.toString(booyah.get(index).getActualReps()));   //this works
//        }
//    }
//}