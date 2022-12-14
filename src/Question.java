import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Iterator;

/**
 * COM809: Group 5
 * Purpose: Base Question class from which TrueFalseQuestion, MultipleChoiceQuestion and ShortQuestion are all derived
 * Author: Vicky Campbell. Method authors explicitly annotated
 * Question Banks Created by: David Fadeyi
 **/
abstract public class Question implements Serializable {
    private String questionText;
    private int points;
    private String topic;
    protected static List<String> qnUniqueTopicList;

    //default constructor
    protected Question() {
        questionText = "";
        points = 0;
    }
    //parameterised constructor
    protected Question(String QuestionText, int Points) {
        questionText = QuestionText;
        points = Points;
    }
    //parameterised constructor
    protected Question(String QuestionText, int Points, String Topic) {
        questionText = QuestionText;
        points = Points;
        topic = Topic;
    }
    //setters & getters
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

    //method to reset all question banks
    //has 2 modes
    //restore from most recent (b)ackup or from the (o)riginal
    protected static void resetAllQuestionBanks(char mode) {
        TrueFalseQuestion.restoreQns(mode);
        ShortQuestion.restoreQns(mode);
        MultipleChoiceQuestion.restoreQns(mode);
    }
    //method to serialize all question banks
    protected static void serializeAllQuestionBanks() {
        TrueFalseQuestion.serialize();
        ShortQuestion.serialize();
        MultipleChoiceQuestion.serialize();
    }
    //method to deserialize all question banks
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
    //method to remove all questions that match a specified topic, from all question banks
    //Iterator used to allow removal of question objects in place, while still iterating through the list
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
    //method to write all question banks to their respective backup text files, leaving original question banks intact
    protected static void backupAllQuestions() {
        TrueFalseQuestion.backupQnsToFile();
        MultipleChoiceQuestion.backupQnsToFile();
        ShortQuestion.backupQnsToFile();
    }

    //method to determine the numerical index of the selected topic
    // based on the dynamically generated list of unique topics
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
    //method to check presence of all serialized question files, required for the system to function
    //if not present they are restored from original text files
    protected static void checkQuestionFiles(){
        MultipleChoiceQuestion.fileCheck();
        ShortQuestion.fileCheck();
        TrueFalseQuestion.fileCheck();
    }
    //method to reset all question files to that of the original text file
    protected static void resetAllQuestions(){
        Question.resetAllQuestionBanks('o');
        Question.serializeAllQuestionBanks();
        Question.deserializeAllQuestionBanks();
    }
    //method to restore all question files from most recent backup
    protected static void restoreAllQnsFromLatestBackup(){
        Question.resetAllQuestionBanks('b');
        Question.serializeAllQuestionBanks();
        Question.deserializeAllQuestionBanks();
    }

}//class
