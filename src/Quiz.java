import java.util.Scanner;

public class Quiz
{
   private static Scanner keyboard = new Scanner(System.in);
   private static String input;

   public static void run()
   {
      System.out.println("Welcome to the Quiz");
      System.out.println("Press enter to begin:");
      input = keyboard.nextLine();

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
            LeaderBoard.updateLeaderboard(studentUser.getUsername(), QuizMaster.getQuizScore());
         }
      }
      LeaderBoard.printLeaderboard("student");
      boolean retake = retakeQuiz();
      if (retake)
      {
         Quiz.run();
      } else
      {
         Main.displayMainMenu();
      }
   }

   protected static boolean retakeQuiz()
   {
      String choice;
      int retakeChoice;
      do
      {
         System.out.println("\nPlease enter a selection: \n1. Retake Quiz \n2. Logout and return to Student Menu");
         choice = keyboard.next();
      } while (!Globals.validMenuChoice(choice, 1, 2));
      retakeChoice = Integer.parseInt(choice);
      return retakeChoice == 1;
   }
}
