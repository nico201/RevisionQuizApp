import java.util.Scanner;

/**
 * Created by V.Campbell on 27/11/2022
 **/

public class Main
{
   public static Scanner keyboard = new Scanner(System.in);
   public static int addNewQuestionMenuChoice;
   public static int adminMenuChoice;
   public static User currentUser;


   public static void main(String[] args)
   {
      int menuChoice;
      do
      {
         // "Log-out" - Clear details of previous user
         currentUser = null;
         LoginOrRegister.menuPrompt();
         menuChoice = LoginOrRegister.getMainMenuChoice();
         if (menuChoice == 3)
         {
            //option 3 - Quit Application
            System.out.println("See you again soon!");
         } else if (menuChoice == 2)
         {
            //option 2  - Admin login/registration
            //Admin Login/Registration via Admin/Teacher Menu
            boolean validLogin = false;
            do
            {
               validLogin = AdminMenu.display();
            } while (!validLogin);

            /*
            Once Admin has logged in successfully
            Display Teacher Admin Submenu
            */
            do
            {
               System.out.println("\nAdmin Area\n*************************");
               System.out.println("1: Show Leaderboard\n2: Add New Question\n3: Log Out");
               System.out.println("Please enter a selection: ");
               adminMenuChoice = keyboard.nextInt();
               if (adminMenuChoice == 1)
               {
                  LeaderBoard.printLeaderboard();
               } else if (adminMenuChoice == 2)
               {
                  do
                  {
                     System.out.println("\nWelcome to Add New Question menu\n*************************");
                     System.out.println("1. Multiple Choice Question\n2. True or False Question\n3. Short Answer Question\n4. Go Back");
                     System.out.println("Please enter a selection: ");
                     addNewQuestionMenuChoice = keyboard.nextInt();
                     switch (addNewQuestionMenuChoice)
                     {
                        case 1:
                           MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion(null, -1, null, null, null, null, null, -1);
                           mcq1.updateQuestion();
                           //Add question to list
                           //Serialize list
                           break;
                        case 2:
                           TrueFalseQuestion tf1 = new TrueFalseQuestion(null, -1, null, '0');
                           tf1.updateQuestion();
                           //Add question to list
                           //Serialize list
                           break;
                        case 3:
                           ShortQuestion sq1 = new ShortQuestion(null, 0, null, null);
                           sq1.updateQuestion();
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
            boolean validStudentLogin = false;
            do
            {
               validStudentLogin = StudentMenu.display();
            } while (!validStudentLogin);
            //Once student has logged in/registered successfully
            System.out.println("\nPress return to begin the quiz!\n*************************");
            keyboard.nextLine();
            // Re-populates Question ArrayLists on re-run
            Globals.populateAllQuestions();
            QuizMaster quizMaster = new QuizMaster();
            QuizMaster.initializeQuizMaster();
            // Use quizMaster.runQuiz() to ask ALL questions in question bank or specify num in call as below
            quizMaster.runQuiz(1, 1, 1);
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
            LeaderBoard.printLeaderboard();
         }//else if

      } while (menuChoice != 3);
   }//main
}//class