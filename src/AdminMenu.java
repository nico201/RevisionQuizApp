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
      boolean loginSuccess =false;
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
       boolean validRegistration=true;
       String username;
       String password;
       Admin admin = new Admin();

       System.out.println();
       System.out.println("Welcome to Teacher Registration!");
       System.out.println("Please enter your C2K Username: ");
       username = keyboard.next();
       System.out.println("Please enter your password: ");
       password = keyboard.next();

       try{
          admin = new Admin(username,password);
          if (admin.userIsUnique(admin.getUsername()))
          {
             Admin.adminList.add(admin);
             System.out.println("\nNew Teacher Admin created!");
             Admin.serialize();
          }
          else
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
          System.out.println("Error: "+e.getMessage());
          validRegistration = false;

       }
       finally
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

       for (Admin adminAdmin : Admin.adminList)
       {
          if (adminAdmin.getUsername().equals(inputUsername))
          {
             if (adminAdmin.getPassword().equals(inputPassword))
             {
                validTeacherLoggedIn = true;
             }
             else {
                System.out.println("User found - password incorrect. PLease try again");
                break;
             }
          }
          else
          {
             System.out.println("User not found. Please try again");
          }
       }
       return validTeacherLoggedIn;
    }
}//class
