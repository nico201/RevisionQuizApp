
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
      System.out.println("\nPlease enter your selection:\n1. Student\n2. Teacher\n3. Quit");
      mainMenuChoice = keyboard.nextInt();
   }

   public static boolean isPasswordCorrect(String pPassword){
      return (ADMIN_PASSWORD.equalsIgnoreCase(pPassword));
   }

   public static void studentRegistration(){
      boolean isAdmin;
      isAdmin = false;
      String studentName;
      int studentID;
      System.out.println("Welcome to Student Sign-Up!");
      System.out.println("Please enter your name: ");
      studentName = keyboard.next();
      System.out.println("Please enter your Student ID");
      studentID = keyboard.nextInt();
      if (isStudentIDUnique(studentID))
      {
         Student newStudent = new Student(false,studentName,studentID);
         System.out.println("\nNew user created!");
      }
   }

   public static void existingStudentLogin(){
      int studentID;
      System.out.println("Welcome back! Please enter your student ID: ");
      studentID = keyboard.nextInt();
   }


   public static int getMainMenuChoice()
   {
      return mainMenuChoice;
   }

   public static boolean isStudentIDUnique(int studentID){
      return true;
   }



}//class