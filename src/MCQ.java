

import java.util.Scanner;

/**
 * Created by Aaron McCloskey on 28/11/2022
 * UPDATE PROGRAM COMMENTS ABOUT PROGRAM HERE
 * Class Name: MCQ.java
 **/
public class MCQ extends Question
{
    public static Scanner keyboard = new Scanner(System.in);
    private final int CORRECT_ANSWER;


    public MCQ(String questionTopic,String questionText,int correctAnswer){
        super(questionTopic,questionText);
        this.CORRECT_ANSWER = correctAnswer;
    }

    public int getMCQResponse()
    {
        System.out.println("Please select a number: ");
        return keyboard.nextInt();

    }

    public String toString(){
        return "MCQ question";
    }



}//class