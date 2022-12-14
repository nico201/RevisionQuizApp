import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * COM809: Group 5
 * Multiple Choice Question Class - Derived from Question base Class
 * Author: Vicky Campbell. Method authors explicitly annotated
 **/
public class MultipleChoiceQuestion extends Question
{
   //static declarations
   protected static ArrayList<MultipleChoiceQuestion> mcQnList = new ArrayList<>();
   private static final String MULTIPLE_CHOICE_QN_FILE_PATH = "multipleChoiceQns.txt";
   private static final String MC_QN_SERIALIZED = "multipleChoiceQns.ser";
   private static final String MC_QN_BACKUP_PATH = "mcQnBackup.txt";

   //class member declarations
   private static int count = 0;
   private String option1;
   private String option2;
   private String option3;
   private String option4;
   private int correctOption;

   //parameterised constructor
   private MultipleChoiceQuestion(String QuestionText, int Points, String Topic, String Opt1, String Opt2, String Opt3, String Opt4, int CorrectOption)
   {

      super(QuestionText, Points, Topic);
      option1 = Opt1;
      option2 = Opt2;
      option3 = Opt3;
      option4 = Opt4;
      correctOption = CorrectOption;
      count++;
   }
   //getters/setters
   protected void setOption1(String Option1)
   {
      option1 = Option1;
   }

   protected String getOption1()
   {
      return option1;
   }

   protected void setOption2(String Option2)
   {
      option2 = Option2;
   }

   protected String getOption2()
   {
      return option2;
   }

   protected void setOption3(String Option3)
   {
      option3 = Option3;
   }

   protected String getOption3()
   {
      return option3;
   }

   protected void setOption4(String Option4)
   {
      option4 = Option4;
   }

   protected String getOption4()
   {
      return option4;
   }

   protected void setCorrectOption(int CorrectOption)
   {
      correctOption = CorrectOption;
   }

   protected int getCorrectOption()
   {
      return correctOption;
   }

   //class methods
   //method to restore all multiple choice questions from text file
   //has 2 modes
   //b - restore from most recent backup file
   //o - restore from original text file
   protected static void restoreQns(char mode)
   {
      String filePath;
      try
      {
         if (mode == 'b'){
            filePath = MC_QN_BACKUP_PATH;
         }
         else {
            filePath=MULTIPLE_CHOICE_QN_FILE_PATH;
         }

         File mcQnFile = new File(filePath);
         Scanner mcQnReader = new Scanner(mcQnFile);
         while (mcQnReader.hasNextLine())
         {
            String qnText = mcQnReader.nextLine();
            int qnPoints = Integer.parseInt(mcQnReader.nextLine());
            String qnTopic = mcQnReader.nextLine();
            String Opt1 = mcQnReader.nextLine();
            String Opt2 = mcQnReader.nextLine();
            String Opt3 = mcQnReader.nextLine();
            String Opt4 = mcQnReader.nextLine();
            int answer = Integer.parseInt(mcQnReader.nextLine());
            MultipleChoiceQuestion qn = new MultipleChoiceQuestion(qnText, qnPoints, qnTopic, Opt1, Opt2, Opt3, Opt4, answer);
            mcQnList.add(qn);
         }
         mcQnReader.close();
      } catch (FileNotFoundException e)
      {
         System.out.println("An error occurred." + e.getMessage());
      }
   }
   //method to serialize all multiple choice questions
   protected static void serialize()
   {
      try
      {
         ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("multipleChoiceQns.ser")));
         out.writeObject(mcQnList);
         out.close();
      } catch (IOException ex)
      {
         Main.logException(ex);
      }
   }
   //method to deserialize all multiple choice questions
   protected static void deserialize()
   {
      try
      {
         ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("multipleChoiceQns.ser")));
         mcQnList = (ArrayList<MultipleChoiceQuestion>) in.readObject();

      } catch (NotSerializableException ex)
      {
         Main.logException(ex);
      } catch (IOException ex)
      {
         ex.printStackTrace();
      } catch (ClassNotFoundException e)
      {
         throw new RuntimeException(e);
      }
   }
   //Author: David
   //Purpose: ??
   protected static void declareInitialiseAndUpdate_NewQuestionObject()
   {
      Scanner keyboard = new Scanner(System.in);
      MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion(null, -1, null, null, null, null, null, -1);
      System.out.println("\nMCQ Question Creation");
      System.out.println("Please enter the topic: ");
      mcq1.setTopic(keyboard.nextLine().trim());
      System.out.println("Please enter the question text: ");
      mcq1.setQuestionText(keyboard.nextLine().trim());
      System.out.println("Please enter the number of points available: ");
      mcq1.setPoints(Integer.parseInt(keyboard.nextLine()));
      System.out.println("Please enter text for option 1: ");
      mcq1.setOption1(keyboard.nextLine().trim());
      System.out.println("Please enter text for option 2: ");
      mcq1.setOption2(keyboard.nextLine().trim());
      System.out.println("Please enter text for option 3: ");
      mcq1.setOption3(keyboard.nextLine().trim());
      System.out.println("Please enter text for option 4: ");
      mcq1.setOption4(keyboard.nextLine().trim());
      System.out.println("Please enter correct option number: ");
      mcq1.setCorrectOption(Integer.parseInt(keyboard.nextLine()));
      System.out.println("New Question has been saved. Thank you!");
      mcQnList.add(mcq1);
      serialize();
   }
   //method to write current question list to separate backup text file for later restoration if required
   protected static void backupQnsToFile()
   {
      try
      {
         FileWriter qnWriter = new FileWriter(MC_QN_BACKUP_PATH);
         String points;
         String correct;
         int qnCount=mcQnList.size();
         for (MultipleChoiceQuestion mcQn : mcQnList)
         {
            qnWriter.write(mcQn.getQuestionText() + "\n");
            points = Integer.toString(mcQn.getPoints());
            qnWriter.write(points + "\n");
            qnWriter.write(mcQn.getTopic() + "\n");
            qnWriter.write(mcQn.getOption1() + "\n");
            qnWriter.write(mcQn.getOption2() + "\n");
            qnWriter.write(mcQn.getOption3() + "\n");
            qnWriter.write(mcQn.getOption4() + "\n");
            correct = Integer.toString(mcQn.getCorrectOption());
            //ensures additional blank line not written out after last value
            if(qnCount!=1){
               qnWriter.write(correct + "\n");
            }
            else{
               qnWriter.write(correct);
            }
            qnCount--;
         }
         qnWriter.flush();
         qnWriter.close();
         System.out.println("Multiple Choice Question Lists have been Successfully Backed Up");
      } catch (IOException ex)
      {
         System.out.println(ex.getMessage());
         Main.logException(ex);
      }
   }
   //method to check if serialized file is present, restores it from original text file if not
   protected static void fileCheck()
   {
      File f = new File(MC_QN_SERIALIZED);
      if (!f.exists())
      {
         System.out.println("Multiple Choice Question files were not found - backup files have been restored");
         restoreQns('o');
         serialize();
      }
   }

}//class
