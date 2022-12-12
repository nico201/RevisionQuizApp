import java.util.Scanner;

/**
 * Created by V.Campbell on 01/12/2022
 * Student Menu System
 **/
public class AdminMenu
{
   private static final String ADMIN_PASSWORD = "RosaleenIsALegend";

   public static void display()
   {
      Scanner keyboard = new Scanner(System.in);
      boolean validRegistration = false;
      boolean exit = false;
      String adminMenuInput;
      int menuChoice;
      Student.deserialize();
      Admin.deserialize();
      Question.resetAllQuestionBanks();
      Question.serializeAllQuestionBanks();
      Question.deserializeAllQuestionBanks();
      do
      {
         System.out.println("\nWelcome to Admin Login/Registration");
         System.out.println("*************************");
         System.out.println("1. Register as a New Teacher \n2. Login as Existing Teacher \n3. Return to Main Menu\nPlease enter a selection: ");
         adminMenuInput = keyboard.nextLine();
      } while (!Globals.validMenuChoice(adminMenuInput, 1, 3));
      menuChoice = Integer.parseInt(adminMenuInput);

      switch (menuChoice)
      {
         case 1:
            do
            {
               if (validAdminAccess())//correct passphrase
               {
                  adminSignUp();
               } else
               {
                  exit = exitLogin();
               }
            } while (!exit && !validRegistration);
            //return to main menu
            Main.displayMainMenu();
            break;
         case 2:
            existingAdminLogin();
            break;
         case 3:
            Main.displayMainMenu();//return to main menu
            break;
         default:
         {
            System.out.println("Invalid Menu Choice");
            display();
         }
      }
   }

   public static void adminSignUp()
   {
      Scanner keyboard = new Scanner(System.in);
      boolean validRegistration = false;
      boolean signUpExit = false;
      String username;
      String password;
      Admin adminUser = new Admin();

      System.out.println("\nWelcome to Teacher Registration!");
      do
      {
         System.out.println("\nPlease enter your C2K Username: ");
         username = keyboard.nextLine().trim();
         System.out.println("Please enter your password: ");
         password = keyboard.nextLine().trim();
         try
         {
            adminUser = new Admin(username, password);
            if (Admin.userIsUnique(adminUser.getUsername()))
            {
               validRegistration = true;
               Admin.adminList.add(adminUser);
               System.out.println("\nNew Teacher Admin created!");
               Admin.serialize();
            } else
            {
               System.out.println("Error: User is not unique. Please try again");
               signUpExit = Globals.exitLogin();
            }
         } catch (PasswordException e)
         {
            //Handle exception
            Globals.logException(e);
            System.out.println(e.getMessage());
            signUpExit = Globals.exitLogin();
         } finally
         {
            if (validRegistration)
            {
               Main.currentAdmin = adminUser;
            } else if (signUpExit)
            {
               AdminMenu.display();
            }
         }
      } while (!validRegistration && !signUpExit);
   }

   public static void existingAdminLogin()
   {
      Scanner keyboard = new Scanner(System.in);
      boolean validLogIn = false;
      boolean exit = false;
      String inputUsername;
      String inputPassword;

      System.out.println();
      System.out.println("\nWelcome to Teacher Login");
      do
      {
         System.out.println("\nPlease enter your username: ");
         inputUsername = keyboard.nextLine().trim();
         System.out.println("Please enter your password: ");
         inputPassword = keyboard.nextLine().trim();

         for (Admin adminUser : Admin.adminList)
         {
            if (adminUser.getUsername().equals(inputUsername) && adminUser.getPassword().equals(inputPassword))
            {
               validLogIn = true;
               Main.currentAdmin = adminUser;
               break;
            }
         }
         if (!validLogIn)
         {
            System.out.println("Username or Password incorrect");
            exit = Globals.exitLogin();
         }
      } while (!validLogIn && !exit);
      if (validLogIn)
      {
         System.out.println("\nAdmin Logged In");
         AdminSubMenu.display();
      } else
      {
         display();
      }
   }

   private static boolean exitLogin()
   {
      Scanner keyboard = new Scanner(System.in);
      String choice;
      int exitChoice;

      do
      {
         System.out.println("\n1. Try again \n2. Return to Main Menu");
         choice = keyboard.nextLine();
      } while (!Globals.validMenuChoice(choice, 1, 2));
      exitChoice = Integer.parseInt(choice);
      return exitChoice != 1;
   }

   private static boolean validAdminAccess()
   {
      Scanner keyboard = new Scanner(System.in);
      String phrase;

      System.out.println("\nPlease enter Admin access phrase:");
      phrase = keyboard.nextLine().trim();
      return phrase.equals(ADMIN_PASSWORD);
   }

}//class
