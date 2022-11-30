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
    public static String tfQnFilePath="tfQns.txt";
    public static String multipleChoiceQnFilePath="multipleChoiceQns.txt";
    public static String shortQnFilePath="shortQns.txt";
    public static String errorLogFilePath="errorLog.txt";

    public static ArrayList<TrueFalseQuestion> tfQnList = new ArrayList<TrueFalseQuestion>();
    public static ArrayList<MultipleChoiceQuestion> mcQnList = new ArrayList<MultipleChoiceQuestion>();
    public static ArrayList<ShortQuestion> shortQnList = new ArrayList<ShortQuestion>();


    //populate all question arrayLists

    public static void populateAllQuestions(){
        readQsFromFile("tf");
        readQsFromFile("mc");
        //readQsFromFile("short");
        serializeTFQns();
        //serializeMCQns();
        //serializeShortQns();
    }
    public static void logException(Exception ex)
    {
        //FileWriter errorWriter = new FileWriter(errorLogFilePath,true);
        //BufferedWriter bw = new BufferedWriter(errorWriter);
        //bw.write(java.time.LocalDate.now()+","+java.time.LocalTime.now()+","+ex.getMessage());
        //bw.newLine();
        //bw.close();
    }
    public static void readQsFromFile(String qnType){

        switch (qnType){
            case "tf":
                populateTFQns(tfQnFilePath);
                break;
            case "mc":
                populateMCQns(multipleChoiceQnFilePath);
                break;
            case "short":
                populateShortQns(shortQnFilePath);
                break;

            default:
                //TODO: change/handle exception
                throw new IllegalStateException("Not a valid question type: " + qnType);

        }

    }

    public static void populateTFQns(String fileName)
    {
        try
        {
            File qnFile = new File(fileName);
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
            System.out.println("An error occurred."+e.getMessage());
        }
    }
    public static void populateMCQns(String fileName)
    {
        try
        {
            File mcQnFile = new File(fileName);
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
                int answer =Integer.parseInt(mcQnReader.nextLine());
                MultipleChoiceQuestion qn = new MultipleChoiceQuestion(qnText, qnPoints, qnTopic, Opt1, Opt2, Opt3,Opt4,answer);
                mcQnList.add(qn);
            }
            mcQnReader.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("An error occurred."+e.getMessage());
        }
    }
    public static void populateShortQns(String fileName)
    {
        try
        {
            File qnFile = new File(fileName);
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
            logException(ex);
        }
    }
    public static void serializeTFQns(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tfQns.ser"));
            out.writeObject(tfQnList);
            out.close();

        }
        catch (NotSerializableException ex){
        }
        catch (IOException ex) {
            logException(ex);
        }
    }
    public static void deserializeTFQns(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("tfQns.ser"));
            tfQnList = (ArrayList<TrueFalseQuestion>)in.readObject();

        }
        catch (NotSerializableException ex){
        }
        catch (IOException ex) {
            logException(ex);
        } catch (ClassNotFoundException ex)
        {
            logException(ex);
        }
    }

    public static void serializeMCQns(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("multipleChoiceQns.ser"));
            out.writeObject(mcQnList);
            out.close();

        }
        catch (NotSerializableException ex){
            logException(ex);
        }
        catch (IOException ex) {
            logException(ex);
        }
    }
    public static void deserializeMCQns(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("multipleChoiceQns.ser"));
            mcQnList = (ArrayList<MultipleChoiceQuestion>)in.readObject();

        }
        catch (NotSerializableException ex){
            logException(ex);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void serializeShortQns(){
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
    public static void deserializeShortQns(){
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
}//class