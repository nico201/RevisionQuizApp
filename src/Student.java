
/**
 * Created by Aaron McCloskey on 25/11/2022
 * Subclass of User for creation of student objects
 * Class Name: Student.java
 **/
public class Student extends User
{
   private String studentName;
   private String studentID;
   private int highestScore;

   public Student()
   {
   }

   public Student(boolean isAdmin, String studentName, String studentID)
   {
      super(isAdmin);
      this.studentName = studentName;
      this.studentID = studentID;
   }

   public void setStudentName(String studentName)
   {
      this.studentName = studentName;
   }

   public void setStudentID(String studentID)
   {
      this.studentID = studentID;
   }

   public void setHighestScore(int highestScore)
   {
      this.highestScore = highestScore;
   }
}//class