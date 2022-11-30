/**
 * Created by vcamp on 29/11/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
public class MultipleChoiceQuestion extends Question
{

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
}//class
