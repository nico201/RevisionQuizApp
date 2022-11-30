
    /**
     * Created by vcamp on 29/11/2022
     * UPDATE  COMMENTS ABOUT PROGRAM HERE
     **/
    /**
     * Created by V.Campbell on 27/11/2022
     * UPDATE  COMMENTS ABOUT PROGRAM HERE
     **/
public class ShortQuestion extends Question
{

    private String answer;

    public ShortQuestion(String QuestionText, int Points, String Topic, String Answer)
    {
         super(QuestionText, Points, Topic);
         answer = Answer;
    }

    public void setAnswer(String Answer)
    {
            answer = Answer;
    }

    public String getAnswer()
    {
            return answer;
    }
}//class


