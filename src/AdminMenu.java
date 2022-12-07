import java.util.Scanner;

/**
 * Created by V.Campbell on 01/12/2022
 * Student Menu System
 **/
public class AdminMenu {
   public static Scanner keyboard = new Scanner(System.in);
   private static String adminMenuInput;
   private static int menuChoice;

   public static void display() {
      Admin.populateAdminList();
      do {
         System.out.println("Welcome to Admin Login/Registration");
         System.out.println("*************************");
         System.out.println("1. Register as a New Teacher \n2. Login as Existing Teacher \n3. Return to Main Menu\nPlease enter a selection: ");
         adminMenuInput = keyboard.next();
      } while (!Globals.validMenuChoice(adminMenuInput, 1, 3));
      menuChoice = Integer.parseInt(adminMenuInput);


      switch (menuChoice) {
         case 1:
            teacherSignUp();
            break;
         case 2:
            existingTeacherLogin();
            break;
         case 3:
            Main.displayMainMenu();//return to main menu
            break;
         default: {
            System.out.println("Invalid Menu Choice");
            display();
         }
      }
   }

   public static void teacherSignUp() {
      boolean validRegistration = true;
      boolean exit = false;
      String username;
      String password;
      Admin adminUser = new Admin();

      System.out.println();
      System.out.println("Welcome to Teacher Registration!");
      do {
         System.out.println("Please enter your C2K Username: ");
         username = keyboard.next();
         System.out.println("Please enter your password: ");
         password = keyboard.next();


         try {
            adminUser = new Admin(username, password);
            if (Admin.userIsUnique(adminUser.getUsername())) {
               Admin.adminList.add(adminUser);
               System.out.println("\nNew Teacher Admin created!");
               Admin.serialize();
            } else {
               System.out.println("Error: User is not unique. Please try again");
               validRegistration = false;
               exit = exitLogin();
            }
         } catch (PasswordException e) {
            //Handle exception
            Globals.logException(e);
            System.out.println(e.getMessage());
            validRegistration = false;
            exit = exitLogin();
         } catch (UsernameException e) {
            //Handle exception
            Globals.logException(e);
            System.out.println("Error: " + e.getMessage());
            validRegistration = false;
            exit = exitLogin();

         } finally {
            if (validRegistration) {
               Main.currentAdmin = adminUser;
            } else if (exit) {
               AdminMenu.display();
            }
         }
      } while (!validRegistration && !exit);
   }

   public static boolean existingTeacherLogin() {
      boolean validTeacherLoggedIn = false;

      String inputUsername;
      String inputPassword;

      System.out.println();
      System.out.println("Welcome to Teacher Login");
      System.out.println("Please enter your username: ");
      inputUsername = keyboard.next();
      System.out.println("Please enter your password: ");
      inputPassword = keyboard.next();

      for (Admin adminUser : Admin.adminList) {
         if (adminUser.getUsername().equals(inputUsername)) {
            if (adminUser.getPassword().equals(inputPassword)) {
               validTeacherLoggedIn = true;
               Main.currentAdmin = adminUser;
            } else {
               System.out.println("User found - password incorrect. PLease try again");
               break;
            }
         } else {
            System.out.println("User not found. Please try again");
         }
      }
      return validTeacherLoggedIn;
   }

   private static boolean exitLogin() {
      String choice;
      int exitChoice;

      do {
         System.out.println("1. Try again \n2. Return to Admin Menu");
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
