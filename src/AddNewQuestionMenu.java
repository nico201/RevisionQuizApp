import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Sub-menu for adding new questions
 Author: Vicky Campbell. Method authors explicitly annotated
 **/
public class AddNewQuestionMenu {

    public static void display() {
        // Initialise method variables
        Scanner keyboard = new Scanner(System.in);
        String adminMenuInput;
        boolean exit;
        int menuChoice;
        do {
            System.out.println("\nWelcome to Add New Question menu\n*************************");
            System.out.println("1. Multiple Choice Question\n2. True or False Question\n3. Short Answer Question\n4. Go back to Admin Menu");
            System.out.println("Please enter a selection: ");
            adminMenuInput = keyboard.nextLine();
        } while (!Main.validMenuChoice(adminMenuInput,1,4));
        menuChoice = Integer.parseInt(adminMenuInput);

        switch (menuChoice) {
            case 1:
                MultipleChoiceQuestion.declareInitialiseAndUpdate_NewQuestionObject();
                break;
            case 2:
                TrueFalseQuestion.declareInitialiseAndUpdate_NewQuestionObject();
                break;
            case 3:
                ShortQuestion.declareInitialiseAndUpdate_NewQuestionObject();
                break;
            case 4:
                AdminSubMenu.display();
                break;
            default:
                System.out.println("Not a valid menu choice.");
        }
        exit = !moreQuestions();
        if (exit) {
            AdminSubMenu.display();
        } else {
            AddNewQuestionMenu.display();
        }
    }
    //method to determine if more questions are to be input
    private static boolean moreQuestions() {
        Scanner keyboard = new Scanner(System.in);
        String choice;
        int questionChoice;

        do {
            System.out.println("\n1. Add another question \n2. Return to Main Menu");
            choice = keyboard.nextLine();
        } while (!Main.validMenuChoice(choice,1,2));
        questionChoice = Integer.parseInt(choice);
        return (questionChoice == 1);
    }

}//class
