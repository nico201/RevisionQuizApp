import java.util.Scanner;

/**
 * Created by V.Campbell on 01/12/2022
 * Student Menu System
 **/
public class StudentMenu
{
   private static Scanner keyboard = new Scanner(System.in);
   private static int studentMenuChoice = 0;

   protected static boolean display()
   {
      Student.populateStudentList();
      System.out.println("Welcome to Student Menu");
      System.out.println("*************************");
      System.out.println("Please enter a selection: \n1. Register as new student \n2. Login as existing student \n3. Return to Main Menu");
      studentMenuChoice = keyboard.nextInt();
      boolean loginSuccess = false;
      switch (studentMenuChoice)
      {
         case 1:
            loginSuccess = studentSignUp();
            break;
         case 2:
            loginSuccess = existingStudentLogin();
            break;
         case 3:
            LoginOrRegister.menuPrompt();//return to main menu
            break;
         default:
         {
            System.out.println("Invalid Menu Choice");
            display();
            break;
         }
      }
      return loginSuccess;
   }

   protected static boolean studentSignUp()
   {
      boolean validRegistration = true;
      String studentForename;
      String studentSurname;
      String password;
      Student studentUser = new Student();

      System.out.println();
      System.out.println("Welcome to Student Sign-Up!");
      System.out.println("Please enter your forename: ");
      studentForename = keyboard.next();
      System.out.println("Please enter your surname: ");
      studentSurname = keyboard.next();
      System.out.println("Please enter your password: ");
      password = keyboard.next();

      try
      {
         studentUser = new Student(studentForename, studentSurname, password);
         if (Student.userIsUnique(studentUser.getUsername()))
         {
            Student.studentList.add(studentUser);
            System.out.println("\nNew user created! Username is " + studentUser.getUsername());
            Student.serialize();
         } else
         {
            System.out.println("Error: User is not unique. Please try again");
            validRegistration = false;
         }
      } catch (PasswordException e)
      {
         //Handle exception
         Globals.logException(e);
         System.out.println(e.getMessage());
         validRegistration = false;
      } catch (UsernameException e)
      {
         //Handle exception
         Globals.logException(e);
         System.out.println("Error: " + e.getMessage());
         validRegistration = false;

      } finally
      {
         if (validRegistration)
         {
            Main.currentUser = studentUser;
         }
      }
      {
         return validRegistration;
      }
   }

   protected static boolean existingStudentLogin()
   {
      boolean validUserLoggedIn = false;

      String inputUsername;
      String inputPassword;

      System.out.println();
      System.out.println("Welcome to Student Login");
      System.out.println("Please enter your username: ");
      inputUsername = keyboard.next();
      System.out.println("Please enter your password: ");
      inputPassword = keyboard.next();

      for (Student studentUser : Student.studentList)
      {
         if (studentUser.getUsername().equals(inputUsername))
         {
            if (studentUser.getPassword().equals(inputPassword))
            {
               validUserLoggedIn = true;
               Main.currentUser = studentUser;
            } else
            {
               System.out.println("User found - password incorrect. Please try again");
               break;
            }
         } else
         {
            System.out.println("User not found. Please try again");
         }
      }
      return validUserLoggedIn;
   }

}//class
