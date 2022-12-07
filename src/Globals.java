import java.time.LocalDateTime;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;

/**
 * Created by V.Campbell on 27/11/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
public class Globals
{
   //populate all question arrayLists
   protected static void populateAllQuestions()
   {
      // Read in all questions from the relevant text files (Question Banks)
      TrueFalseQuestion.populate();
      MultipleChoiceQuestion.populate();
      ShortQuestion.populate();

      //Serialize questions to file
      TrueFalseQuestion.serialize();
      MultipleChoiceQuestion.serialize();
      ShortQuestion.serialize();
   }

   //method to log exceptions to file
   protected static void logException(Exception ex)
   {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      String currentDateAndTIme = dtf.format(now);
      try
      {
         File errorFile = new File("errorLog.txt");
         FileWriter fw = new FileWriter(errorFile, true);
         fw.write("\n" + currentDateAndTIme + ", " + ex.getMessage());
         fw.close();
      } catch (Exception e)
      {
         System.out.println(ex.getMessage());
      }
   }

}//class