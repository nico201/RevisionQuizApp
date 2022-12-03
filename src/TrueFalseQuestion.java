import java.util.Scanner;

/**
 * Created by vcamp on 29/11/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
public class TrueFalseQuestion extends Question
{
    Scanner keyboard = new Scanner(System.in);

    private char answer;

    public TrueFalseQuestion(String QuestionText, int Points, String Topic,char Answer){
        super(QuestionText, Points, Topic);
        answer = Answer;
    }

    public void setAnswer(char Answer)
    {
        answer = Answer;
    }

    public char getAnswer()
    {
        return answer;
    }

    public void updateTf1() {
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
    }
}//class
