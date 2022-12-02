/**
 * Created by vcamp on 29/11/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by V.Campbell on 27/11/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
public class Globals
{

    public static String adminPassword="admin#2Password";



    //populate all question arrayLists
    public static void populateAllQuestions(){
        // Read in all questions from the relevant text files (Question Banks)
        TrueFalseQuestion.populate();
        MultipleChoiceQuestion.populate();
        ShortQuestion.populate();

        //Serialize questions to file
        TrueFalseQuestion.serialize();
        MultipleChoiceQuestion.serialize();
        ShortQuestion.serialize();
    }
    public static void logException(Exception ex)
    {
        try
        {
            File errorFile = new File("errorLog.txt");
            FileWriter fw = new FileWriter(errorFile, true);
            fw.write(ex.getMessage());
            fw.close();
        }
        catch (Exception e)
        {
            System.out.println(ex.getMessage());
        }
    }

}//class