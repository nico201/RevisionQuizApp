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
 * Author: Vicky Campbell. Method authors explicitly annotated
 **/
public class Admin extends User
{
   private boolean isSuperAdmin;
   private boolean canDeleteTopics;
   private boolean canResetQuestionBanks;
   private boolean canResetScores;

   protected static ArrayList<Admin> adminList = new ArrayList<>();
   private static final String ADMIN_FILE_PATH = "admins.txt";
   private static final String ADMIN_BACKUP_FILE_PATH = "adminBackup.txt";
   private static final String ADMIN_SERIALIZED = "admin.ser";
   protected static final String ADMIN_PASSPHRASE = "RosaleenIsALegend";
   private static final String SUPER_ADMIN_HASH = "0b28a5799a32c687dad2c5183718ceac";

   //default constructor
   protected Admin()
   {
      super();
      isSuperAdmin = false;
      canDeleteTopics = false;
      canResetQuestionBanks = false;
      canResetScores = false;
   }
   //parameterised constructor
   protected Admin(String Username, String Password) throws PasswordException, UsernameException
   {
      super(Username, Password);
      isSuperAdmin = false;
      canDeleteTopics = false;
      canResetQuestionBanks = false;
      canResetScores = false;
   }
   //parameterised constructor
   protected Admin(String Username, String Password, boolean superAdmin, boolean DeleteTopics, boolean ResetQuestions, boolean ResetScore) throws PasswordException, UsernameException
   {
      super(Username, Password);
      isSuperAdmin = superAdmin;
      canDeleteTopics = DeleteTopics;
      canResetQuestionBanks = ResetQuestions;
      canResetScores = ResetScore;
   }
   //toString method
   public String toString()
   {
      return ("Admin username: " + getUsername() + "\nAdmin Rights:" + "\nCan Delete Topics: " + canDeleteTopics + "\nCan Reset Question Banks: " + canResetQuestionBanks + "\nCan Reset Quiz Scores: " + canResetScores);
   }
   //method to check if serialized file is found, if not, it restores the original admin data from text file
   protected static void fileCheck()
   {
      File f = new File(ADMIN_SERIALIZED);
      if (!f.exists())
      {
         System.out.println("Admin files were not found - backup files have been restored");
         restoreAdmins('o');
         serialize();
      }
   }
   //method to assign permissions/rights to admin user (can oly be called by a superAdmin)
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
      } else
      {
         System.out.println("Can admin delete questions? Y or N");
         delQns = keyboard.next().trim().toLowerCase().substring(0, 1);
         System.out.println("Can admin delete students? Y or N");
         delStudents = keyboard.next().trim().toLowerCase().substring(0, 1);
         System.out.println("Can admin reset scores? Y or N");
         resetScore = keyboard.next().trim().toLowerCase().substring(0, 1);

         isSuperAdmin = false;
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
   //setters & getters for class members/attributes
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

   protected boolean getIsSuperAdmin()
   {
      return isSuperAdmin;
   }
   private void setIsSuperAdmin(boolean superAdmin){
      isSuperAdmin = superAdmin;
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

   //method to restore all admins - has 2 modes
   //b - restore from most recent backup file
   //o - restore from original text file
   protected static void restoreAdmins(char mode)
   {
      String filePath = null;
      try
      {
         if (mode == 'b')
         {
            filePath = ADMIN_BACKUP_FILE_PATH;
         } else if (mode == 'o')
         {
            filePath = ADMIN_FILE_PATH;
         }
         File adminFile = new File(filePath);
         Scanner adminReader = new Scanner(adminFile);
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
   //method to back up all admin data to text file
   //separate text file used so that original data is not overwritten
   protected static void backupToFile()
   {
      int userCount = adminList.size();
      try
      {
         FileWriter adminBackup = new FileWriter(ADMIN_BACKUP_FILE_PATH);
         for (Admin admin : adminList)
         {
            adminBackup.write(admin.getUsername() + "\n");
            adminBackup.write(admin.getPassword() + "\n");
            adminBackup.write(admin.isSuperAdmin + "\n");
            adminBackup.write(admin.canDeleteTopics + "\n");
            adminBackup.write(admin.canResetQuestionBanks + "\n");
            //checks if it is the last line being written to file
            //ensures blank line not written to file which would cause problems upon restore
            if (userCount != 1)
            {
               adminBackup.write(admin.canResetScores + "\n");
            } else
            {
               adminBackup.write(String.valueOf(admin.canResetScores));
            }
            userCount--;
         }
         adminBackup.flush();
         adminBackup.close();
         System.out.println("Student Details have been Successfully Backed Up");
      } catch (IOException ex)
      {
         System.out.println(ex.getMessage());
         Main.logException(ex);
      }
   }
   //method to serialize all admin data to file
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
   //method to deserialize all admin data from file
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
   //method to check uniqueness of username provided (true=unique)
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
   //method to check if the hash of the passphrase entered is equal to the hash of the required admin passphrase
   //adapted from method found online (https://mkyong.com/java/java-md5-hashing-example/)
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
   //method to output all admin permissions
   protected static void viewAllAdminPermissions()
   {
      for (Admin adm : adminList)
      {
         String details = adm.toString();
         System.out.println(details + "\n");
      }
   }


}//class