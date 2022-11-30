/**
 * Created by vcamp on 29/11/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
public class TrueFalseQuestion extends Question
{

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
}//class
