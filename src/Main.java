import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Startup screen & Main menu
 **/

public class Main
{
   protected static Student currentStudent;
   protected static Admin currentAdmin;

   public static void main(String[] args)
   {
      performSystemCheck();//if serialized files are not found they are automatically restored from the original text files
      //deserialize all files
      User.deserializeAllUsers();
      Question.deserializeAllQuestionBanks();
      Main.displayMainMenu();
   }

   public static void displayMainMenu()
   {
      Scanner keyboard = new Scanner(System.in);
      String mainMenuChoiceInput;
      int menuChoice;

      currentStudent = null;
      currentAdmin = null;

      do
      {
         System.out.println("\n******* CCEA GCSE Digital Technology - Unit 1 Revision Quiz *******");
         System.out.println("\nWelcome to the Main Menu\n*************************\n1. Student\n2. Admin\n3. Quit\nPlease enter your selection: ");

         mainMenuChoiceInput = keyboard.next();

      } while (!validMenuChoice(mainMenuChoiceInput, 1, 3));

      menuChoice = Integer.parseInt(mainMenuChoiceInput);

      if (menuChoice == 2)//option 2  - Admin login/registration
      {
         AdminMenu.display();
         //Once Admin has logged in successfully. Display Teacher Admin Submenu
      } else if (menuChoice == 1)//option 1 - Student Registration via Student Menu
      {
         StudentMenu.display();
      } else
      {
         System.out.println("See you again soon!");
         Admin.serialize();
         Student.serialize();
         Question.serializeAllQuestionBanks();
         System.exit(0);
      }
   }//main

   //populate all question arrayLists
   protected static void populateAllQuestions() {
       Question.deserializeAllQuestionBanks();
   }

   //simple method to prompt user to retry/exit
   protected static boolean exitLogin() {
       Scanner keyboard = new Scanner(System.in);
       String choice;
       int exitChoice;

       do {
           System.out.println("1. Try again \n2. Return to Previous Menu");
           choice = keyboard.next();
       } while (!validMenuChoice(choice, 1, 2));
       exitChoice = Integer.parseInt(choice);
       return exitChoice != 1;
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
   protected static boolean TryParseInt(String inputString) {

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
   protected static boolean validMenuChoice(String inputString, int min, int max) {
       boolean isValidMenuChoice = false;
       int menuChoice;
       if(!inputString.equals(null)){
          if (TryParseInt(inputString)) {
             menuChoice = Integer.parseInt(inputString);
             if (menuChoice >= min && menuChoice <= max) {
                isValidMenuChoice = true;
             } else {
                System.out.println("Menu choice out of range. Please try again!");
             }
          } else {
             System.out.println("Not a valid integer. Please try again!");
          }
       }
       return isValidMenuChoice;
   }

   protected static void performSystemCheck(){
      //check presence of user files
      User.checkUserFiles();
      //check presence of question files
      Question.checkQuestionFiles();
   }

}//class