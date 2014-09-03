/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package greet;

import java.util.Scanner;

/**
 *
 * @author dmr for UAH
 */
public class InputGreeter {
    private String name;
    private Greeter localGreeter;
    
    public void getNameofUser(){
        Scanner in = new Scanner(System.in);
      System.out.print("What is your name?");
      String userName = in.next();
      name = userName;
    }
    
    public void greetUser(){
    localGreeter = new Greeter(name);
    System.out.println(localGreeter.sayHello());
    }
}
