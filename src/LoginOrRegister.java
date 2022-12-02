
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

   public static void studentRegistration(){
      boolean isAdmin;
      isAdmin = false;
      String studentName = "null";
      String studentID = "null";
      System.out.println("\nWelcome to Student Sign-Up!\n*************************");
      System.out.println("Please enter your name: ");
      studentName = keyboard.next();

      while (studentID.length() != 6)
      {
         System.out.println("Please enter a student ID of 6 alphanumeric characters: ");
         studentID = keyboard.next();
      }

      if (isStudentIDUnique(studentID))
      {
         Student newStudent = new Student(false,studentName,studentID);
         System.out.println("\nNew user created!");
      }
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

   public static boolean isStudentIDUnique(String studentID){
      return true;
   }



}//class