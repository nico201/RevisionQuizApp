import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * COM809: Group 5
 * Purpose: Subclass of User for creation of student objects
 * Author: Vicky Campbell. Method authors explicitly annotated
 **/
public class Student extends User
{
   private int highestScore;
   protected static ArrayList<Student> studentList = new ArrayList<>();
   private static final String STUDENT_FILE_PATH = "students.txt";
   private static final String STUDENT_BACKUP_FILE_PATH = "studentBackup.txt";
   private static final String STUDENT_SERIALIZED = "students.ser";

   //default constructor
   protected Student()
   {
      highestScore = 0;
   }

   //parameterised constructor
   protected Student(String Username, String Password, int HighScore) throws PasswordException
   {
      super(Username, Password);
      highestScore = HighScore;
   }
   //parameterised constructor
   protected Student(String Forename, String Surname, String Password) throws PasswordException, UsernameException
   {
      super(Forename, Surname, Password);
      highestScore = 0;
   }
   //method to check if serialized file is found, if not, it restores the original admin data from text file
   protected static void fileCheck()
   {
      File f = new File(STUDENT_SERIALIZED);
      if (!f.exists())
      {
         System.out.println("Student files were not found - backup files have been restored");
         restoreStudents('o');
         serialize();
      }
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Resets the Student high score instance variable to zero
    */
   private void resetHighScore()
   {
      this.highestScore = 0;
   }

   //method to ensure score is only updated if greater than the current high score
   protected void setHighestScore(int HighestScore)
   {
      if (HighestScore > highestScore)
      {
         highestScore = HighestScore;
      }
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: Gets the Student high score instance variable
    */
   protected int getHighestScore()
   {
      return highestScore;
   }

   //method to restore all students - has 2 modes
   //b - restore from most recent backup file
   //o - restore from original text file
   protected static void restoreStudents(char mode)
   {
      String filePath = null;
      try
      {
         if (mode == 'b')
         {
            filePath = STUDENT_BACKUP_FILE_PATH;
         } else if (mode == 'o')
         {
            filePath = STUDENT_FILE_PATH;
         }
         File studentFile = new File(filePath);
         Scanner studentReader = new Scanner(studentFile);
         while (studentReader.hasNextLine())
         {
            String Username = studentReader.nextLine();
            String Password = studentReader.nextLine();
            int HighScore = Integer.parseInt(studentReader.nextLine());

            Student std = new Student(Username, Password, HighScore);
            studentList.add(std);
         }
         studentReader.close();
      } catch (FileNotFoundException | PasswordException e)
      {
         System.out.println("An error occurred." + e.getMessage());
      }
   }
   //method to back up all student data to text file
   //separate text file used so that original data is not overwritten
   protected static void backupToFile()
   {
      try
      {
         FileWriter studentBackup = new FileWriter(STUDENT_BACKUP_FILE_PATH);
         int userCount = studentList.size();
         for (Student student : studentList)
         {
            studentBackup.write(student.getUsername() + "\n");
            studentBackup.write(student.getPassword() + "\n");
            String points = Integer.toString(student.getHighestScore());
            if (userCount != 1)
            {
               studentBackup.write(points + "\n");
            } else
            {
               studentBackup.write(points);
            }
            userCount--;
         }
         studentBackup.flush();
         studentBackup.close();
         System.out.println("Student Details have been Successfully Backed Up");
      } catch (IOException ex)
      {
         System.out.println(ex.getMessage());
         Main.logException(ex);
      }
   }
   //method to serialize all student data to file
   protected static void serialize()
   {
      try
      {
         ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("students.ser")));
         out.writeObject(studentList);
         out.close();
      } catch (IOException ex)
      {
         Main.logException(ex);
      }
   }
   //method to deserialize all student data from file
   protected static void deserialize()
   {
      try
      {
         ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("students.ser")));
         studentList = (ArrayList<Student>) in.readObject();

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
   //method to determine if generated username is unique
   protected static boolean userIsUnique(String Username)
   {
      boolean isUnique = true;
      for (Student std : studentList)
      {
         if (std.getUsername().equals(Username))
         {
            isUnique = false;
            break;
         }
      }
      return isUnique;
   }

   /*
    * Authors: Nico Sweeney-Ortiz
    * Purpose: A static Student class method that loops through all
    *          student objects in studentList and resets the high scores
    */
   protected static void resetAllHighScores()
   {
      for (Student std : studentList)
      {
         std.resetHighScore();
      }
      System.out.println("All student Quiz high scores have been reset.");
      Student.serialize();
      System.out.println("Student profiles have been successfully saved.");
   }

}//class