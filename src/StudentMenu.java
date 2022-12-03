import java.util.Scanner;

/**
 * Created by V.Campbell on 01/12/2022
 * Student Menu System
 **/
public class StudentMenu
{
   public static Scanner keyboard = new Scanner(System.in);
   public static int studentMenuChoice = 0;

   public static boolean display()
   {
      Student.populateStudentList();
      System.out.println("** STUDENT MENU **");
      System.out.println("Welcome Student");
      System.out.println("Please enter a selection: \n1. Register as new student \n2. Login as existing student \n3. Return to Main Menu");
      studentMenuChoice = keyboard.nextInt();
      switch (studentMenuChoice)
      {
         case 1:
            return studentSignUp();
         //break;
         case 2:
            return existingStudentLogin();
         //break;
         case 3:
            //TODO: Fix this mess!
            //MainMenu.display();
            //break;
         default:
         {
            System.out.println("Invalid Menu Choice");
            display();
         }
      }
      return false;
   }

    public static boolean studentSignUp()
    {
       boolean validRegistration=true;
       String studentForename;
       String studentSurname;
       String studentID;
       String password;
       Student std = new Student();

       System.out.println();
       System.out.println("Welcome to Student Sign-Up!");
       System.out.println("Please enter your forename: ");
       studentForename = keyboard.next();
       System.out.println("Please enter your surname: ");
       studentSurname = keyboard.next();
       System.out.println("Please enter your password: ");
       password = keyboard.next();

       try{
          std = new Student(studentForename,studentSurname,password);
          if (Student.userIsUnique(std.getUsername()))
          {
             Student.studentList.add(std);
             System.out.println("\nNew user created! Username is "+std.getUsername());
             Student.serialize();
          }
          else
          {
             System.out.println("User is not unique. Please try again");
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
          System.out.println(e.getMessage());
          validRegistration = false;

       }
       finally
       {
          return validRegistration;
       }

    }
    public static boolean existingStudentLogin()
    {
       boolean userFound = false;

       String inputUsername;
       String inputPassword;

       System.out.println();
       System.out.println("Welcome to Student Login");
       System.out.println("Please enter your username: ");
       inputUsername = keyboard.next();
       System.out.println("Please enter your password: ");
       inputPassword = keyboard.next();

       for (Student std : Student.studentList)
       {
          if (std.getUsername().equals(inputUsername)  && std.getPassword().equals(inputPassword))
          {
             userFound = true;
          }
       }
       return userFound;
    }
}//class
