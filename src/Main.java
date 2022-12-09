import java.util.Scanner;

/**
 * Created by V.Campbell on 27/11/2022
 **/

public class Main {
   private static Scanner keyboard = new Scanner(System.in);
   protected static Student currentStudent;
   protected static Admin currentAdmin;


   public static void main(String[] args) {
      displayMainMenu();
   }//main

   public static void displayMainMenu() {
      currentStudent=null;
      currentAdmin=null;
      int mainMenuChoice;
      String mainMenuChoiceInput;
      do {
         System.out.println("******* CCEA GCSE Digital Technology - Unit 1 Revision Quiz *******");
         System.out.println("\n*******  Main Menu  *******\n1. Student\n2. Teacher\n3. Quit\nPlease enter your selection: ");
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
            System.out.println("See you again soon!");
            break;
         default:
            System.out.println("Not a valid choice");
      }
   }
}//class