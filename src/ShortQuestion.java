import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by V.Campbell on 27/11/2022
 * Derived short answer question class
 **/
public class ShortQuestion extends Question
{
   private static final String shortQnFilePath = "shortQns.txt";
   private static Scanner keyboard = new Scanner(System.in);
   private static int count = 0;
   protected static ArrayList<ShortQuestion> shortQnList = new ArrayList<>();
   private String answer;

   protected ShortQuestion(String QuestionText, int Points, String Topic, String Answer)
   {
      super(QuestionText, Points, Topic);
      answer = Answer;
      count++;
   }

   protected void setAnswer(String Answer)
   {
      answer = Answer;
   }

   protected String getAnswer()
   {
      return answer;
   }

   protected static void populate()
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
            String answer = qnReader.nextLine();
            ShortQuestion qn = new ShortQuestion(qnText, qnPoints, qnTopic, answer);
            shortQnList.add(qn);
         }
         qnReader.close();
      } catch (FileNotFoundException ex)

      {
         System.out.println("An error occurred." + ex.getMessage());
         Globals.logException(ex);
      }
   }

   protected static void serialize()
   {
      try
      {
         ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("shortQns.ser")));
         out.writeObject(shortQnList);
         out.close();

      } catch (NotSerializableException ex)
      {
         //TODO: Fix Catch
      } catch (IOException ex)
      {
         ex.printStackTrace();
      }
   }

   private static void deserialize()
   {
      try
      {
         ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("shortQns.ser")));
         shortQnList = (ArrayList<ShortQuestion>) in.readObject();

      } catch (NotSerializableException ex)
      {
         //TODO: Fix Catch
      } catch (IOException ex)
      {
         ex.printStackTrace();
      } catch (ClassNotFoundException e)
      {
         throw new RuntimeException(e);
      }
   }

   protected static void declareInitialiseAndUpdate_NewQuestionObject()
   {
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
      shortQnList.add(sq1);
      serialize();
   }

}//class


