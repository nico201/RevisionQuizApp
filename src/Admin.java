import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.security.MessageDigest;

/**
 * COM809: Group 5
 * Purpose: Extends User class to create an Admin profile;
 **/
public class Admin extends User
{
   private boolean isSuperAdmin;
   private boolean canDeleteTopics;
   private boolean canResetQuestionBanks;
   private boolean canResetScores;

   protected static ArrayList<Admin> adminList = new ArrayList<>();
   private static final String ADMIN_FILE_PATH = "admins.txt";
   protected static final String ADMIN_PASSPHRASE = "RosaleenIsALegend";
   private static final String SUPER_ADMIN_HASH = "0b28a5799a32c687dad2c5183718ceac";

   protected Admin()
   {
      super();
      isSuperAdmin = false;
      canDeleteTopics = false;
      canResetQuestionBanks = false;
      canResetScores = false;
   }

   protected Admin(String Username, String Password) throws PasswordException, UsernameException
   {
      super(Username, Password);
      isSuperAdmin = false;
      canDeleteTopics = false;
      canResetQuestionBanks = false;
      canResetScores = false;
   }

   protected Admin(String Username, String Password, boolean superAdmin, boolean DeleteTopics, boolean ResetQuestions, boolean ResetScore) throws PasswordException, UsernameException
   {
      super(Username, Password);
      isSuperAdmin = superAdmin;
      canDeleteTopics = DeleteTopics;
      canResetQuestionBanks = ResetQuestions;
      canResetScores = ResetScore;
   }

   public String toString()
   {
      return ("Admin username: " + getUsername() + "\nAdmin Rights:" + "\nCan Delete Topics: " + canDeleteTopics + "\nCan Reset Question Banks: " + canResetQuestionBanks + "\nCan Reset Quiz Scores: " + canResetScores);
   }

   protected void assignRights()
   {
      Scanner keyboard = new Scanner(System.in);
      String superAdm;
      String delQns;
      String delStudents;
      String resetScore;

      System.out.println("Is this user a Super Admin? Y or N");
      superAdm = keyboard.next().trim().toLowerCase().substring(0, 1);
      if (superAdm.equalsIgnoreCase("y"))
      {
         isSuperAdmin = true;
         canDeleteTopics = true;
         canResetQuestionBanks = true;
         canResetScores = true;
      }
      else {
         System.out.println("Can admin delete questions? Y or N");
         delQns = keyboard.next().trim().toLowerCase().substring(0, 1);
         System.out.println("Can admin delete students? Y or N");
         delStudents = keyboard.next().trim().toLowerCase().substring(0, 1);
         System.out.println("Can admin reset scores? Y or N");
         resetScore = keyboard.next().trim().toLowerCase().substring(0, 1);

         isSuperAdmin=false;
         if (delQns.equalsIgnoreCase("y"))
         {
            canDeleteTopics = true;
         }
         if (delStudents.equalsIgnoreCase("y"))
         {
            canResetQuestionBanks = true;
         }
         if (resetScore.equalsIgnoreCase("y"))
         {
            canResetScores = true;
         }
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

   private void setSuperAdmin(boolean superAdmin)
   {
      isSuperAdmin = superAdmin;
   }

   public boolean isSuperAdmin()
   {
      return isSuperAdmin;
   }

   private void setCanDeleteTopics(boolean deleteTopics)
   {
      canDeleteTopics = deleteTopics;
   }

   public boolean getCanDeleteTopics()
   {
      return canDeleteTopics;
   }

   private void setCanResetQuestionBanks(boolean deleteStudents)
   {
      canResetQuestionBanks = deleteStudents;
   }

   public boolean getCanResetQuestionBanks()
   {
      return canResetQuestionBanks;
   }

   private void setCanResetScores(boolean resetScore)
   {
      canResetScores = resetScore;
   }

   public boolean getCanResetScores()
   {
      return canResetScores;
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

            boolean SuperAdmin = Boolean.parseBoolean(adminReader.nextLine());
            boolean DeleteTopics = Boolean.parseBoolean(adminReader.nextLine());
            boolean ResetQuestions = Boolean.parseBoolean(adminReader.nextLine());
            boolean ResetScore = Boolean.parseBoolean(adminReader.nextLine());
            Admin admin = new Admin(Username, Password, SuperAdmin, DeleteTopics, ResetQuestions, ResetScore);
            adminList.add(admin);
         }
         adminReader.close();
      } catch (FileNotFoundException | PasswordException | UsernameException e)
      {
         Main.logException(e);
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
      } catch (IOException ex)
      {
         Main.logException(ex);
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
         Main.logException(ex);
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

   protected static boolean validSuperAdmin(String inputString)
   {
      byte[] msg = inputString.getBytes();

      byte[] hash = null;
      try
      {
         MessageDigest md = MessageDigest.getInstance("MD5");
         hash = md.digest(msg);
      } catch (NoSuchAlgorithmException ex)
      {
         Main.logException(ex);
      }
      StringBuilder strBuilder = new StringBuilder();
      for (byte b : hash)
      {
         strBuilder.append(String.format("%02x", b));
      }
      String strHash = strBuilder.toString();

      return strHash.equals(Admin.SUPER_ADMIN_HASH);

   }

   protected static void viewAllAdminPermissions()
   {
      for (Admin adm : adminList)
      {
         String details = adm.toString();
         System.out.println(details + "\n");
      }
   }

}//class