/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package store;

import model.TraineeHolder;




/**
 *
 * @author Jared
 */
public class XMLMain 
{
      public static void main(String args[]){
   
   
   
   // create xml file
   CreateXML createXml = new CreateXML();
   createXml.createXML();
   
   TraineeHolder tmp=new TraineeHolder();
   createXml.receiveData(tmp);
   
   
   
   // parsing xml
   //ReadXml readXml = new ReadXml();
   //readXml.readXmlFile();
   
   //modify xml
   //ModifyXml modXml = new ModifyXml();
   //modXml.modifyXmlFile();
   
   // parsing xml
//   ReadXml readXml = new ReadXml();
//   List<Student> stuList = readXml.readXmlFileToList();
//   for (Student stu : stuList){
//       System.out.println("From passed list");
//       System.out.println("First name: " + stu.getFirstame());
//       System.out.println("Last name: " + stu.getLastname());
//       System.out.println("email: " + stu.getEmail());
//       System.out.println("Phone: " + stu.getPhone());
       
   //testing===================================================
//   TraineeHolder aTrainer = new TraineeHolder();    
//   System.out.println(aTrainer.getTraineeNames());

   
   }
  }

    

