
import java.util.Scanner;

/**
 * Created by Aaron McCloskey on 25/11/2022
 * Runs the Revision Quiz package
 * Class Name: Application.java
 * Version 1.0 (first iteration)
 **/
public class Application
{
    public static Scanner keyboard = new Scanner(System.in);

    private static String passwordAttempt;
    private static int chooseNewOrExisting;
    private static int adminMenuChoice;


    public static void main(String[] args)
    {
        do
        {
            LoginOrRegister.menuPrompt();
            if (LoginOrRegister.getMainMenuChoice() == 3)
                System.out.println("See you again soon!");

            else if (LoginOrRegister.getMainMenuChoice() == 2)
            {
                System.out.println("Please enter the admin password: ");
                passwordAttempt = keyboard.next();
                    if (LoginOrRegister.isPasswordCorrect(passwordAttempt))
                    {
                        System.out.println("\nPassword is Correct!");
                        do
                        {
                            System.out.println("\nWelcome to the Admin Area");
                            System.out.println("Please enter a selection: ");
                            System.out.println("1: Show Leaderboard\n2: Add New Question\n3: Log Out");
                            adminMenuChoice = keyboard.nextInt();
                            if (adminMenuChoice == 1)
                                LeaderBoard.printLeaderboard();
                            else if (adminMenuChoice == 2)
                                Question.addQuestion();
                        } while (adminMenuChoice != 3);
                    }
                    else
                        System.out.println("Sorry that's incorrect");
            }
                 else if (LoginOrRegister.getMainMenuChoice() == 1)
                 {
                     System.out.println("Welcome Student");
                     System.out.println("Please enter a selection: \n1. New User\n2. Existing User");
                     chooseNewOrExisting = keyboard.nextInt();
                     if (chooseNewOrExisting == 1)
                     {
                        LoginOrRegister.studentRegistration();
                     }
                     else
                     {
                        LoginOrRegister.existingStudentLogin();
                     }
                     System.out.println("\nLet's begin the quiz!\n******************");
                     System.out.println("Please press ENTER to return to main menu");
                     keyboard.nextLine();;
                 }
        } while (LoginOrRegister.getMainMenuChoice() != 3);
    }//main
}//class