import java.util.Collections;
import java.util.Scanner;

/**
 * Filename: QuizMaster
 * Created by: nsweeney-ortiz on 01/12/2022
 * Comment: Class & Methods to run Quiz, record student answers, and output results
 **/
public class QuizMaster
{
   // instance attributes
   private Boolean ansCorrect; // May not be required.
   private int markAvail;
   private int markAwarded;

   // static class attributes
   private static int mainQuizNumTFQns = 1;
   private static int mainQuizNumShortQns = 1;
   private static int mainQuizNumMCQns = 1;
   private static int numQuestionsAsked;
   private static int totalMarkAvailable;
   private static int totalMarkAwarded;

   private static Scanner keyboard = new Scanner(System.in);

   private QuizMaster()
   {
      this.ansCorrect = false;
      this.markAvail = 1;
      this.markAwarded = 0;
   }//default constructor

   private static void runQuiz()
   {
      int maxTFQns, maxMCQns, maxShortQns;
      maxTFQns = TrueFalseQuestion.tfQnList.size();
      maxShortQns = ShortQuestion.shortQnList.size();
      maxMCQns = MultipleChoiceQuestion.mcQnList.size();
      // Run quiz and ask all questions in question bank
      runQuiz(maxTFQns, maxShortQns, maxMCQns);
   }

   protected static void runQuiz(String quizTopic)
   {
      if (quizTopic.equalsIgnoreCase("mainQuiz"))
         runQuiz(mainQuizNumTFQns, mainQuizNumShortQns, mainQuizNumMCQns);
   }

   protected static void runQuiz(int numTfQns, int numShortQns, int numMCQns)
   {
      QuizMaster quizMaster;

      // Limit max number of questions asked by number of question in question bank
      numTfQns = Math.min(numTfQns, TrueFalseQuestion.tfQnList.size());
      numShortQns = Math.min(numShortQns, ShortQuestion.shortQnList.size());
      numMCQns = Math.min(numMCQns, MultipleChoiceQuestion.mcQnList.size());

      // Randomize Question Banks
      Collections.shuffle(TrueFalseQuestion.tfQnList);
      Collections.shuffle(ShortQuestion.shortQnList);
      Collections.shuffle(MultipleChoiceQuestion.mcQnList);

      // Ask specified number of T/F questions
      for (int qNum = 0; qNum < numTfQns; qNum++)
      {
         quizMaster = new QuizMaster();
         quizMaster.askQuestion("tfQn");
      }

      // Ask specified number of short answer questions
      for (int qNum = 0; qNum < numShortQns; qNum++)
      {
         quizMaster = new QuizMaster();
         quizMaster.askQuestion("shortQn");
      }

      // Ask specified number of multiple choice questions
      for (int qNum = 0; qNum < numMCQns; qNum++)
      {
         quizMaster = new QuizMaster();
         quizMaster.askQuestion("mcQn");
      }
   }

   private void askQuestion(String questionType)
   {
      if (questionType.equalsIgnoreCase("tfQn")) askTFQuestion();
      else if (questionType.equalsIgnoreCase("mcQn")) askMCQuestion();
      else if (questionType.equalsIgnoreCase("shortQn")) askShortQuestion();
      //else TO DO: exception handling
   }

   private void askTFQuestion()
   {
      String answer, correctAnswer;
      int zeroIndex = 0;
      setMarkAvail(TrueFalseQuestion.tfQnList.get(zeroIndex).getPoints());
      System.out.println("\n" + (numQuestionsAsked + 1) + ". " + TrueFalseQuestion.tfQnList.get(zeroIndex).getQuestionText());
      System.out.println("\nAnswer 'true' or 'false': ");
      answer = keyboard.next();
      if (TrueFalseQuestion.tfQnList.get(zeroIndex).getAnswer() == 'T') correctAnswer = "true";
      else correctAnswer = "false";
      if (answer.equalsIgnoreCase(correctAnswer))
      {
         this.ansCorrect = true;
         this.markAwarded = this.markAvail;
      } else this.ansCorrect = false;
      recordQuestionMark();
      TrueFalseQuestion.tfQnList.remove(zeroIndex);
      numQuestionsAsked++;
   }

