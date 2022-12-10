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
      do
      {
         System.out.println("\nAdmin Area\n*************************");
         System.out.println("1: Show Full Leaderboard\n2: Add New Question\n3: Set Quiz Parameters \n4: Log Out");
         System.out.println("Please enter a selection: ");

         adminMenuInput = keyboard.nextLine();
      } while (!Globals.validMenuChoice(adminMenuInput, 1, 4));
      menuChoice = Integer.parseInt(adminMenuInput);

      switch (menuChoice)
      {
         case 1:
            LeaderBoard.printLeaderboard("admin");
            display();
            break;
         case 2:
            AddNewQuestionMenu.display();
            Main.displayMainMenu();//return to main menu
            break;
         case 3:
            getSetQuizParameters();
            display();
            break;
         case 4:
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

   public static void getSetQuizParameters()
   {
      do
      {
         System.out.println("\nMain Quiz Parameters: ");
         System.out.println("\tAsk " + QuizMaster.getMainQuizNumMCQns() + " Mutiple Choice Question/s");
         System.out.println("\tAsk " + QuizMaster.getMainQuizNumTFQns() + " True/False Question/s");
         System.out.println("\tAsk " + QuizMaster.getMainQuizNumShortQns() + " Short Question/s");
         System.out.println("****************************************************");
         System.out.println("1. Change number of Multiple Choice Questions to ask");
         System.out.println("2. Change number of True/False Questions to ask");
         System.out.println("3. Change number of Short Questions to ask");
         System.out.println("4. Go back to Admin Menu");
         System.out.println("Please enter a selection: ");
         adminMenuInput = keyboard.nextLine();
      } while (!Globals.validMenuChoice(adminMenuInput, 1, 4));
      menuChoice = Integer.parseInt(adminMenuInput);
      switch (menuChoice)
      {
         case 1:
            do
            {
               System.out.println("\nHow many Multiple Choice questions should be asked?");
               adminMenuInput = keyboard.nextLine();
            } while (!Globals.validMenuChoice(adminMenuInput, 1, 30));
            menuChoice = Integer.parseInt(adminMenuInput);
            QuizMaster.setMainQuizNumMCQns(menuChoice);
            System.out.println("The main quiz will now ask " + QuizMaster.getMainQuizNumMCQns() + " multiple choice questions");
            getSetQuizParameters();
            break;
         case 2:
            do
            {
               System.out.println("\nHow many True/False questions should be asked?");
               adminMenuInput = keyboard.nextLine();
            } while (!Globals.validMenuChoice(adminMenuInput, 1, 30));
            menuChoice = Integer.parseInt(adminMenuInput);
            QuizMaster.setMainQuizNumTFQns(menuChoice);
            System.out.println("The main quiz will now ask " + QuizMaster.getMainQuizNumTFQns() + "  True/False questions");
            getSetQuizParameters();
            break;
         case 3:
            do
            {
               System.out.println("\nHow many Short questions should be asked?");
               adminMenuInput = keyboard.nextLine();
            } while (!Globals.validMenuChoice(adminMenuInput, 1, 30));
            menuChoice = Integer.parseInt(adminMenuInput);
            QuizMaster.setMainQuizNumShortQns(menuChoice);
            System.out.println("The main quiz will now ask " + QuizMaster.getMainQuizNumShortQns() + "  short questions");
            getSetQuizParameters();
            break;
         case 4:
            display();
            break;
      }
   }
}//class
