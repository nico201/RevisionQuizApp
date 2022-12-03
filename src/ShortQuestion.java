
    /**
     * Created by vcamp on 29/11/2022
     * UPDATE  COMMENTS ABOUT PROGRAM HERE
     **/

    import java.util.Scanner;

    /**
     * Created by V.Campbell on 27/11/2022
     * UPDATE  COMMENTS ABOUT PROGRAM HERE
     **/
public class ShortQuestion extends Question
{
    Scanner keyboard = new Scanner(System.in);

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

    public void updateSq1() {
        ShortQuestion sq1 = new ShortQuestion(null, 0, null, null);
        System.out.println("Short Answer Question Creation");
        System.out.println("Please enter the question text: ");
        sq1.setQuestionText(keyboard.next());
        sq1.setQuestionText(keyboard.nextLine());
        System.out.println("Please enter the number of points available: ");
        sq1.setPoints(keyboard.nextInt());
        System.out.println("Please enter the topic: ");
        sq1.setTopic(keyboard.next());
        System.out.println("Please enter the correct answer");
        sq1.setAnswer(keyboard.next());
        sq1.setAnswer(keyboard.nextLine());
    }
}//class


