import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by V.Campbell on 25/11/2022
 * Subclass of User for creation of student objects
 * Class Name: Student.java
 **/
public class Student extends User
{
   public static ArrayList<Student> studentList = new ArrayList<Student>();
   public static String studentFilePath= "students.txt";
   private int highestScore;

   public Student()
   {
      highestScore = 0;
   }

   public Student(String Username, String Password, int HighScore) throws PasswordException, UsernameException
   {
      super(Username, Password);
      highestScore=HighScore;
   }
   public Student(String Forename, String Surname, String Password) throws PasswordException, UsernameException
   {
      super(Forename, Surname, Password);
      highestScore=0;
   }

   public void setHighestScore(int HighestScore)
   {
      highestScore = HighestScore;
   }

   public static void populateStudentList()
   {
      try
      {
         File studentFile = new File(studentFilePath);
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
         System.out.println("An error occurred."+e.getMessage());
      }
   }
   public static void serialize(){
      try {
         ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.ser"));
         out.writeObject(studentList);
         out.close();
      }
      catch (NotSerializableException ex){
         Globals.logException(ex);
      }
      catch (IOException ex) {
         Globals.logException(ex);
      }
   }
   public static void deserialize(){
      try {
         ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.ser"));
         studentList = (ArrayList<Student>)in.readObject();

      }
      catch (NotSerializableException ex){
         Globals.logException(ex);
      }
      catch (IOException ex) {
         ex.printStackTrace();
      } catch (ClassNotFoundException e)
      {
         throw new RuntimeException(e);
      }
   }
   public static boolean userIsUnique(String Username)
   {
      boolean isUnique = true;
      for (Student std : studentList)
      {
         if (std.getUsername() == Username)
         {
            isUnique = false;
         }
      }
      return isUnique;
   }

}//class