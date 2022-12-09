import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by V.Campbell on 29/11/2022
 * Derived class for True/False answer questions
 **/
public class TrueFalseQuestion extends Question
{
   private static final String tfQnFilePath = "tfQns.txt";
   private static int count = 0;
   protected static ArrayList<TrueFalseQuestion> tfQnList = new ArrayList<>();
   protected static Scanner keyboard = new Scanner(System.in);
   protected char answer;

   protected TrueFalseQuestion(String QuestionText, int Points, String Topic, char Answer)
   {
      super(QuestionText, Points, Topic);
      answer = Answer;
      count++;
   }

   protected void setAnswer(char Answer)
   {
      answer = Answer;
   }

   protected char getAnswer()
   {
      return answer;
   }

   protected static void populate()
   {
      try
      {
         File qnFile = new File(tfQnFilePath);
         Scanner qnReader = new Scanner(qnFile);
         while (qnReader.hasNextLine())
         {
            String qnText = qnReader.nextLine();
            int qnPoints = Integer.parseInt(qnReader.nextLine());
            String qnTopic = qnReader.nextLine();
            char answer = qnReader.nextLine().charAt(0);
            TrueFalseQuestion qn = new TrueFalseQuestion(qnText, qnPoints, qnTopic, answer);
            tfQnList.add(qn);
         }
         qnReader.close();
      } catch (FileNotFoundException e)
      {
         System.out.println("An error occurred." + e.getMessage());
      }
   }

   protected static void serialize()
   {
      try
      {
         ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("tfQns.ser")));
         out.writeObject(TrueFalseQuestion.tfQnList);
         out.close();

      } catch (NotSerializableException ex)
      {
         //TODO: Fix Catch
      } catch (IOException ex)
      {
         Globals.logException(ex);
      }
   }

   protected static void deserialize()
   {
      try
      {
         ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("tfQns.ser")));
         tfQnList = (ArrayList<TrueFalseQuestion>) in.readObject();

      } catch (NotSerializableException ex)
      {
         //TODO: Fix Catch
      } catch (IOException | ClassNotFoundException ex)
      {
         Globals.logException(ex);
      }
   }

   protected static void declareInitialiseAndUpdate_NewQuestionObject()
   {
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
      tfQnList.add(tf1);
      serialize();
   }

}//class
