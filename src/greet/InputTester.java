package greet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

public class InputTester
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.print("How old are you?");
      int age = in.nextInt();
      age++;
      System.out.println("Next year, you'll be " + age);
   }
}
