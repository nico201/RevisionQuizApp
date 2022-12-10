import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by V.Campbell on 03/12/2022
 * Extends User class to create a Teacher profile;
 **/
public class Admin extends User
{
   private boolean canDeleteQuestions;
   private boolean canDeleteStudents;
   private boolean canResetScore;

   protected static ArrayList<Admin> adminList = new ArrayList<>();
   private static final String ADMIN_FILE_PATH = "admins.txt";

   private static Scanner keyboard = new Scanner(System.in);

   protected Admin()
   {
      super();
      canDeleteQuestions = false;
      canDeleteStudents = false;
      canResetScore = false;
   }

   protected Admin(String Username, String Password) throws PasswordException, UsernameException
   {
      super(Username, Password);
      canDeleteQuestions = false;
      canDeleteStudents = false;
      canResetScore = false;
   }

   protected Admin(String Username, String Password, boolean DeleteQns, boolean DeleteStudents, boolean ResetScore) throws PasswordException, UsernameException
   {
      super(Username, Password);
      canDeleteQuestions = DeleteQns;
      canDeleteStudents = DeleteStudents;
      canResetScore = ResetScore;
   }

   public String toString()
   {
      return ("Admin username: " + getUsername() + "\n Admin Rights:" + "\nCan Delete Questions: " + canDeleteQuestions + "\nCan Delete Students: " + canDeleteStudents + "\nCan Reset Quiz Scores: " + canResetScore);
   }

   private void assignRights()
   {
      String delQns;
      String delStudents;
      String resetScore;

      System.out.println("Can admin delete questions? Y or N");
      delQns = keyboard.next().toLowerCase();

      System.out.println("Can admin delete students? Y or N");
      delStudents = keyboard.next().toLowerCase();

      System.out.println("Can admin reset scores? Y or N");
      resetScore = keyboard.next().toLowerCase();

      if (delQns.equalsIgnoreCase("y"))
      {
         canDeleteQuestions = true;
      }
      if (delStudents.equalsIgnoreCase("y"))
      {
         canDeleteStudents = true;
      }
      if (resetScore.equalsIgnoreCase("y"))
      {
         canResetScore = true;
      }
   }

   private void setUsername(String Username) throws UsernameException
   {
      String regex = "^[a-z]{2,}[0-9]{3}$";
      boolean rightFormat = Username.matches(regex);

      if (Username.length() >= 4) //at least 1 letter and 3 numbers = 5 characters as per regex
      {
         if (rightFormat)
         {
            username = Username;
         } else
         {
            throw new UsernameException("Username doesn't match format expected. Should be 2+ letters and 3 numbers");
         }
      } else
      {
         throw new UsernameException("Username doesn't meet minimum length requirements (5 characters)");
      }
   }

   protected static void populateAdminList()
   {
      try
      {
         File teacherFile = new File(ADMIN_FILE_PATH);
         Scanner adminReader = new Scanner(teacherFile);
         while (adminReader.hasNextLine())
         {
            String Username = adminReader.nextLine();
            String Password = adminReader.nextLine();
            boolean DeleteQuestions = Boolean.parseBoolean(adminReader.nextLine());
            boolean DeleteStudents = Boolean.parseBoolean(adminReader.nextLine());
            boolean ResetScore = Boolean.parseBoolean(adminReader.nextLine());
            Admin admin = new Admin(Username, Password, DeleteQuestions, DeleteStudents, ResetScore);
            adminList.add(admin);
         }
         adminReader.close();
      } catch (FileNotFoundException | PasswordException | UsernameException e)
      {
         Globals.logException(e);
         System.out.println("An error occurred." + e.getMessage());
      }
   }

   protected static void serialize()
   {
      try
      {
         ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("admin.ser")));
         out.writeObject(adminList);
         out.close();
      } catch (NotSerializableException ex)
      {
         Globals.logException(ex);
      } catch (IOException ex)
      {
         Globals.logException(ex);
      }
   }

   protected static void deserialize()
   {
      try
      {
         ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("admin.ser")));
         adminList = (ArrayList<Admin>) in.readObject();
      } catch (NotSerializableException ex)
      {
         Globals.logException(ex);
      } catch (IOException ex)
      {
         ex.printStackTrace();
      } catch (ClassNotFoundException e)
      {
         throw new RuntimeException(e);
      }
   }

   protected static boolean userIsUnique(String Username)
   {
      boolean isUnique = true;
      for (Admin admin : adminList)
      {
         if (admin.getUsername().equals(Username))
         {
            isUnique = false;
            break;
         }
      }
      return isUnique;
   }

}//class