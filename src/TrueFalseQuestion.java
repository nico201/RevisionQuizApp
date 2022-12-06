import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by V.Campbell on 29/11/2022
 * Derived class for True/False answer questions
 **/
public class TrueFalseQuestion extends Question
{
    private static int count=0;
    public static ArrayList<TrueFalseQuestion> tfQnList = new ArrayList<TrueFalseQuestion>();
    public static String tfQnFilePath="tfQns.txt";
    public static Scanner keyboard = new Scanner(System.in);
    private char answer;

    public TrueFalseQuestion(String QuestionText, int Points, String Topic,char Answer){
        super(QuestionText, Points, Topic);
        answer = Answer;
        count++;
    }

    public void setAnswer(char Answer)
    {
        answer = Answer;
    }

    public char getAnswer()
    {
        return answer;
    }

    public static void populate()
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
            System.out.println("An error occurred."+e.getMessage());
        }
    }

    public static void serialize(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tfQns.ser"));
            out.writeObject(TrueFalseQuestion.tfQnList);
            out.close();

        }
        catch (NotSerializableException ex){
        }
        catch (IOException ex) {
            Globals.logException(ex);
        }
    }
    public static void deserialize(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("tfQns.ser"));
            tfQnList = (ArrayList<TrueFalseQuestion>)in.readObject();

        }
        catch (NotSerializableException ex){
        }
        catch (IOException ex) {
            Globals.logException(ex);
        } catch (ClassNotFoundException ex)
        {
            Globals.logException(ex);
        }
    }
    public static void declareInitialiseAndUpdate_NewQuestionObject() {
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
    }

    /**
    public static void askQuestion(int qnNum)
    {
        String qnTxt = tfQnList.get(qnNum).getQuestionText();
        System.out.println(qnTxt);
        System.out.println("True or False?");
        char userAnswer = keyboard.next().toUpperCase().charAt(0);
        if (userAnswer==tfQnList.get(qnNum).getAnswer())
        {
            Quiz.score++;
            System.out.println("Correct");
        }
        else
        {
            System.out.println("Wrong");
        }
    }
     **/

}//class
