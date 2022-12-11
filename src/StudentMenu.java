import java.util.Scanner;

/**
 * Created by V.Campbell on 01/12/2022
 * Student Menu System
 **/
public class StudentMenu
{
   private static String studentMenuInput;
   private static boolean exit = false;

   protected static void display()
   {
      Scanner keyboard = new Scanner(System.in);

      Student.deserialize();
      int studentMenuChoice;
      do
      {
         System.out.println("\nWelcome to Student Menu");
         System.out.println("*************************");
         System.out.println("1. Register as new student \n2. Login as existing student \n3. Return to Main Menu\nPlease enter a selection: ");
         studentMenuInput = keyboard.nextLine();
      } while (!Globals.validMenuChoice(studentMenuInput, 1, 3));
      studentMenuChoice = Integer.parseInt(studentMenuInput);
      switch (studentMenuChoice)
      {
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

   protected static void studentSignUp()
   {
      Scanner keyboard = new Scanner(System.in);
      boolean validRegistration;
      String studentForename;
      String studentSurname;
      String password;
      Student studentUser = new Student();
      exit = false;

      System.out.println("\nWelcome to Student Sign-Up!");
      do
      {
         validRegistration = true;
         System.out.println("Please enter your forename: ");
         studentForename = keyboard.nextLine().trim();
         System.out.println("Please enter your surname: ");
         studentSurname = keyboard.nextLine().trim();
         System.out.println("Please enter your password: ");
         password = keyboard.nextLine().trim();

         try
         {
            studentUser = new Student(studentForename, studentSurname, password);
            if (Student.userIsUnique(studentUser.getUsername()))
            {
               Student.studentList.add(studentUser);
               Main.currentStudent = studentUser;//set current user equal to the newly registered student
               System.out.println("\nNew user created! Username is " + studentUser.getUsername());
               Student.serialize();
            } else
            {
               System.out.println("Error: User is not unique.");
               validRegistration = false;
               exit = Globals.exitLogin();
            }
         } catch (PasswordException e)
         {
            //Handle exception
            Globals.logException(e);
            System.out.println(e.getMessage());
            exit = Globals.exitLogin();
            validRegistration = false;
         } catch (UsernameException e)
         {
            //Handle exception
            Globals.logException(e);
            System.out.println("Error: " + e.getMessage());
            validRegistration = false;
            exit = Globals.exitLogin();
         } finally
         {
            if (validRegistration)
            {
               Main.currentStudent = studentUser;
            }
         }
      } while (!validRegistration && !exit);
      if (validRegistration)
      {
         QuizMenu.display();
      } else if (exit)
      {
         StudentMenu.display();
      }
   }

   protected static void existingStudentLogin()
   {
      Scanner keyboard = new Scanner(System.in);
      exit = false;
      boolean validLogIn = false;
      String inputUsername;
      String inputPassword;

      System.out.println();
      System.out.println("\nWelcome to Student Login");
      do
      {
         System.out.println("\nPlease enter your username: ");
         inputUsername = keyboard.nextLine().trim();
         System.out.println("Please enter your password: ");
         inputPassword = keyboard.nextLine().trim();

         for (Student student : Student.studentList)
         {
            if (student.getUsername().equals(inputUsername) && student.getPassword().equals(inputPassword))
            {
               validLogIn = true;
               Main.currentStudent = student;
               break;
            }
         }
         if (!validLogIn)
         {
            System.out.println("\nUsername or Password incorrect");
            exit = Globals.exitLogin();
         }
      } while (!validLogIn && !exit);
      if (validLogIn)
      {
         QuizMenu.display();
      } else if (exit)
      {
         StudentMenu.display();
      }
   }

}//class
