import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Student Menu System
 *  * Author: Vicky Campbell. Method authors explicitly annotated
 **/
public class StudentMenu
{
   protected static void display()
   {
      Scanner keyboard = new Scanner(System.in);
      String studentMenuInput;
      int studentMenuChoice;

      Student.deserialize();
      do
      {
         System.out.println("\nWelcome to Student Menu");
         System.out.println("*************************");
         System.out.println("1. Register as new student \n2. Login as existing student \n3. Return to Main Menu\nPlease enter a selection: ");
         studentMenuInput = keyboard.nextLine();
      } while (!Main.validMenuChoice(studentMenuInput, 1, 3));
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
   //method to register new student
   protected static void studentSignUp()
   {
      Scanner keyboard = new Scanner(System.in);
      boolean validRegistration;
      boolean exit = false;
      String studentForename, studentSurname, password;
      Student studentUser = new Student();


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
               exit = Main.exitLogin();
            }
         } catch (PasswordException e)
         {
            //Handle exception
            Main.logException(e);
            System.out.println(e.getMessage());
            exit = Main.exitLogin();
            validRegistration = false;
         } catch (UsernameException e)
         {
            //Handle exception
            Main.logException(e);
            System.out.println("Error: " + e.getMessage());
            validRegistration = false;
            exit = Main.exitLogin();
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
      } else
      {
         StudentMenu.display();
      }
   }

   //method to validate login of existing student
   protected static void existingStudentLogin()
   {
      Scanner keyboard = new Scanner(System.in);
      boolean exit = false;
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
            exit = Main.exitLogin();
         }
      } while (!validLogIn && !exit);
      if (validLogIn)
      {
         QuizMenu.display();
      } else
      {
         StudentMenu.display();
      }
   }

}//class
