import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by V.Campbell on 27/11/2022
 * Derived short answer question class
 **/
public class ShortQuestion extends Question
{
   private static int count=0;
   public static ArrayList<ShortQuestion> shortQnList = new ArrayList<ShortQuestion>();
   private static String shortQnFilePath="shortQns.txt";
   private String answer;

   public ShortQuestion(String QuestionText, int Points, String Topic, String Answer)
   {
      super(QuestionText, Points, Topic);
      answer = Answer;
      count++;
   }

   public void setAnswer(String Answer)
   {
      answer = Answer;
   }

   public String getAnswer()
   {
      return answer;
   }

   public static void populate()
   {
      try
      {
         File qnFile = new File(shortQnFilePath);
         Scanner qnReader = new Scanner(qnFile);
         while (qnReader.hasNextLine())
         {
            String qnText = qnReader.nextLine();
            int qnPoints = Integer.parseInt(qnReader.nextLine());
            String qnTopic = qnReader.nextLine();
            String answer =qnReader.nextLine();
            ShortQuestion qn = new ShortQuestion(qnText, qnPoints, qnTopic, answer);
            shortQnList.add(qn);
         }
         qnReader.close();
      } catch (FileNotFoundException ex)

      {
         System.out.println("An error occurred."+ex.getMessage());
         Globals.logException(ex);
      }
   }

   public static void serialize(){
      try {
         ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("shortQns.ser"));
         out.writeObject(shortQnList);
         out.close();

      }
      catch (NotSerializableException ex){
      }
      catch (IOException ex) {
         ex.printStackTrace();
      }
   }
   public static void deserialize(){
      try {
         ObjectInputStream in = new ObjectInputStream(new FileInputStream("shortQns.ser"));
         shortQnList = (ArrayList<ShortQuestion>)in.readObject();

      }
      catch (NotSerializableException ex){
      }
      catch (IOException ex) {
         ex.printStackTrace();
      } catch (ClassNotFoundException e)
      {
         throw new RuntimeException(e);
      }
   }
   public static void updateQuestion() {
      Scanner keyboard = new Scanner(System.in);
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
   }
}//class


