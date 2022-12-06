import java.util.Scanner;

/**
 * Created by V.Campbell on 01/12/2022
 * Student Menu System
 **/
public class AdminMenu
{
   public static Scanner keyboard = new Scanner(System.in);
   public static int menuChoice = 0;

   public static boolean display()
   {
      Admin.populateAdminList();
      System.out.println("** ADMIN MENU **");
      System.out.println("Please enter a selection: \n1. Register as new teacher \n2. Login as existing teacher \n3. Return to Main Menu");
      menuChoice = keyboard.nextInt();
      boolean loginSuccess = false;
      switch (menuChoice)
      {
         case 1:
            loginSuccess = teacherSignUp();
            break;
         case 2:
            loginSuccess = existingTeacherLogin();
            break;
         case 3:
            LoginOrRegister.menuPrompt();//return to main menu
            break;
         default:
         {
            System.out.println("Invalid Menu Choice");
            display();
         }
      }
      return loginSuccess;
   }

   public static boolean teacherSignUp()
   {
      boolean validRegistration = true;
      String username;
      String password;
      Admin adminUser = new Admin();

      System.out.println();
      System.out.println("Welcome to Teacher Registration!");
      System.out.println("Please enter your C2K Username: ");
      username = keyboard.next();
      System.out.println("Please enter your password: ");
      password = keyboard.next();

      try
      {
         adminUser = new Admin(username, password);
         if (adminUser.userIsUnique(adminUser.getUsername()))
         {
            Admin.adminList.add(adminUser);
            System.out.println("\nNew Teacher Admin created!");
            Admin.serialize();
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
            Main.currentUser = adminUser;
         }
      }
      {
         return validRegistration;
      }
   }

   public static boolean existingTeacherLogin()
   {
      boolean validTeacherLoggedIn = false;

      String inputUsername;
      String inputPassword;

      System.out.println();
      System.out.println("Welcome to Teacher Login");
      System.out.println("Please enter your username: ");
      inputUsername = keyboard.next();
      System.out.println("Please enter your password: ");
      inputPassword = keyboard.next();

      for (Admin adminUser : Admin.adminList)
      {
         if (adminUser.getUsername().equals(inputUsername))
         {
            if (adminUser.getPassword().equals(inputPassword))
            {
               validTeacherLoggedIn = true;
               Main.currentUser = adminUser;
            } else
            {
               System.out.println("User found - password incorrect. PLease try again");
               break;
            }
         } else
         {
            System.out.println("User not found. Please try again");
         }
      }
      return validTeacherLoggedIn;
   }

}//class
