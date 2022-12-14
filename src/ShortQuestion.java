import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Derived short answer question class
 * Author: Vicky Campbell. Method authors explicitly annotated
 **/
public class ShortQuestion extends Question
{
   //class members/variables
   private String answer;
   //static members/variables
   private static final String SHORT_QN_FILE_PATH = "shortQns.txt";
   private static final String SHORT_QN_BACKUP_PATH = "shortQnBackup.txt";
   private static final String SHORT_QN_SERIALIZED = "shortQns.ser";
   private static int count = 0;
   protected static ArrayList<ShortQuestion> shortQnList = new ArrayList<>();

   //parameterised constructor
   protected ShortQuestion(String QuestionText, int Points, String Topic, String Answer)
   {
      super(QuestionText, Points, Topic);
      answer = Answer;
      count++;
   }
   //getters & setters
   protected void setAnswer(String Answer)
   {
      answer = Answer;
   }

   protected String getAnswer()
   {
      return answer;
   }

   //method to restore all short answer questions from text file
   //has 2 modes
   //b - restore from most recent backup file
   //o - restore from original text file
   protected static void restoreQns(char mode)
   {
      String filePath = null;
      try
      {
         if (mode == 'b'){
            filePath = SHORT_QN_BACKUP_PATH;
         }
         else if(mode =='o'){
            filePath=SHORT_QN_FILE_PATH;
         }
         File qnFile = new File(filePath);
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
         Main.logException(ex);
      }
   }
   //method to serialize all short answer questions
   protected static void serialize()
   {
      try
      {
         ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("shortQns.ser")));
         out.writeObject(shortQnList);
         out.close();

      } catch (IOException ex)
      {
         System.out.println(ex.getMessage());
         Main.logException(ex);
      }
   }
   //method to deserialize all short answer questions
   protected static void deserialize()
   {
      try
      {
         ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("shortQns.ser")));
         shortQnList = (ArrayList<ShortQuestion>) in.readObject();

      } catch (IOException | ClassNotFoundException ex)
      {
         System.out.println(ex.getMessage());
         Main.logException(ex);
      }
   }
   //Author: David
   //Purpose: ??
   protected static void declareInitialiseAndUpdate_NewQuestionObject()
   {
      Scanner keyboard = new Scanner(System.in);
      ShortQuestion sq1 = new ShortQuestion(null, 0, null, null);
      System.out.println("Short Answer Question Creation");
      System.out.println("Please enter the topic: ");
      sq1.setTopic(keyboard.nextLine().trim());
      System.out.println("Please enter the question text: ");
      sq1.setQuestionText(keyboard.nextLine().trim());
      System.out.println("Please enter the number of points available: ");
      sq1.setPoints(Integer.parseInt(keyboard.nextLine()));
      System.out.println("Please enter the correct answer");
      sq1.setAnswer(keyboard.nextLine().trim());
      System.out.println("New Question has been saved. Thank you!");
      shortQnList.add(sq1);
      serialize();
   }
   //method to write current question list to separate backup text file for later restoration if required
   protected static void backupQnsToFile()
   {
      try
      {
         FileWriter qnWriter = new FileWriter(SHORT_QN_BACKUP_PATH);
         String points;
         int qnCount=shortQnList.size();
         for (ShortQuestion shortQn : shortQnList)
         {
            qnWriter.write(shortQn.getQuestionText() + "\n");
            points = Integer.toString(shortQn.getPoints());
            qnWriter.write(points + "\n");
            qnWriter.write(shortQn.getTopic() + "\n");
            if(qnCount!=1){
               qnWriter.write(shortQn.getAnswer() + "\n");
            }
            else{
               qnWriter.write(shortQn.getAnswer());
            }
            qnCount--;
         }
         qnWriter.flush();
         qnWriter.close();
         System.out.println("Short Answer Question Lists have been Successfully Backed Up");
      } catch (IOException ex)
      {
         System.out.println(ex.getMessage());
         Main.logException(ex);
      }
   }
   //method to check if serialized file is present, restores it from original text file if not
   protected static void fileCheck()
   {
      File f = new File(SHORT_QN_SERIALIZED);
      if (!f.exists())
      {
         System.out.println("Short Answer Question files were not found - backup files have been restored");
         restoreQns('o');
         serialize();
      }
   }

}//class


