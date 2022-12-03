
import java.util.Scanner;

/**
 * Created by Aaron McCloskey on 27/11/2022
 * UPDATE PROGRAM COMMENTS ABOUT PROGRAM HERE
 * Class Name: LoginOrRegister.java
 **/
public class LoginOrRegister
{
   public static Scanner keyboard = new Scanner(System.in);

   private static int mainMenuChoice = 0;

   private static final String ADMIN_PASSWORD = "RosaleenIsALegend";

   public static void menuPrompt(){
      System.out.println("\nWelcome to the Main Menu\n*************************\n1. Student\n2. Teacher\n3. Quit\nPlease enter your selection: ");
      mainMenuChoice = keyboard.nextInt();
   }

   public static boolean isPasswordCorrect(String pPassword){
      return (ADMIN_PASSWORD.equalsIgnoreCase(pPassword));
   }



   public static void existingStudentLogin(){
      String studentID;
      System.out.println("Welcome back! Please enter your student ID: ");
      studentID = keyboard.next();
   }


   public static int getMainMenuChoice()
   {
      return mainMenuChoice;
   }




}//class