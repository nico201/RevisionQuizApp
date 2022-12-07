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
            //Once Admin has logged in successfully. Display Teacher Admin Submenu
            do {
               LoginOrRegister.printSuccessfulLogin_AdminAreaMenu();
               //User given 3 menu choice 1. Show leaderboard, 2. Add new question, 3. Log out
               adminMenuChoice = keyboard.nextInt();
               if (adminMenuChoice == 1) {
                  LeaderBoard.printLeaderboard("admin");
               } else if (adminMenuChoice == 2) {
                  do {
                     LoginOrRegister.printAddNewQuestionMenu();
                     // Add new question menu displayed: 1. Multiple Choice Question, 2. True or False Question, 3. Short Answer Question, 4. Go Back
                     addNewQuestionMenuChoice = keyboard.nextInt();
                     switch (addNewQuestionMenuChoice) {
                        case 1:
                           MultipleChoiceQuestion.declareInitialiseAndUpdate_NewQuestionObject();
                           //Add question to list
                           //Serialize list
                           break;
                        case 2:
                           TrueFalseQuestion.declareInitialiseAndUpdate_NewQuestionObject();
                           //Add question to list
                           //Serialize list
                           break;
                        case 3:
                           ShortQuestion.declareInitialiseAndUpdate_NewQuestionObject();
                           //Add question to list
                           //Serialize list
                           break;
                        case 4:
                           break;
                        default:
                           System.out.println("Not a valid menu choice.");
                     }
                  } while (addNewQuestionMenuChoice != 4);
               }
            } while (adminMenuChoice != 3);
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