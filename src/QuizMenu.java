import java.util.Scanner;

public class QuizMenu
{
   public static void display()
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("\nWelcome to the Quiz");
      System.out.println("Press enter to begin:");
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
         if (studentUser.getUsername().equals(Main.currentStudent.username))
         {
            studentUser.setHighestScore(QuizMaster.getQuizScore());
         }
      }
      Student.serialize();
      // Display the student version of the Leaderboard
      LeaderBoard.printLeaderboard("student");
      // And provide option to retake the quiz
      boolean retake = retakeQuiz();
      if (retake)
      {
         QuizMenu.display();
      } else
      {
         Main.displayMainMenu();
      }
   }

   protected static boolean retakeQuiz()
   {
      Scanner keyboard = new Scanner(System.in);
      String choice;
      int retakeChoice;
      do
      {
         System.out.println("\nPlease enter a selection: \n1. Retake Quiz \n2. Logout and return to Main Menu");
         choice = keyboard.nextLine();
      } while (!Globals.validMenuChoice(choice, 1, 2));
      retakeChoice = Integer.parseInt(choice);
      return retakeChoice == 1;
   }
}
