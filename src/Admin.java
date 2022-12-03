import java.util.Scanner;
/**
 * Created by V.Campbell on 03/12/2022
 * Extends User class to create an admin profile;
 * Class Name: Admin.java
 **/
public class Admin extends User
{
    private boolean canDeleteQuestions;
    private boolean canDeleteStudents;
    private boolean canResetScore;

    private static Scanner keyboard = new Scanner(System.in);
    public Admin(String Username, String Password) throws PasswordException, UsernameException
    {
        super(Username, Password);
        canDeleteQuestions=false;
        canDeleteStudents=false;
        canResetScore=false;

    }
    public Admin(String Username, String Password, boolean DeleteQns, boolean DeleteStudents, boolean ResetScore) throws PasswordException, UsernameException
    {
        super(Username, Password);
        canDeleteQuestions=DeleteQns;
        canDeleteStudents=DeleteStudents;
        canResetScore=ResetScore;

    }

    public String toString(){
        return "This is the admin profile";
    }

    public void assignRights()
    {
        String delQns;
        String delStudents;
        String resetScore;

        System.out.println("Can admin delete questions? Y or N");
        delQns= keyboard.next().toLowerCase();

        System.out.println("Can admin delete students? Y or N");
        delStudents= keyboard.next().toLowerCase();

        System.out.println("Can admin reset scores? Y or N");
        resetScore= keyboard.next().toLowerCase();

        if(delQns=="y")
        {
            canDeleteQuestions=true;
        }
        if(delStudents=="y")
        {
            canDeleteStudents=true;
        }
        if (resetScore=="y")
        {
            canResetScore=true;
        }
    }
}//class