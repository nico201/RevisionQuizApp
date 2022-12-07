import java.util.Scanner;

/**
 * Created by V.Campbell on 01/12/2022
 * Student Menu System
 **/
public class StudentMenu {
   private static Scanner keyboard = new Scanner(System.in);
   private static String studentMenuInput;

   protected static void display() {
      Student.populateStudentList();
      int studentMenuChoice;
      do {
         System.out.println("Welcome to Student Menu");
         System.out.println("*************************");
         System.out.println("1. Register as new student \n2. Login as existing student \n3. Return to Main Menu\nPlease enter a selection: ");
         studentMenuInput = keyboard.next();
      } while (!Globals.validMenuChoice(studentMenuInput, 1, 3));
      studentMenuChoice = Integer.parseInt(studentMenuInput);
      switch (studentMenuChoice) {
         case 1:
            studentSignUp();
            break;
         case 2:
            existingStudentLogin();
            break;
         case 3:
            Main.displayMainMenu();
            break;
         default: //not really required as menu choice is validated
         {
            System.out.println("Invalid Menu Choice");
            display();
            break;
         }
      }
   }

   protected static void studentSignUp() {
      boolean validRegistration = true;
      String studentForename;
      String studentSurname;
      String password;
      Student studentUser = new Student();
      boolean exit = false;

      System.out.println("Welcome to Student Sign-Up!");
      do {
         System.out.println();
         System.out.println("Please enter your forename: ");
         studentForename = keyboard.next();
         System.out.println("Please enter your surname: ");
         studentSurname = keyboard.next();
         System.out.println("Please enter your password: ");
         password = keyboard.next();

         try {
            studentUser = new Student(studentForename, studentSurname, password);
            if (Student.userIsUnique(studentUser.getUsername())) {
               Student.studentList.add(studentUser);
               Main.currentStudent = studentUser;//set current user equal to the newly registered student
               System.out.println("\nNew user created! Username is " + studentUser.getUsername());
               Student.serialize();
            } else {
               System.out.println("Error: User is not unique.");
               validRegistration = false;
               exit = exitLogin();
            }
         } catch (PasswordException e) {
            //Handle exception
            Globals.logException(e);
            System.out.println(e.getMessage());
            exit = exitLogin();
            validRegistration = false;
         } catch (UsernameException e) {
            //Handle exception
            Globals.logException(e);
            System.out.println("Error: " + e.getMessage());
            validRegistration = false;
            exit = exitLogin();
         }
         finally {
            if (validRegistration)
            {
               Main.currentStudent = studentUser;
            }
         }
      } while (!validRegistration && !exit);
      if (validRegistration)
      {
         Quiz.run();
      } else if (exit)
      {
         StudentMenu.display();
      }
   }

   protected static void existingStudentLogin() {

      boolean validUserLoggedIn = false;
      String inputUsername;
      String inputPassword;

      System.out.println();
      System.out.println("Welcome to Student Login");
      System.out.println("Please enter your username: ");
      inputUsername = keyboard.next();
      System.out.println("Please enter your password: ");
      inputPassword = keyboard.next();

      for (Student student : Student.studentList) {
         if (student.getUsername().equals(inputUsername)) {
            if (student.getPassword().equals(inputPassword)) {
               validUserLoggedIn = true;
               Main.currentStudent = (Student)student;
               Quiz.run();
            } else {
               System.out.println("User found - password incorrect");
               exitLogin();
            }
            break;
         } else {
            System.out.println("User not found.");
            exitLogin();
         }
      }
   }

   private static boolean exitLogin() {
      String choice;
      int exitChoice;

      do {
         System.out.println("1. Try again \n2. Return to Student Menu");
         choice = keyboard.next();
      } while (!Globals.validMenuChoice(choice, 1, 2));
      exitChoice = Integer.parseInt(choice);
      if (exitChoice == 1) {
         return false;
      } else {
         return true;
      }
   }
}//class
