import java.util.Scanner;

/**
 * Created by V.Campbell on 07/12/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
public class AdminSubMenu
{
   public static Scanner keyboard = new Scanner(System.in);
   private static String adminMenuInput;
   private static int menuChoice;
   private static boolean exit = false;

   public static void display()
   {
      System.out.println("\nAdmin Logged In");
      do
      {
         System.out.println("Admin Area\n*************************");
         System.out.println("1: Show Full Leaderboard\n2: Add New Question\n3: Log Out");
         System.out.println("Please enter a selection: ");

         adminMenuInput = keyboard.next();
      } while (!Globals.validMenuChoice(adminMenuInput, 1, 5));
      menuChoice = Integer.parseInt(adminMenuInput);

      switch (menuChoice)
      {
         case 1:
            LeaderBoard.printLeaderboard("admin");
            display();
            break;
         case 2:
            do
            {
               AddNewQuestionMenu.display();
               exit = moreQuestions();
            } while (!exit);
            Main.displayMainMenu();//return to main menu
            break;
         case 3:
            Main.displayMainMenu();//return to main menu
            //Clear current user/admin in Main
            break;
         default:
         {
            System.out.println("Invalid Menu Choice");
            display();
         }
      }
   }

   private static boolean moreQuestions()
   {
      String choice;
      int questionChoice;

      do
      {
         System.out.println("1. Add another question \n2. Return to Main Menu");
         choice = keyboard.next();
      } while (!Globals.validMenuChoice(choice, 1, 2));
      questionChoice = Integer.parseInt(choice);
      return questionChoice != 1;
   }
}//class
