import java.util.Collections;
import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Class & Methods to run Quiz, record student marks, and output results
 **/
public class QuizMaster
{
   // static constants
   protected static final String MAIN_QUIZ = "mainQuiz";
   protected static final int MAX_QUESTIONS = 30;
   protected static final int MIN_QUESTIONS = 1;
   private static final String TFQN = "tfQn", MCQN = "mcQn", SHORTQN = "shortQn";

   // static class attributes
   private static int mainQuizNumTFQns = 1;
   private static int mainQuizNumShortQns = 1;
   private static int mainQuizNumMCQns = 1;
   private static int numQuestionsAsked;
   private static int totalMarkAvailable;
   private static int totalMarkAwarded;

   // instance attributes
   private int markAvail;
   private int markAwarded;

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Default constructor
    */
   private QuizMaster()
   {
      this.markAvail = 1;
      this.markAwarded = 0;
   }//default constructor

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Runs the student revision quiz asking all questions of each type:
    *          True/False, Short, Multiple Choice, available in the question banks
    */
   private static void runQuiz()
   {
      int maxTFQns, maxMCQns, maxShortQns;
      maxTFQns = TrueFalseQuestion.tfQnList.size();
      maxShortQns = ShortQuestion.shortQnList.size();
      maxMCQns = MultipleChoiceQuestion.mcQnList.size();
      // Run quiz and ask all questions in question bank
      runQuiz(maxTFQns, maxShortQns, maxMCQns, MAIN_QUIZ);
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Runs the student revision quiz asking the pre-defined number of questions
    *          of each type: True/False, Short, Multiple Choice, of the specified topic as
    *          indicated by the calling parameter. The pre-defined number of questions to
    *          be asked can be changed in the program via the Admin menu.
    * Notes: Questions asked of a particular type may be less than the number requested due to
    *        insufficient questions of the requested topic available in the question banks
    */
   protected static void runQuiz(String quizTopic)
   {
      // Run quiz with specified parameters for number of question and topic or main quiz
      runQuiz(mainQuizNumTFQns, mainQuizNumShortQns, mainQuizNumMCQns, quizTopic);
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Runs the student revision quiz asking the specified number of questions of
    *          each type: True/False, Short, Multiple Choice, of the specified topic as
    *          indicated by the calling parameters.
    * Notes: Questions asked of a particular type may be less than the number requested due to
    *        insufficient questions of the requested topic available in the question banks
    */
   protected static void runQuiz(int numTfQns, int numShortQns, int numMCQns, String quizTopic)
   {
      // Initialise an instance of the QuizMaster class representing a question asked
      QuizMaster quizMaster;

      // Limit max number of questions asked by number of questions in question bank
      numTfQns = Math.min(numTfQns, TrueFalseQuestion.tfQnList.size());
      numShortQns = Math.min(numShortQns, ShortQuestion.shortQnList.size());
      numMCQns = Math.min(numMCQns, MultipleChoiceQuestion.mcQnList.size());

      // Randomize Question Banks to increase material coverage & quiz difficulty
      Collections.shuffle(TrueFalseQuestion.tfQnList);
      Collections.shuffle(ShortQuestion.shortQnList);
      Collections.shuffle(MultipleChoiceQuestion.mcQnList);

      // Ask specified number of T/F questions from specified topic
      for (int qNum = 0; qNum < numTfQns; qNum++)
      {
         // Create a new instance of the QuizMaster class for each question asked
         quizMaster = new QuizMaster();
         quizMaster.askQuestion(TFQN, quizTopic);
      }

      // Ask specified number of short answer questions from specified topic
      for (int qNum = 0; qNum < numShortQns; qNum++)
      {
         // Create a new instance of the QuizMaster class for each question asked
         quizMaster = new QuizMaster();
         quizMaster.askQuestion(SHORTQN, quizTopic);
      }

      // Ask specified number of multiple choice questions from specified topic
      for (int qNum = 0; qNum < numMCQns; qNum++)
      {
         // Create a new instance of the QuizMaster class for each question asked
         quizMaster = new QuizMaster();
         quizMaster.askQuestion(MCQN, quizTopic);
      }
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Asks the student a question from a specified topic by calling
    *          the appropriate question type method based on provided parameters
    */
   private void askQuestion(String questionType, String quizTopic)
   {
      // Call appropriate 'askQuestion' method based on required question type and topic
      if (questionType.equalsIgnoreCase(TFQN)) askTFQuestion(quizTopic);
      else if (questionType.equalsIgnoreCase(MCQN)) askMCQuestion(quizTopic);
      else if (questionType.equalsIgnoreCase(SHORTQN)) askShortQuestion(quizTopic);
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Assigns a True/False question from the question bank to an instance of quizMaster,
    *          prints the question to screen, reads in the student response, evaluates and records the student mark,
    *          and then removes the question asked from the question bank so that it is not re-asked in the same session
    */
   private void askTFQuestion(String topic)
   {
      Scanner keyboard = new Scanner(System.in);
      String answer, correctAnswer;
      boolean qnFound = false;
      int index = 0;
      do
      {
         // Find question of specified topic or take first question from bank for main quiz
         if (topic.equalsIgnoreCase(MAIN_QUIZ) | TrueFalseQuestion.tfQnList.get(index).getTopic().equalsIgnoreCase(topic))
         {
            qnFound = true;
            // Set available marks for question asked
            setMarkAvail(TrueFalseQuestion.tfQnList.get(index).getPoints());
            do
            {
               // Ask question and get student response
               System.out.println("\nTopic: " + TrueFalseQuestion.tfQnList.get(index).getTopic());
               System.out.println((numQuestionsAsked + 1) + ". " + TrueFalseQuestion.tfQnList.get(index).getQuestionText());
               System.out.println("Number of points available: " + TrueFalseQuestion.tfQnList.get(index).getPoints());
               System.out.println("\nAnswer 'true' or 'false': ");
               answer = keyboard.nextLine().trim();
               // Handle unexpected student inputs
            } while (!(answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("false")));
            if (TrueFalseQuestion.tfQnList.get(index).getAnswer() == 'T') correctAnswer = "true";
            else correctAnswer = "false";
            // Evaluate student answer & set instance variables for this question
            if (answer.equalsIgnoreCase(correctAnswer))
               this.markAwarded = this.markAvail;
            // Save students score for this question
            recordQuestionMark();
            // Remove this question from question bank so that it is not re-asked for this user
            TrueFalseQuestion.tfQnList.remove(index);
            // Increment static class variable tracking number of questions asked
            numQuestionsAsked++;
         } else index++;
         // If no questions are found of specified topic in the question bank then this question type is skipped
      } while (!qnFound && ((index + 1) <= TrueFalseQuestion.tfQnList.size()));
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Assigns a Short question from the question bank to an instance of quizMaster,
    *          prints the question to screen, reads in the student response, evaluates and records the student mark,
    *          and then removes the question asked from the question bank so that it is not re-asked in the same session
    */
   private void askShortQuestion(String topic)
   {
      Scanner keyboard = new Scanner(System.in);
      String answer, correctAnswer;
      boolean qnFound = false;
      int index = 0;
      do
      {
         // Find question of specified topic or take first question from bank for main quiz
         if (topic.equalsIgnoreCase(MAIN_QUIZ) | ShortQuestion.shortQnList.get(index).getTopic().equalsIgnoreCase(topic))
         {
            qnFound = true;
            // Set available marks for question asked
            setMarkAvail(ShortQuestion.shortQnList.get(index).getPoints());
            // Ask question and get student response
            System.out.println("\nTopic: " + ShortQuestion.shortQnList.get(index).getTopic());
            System.out.println((numQuestionsAsked + 1) + ". " + ShortQuestion.shortQnList.get(index).getQuestionText());
            System.out.println("Number of points available: " + ShortQuestion.shortQnList.get(index).getPoints());
            System.out.println("\nPlease enter a short answer: ");
            answer = keyboard.nextLine().trim();
            correctAnswer = ShortQuestion.shortQnList.get(index).getAnswer();
            // Evaluate student answer & set instance variables for this question
            if (answer.equalsIgnoreCase(correctAnswer))
               this.markAwarded = this.markAvail;
            // Save students score for this question
            recordQuestionMark();
            // Remove this question from question bank so that it is not re-asked for this user
            ShortQuestion.shortQnList.remove(index);
            // Increment static class variable tracking number of questions asked
            numQuestionsAsked++;
         } else index++;
         // If no questions are found of specified topic in the question bank then this question type is skipped
      } while (!qnFound && ((index + 1) <= ShortQuestion.shortQnList.size()));
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Assigns a Multiple Choice question from the question bank to an instance of quizMaster,
    *          prints the question to screen, reads in the student response, evaluates and records the student mark,
    *          and then removes the question asked from the question bank so that it is not re-asked in the same session
    */
   private void askMCQuestion(String topic)
   {
      Scanner keyboard = new Scanner(System.in);
      String userInput;
      int answer, correctAnswer;
      boolean qnFound = false;
      int index = 0;
      do
      {
         // Find question of specified topic or take first question from bank for main quiz
         if (topic.equalsIgnoreCase(MAIN_QUIZ) | MultipleChoiceQuestion.mcQnList.get(index).getTopic().equalsIgnoreCase(topic))
         {
            qnFound = true;
            // Set available marks for question asked
            setMarkAvail(MultipleChoiceQuestion.mcQnList.get(index).getPoints());
            do
            {
               // Ask question and get student response
               System.out.println("\nTopic: " + MultipleChoiceQuestion.mcQnList.get(index).getTopic());
               System.out.println((numQuestionsAsked + 1) + ". " + MultipleChoiceQuestion.mcQnList.get(index).getQuestionText());
               System.out.println("\t1. " + MultipleChoiceQuestion.mcQnList.get(index).getOption1());
               System.out.println("\t2. " + MultipleChoiceQuestion.mcQnList.get(index).getOption2());
               System.out.println("\t3. " + MultipleChoiceQuestion.mcQnList.get(index).getOption3());
               System.out.println("\t4. " + MultipleChoiceQuestion.mcQnList.get(index).getOption4());
               System.out.println("Number of points available: " + MultipleChoiceQuestion.mcQnList.get(index).getPoints());
               System.out.println("\nEnter correct option: ");
               userInput = keyboard.nextLine().trim();
               // Handle unexpected student inputs
            } while (!Main.validMenuChoice(userInput, 1, 4));
            answer = Integer.parseInt(userInput);
            correctAnswer = MultipleChoiceQuestion.mcQnList.get(index).getCorrectOption();
            // Evaluate student answer & set instance variables for this question
            if (answer == correctAnswer)
               this.markAwarded = this.markAvail;
            // Save students score for this question
            recordQuestionMark();
            // Remove this question from question bank so that it is not re-asked for this user
            MultipleChoiceQuestion.mcQnList.remove(index);
            // Increment static class variable tracking number of questions asked
            numQuestionsAsked++;
         } else index++;
         // If no questions are found of specified topic in the question bank then this question type is skipped
      } while (!qnFound && ((index + 1) <= MultipleChoiceQuestion.mcQnList.size()));
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Sets the marks available for a question taken from a question bank to an instance of quizMaster (question asked)
    */
   private void setMarkAvail(int markAvail)
   {
      this.markAvail = markAvail;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Tallys the marks available and marks awarded of each quizMaster instance (question asked) to the static class
    */
   private void recordQuestionMark()
   {
      totalMarkAvailable += this.markAvail;
      totalMarkAwarded += this.markAwarded;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Initialises the static variables of the QuizMaster class
    */
   protected static void initializeQuizMaster()
   {
      QuizMaster.numQuestionsAsked = 0;
      QuizMaster.totalMarkAvailable = 0;
      QuizMaster.totalMarkAwarded = 0;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Prints the student's quiz marks awarded out of marks available to screen
    */
   protected static void printQuizResult()
   {
      System.out.println("\nQuiz Result: " + QuizMaster.getTotalMarkAwarded() + " marks out of " + QuizMaster.getTotalMarkAvailable());
      System.out.println("Quiz Score: " + getQuizScore());
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Calculates and returns student quiz score based on total marks awarded
    *          over total marks available, from all questions asked, as an integer
    *          percentage with exception handling for division by zero
    */
   protected static int getQuizScore()
   {
      int score = 0;
      try
      {
         score = (100 * QuizMaster.getTotalMarkAwarded() / QuizMaster.getTotalMarkAvailable());
      }//try
      catch (ArithmeticException error)
      {
         System.out.println("Error - Division by Zero");
      }//catch
      return score;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Getter for QuizMaster static int variable 'mainQuizNumTFQns'
    */
   protected static int getMainQuizNumTFQns()
   {
      return mainQuizNumTFQns;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Getter for QuizMaster static int variable 'mainQuizNumShortQns'
    */
   protected static int getMainQuizNumShortQns()
   {
      return mainQuizNumShortQns;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Getter for QuizMaster static int variable 'mainQuizNumMCQns'
    */
   protected static int getMainQuizNumMCQns()
   {
      return mainQuizNumMCQns;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Setter for QuizMaster static int variable 'mainQuizNumTFQns'
    */
   protected static void setMainQuizNumTFQns(int numTFQns)
   {
      mainQuizNumTFQns = numTFQns;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Setter for QuizMaster static int variable 'mainQuizNumShortQns'
    */
   protected static void setMainQuizNumShortQns(int numShortQns)
   {
      mainQuizNumShortQns = numShortQns;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Setter for QuizMaster static int variable 'mainQuizNumMCQns'
    */
   protected static void setMainQuizNumMCQns(int numMCQns)
   {
      mainQuizNumMCQns = numMCQns;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Getter for QuizMaster static int variable 'totalMarkAvailable'
    */
   protected static int getTotalMarkAvailable()
   {
      return totalMarkAvailable;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Getter for QuizMaster static int variable 'totalMarkAwarded'
    */
   protected static int getTotalMarkAwarded()
   {
      return totalMarkAwarded;
   }

}//class
