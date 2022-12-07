import java.util.Scanner;

/**
 * Created by vcamp on 07/12/2022
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
      do
      {
         System.out.println("\nAdmin Logged In");
         System.out.println("Admin Area\n*************************");
         System.out.println("1: Show Leaderboard\n2: Add New Question\n3: Log Out");
         System.out.println("Please enter a selection: ");

         adminMenuInput = keyboard.next();
      } while (!Globals.validMenuChoice(adminMenuInput, 1, 3));
      menuChoice = Integer.parseInt(adminMenuInput);

      switch (menuChoice)
      {
         case 1:
            LeaderBoard.printLeaderboard("admin");
            display();
            break;
         case 2:
            AddNewQuestionMenu.display();
            break;
         case 3:
            Main.displayMainMenu();//return to main menu
            break;
         default:
         {
            System.out.println("Invalid Menu Choice");
            display();
         }
      }
   }
}//class
