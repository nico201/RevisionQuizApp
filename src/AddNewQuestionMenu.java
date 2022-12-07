import java.util.Scanner;

/**
 * Created by V.Campbell on 01/12/2022
 * Student Menu System
 **/
public class AddNewQuestionMenu
{
    public static Scanner keyboard = new Scanner(System.in);
    private static String adminMenuInput;
    private static int menuChoice;
    private static boolean exit = false;

    public static void display()
    {
        do
        {
            System.out.println("\nWelcome to Add New Question menu\n*************************");
            System.out.println("1. Multiple Choice Question\n2. True or False Question\n3. Short Answer Question\n4. Go Back");
            System.out.println("Please enter a selection: ");
            adminMenuInput = keyboard.next();
        } while (!Globals.validMenuChoice(adminMenuInput, 1, 4));
        menuChoice = Integer.parseInt(adminMenuInput);

        switch (menuChoice) {
            case 1:
                MultipleChoiceQuestion.declareInitialiseAndUpdate_NewQuestionObject();
                //Add question to list
                //Serialize list
                break;
            case 2:
                TrueFalseQuestion.declareInitialiseAndUpdate_NewQuestionObject();
                //Add question to list
                //Serialize list
                break;
            case 3:
                ShortQuestion.declareInitialiseAndUpdate_NewQuestionObject();
                //Add question to list
                //Serialize list
                break;
            case 4:
                break;
            default:
                System.out.println("Not a valid menu choice.");
        }
    }

}//class
