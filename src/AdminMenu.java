import java.util.Scanner;

/**
 * Created by V.Campbell on 01/12/2022
 * Student Menu System
 **/
public class AdminMenu
{
   private static final String ADMIN_PASSWORD = "RosaleenIsALegend";

   public static Scanner keyboard = new Scanner(System.in);
   private static String adminMenuInput;
   private static int menuChoice;
   private static boolean exit = false;

   public static void display()
   {
      Admin.populateAdminList();
      do
      {
         System.out.println("Welcome to Admin Login/Registration");
         System.out.println("*************************");
         System.out.println("1. Register as a New Teacher \n2. Login as Existing Teacher \n3. Return to Main Menu\nPlease enter a selection: ");
         adminMenuInput = keyboard.next();
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
               }
               else
               {
                 exit =exitLogin();
               }
            }while(!exit);
            Main.displayMainMenu();//return to main menu
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
      boolean validRegistration = false;
      boolean exit = false;
      String username;
      String password;
      Admin adminUser = new Admin();

      System.out.println();
      System.out.println("Welcome to Teacher Registration!");
      do
      {
         System.out.println("Please enter your C2K Username: ");
         username = keyboard.next();
         System.out.println("Please enter your password: ");
         password = keyboard.next();
         try
         {
            adminUser = new Admin(username, password);
            if (Admin.userIsUnique(adminUser.getUsername()))
            {
               validRegistration=true;
               Admin.adminList.add(adminUser);
               System.out.println("\nNew Teacher Admin created!");
               Admin.serialize();
            } else
            {
               System.out.println("Error: User is not unique. Please try again");
               exit = Globals.exitLogin();
            }
         } catch (PasswordException e)
         {
            //Handle exception
            Globals.logException(e);
            System.out.println(e.getMessage());
            exit = Globals.exitLogin();
         } catch (UsernameException e)
         {
            //Handle exception
            Globals.logException(e);
            System.out.println("Error: " + e.getMessage());
            exit = Globals.exitLogin();

         } finally
         {
            if (validRegistration)
            {
               Main.currentAdmin = adminUser;
            } else if (exit)
            {
               AdminMenu.display();
            }
         }
      } while (!validRegistration && !exit);
   }

   public static void existingAdminLogin()
   {
      boolean validLogIn = false;

      String inputUsername;
      String inputPassword;

      System.out.println();
      System.out.println("Welcome to Teacher Login");
      do
      {
         System.out.println("Please enter your username: ");
         inputUsername = keyboard.next();
         System.out.println("Please enter your password: ");
         inputPassword = keyboard.next();

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
         AdminSubMenu.display();
      } else if (exit)
      {
         display();
      }
   }

      private static boolean exitLogin () {
      String choice;
      int exitChoice;

      do
      {
         System.out.println("1. Try again \n2. Return to Main Menu");
         choice = keyboard.next();
      } while (!Globals.validMenuChoice(choice, 1, 2));
      exitChoice = Integer.parseInt(choice);
         return exitChoice != 1;
   }
   private static boolean validAdminAccess()
   {
      String phrase="";
      System.out.println("Please enter Admin access phrase:");
      phrase = keyboard.next();
      return phrase.equals(ADMIN_PASSWORD);
   }

   }//class
