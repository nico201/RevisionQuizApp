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
   public static boolean TryParseInt(String inputString)
   {

      boolean validInt = true;
      for (int i = 0; i < inputString.length(); i++)
      {
         if (!Character.isDigit(inputString.charAt(i)))
         {
            validInt = false;
            break;
         }
      }
      return validInt;
   }

   public static boolean validMenuChoice(String inputString, int min, int max) {
      boolean isValidMenuChoice = false;
      int menuChoice;
      if (Globals.TryParseInt(inputString)) {
         menuChoice = Integer.parseInt(inputString);
         if (menuChoice >= min && menuChoice <= max) {
            isValidMenuChoice = true;
         } else {
            System.out.println("Menu choice out of range. Please try again!");
         }
      } else {
         System.out.println("Not a valid integer. Please try again!");
      }
      return isValidMenuChoice;
   }
}//class