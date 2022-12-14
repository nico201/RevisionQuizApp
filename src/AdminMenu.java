import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Menu for Admins (Login/Registration)
 * Author: Vicky Campbell. Method authors explicitly annotated
 **/
public class AdminMenu
{
   private static boolean exit = false;
   private static boolean validRegistration = false;

   public static void display()
   {
      Scanner keyboard = new Scanner(System.in);
      String adminMenuInput;
      int menuChoice;

      do
      {
         System.out.println("\nWelcome to Admin Login/Registration");
         System.out.println("*************************");
         System.out.println("1. Register as a New Teacher \n2. Login as Existing Teacher \n3. Return to Main Menu\nPlease enter a selection: ");
         adminMenuInput = keyboard.nextLine();
      } while (!Main.validMenuChoice(adminMenuInput,1, 3));
      menuChoice = Integer.parseInt(adminMenuInput);

      char adminType;
      switch (menuChoice)
      {
         case 1:
            do
            {
               adminType = validAdminAccess();
               if (adminType == 'a' || adminType == 's')
               {
                  adminSignUp(adminType);
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
   //method to register new admin
   public static void adminSignUp(char adminType)
   {
      Scanner keyboard = new Scanner(System.in);
      validRegistration = false;
      boolean signUpExit = false;
      String username;
      String password;
      Admin adminUser = new Admin();

      System.out.println("\nWelcome to Admin Registration!");
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
               //if new admin is a superadmin, they can assign rights to the new account
               if (adminType == 's')
               {
                  adminUser.assignRights();
               }
               Admin.adminList.add(adminUser);
               System.out.println("\nNew Teacher Admin created!");
               Admin.serialize();
            } else
            {
               System.out.println("Error: User is not unique. Please try again");
               signUpExit = Main.exitLogin();
            }
         } catch (PasswordException e)
         {
            //Handle exception
            Main.logException(e);
            System.out.println(e.getMessage());
            signUpExit = Main.exitLogin();
         } catch (UsernameException e)
         {
            //Handle exception
            Main.logException(e);
            System.out.println("Error: " + e.getMessage());
            signUpExit = Main.exitLogin();

         } finally
         {
            if (validRegistration)
            {
               Main.currentAdmin = adminUser;
               AdminSubMenu.display();
            } else if (signUpExit)
            {
               AdminMenu.display();
            }
         }
      } while (!validRegistration && !signUpExit);
   }

   //method to validate login of existing admin
   public static void existingAdminLogin()
   {
      Scanner keyboard = new Scanner(System.in);
      boolean validLogIn = false;

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
            exit = Main.exitLogin();
         }
      } while (!validLogIn && !exit);
      if (validLogIn)
      {
         System.out.println("\nAdmin Logged In");
         AdminSubMenu.display();
      } else if (exit)
      {
         display();
      }
   }
   //method to allow user to try again/exit menu
   private static boolean exitLogin()
   {
      Scanner keyboard = new Scanner(System.in);
      String choice;
      int exitChoice;

      do
      {
         System.out.println("\n1. Try again \n2. Return to Main Menu");
         choice = keyboard.nextLine();
      } while (!Main.validMenuChoice(choice,1, 2));
      exitChoice = Integer.parseInt(choice);
      return exitChoice != 1;
   }
   //method to determine whether a valid admin passphrase has been entered
   //determines user type based on the passphrase entered
   private static char validAdminAccess()
   {
      Scanner keyboard = new Scanner(System.in);
      String phrase;
      System.out.println("\nPlease enter Admin access phrase:");
      phrase = keyboard.nextLine().trim();

      if (phrase.equals(Admin.ADMIN_PASSPHRASE))
      {
         return 'a';
      } else if (Admin.validSuperAdmin(phrase))
      {
         return 's';
      } else
      {
         return 'f';
      }

   }

}//class
