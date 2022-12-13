import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Iterator;

/**
 * COM809: Group 5
 * Purpose: Base Question class from which TrueFalseQuestion, MultipleChoiceQuestion and ShortQuestion are all derived
 **/
abstract public class Question implements Serializable {
    private String questionText;
    private int points;
    private String topic;
    protected static List<String> qnUniqueTopicList;

    protected Question() {
        questionText = "";
        points = 0;
    }

    protected Question(String QuestionText, int Points) {
        questionText = QuestionText;
        points = Points;
    }

    protected Question(String QuestionText, int Points, String Topic) {
        questionText = QuestionText;
        points = Points;
        topic = Topic;
    }

    protected void setQuestionText(String QuestionText) {
        questionText = QuestionText;
    }

    protected String getQuestionText() {
        return questionText;
    }

    protected void setPoints(int Points) {
        points = Points;
    }

    protected int getPoints() {
        return points;
    }

    protected void setTopic(String Topic) {
        topic = Topic.toLowerCase();
    }

    protected String getTopic() {
        return topic;
    }

    protected static void resetAllQuestionBanks() {
        TrueFalseQuestion.restoreOriginalQns();
        ShortQuestion.restoreOriginalQns();
        MultipleChoiceQuestion.restoreOriginalQns();
    }

    protected static void serializeAllQuestionBanks() {
        TrueFalseQuestion.serialize();
        ShortQuestion.serialize();
        MultipleChoiceQuestion.serialize();
    }

    protected static void deserializeAllQuestionBanks() {
        TrueFalseQuestion.deserialize();
        ShortQuestion.deserialize();
        MultipleChoiceQuestion.deserialize();
    }

    /*
     * Authors: Nico Sweeney-Ortiz
     * Purpose: Populate a list of unique question topics from all questions
     *          currently in question banks to be used to display topics to user
     *          for selection of quiz topic or for topic deletion etc.
     */
    protected static void populateUniqueTopics() {
        ArrayList<String> qnTopicList = new ArrayList<>();
        for (TrueFalseQuestion tfQn : TrueFalseQuestion.tfQnList) {
            // Add all question topics from True/False bank to qnTopicList
            qnTopicList.add(tfQn.getTopic());
        }
        for (ShortQuestion shortQn : ShortQuestion.shortQnList) {
            // Add all question topics from Short bank to qnTopicList
            qnTopicList.add(shortQn.getTopic());
        }
        for (MultipleChoiceQuestion mcQn : MultipleChoiceQuestion.mcQnList) {
            // Add all question topics from Multiple Choice bank to qnTopicList
            qnTopicList.add(mcQn.getTopic());
        }
        // Populate qnUniqueTopicList only with unique topics from across all question banks
        qnUniqueTopicList = qnTopicList.stream().distinct().collect(Collectors.toList());
    }

    protected static void removeTopicQuestions(int topicPosition) {
        String removalTopic = qnUniqueTopicList.get(topicPosition);
        int qnsRemoved = 0;

        Iterator<TrueFalseQuestion> tfQnIterator = TrueFalseQuestion.tfQnList.iterator();
        while (tfQnIterator.hasNext()) {
            TrueFalseQuestion qn = tfQnIterator.next();
            if (qn.getTopic().equals(removalTopic)) {
                tfQnIterator.remove();
                qnsRemoved++;
            }
        }

        Iterator<ShortQuestion> shortQnIterator = ShortQuestion.shortQnList.iterator();
        while (shortQnIterator.hasNext()) {
            ShortQuestion qn = shortQnIterator.next();
            if (qn.getTopic().equals(removalTopic)) {
                shortQnIterator.remove();
                qnsRemoved++;
            }
        }

        Iterator<MultipleChoiceQuestion> mcQnIterator = MultipleChoiceQuestion.mcQnList.iterator();
        while (mcQnIterator.hasNext()) {
            MultipleChoiceQuestion qn = mcQnIterator.next();
            if (qn.getTopic().equals(removalTopic)) {
                mcQnIterator.remove();
                qnsRemoved++;
            }
        }
        System.out.println(qnsRemoved + " questions with topic " + removalTopic + " have now been removed");
        //Serialize all question lists after removals
        serializeAllQuestionBanks();
        System.out.println("Question banks have been updated");
    }

    protected static void backupAllQuestions() {
        TrueFalseQuestion.backupQnsToFile();
        MultipleChoiceQuestion.backupQnsToFile();
        ShortQuestion.backupQnsToFile();
    }

    protected static int chosenTopic(String selectionText) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nQuestion Topics:");
        Question.populateUniqueTopics();
        int position = 1;
        String topicChosen;
        int topicNum;
        for (String topic : Question.qnUniqueTopicList) {
            System.out.println(position + ": " + topic);
            position++;
        }
        do {
            System.out.println(selectionText);
            topicChosen = keyboard.nextLine();
        } while (!Main.validMenuChoice(topicChosen, 1, Question.qnUniqueTopicList.size()));
        topicNum = Integer.parseInt(topicChosen);
        return topicNum - 1;
    }
    protected static void checkQuestionFiles(){
        MultipleChoiceQuestion.fileCheck();
        ShortQuestion.fileCheck();
        TrueFalseQuestion.fileCheck();
    }
    protected static void resetAllQuestions(){
        Question.resetAllQuestionBanks();
        Question.serializeAllQuestionBanks();
        Question.deserializeAllQuestionBanks();
    }

}//class
