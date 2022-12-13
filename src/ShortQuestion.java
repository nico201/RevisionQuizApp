import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Derived short answer question class
 **/
public class ShortQuestion extends Question {
    //class members/variables
    private String answer;
    //static members/variables
    private static final String SHORT_QN_FILE_PATH = "shortQns.txt";
    private static final String SHORT_QN_BACKUP_PATH = "shortQnBackup.txt";
    private static int count = 0;
    protected static ArrayList<ShortQuestion> shortQnList = new ArrayList<>();


    protected ShortQuestion(String QuestionText, int Points, String Topic, String Answer) {
        super(QuestionText, Points, Topic);
        answer = Answer;
        count++;
    }

    protected void setAnswer(String Answer) {
        answer = Answer;
    }

    protected String getAnswer() {
        return answer;
    }

    protected static void restoreOriginalQns() {
        try {
            File qnFile = new File(SHORT_QN_FILE_PATH);
            Scanner qnReader = new Scanner(qnFile);
            while (qnReader.hasNextLine()) {
                String qnText = qnReader.nextLine();
                int qnPoints = Integer.parseInt(qnReader.nextLine());
                String qnTopic = qnReader.nextLine();
                String answer = qnReader.nextLine();
                ShortQuestion qn = new ShortQuestion(qnText, qnPoints, qnTopic, answer);
                shortQnList.add(qn);
            }
            qnReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred." + ex.getMessage());
            Main.logException(ex);
        }
    }

    protected static void serialize() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("shortQns.ser")));
            out.writeObject(shortQnList);
            out.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Main.logException(ex);
        }
    }

    protected static void deserialize() {
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("shortQns.ser")));
            shortQnList = (ArrayList<ShortQuestion>) in.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            Main.logException(ex);
        }
    }

    protected static void declareInitialiseAndUpdate_NewQuestionObject() {
        Scanner keyboard = new Scanner(System.in);
        ShortQuestion sq1 = new ShortQuestion(null, 0, null, null);
        System.out.println("Short Answer Question Creation");
        System.out.println("Please enter the topic: ");
        sq1.setTopic(keyboard.nextLine().trim());
        System.out.println("Please enter the question text: ");
        sq1.setQuestionText(keyboard.nextLine().trim());
        System.out.println("Please enter the number of points available: ");
        sq1.setPoints(Integer.parseInt(keyboard.nextLine()));
        System.out.println("Please enter the correct answer");
        sq1.setAnswer(keyboard.nextLine().trim());
        System.out.println("New Question has been saved. Thank you!");
        shortQnList.add(sq1);
        serialize();
    }

    protected static void backupQnsToFile() {
        try {
            FileWriter qnWriter = new FileWriter(SHORT_QN_BACKUP_PATH);
            String points;
            for (ShortQuestion shortQn : shortQnList) {
                qnWriter.write(shortQn.getQuestionText() + "\n");
                points = Integer.toString(shortQn.getPoints());
                qnWriter.write(points + "\n");
                qnWriter.write(shortQn.getTopic() + "\n");
                qnWriter.write(shortQn.getAnswer() + "\n");
            }
            qnWriter.close();
            System.out.println("Short Answer Question Lists have been Successfully Backed Up");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Main.logException(ex);
        }
    }

}//class


