import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Subclass of User for creation of student objects
 **/
public class Student extends User {
    protected static ArrayList<Student> studentList = new ArrayList<>();
    private static final String STUDENT_FILE_PATH = "students.txt";
    private static final String STUDENT_SERIALIZED = "students.ser";
    private int highestScore;

    protected Student() {
        highestScore = 0;
    }

    protected Student(String Username, String Password, int HighScore) throws PasswordException {
        super(Username, Password);
        highestScore = HighScore;
    }

    protected Student(String Forename, String Surname, String Password) throws PasswordException, UsernameException {
        super(Forename, Surname, Password);
        highestScore = 0;
    }

    protected static void fileCheck() {
        File f = new File(STUDENT_SERIALIZED);
        if (!f.exists()) {
            System.out.println("Student files were not found - backup files have been restored");
            populateStudentList();
            serialize();
        }
    }

    /*
     * Authors: Nico Sweeney-Ortiz
     * Purpose: Resets the Student high score instance variable to zero
     */
    private void resetHighScore() {
        this.highestScore = 0;
    }

    protected void setHighestScore(int HighestScore) {
        if (HighestScore > highestScore) {
            highestScore = HighestScore;
        }
    }

    /*
     * Authors: Nico Sweeney-Ortiz
     * Purpose: Gets the Student high score instance variable
     */
    protected int getHighestScore() {
        return highestScore;
    }

    protected static void populateStudentList() {
        try {
            File studentFile = new File(STUDENT_FILE_PATH);
            Scanner studentReader = new Scanner(studentFile);
            while (studentReader.hasNextLine()) {
                String Username = studentReader.nextLine();
                String Password = studentReader.nextLine();
                int HighScore = Integer.parseInt(studentReader.nextLine());

                Student std = new Student(Username, Password, HighScore);
                studentList.add(std);
            }
            studentReader.close();
        } catch (FileNotFoundException | PasswordException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }

    protected static void serialize() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("students.ser")));
            out.writeObject(studentList);
            out.close();
        } catch (IOException ex) {
            Main.logException(ex);
        }
    }

    protected static void deserialize() {
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("students.ser")));
            studentList = (ArrayList<Student>) in.readObject();

        } catch (NotSerializableException ex) {
            Main.logException(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected static boolean userIsUnique(String Username) {
        boolean isUnique = true;
        for (Student std : studentList) {
            if (std.getUsername().equals(Username)) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    /*
     * Authors: Nico Sweeney-Ortiz
     * Purpose: A static Student class method that loops through all
     *          student objects in studentList and resets the high scores
     */
    protected static void resetAllHighScores() {
        for (Student std : studentList) {
            std.resetHighScore();
        }
        System.out.println("All student Quiz high scores have been reset.");
        Student.serialize();
        System.out.println("Student profiles have been successfully saved.");
    }

}//class