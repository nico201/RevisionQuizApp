
import java.util.Scanner;

/**
 * Created by Aaron McCloskey on 28/11/2022
 * UPDATE PROGRAM COMMENTS ABOUT PROGRAM HERE
 * Class Name: SW.java
 **/
public class SW extends Question
{
    public static Scanner keyboard = new Scanner(System.in);
    private final String CORRECT_ANSWER;


    public SW(String questionTopic,String questionText,String correctAnswer){
        super(questionTopic,questionText);
        this.CORRECT_ANSWER = correctAnswer;
    }

    public String getSWResponse()
    {
        System.out.println("Please enter a single word answer: ");
        return keyboard.next();

    }

    public String toString(){
        return "SW question";
    }


}//class