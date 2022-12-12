import java.time.LocalDateTime;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Created by V.Campbell on 27/11/2022
 * Class created with several static methods with global scope
 * Contains general purpose methods not specific to any non-application classes
 **/
public class Globals {
    private static final Scanner keyboard = new Scanner(System.in);

    //populate all question arrayLists
    protected static void populateAllQuestions() {
        //Question.resetAllQuestionBanks();
        //Question.serializeAllQuestionBanks();
        Question.deserializeAllQuestionBanks();
    }

    //custom method to log exceptions to file
    //values are CSV so that they are suitable for export and further analysis by developer/tester
    protected static void logException(Exception ex) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateAndTIme = dtf.format(now);
        try {
            File errorFile = new File("errorLog.txt");
            FileWriter fw = new FileWriter(errorFile, true);
            fw.write("\n" + currentDateAndTIme + ", " + ex.getMessage());
            fw.close();
        } catch (Exception e) {
            System.out.println(ex.getMessage());
        }
    }

    //custom method to verify if input string is a valid integer
    public static boolean TryParseInt(String inputString) {

        boolean validInt = true;
        for (int i = 0; i < inputString.length(); i++) {
            if (!Character.isDigit(inputString.charAt(i))) {
                validInt = false;
                break;
            }
        }
        return validInt;
    }

    //custom method to validate if input sting is a valid integer and in the specified range of menu choices available
    //can also be reused for any range not, just menu choices
    public static boolean validMenuChoice(String inputString, int min, int max) {
        boolean isValidMenuChoice = false;
        int menuChoice;
        if (Globals.TryParseInt(inputString)) {
            menuChoice = Integer.parseInt(inputString);
            if (menuChoice >= min && menuChoice <= max) {
                isValidMenuChoice = true;
            } else {
                System.out.println("Menu choice out of range. Please try again!");
            }
        } else {
            System.out.println("Not a valid integer. Please try again!");
        }
        return isValidMenuChoice;
    }

    //simple method to prompt user to retry/exit
    protected static boolean exitLogin() {
        String choice;
        int exitChoice;

        do {
            System.out.println("1. Try again \n2. Return to Previous Menu");
            choice = keyboard.next();
        } while (!Globals.validMenuChoice(choice, 1, 2));
        exitChoice = Integer.parseInt(choice);
        return exitChoice != 1;
    }
}//class