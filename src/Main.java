import java.util.Scanner;

/**
 * Created by V.Campbell on 27/11/2022
 **/

public class Main
{
   public static Scanner keyboard = new Scanner(System.in);
   public static int addNewQuestionMenuChoice;

   public static int adminMenuChoice;


   public static void main(String[] args)
   {
      do
      {
         LoginOrRegister.menuPrompt();

         if (LoginOrRegister.getMainMenuChoice() == 3)
            System.out.println("See you again soon!");

         else if (LoginOrRegister.getMainMenuChoice() == 2)
         {
            System.out.println("Please enter the admin password: ");
            String passwordAttempt = keyboard.next();
            if (LoginOrRegister.isPasswordCorrect(passwordAttempt))
            {
               System.out.println("\nPassword is Correct!");
               do
               {
                  System.out.println("\nWelcome to the Admin Area\n*************************");
                  System.out.println("1: Show Leaderboard\n2: Add New Question\n3: Log Out");
                  System.out.println("Please enter a selection: ");
                  adminMenuChoice = keyboard.nextInt();
                  if (adminMenuChoice == 1)
                     LeaderBoard.printLeaderboard();
                  else if (adminMenuChoice == 2) {
                     do {
                        System.out.println("\nWelcome to Add New Question menu\n*************************");
                        System.out.println("1. Multiple Choice Question\n2. True or False Question\n3. Short Answer Question\n4. Go Back");
                        System.out.println("Please enter a selection: ");
                        addNewQuestionMenuChoice = keyboard.nextInt();
                        if (addNewQuestionMenuChoice == 1) {
                           MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion(null, -1, null, null, null, null, null, -1);
                           mcq1.updateMcq1();
                           System.out.println("New Question has been saved. Thank you!");
                        }//if
                        if (addNewQuestionMenuChoice == 2) {
                           TrueFalseQuestion tf1 = new TrueFalseQuestion(null, -1, null, '0');
                           tf1.updateTf1();
                           System.out.println("New Question has been saved. Thank you!");
                        }//if
                        if (addNewQuestionMenuChoice == 3) {
                           ShortQuestion sq1 = new ShortQuestion(null, 0, null, null);
                           sq1.updateSq1();
                           System.out.println("New Question has been saved. Thank you!");
                        }//if
                     }//do
                     while (addNewQuestionMenuChoice != 4);
                  }
               } while (adminMenuChoice != 3);
            } else
               System.out.println("Sorry that's incorrect");
         } else if (LoginOrRegister.getMainMenuChoice() == 1)
         {
            System.out.println("\nWelcome Student\n*************************");
            System.out.println("1. New User\n2. Existing User\nPlease enter your selection: ");
            int chooseNewOrExisting = keyboard.nextInt();
            if (chooseNewOrExisting == 1)
            {
               LoginOrRegister.studentRegistration();
            } else
            {
               LoginOrRegister.existingStudentLogin();
            }
            System.out.println("\nPress return to begin the quiz!\n*************************");
            keyboard.nextLine();
            keyboard.nextLine();
            {
               // Re-populates Question ArrayLists on re-run
               Globals.populateAllQuestions();
               QuizMaster quizMaster = new QuizMaster();
               // Use quizMaster.runQuiz() to ask ALL questions in question bank or specify num in call as below
               quizMaster.runQuiz(1, 1, 1);
               QuizMaster.printQuizResult();
               LeaderBoard.printLeaderboard();
            }
         }//else if
      } while (LoginOrRegister.getMainMenuChoice() != 3);
   }//main
}//class