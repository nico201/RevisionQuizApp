/**
 * Created by Aaron McCloskey on 27/11/2022
 * UPDATE PROGRAM COMMENTS ABOUT PROGRAM HERE
 * Class Name: LoginOrRegister.java
 **/
public class LoginOrRegister {

    protected static void quitMessage() {
        System.out.println("See you again soon!");
    }

    protected static void printSuccessfulLogin_AdminAreaMenu() {
        System.out.println("\nTeacher Logged In");
        System.out.println("Admin Area\n*************************");
        System.out.println("1: Show Leaderboard\n2: Add New Question\n3: Log Out");
        System.out.println("Please enter a selection: ");

    }

    protected static void printAddNewQuestionMenu() {
        System.out.println("\nWelcome to Add New Question menu\n*************************");
        System.out.println("1. Multiple Choice Question\n2. True or False Question\n3. Short Answer Question\n4. Go Back");
        System.out.println("Please enter a selection: ");
    }

}//class