   private void askShortQuestion()
   {
      String answer, correctAnswer;
      int zeroIndex = 0;
      setMarkAvail(ShortQuestion.shortQnList.get(zeroIndex).getPoints());
      System.out.println("\n" + (numQuestionsAsked + 1) + ". " + ShortQuestion.shortQnList.get(zeroIndex).getQuestionText());
      System.out.println("\nPlease enter a one word answer: ");
      answer = keyboard.next();
      correctAnswer = ShortQuestion.shortQnList.get(zeroIndex).getAnswer();
      if (answer.equalsIgnoreCase(correctAnswer))
      {
         this.ansCorrect = true;
         this.markAwarded = this.markAvail;
      } else this.ansCorrect = false;
      recordQuestionMark();
      ShortQuestion.shortQnList.remove(zeroIndex);
      numQuestionsAsked++;
   }

   private void askMCQuestion()
   {
      int answer, correctAnswer;
      int zeroIndex = 0;
      setMarkAvail(MultipleChoiceQuestion.mcQnList.get(zeroIndex).getPoints());
      System.out.println("\n" + (numQuestionsAsked + 1) + ". " + MultipleChoiceQuestion.mcQnList.get(zeroIndex).getQuestionText());
      System.out.println("\t1. " + MultipleChoiceQuestion.mcQnList.get(zeroIndex).getOption1());
      System.out.println("\t2. " + MultipleChoiceQuestion.mcQnList.get(zeroIndex).getOption2());
      System.out.println("\t3. " + MultipleChoiceQuestion.mcQnList.get(zeroIndex).getOption3());
      System.out.println("\t4. " + MultipleChoiceQuestion.mcQnList.get(zeroIndex).getOption4());
      System.out.println("\nEnter correct option: ");
      answer = keyboard.nextInt();
      correctAnswer = MultipleChoiceQuestion.mcQnList.get(zeroIndex).getCorrectOption();
      if (answer == correctAnswer)
      {
         this.ansCorrect = true;
         this.markAwarded = this.markAvail;
      } else this.ansCorrect = false;
      recordQuestionMark();
      MultipleChoiceQuestion.mcQnList.remove(zeroIndex);
      numQuestionsAsked++;
   }

   private void setMarkAvail(int markAvail)
   {
      this.markAvail = markAvail;
   }

   private void recordQuestionMark()
   {
      totalMarkAvailable += this.markAvail;
      totalMarkAwarded += this.markAwarded;
   }

   protected static void initializeQuizMaster()
   {
      QuizMaster.numQuestionsAsked = 0;
      QuizMaster.totalMarkAvailable = 0;
      QuizMaster.totalMarkAwarded = 0;
   }

   protected static int getMainQuizNumTFQns()
   {
      return mainQuizNumTFQns;
   }

   protected static int getMainQuizNumShortQns()
   {
      return mainQuizNumShortQns;
   }

   protected static int getMainQuizNumMCQns()
   {
      return mainQuizNumMCQns;
   }

   protected static void setMainQuizNumTFQns(int numTFQns)
   {
      mainQuizNumTFQns = numTFQns;
   }

   protected static void setMainQuizNumShortQns(int numShortQns)
   {
      mainQuizNumShortQns = numShortQns;
   }

   protected static void setMainQuizNumMCQns(int numMCQns)
   {
      mainQuizNumMCQns = numMCQns;
   }

   protected static int getTotalMarkAvailable()
   {
      return totalMarkAvailable;
   }

   protected static int getTotalMarkAwarded()
   {
      return totalMarkAwarded;
   }

   protected static void printQuizResult()
   {
      System.out.println("\nQuiz Result: " + QuizMaster.getTotalMarkAwarded() + " marks out of " + QuizMaster.getTotalMarkAvailable());
      System.out.println("Quiz Score: " + getQuizScore() + "\n");
   }

   protected static int getQuizScore()
   {
      return (100 * QuizMaster.getTotalMarkAwarded() / QuizMaster.getTotalMarkAvailable());
   }

}//class
