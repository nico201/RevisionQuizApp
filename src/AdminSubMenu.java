import java.util.Scanner;

/**
 * Created by V.Campbell on 07/12/2022
 * Admin Submenu - Gives Admins/SuperAdmins different access rights
 * access/permissions are differentiated based on various permissions set in Admin class at instantiation
 **/
public class AdminSubMenu
{
   private static Scanner keyboard = new Scanner(System.in);
   private static String adminMenuInput;
   private static int maxMenuOptions=9;
   private static int menuChoice;
   //private static boolean exit = false;

   public static void display()
   {
      do
      {
         System.out.println("\n********** Admin Area **********");
         System.out.println("1: Show Full Leaderboard\n2: Reset All Student Scores\n3: Set Quiz Parameters\n4: Add New Topic\n5: Add New Question\n6: Remove topic\n7: Reset all question banks\n8: Backup all question banks\n9: Log Out & Return to Main Menu");
         if (Main.currentAdmin.isSuperAdmin())
         {
            maxMenuOptions=10;
            System.out.println("**** SUPER ADMIN **** \n10: View all Admin Permissions");
         }
         System.out.println("Please enter a selection: ");

         adminMenuInput = keyboard.nextLine();
      } while (!Globals.validMenuChoice(adminMenuInput,1, maxMenuOptions));
      menuChoice = Integer.parseInt(adminMenuInput);

      switch (menuChoice)
      {
         case 1:
            LeaderBoard.printLeaderboard("admin");
            display();
            break;
         case 2:
            if (Main.currentAdmin.getCanResetScores()){
               Student.resetAllHighScores();
            } else{
               System.out.println("You do not have permission to reset scores. Please make another choice.");
            }
            display();
            break;
         case 3:
            getSetQuizParameters();
            display();
            break;
         case 4:
            addNewTopic();
            display();
            break;
         case 5:
            AddNewQuestionMenu.display();
            Main.displayMainMenu();//return to main menu
            break;
         case 6:
            if (Main.currentAdmin.getCanDeleteTopics()){
               int topicNum = Question.chosenTopic("Which topic would you like to remove all questions for?");
               Question.removeTopicQuestions(topicNum);
            } else{
               System.out.println("You do not have permission to delete topics. Please make another choice.");
            }
            display();
            break;
         case 7:
            if (Main.currentAdmin.getCanResetQuestionBanks()){
               Question.resetAllQuestionBanks();
            } else{
               System.out.println("You do not have permission to reset Question Banks. Please make another choice.");
            }
            display();
            break;
         case 8:
            Question.backupAllQuestions();
            display();
            break;
         case 9:
            Main.currentAdmin=null;
            Main.displayMainMenu();//return to main menu
            break;
         case 10:
            if (Main.currentAdmin.isSuperAdmin())
            {
               Admin.viewAllAdminPermissions();
            }
            else{
               System.out.println("You do not have permission to view admin permissions. Please make another choice.");
            }
            display();
            break;
         default:
         {
            System.out.println("Invalid Menu Choice");
            display();
         }
      }
   }

   // this is a 'dummy' option for user peace of mind - makes no actual changes
   private static void addNewTopic()
   {
      System.out.println("Please enter the new topic: ");
      keyboard.nextLine();
      System.out.println("\nYou can now use the 'Add New Question' option to add questions for this topic.\n");
   }

   public static void getSetQuizParameters()
   {
      do
      {
         System.out.println("\nMain Quiz Parameters: ");
         System.out.println("\tAsk " + QuizMaster.getMainQuizNumMCQns() + " Multiple Choice Question/s");
         System.out.println("\tAsk " + QuizMaster.getMainQuizNumTFQns() + " True/False Question/s");
         System.out.println("\tAsk " + QuizMaster.getMainQuizNumShortQns() + " Short Question/s");
         System.out.println("****************************************************");
         System.out.println("1. Change number of Multiple Choice Questions to ask");
         System.out.println("2. Change number of True/False Questions to ask");
         System.out.println("3. Change number of Short Questions to ask");
         System.out.println("4. Go back to Admin Menu");
         System.out.println("Please enter a selection: ");
         adminMenuInput = keyboard.nextLine();
      } while (!Globals.validMenuChoice(adminMenuInput,1,4));
      menuChoice = Integer.parseInt(adminMenuInput);
      switch (menuChoice)
      {
         case 1:
            do
            {
               System.out.println("\nHow many Multiple Choice questions should be asked?");
               adminMenuInput = keyboard.nextLine();
            } while (!Globals.validMenuChoice(adminMenuInput,1, 30));
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
            } while (!Globals.validMenuChoice(adminMenuInput,1,30));
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
