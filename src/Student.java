import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by V.Campbell on 25/11/2022
 * Subclass of User for creation of student objects
 * Class Name: Student.java
 **/
public class Student extends User
{
   protected static ArrayList<Student> studentList = new ArrayList<>();
   private static final String STUDENT_FILE_PATH = "students.txt";
   private int highestScore;

   protected Student()
   {
      highestScore = 0;
   }

   protected Student(String Username, String Password, int HighScore) throws PasswordException, UsernameException
   {
      super(Username, Password);
      highestScore = HighScore;
   }

   protected Student(String Forename, String Surname, String Password) throws PasswordException, UsernameException
   {
      super(Forename, Surname, Password);
      highestScore = 0;
   }

   protected void setHighestScore(int HighestScore)
   {
      if (HighestScore > highestScore)
      {
         highestScore = HighestScore;
      }
   }

   protected int getHighestScore()
   {
      return highestScore;
   }

   protected static void populateStudentList()
   {
      try
      {
         File studentFile = new File(STUDENT_FILE_PATH);
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
      } catch (FileNotFoundException | PasswordException | UsernameException e)
      {
         System.out.println("An error occurred." + e.getMessage());
      }
   }

   protected static void serialize()
   {
      try
      {
         ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get("students.ser")));
         out.writeObject(studentList);
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
         ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("students.ser")));
         studentList = (ArrayList<Student>) in.readObject();

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

}//class