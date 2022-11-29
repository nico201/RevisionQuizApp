
import java.util.Scanner;

/**
 * Created by Aaron McCloskey on 27/11/2022
 * Early attempt at new question. Likely to made redundant.
 * Class Name: quizPage.java
 **/
public class Question
{
   public static Scanner keyboard = new Scanner(System.in);

   private String questionTopic;
   private String questionText;
   private static int questionCounter = 0;
   private int uniqueID;


   public static void addQuestion()
   {
      final int CORRECT_ANSWER_INT;
      final String CORRECT_ANSWER_STRING;

      String questionTopic, questionText, questionTypeSelection, addAdditionalQuestion;

      System.out.println("Please enter the question topic: ");
      questionTopic = keyboard.nextLine();
      System.out.println("Please enter question text: ");
      System.out.println("(If entering an MCQ, please include numerical options)");
      questionText = keyboard.nextLine();
      System.out.println("Question Type: Please enter (M) for MCQ, (S) for Single Word Answer or (T) for True or False");
      questionTypeSelection = keyboard.nextLine();
      if (questionTypeSelection.charAt(0) == 'M')
      {
         System.out.println("Please enter an integer for the correct answer");
         CORRECT_ANSWER_INT = keyboard.nextInt();
         MCQ newMCQ = new MCQ(questionTopic,questionText,CORRECT_ANSWER_INT);
      }
      else if (questionTypeSelection.charAt(0) == 'T')
      {
         System.out.println("Please enter true or false for the correct answer");
         CORRECT_ANSWER_STRING = keyboard.next();
         TOrF newTOrF = new TOrF(questionTopic,questionText,CORRECT_ANSWER_STRING);
      }
          else if (questionTypeSelection.charAt(0) == 'S')
          {
            System.out.println("Please enter a single word for the correct answer");
            CORRECT_ANSWER_STRING = keyboard.next();
            SW newSW = new SW(questionTopic,questionText,CORRECT_ANSWER_STRING);
          }

      System.out.println("Would you like to enter another question? Y or N");
      addAdditionalQuestion = keyboard.next();
      if (addAdditionalQuestion.charAt(0) == 'Y')
         addQuestion();
   }

   public Question(){
   }

   public Question(String questionTopic, String questionText)
   {
      this.questionTopic = questionTopic;
      this.questionText = questionText;
      this.uniqueID = questionCounter;
      questionCounter++;
   }

   public void printQuestion(){
      System.out.println(questionText);
   }

   public String toString(){
      return "This is a question";
   }

}//class