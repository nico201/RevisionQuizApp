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
                           System.out.println("\nMCQ Question Creation");
                           System.out.println("Please enter the question text: ");
                           mcq1.setQuestionText(keyboard.next());//
                           mcq1.setQuestionText(keyboard.nextLine());//"Workaround for quirk in Scanner class"- Aaron
                           System.out.println("Please enter the number of points available: ");
                           mcq1.setPoints(keyboard.nextInt());
                           System.out.println("Please enter the topic: ");
                           mcq1.setTopic(keyboard.next());
                           System.out.println("Please enter text for option 1: ");
                           mcq1.setOption1(keyboard.next());
                           System.out.println("Please enter text for option 2: ");
                           mcq1.setOption2(keyboard.next());
                           System.out.println("Please enter text for option 3: ");
                           mcq1.setOption3(keyboard.next());
                           System.out.println("Please enter text for option 4: ");
                           mcq1.setOption4(keyboard.next());
                           System.out.println("Please enter correct option number: ");
                           mcq1.setCorrectOption(keyboard.nextInt());
                           System.out.println("New Question has been saved. Thank you!");
                        }//if
                        if (addNewQuestionMenuChoice == 2) {
                           TrueFalseQuestion tf1 = new TrueFalseQuestion(null, -1, null, '0');
                           System.out.println("\nTrue or False Question Creation");
                           System.out.println("Please enter the question text: ");
                           tf1.setQuestionText(keyboard.next());
                           tf1.setQuestionText(keyboard.nextLine());
                           System.out.println("Please enter the number of points available: ");
                           tf1.setPoints(keyboard.nextInt());
                           System.out.println("Please enter the topic: ");
                           tf1.setTopic(keyboard.next());
                           System.out.println("Please enter answer T or F: ");
                           tf1.setAnswer(keyboard.next().charAt(0));
                           System.out.println("New Question has been saved. Thank you!");
                        }//if
                        if (addNewQuestionMenuChoice == 3) {
                           ShortQuestion sq1 = new ShortQuestion(null, 0, null, null);
                           System.out.println("Short Answer Question Creation");
                           System.out.println("Please enter the question text: ");
                           sq1.setQuestionText(keyboard.next());
                           sq1.setQuestionText(keyboard.nextLine());
                           System.out.println("Please enter the number of points available: ");
                           sq1.setPoints(keyboard.nextInt());
                           System.out.println("Please enter the topic: ");
                           sq1.setTopic(keyboard.next());
                           System.out.println("Please enter the correct answer");
                           sq1.setAnswer(keyboard.next());
                           sq1.setAnswer(keyboard.nextLine());
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
            //Student Registration via Student Menu
            boolean validLogin=false;
            do{
               validLogin= StudentMenu.display();
            }while (!validLogin);

            System.out.println("\nPress return to begin the quiz!\n*************************");
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