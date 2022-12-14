import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Derived class for True/False answer questions
 * Author: Vicky Campbell. Method authors explicitly annotated
 **/
public class TrueFalseQuestion extends Question {
    protected char answer;
    private static final String TF_QN_FILE_PATH = "tfQns.txt";
    private static final String TF_QN_BACKUP_PATH = "tfQnBackup.txt";
    private static final String TF_QN_SERIALIZED = "tfQns.ser";
    private static int count = 0;
    protected static ArrayList<TrueFalseQuestion> tfQnList = new ArrayList<>();

    //parameterised constructor
    protected TrueFalseQuestion(String QuestionText, int Points, String Topic, char Answer) {
        super(QuestionText, Points, Topic);
        answer = Answer;
        count++;
    }
    //getters & setters
    protected void setAnswer(char Answer) {
        answer = Answer;
    }

    protected char getAnswer() {
        return answer;
    }

    //method to restore all true false questions from text file
    //has 2 modes
    //b - restore from most recent backup file
    //o - restore from original text file
    protected static void restoreQns(char mode)
    {
        String filePath = null;
        try
        {
            if (mode == 'b'){
                filePath = TF_QN_BACKUP_PATH;
            }
            else if(mode =='o'){
                filePath=TF_QN_FILE_PATH;
            }
            File qnFile = new File(filePath);
            Scanner qnReader = new Scanner(qnFile);
            while (qnReader.hasNextLine()) {
                String qnText = qnReader.nextLine();
                int qnPoints = Integer.parseInt(qnReader.nextLine());
                String qnTopic = qnReader.nextLine();
                char answer = qnReader.nextLine().charAt(0);
                TrueFalseQuestion qn = new TrueFalseQuestion(qnText, qnPoints, qnTopic, answer);
                tfQnList.add(qn);
            }
            qnReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }
    //method to serialize all True False questions
    protected static void serialize() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("tfQns.ser")));
            out.writeObject(TrueFalseQuestion.tfQnList);
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Main.logException(ex);
        }
    }
    //method to deserialize all True False questions
    protected static void deserialize() {
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("tfQns.ser")));
            tfQnList = (ArrayList<TrueFalseQuestion>) in.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            Main.logException(ex);
        }
    }
    //Author: David
    //Purpose: ??
    protected static void declareInitialiseAndUpdate_NewQuestionObject() {
        Scanner keyboard = new Scanner(System.in);
        TrueFalseQuestion tf1 = new TrueFalseQuestion(null, -1, null, '0');
        System.out.println("\nTrue or False Question Creation");
        System.out.println("Please enter the topic: ");
        tf1.setTopic(keyboard.nextLine().trim());
        System.out.println("Please enter the question text: ");
        tf1.setQuestionText(keyboard.nextLine().trim());
        System.out.println("Please enter the number of points available: ");
        tf1.setPoints(Integer.parseInt(keyboard.nextLine()));
        System.out.println("Please enter answer T or F: ");
        tf1.setAnswer(keyboard.nextLine().trim().charAt(0));
        System.out.println("New Question has been saved. Thank you!");
        tfQnList.add(tf1);
        serialize();
    }
    //method to write current question list to separate backup text file for later restoration if required
    protected static void backupQnsToFile() {
        try {
            FileWriter qnWriter = new FileWriter(TF_QN_BACKUP_PATH);
            int qnCount = tfQnList.size();
            for (TrueFalseQuestion tfQn : tfQnList) {
                qnWriter.write(tfQn.getQuestionText() + "\n");
                String points = Integer.toString(tfQn.getPoints());
                qnWriter.write(points + "\n");
                qnWriter.write(tfQn.getTopic() + "\n");
                if(qnCount!=1){
                    qnWriter.write(tfQn.getAnswer() + "\n");
                }
                else{
                    qnWriter.write(tfQn.getAnswer());
                }
                qnCount--;
            }
            qnWriter.flush();
            qnWriter.close();
            System.out.println("True False Question Lists have been Successfully Backed Up");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Main.logException(ex);
        }
    }
    //method to check if serialized file is present, restores it from original text file if not
    protected static void fileCheck() {
        File f = new File(TF_QN_SERIALIZED);
        if (!f.exists()) {
            System.out.println("True/False Question files were not found - backup files have been restored");
            restoreQns('o');
            serialize();
        }
    }

}//class
