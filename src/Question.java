import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by V.Campbell on 27/11/2022
 * UPDATE  COMMENTS ABOUT PROGRAM HERE
 **/
abstract public class Question implements Serializable
{

   private String questionText;
   private int points;
   private String topic;
   protected static List<String> qnUniqueTopicList;

   protected Question()
   {
      questionText = "";
      points = 0;
   }

   protected Question(String QuestionText, int Points)
   {
      questionText = QuestionText;
      points = Points;
   }

   protected Question(String QuestionText, int Points, String Topic)
   {
      questionText = QuestionText;
      points = Points;
      topic = Topic;
   }

   protected void setQuestionText(String QuestionText)
   {
      questionText = QuestionText;
   }

   protected String getQuestionText()
   {
      return questionText;
   }

   protected void setPoints(int Points)
   {
      points = Points;
   }

   protected int getPoints()
   {
      return points;
   }

   protected void setTopic(String Topic)
   {
      topic = Topic;
   }

   protected String getTopic()
   {
      return topic;
   }

   protected static void populateUniqueTopics()
   {
      ArrayList<String> qnTopicList = new ArrayList<>();
      for (TrueFalseQuestion tfQn : TrueFalseQuestion.tfQnList)
      {
         qnTopicList.add(tfQn.getTopic());
      }
      for (ShortQuestion shortQn : ShortQuestion.shortQnList)
      {
         qnTopicList.add(shortQn.getTopic());
      }
      for (MultipleChoiceQuestion mcQn : MultipleChoiceQuestion.mcQnList)
      {
         qnTopicList.add(mcQn.getTopic());
      }
      qnUniqueTopicList = qnTopicList.stream().distinct().collect(Collectors.toList());
   }

}//class
