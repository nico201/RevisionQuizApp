import java.io.Serializable;

/**
 * Created by V.Campbell on 27/11/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
abstract public class Question implements Serializable {

    private String questionText;
    private int points;
    private String topic;

    public Question() {
        questionText = "";
        points = 0;
    }

    public Question(String QuestionText, int Points) {
        questionText = QuestionText;
        points = Points;
    }

    public Question(String QuestionText, int Points, String Topic) {
        questionText = QuestionText;
        points = Points;
        topic = Topic;
    }

    public void setQuestionText(String QuestionText) {
        questionText = QuestionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setPoints(int Points) {
        points = Points;
    }

    public int getPoints() {
        return points;
    }

    public void setTopic(String Topic) {
        topic = Topic;
    }

    public String getTopic() {
        return topic;
    }

}//class
