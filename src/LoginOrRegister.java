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

   private static boolean isPasswordCorrect(String pPassword){
      return (ADMIN_PASSWORD.equalsIgnoreCase(pPassword));
   }

   protected static int getMainMenuChoice()
   {
      return mainMenuChoice;
   }


   protected static void quitMessage()
   {
      System.out.println("See you again soon!");
   }

   protected static void printSuccessfulLogin_AdminAreaMenu()
   {
      System.out.println("\nTeacher Logged In");
      System.out.println("Admin Area\n*************************");
      System.out.println("1: Show Leaderboard\n2: Add New Question\n3: Log Out");
      System.out.println("Please enter a selection: ");

   }

   protected static void printAddNewQuestionMenu()
   {
      System.out.println("\nWelcome to Add New Question menu\n*************************");
      System.out.println("1. Multiple Choice Question\n2. True or False Question\n3. Short Answer Question\n4. Go Back");
      System.out.println("Please enter a selection: ");
   }

}//class