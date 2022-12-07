import java.util.Scanner;

/**
 * Created by V.Campbell on 27/11/2022
 **/

public class Main
{
   private static Scanner keyboard = new Scanner(System.in);
   private static int addNewQuestionMenuChoice;
   private static int adminMenuChoice;
   private static boolean validLogin = false;
   private static boolean validStudentLogin = false;
   protected static User currentUser;


   public static void main(String[] args)
   {
      int menuChoice;
      do
      {
         LoginOrRegister.menuPrompt();
         menuChoice = LoginOrRegister.getMainMenuChoice();
         if (menuChoice == 3)
         {
            //option 3 - Quit Application
            LoginOrRegister.quitMessage();
         } else if (menuChoice == 2)
         {
            //option 2  - Admin login/registration
            validLogin = AdminMenu.display();
            //Once Admin has logged in successfully. Display Teacher Admin Submenu
            do
            {
               LoginOrRegister.printSuccessfulLogin_AdminAreaMenu();
               //User given 3 menu choice 1. Show leaderboard, 2. Add new question, 3. Log out
               adminMenuChoice = keyboard.nextInt();
               if (adminMenuChoice == 1)
               {
                  LeaderBoard.printLeaderboard("admin");
               } else if (adminMenuChoice == 2)
               {
                  do
                  {
                     LoginOrRegister.printAddNewQuestionMenu();
                     // Add new question menu displayed: 1. Multiple Choice Question, 2. True or False Question, 3. Short Answer Question, 4. Go Back
                     addNewQuestionMenuChoice = keyboard.nextInt();
                     switch (addNewQuestionMenuChoice)
                     {
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
         } else if (menuChoice == 1)
         {
            //Student Registration via Student Menu
            validStudentLogin = StudentMenu.display();
            //Once student has logged in/registered successfully
            System.out.println("\nPress return to begin the quiz!\n*************************");
            keyboard.nextLine();
            Globals.populateAllQuestions();
            // Re-populates Question ArrayLists on re-run
            QuizMaster.initializeQuizMaster();
            // Use quizMaster.runQuiz() to ask ALL questions in question bank or specify num in call as below
            QuizMaster.runQuiz("mainQuiz");
            QuizMaster.printQuizResult();
            // Update the student user's highest score
            for (Student studentUser : Student.studentList)
            {
               if (studentUser.getUsername().equals(currentUser.username))
               {
                  studentUser.setHighestScore(QuizMaster.getQuizScore());
                  LeaderBoard.updateLeaderboard(studentUser.getUsername(), QuizMaster.getQuizScore());
               }
            }
            LeaderBoard.printLeaderboard("student");
         }//else if
      } while (menuChoice != 3);
   }//main
}//class