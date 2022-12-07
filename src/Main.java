import java.util.Scanner;

/**
 * Created by V.Campbell on 27/11/2022
 **/

public class Main {
   private static Scanner keyboard = new Scanner(System.in);
   private static int addNewQuestionMenuChoice;
   private static int adminMenuChoice;
   private static boolean validLogin = false;
   private static boolean validStudentLogin = false;
   protected static Student currentStudent;
   protected static Admin currentAdmin;


   public static void main(String[] args) {
      displayMainMenu();
   }//main

   public static void displayMainMenu() {
      int mainMenuChoice;
      String mainMenuChoiceInput;
      do {
         System.out.println("\nWelcome to the Main Menu\n*************************\n1. Student\n2. Teacher\n3. Quit\nPlease enter your selection: ");
         mainMenuChoiceInput = keyboard.next();

      } while (!Globals.validMenuChoice(mainMenuChoiceInput, 1, 3));
      mainMenuChoice = Integer.parseInt(mainMenuChoiceInput);

      switch (mainMenuChoice) {
         case 1:
            StudentMenu.display();
            break;
         case 2:
            AdminMenu.display();
            break;
         case 3:
            //option 3 - Quit Application
            System.out.println("See you again soon!");
            break;
         default:
            System.out.println("Not a valid choice");
      }
   }
}//class