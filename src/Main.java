import java.util.Scanner;

/**
 * Created by V.Campbell on 27/11/2022
 * Startup screen & Main menu
 **/

public class Main
{
   protected static Student currentStudent;
   protected static Admin currentAdmin;

   public static void main(String[] args)
   {
      Main.displayMainMenu();
   }

   public static void displayMainMenu()
   {
      Scanner keyboard = new Scanner(System.in);
      String mainMenuChoiceInput;
      int menuChoice;

      currentStudent = null;
      currentAdmin = null;

      Student.deserialize();
      Admin.deserialize();
      Question.deserializeAllQuestionBanks();

      do
      {
         System.out.println("\n******* CCEA GCSE Digital Technology - Unit 1 Revision Quiz *******");
         System.out.println("\nWelcome to the Main Menu\n*************************\n1. Student\n2. Admin\n3. Quit\nPlease enter your selection: ");

         mainMenuChoiceInput = keyboard.next();

      } while (!Globals.validMenuChoice(mainMenuChoiceInput, 1, 3));

      menuChoice = Integer.parseInt(mainMenuChoiceInput);

      if (menuChoice == 2)//option 2  - Admin login/registration
      {
         AdminMenu.display();
         //Once Admin has logged in successfully. Display Teacher Admin Submenu
      } else if (menuChoice == 1)//option 1 - Student Registration via Student Menu
      {
         StudentMenu.display();
      } else
      {
         LoginOrRegister.quitMessage();
         System.exit(0);
      }
   }//main

}//class