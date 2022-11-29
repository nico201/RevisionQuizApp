
import java.util.Scanner;

/**
 * Created by Aaron McCloskey on 28/11/2022
 * UPDATE PROGRAM COMMENTS ABOUT PROGRAM HERE
 * Class Name: TOrF.java
 **/
public class TOrF extends Question
{
    public static Scanner keyboard = new Scanner(System.in);
    private final String CORRECT_ANSWER;


    public TOrF(String questionTopic,String questionText,String correctAnswer){
        super(questionTopic,questionText);
        this.CORRECT_ANSWER = correctAnswer;
    }

    public String getTOrFResponse()
    {
        System.out.println("Please enter 'true' or 'false': ");
        return keyboard.next();

    }

    public String toString(){
        return "TOrF question";
    }

}//class