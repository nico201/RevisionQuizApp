import java.util.Scanner;

/**
 * Filename: QuestionMaster
 * Created by: nsweeney-ortiz on 01/12/2022
 * Comment: UPDATE PROGRAM COMMENTS ABOUT PROGRAM HERE
 **/
public class QuizMaster
{
   // static constant variables
   final static int tfQnMark = 1;
   final static int shortQnMark = 2;
   final static int mcQnMark = 4;

   // instance attributes
   private Boolean ansCorrect; // May not be required.
   private int markAvail;
   private int markAwarded;

   // static class attributes
   private static int numQuestionsAsked = 0;
   private static int totalMarkAvailable = 0;
   private static int totalMarkAwarded = 0;

   private static Scanner keyboard = new Scanner(System.in);

   public QuizMaster(){
      this.ansCorrect = false;
      this.markAvail = 1;
      this.markAwarded = 0;
   }//default constructor

   public void askQuestion(String questionType){
      if (questionType.equalsIgnoreCase("tfQn"))
         askTFQuestion();
      else if (questionType.equalsIgnoreCase("mcQn"))
         askMCQuestion();
      else if (questionType.equalsIgnoreCase("shortQn"))
         askShortQuestion();
      //else TO DO: exception handling
   }

   public void runQuiz(){
      int maxTFQns, maxMCQns, maxShortQns;
      maxTFQns = Globals.tfQnList.size();
      maxShortQns = Globals.shortQnList.size();
      maxMCQns = Globals.mcQnList.size();
      // Run quiz and ask all questions in question bank
      runQuiz(maxTFQns, maxShortQns, maxMCQns);
   }

   public void runQuiz(int numTfQns, int numShortQns, int numMCQns){
      QuizMaster quizMaster;

      // Limit max number of questions asked by number of question in question bank
      numTfQns = Math.min(numTfQns, Globals.tfQnList.size());
      numShortQns  = Math.min(numShortQns, Globals.shortQnList.size());
      numMCQns = Math.min(numMCQns, Globals.mcQnList.size());

      // Ask specified number of T/F questions
      for ( int qNum = 0; qNum < numTfQns; qNum++)
      {
         quizMaster = new QuizMaster();
         quizMaster.askQuestion("tfQn");
      }

      // Ask specified number of short answer questions
      for ( int qNum = 0; qNum < numShortQns; qNum++)
      {
         quizMaster = new QuizMaster();
         quizMaster.askQuestion("shortQn");
      }

      // Ask specified number of multiple choice questions
      for ( int qNum = 0; qNum < numMCQns; qNum++)
      {
         quizMaster = new QuizMaster();
         quizMaster.askQuestion("mcQn");
      }
   }

   private void askTFQuestion(){
      String answer, correctAnswer;
      setMarkAvail(tfQnMark);
      System.out.println("\n" + (numQuestionsAsked+1) + ". " + Globals.tfQnList.get(0).getQuestionText());
      System.out.println("\nAnswer 'true' or 'false': ");
      answer = keyboard.next();
      if (Globals.tfQnList.get(0).getAnswer() == 'T')
         correctAnswer = "true";
      else
         correctAnswer = "false";
      if (answer.equalsIgnoreCase(correctAnswer))
      {
         this.ansCorrect = true;
         this.markAwarded = this.markAvail;
      }
      else
         this.ansCorrect = false;
      recordQuestionMark();
      Globals.tfQnList.remove(0);
      numQuestionsAsked++;
   }

   private void askShortQuestion(){
      String answer, correctAnswer;
      setMarkAvail(shortQnMark);
      System.out.println("\n" + (numQuestionsAsked+1) + ". " + Globals.shortQnList.get(0).getQuestionText());
      System.out.println("\nPlease enter a one word answer: ");
      answer = keyboard.next();
      correctAnswer = Globals.shortQnList.get(0).getAnswer();
      if (answer.equalsIgnoreCase(correctAnswer))
      {
         this.ansCorrect = true;
         this.markAwarded = this.markAvail;
      }
      else
         this.ansCorrect = false;
      recordQuestionMark();
      Globals.shortQnList.remove(0);
      numQuestionsAsked++;
   }

   private void askMCQuestion(){
      int answer, correctAnswer;
      setMarkAvail(mcQnMark);
      System.out.println("\n" + (numQuestionsAsked+1) + ". " + Globals.mcQnList.get(0).getQuestionText());
      System.out.println("\t1. " + Globals.mcQnList.get(0).getOption1());
      System.out.println("\t2. " + Globals.mcQnList.get(0).getOption2());
      System.out.println("\t3. " + Globals.mcQnList.get(0).getOption3());
      System.out.println("\t4. " + Globals.mcQnList.get(0).getOption4());
      System.out.println("\nEnter correct option: ");
      answer = keyboard.nextInt();
      correctAnswer = Globals.mcQnList.get(0).getCorrectOption();
      if (answer == correctAnswer)
      {
         this.ansCorrect = true;
         this.markAwarded = this.markAvail;
      }
      else
         this.ansCorrect = false;
      recordQuestionMark();
      Globals.mcQnList.remove(0);
      numQuestionsAsked++;
   }

   private void setMarkAvail(int markAvail){
      this.markAvail = markAvail;
   }

   private void recordQuestionMark(){
      totalMarkAvailable += this.markAvail;
      totalMarkAwarded += this.markAwarded;
   }

   public static int getTotalMarkAvailable(){
      return totalMarkAvailable;
   }

   public static int getTotalMarkAwarded(){
      return totalMarkAwarded;
   }

   public static void printQuizResult(){
      System.out.println("\nQuiz Result: " + QuizMaster.getTotalMarkAwarded() + " marks out of " + QuizMaster.getTotalMarkAvailable());
      System.out.println("Quiz Score: " + getQuizScore() + "\n");
   }

   public static int getQuizScore(){
      return (QuizMaster.getTotalMarkAwarded()/QuizMaster.getTotalMarkAvailable()*100);
   }

}//class
