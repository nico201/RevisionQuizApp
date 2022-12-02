import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by V.Campbell on 29/11/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
public class MultipleChoiceQuestion extends Question
{
    private static ArrayList<MultipleChoiceQuestion> mcQnList = new ArrayList<MultipleChoiceQuestion>();
    private static String multipleChoiceQnFilePath="multipleChoiceQns.txt";
    private static int count=0;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctOption;

    public MultipleChoiceQuestion(String QuestionText, int Points, String Topic, String Opt1, String Opt2, String Opt3, String Opt4, int CorrectOption){

        super(QuestionText, Points, Topic);
        option1 = Opt1;
        option2=Opt2;
        option3=Opt3;
        option4=Opt4;
        correctOption=CorrectOption;
        count++;
    }

    public void setOption1(String Option1)
    {
        option1 = Option1;
    }

    public String getOption1()
    {
        return option1;
    }

    public void setOption2(String Option2)
    {
        option2 = Option2;
    }

    public String getOption2()
    {
        return option2;
    }

    public void setOption3(String Option3)
    {
        option3 = Option3;
    }

    public String getOption3()
    {
        return option3;
    }

    public void setOption4(String Option4)
    {
        option4 = Option4;
    }

    public String getOption4()
    {
        return option4;
    }

    public void setCorrectOption(int CorrectOption) {
        correctOption = CorrectOption;
    }

    public int getCorrectOption() {
        return correctOption;
    }
    //class methods
    public static void populate()
    {
        try
        {
            File mcQnFile = new File(multipleChoiceQnFilePath);
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
    public static void serialize(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("multipleChoiceQns.ser"));
            out.writeObject(mcQnList);
            out.close();

        }
        catch (NotSerializableException ex){
            Globals.logException(ex);
        }
        catch (IOException ex) {
            Globals.logException(ex);
        }
    }
    public static void deserialize(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("multipleChoiceQns.ser"));
            mcQnList = (ArrayList<MultipleChoiceQuestion>)in.readObject();

        }
        catch (NotSerializableException ex){
            Globals.logException(ex);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
}//class
