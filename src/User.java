import java.io.Serializable;
import java.util.Random;

/**
 * COM809: Group 5
 * Purpose: abstract base class for user creation
 * Author: Vicky Campbell. Method authors explicitly annotated
 **/
abstract public class User implements Serializable {
    private String forename;
    private String surname;
    protected String username;
    protected String password;
    private static String error = null;

    //default canstructor
    protected User() {
        username = "";
        password = "";
    }
    //parameterised constructor
    protected User(String Username, String Password) throws PasswordException {
        error = null;
        username = Username;
        setPassword(Password);
    }
    //parameterised constructor
    protected User(String Forename, String Surname, String Password) throws UsernameException, PasswordException {
        error = null;
        forename = Forename;
        surname = Surname;
        setUsername();
        setPassword(Password);
    }
    //getters & setters

    //custom setter for username
    protected void setUsername() throws UsernameException {
        if (forename.length() > 2 && surname.length() > 2) {
            Random rnd = new Random();
            int studentNum = rnd.nextInt(1000);
            username = forename.toLowerCase().charAt(0) + surname.toLowerCase() + studentNum;
        } else {
            throw new UsernameException("Forename or Surname don't meet minimum length requirements");
        }
    }

    protected String getUsername() {
        return username;
    }

    //custom setter for password
    protected void setPassword(String Password) throws PasswordException {
        if (Password.length() <= 8)
            error += "\nPassword is not at least 8 characters";

        if (!hasDigit(Password))
            error += "\nPassword does not contain a digit";

        if (!hasUpper(Password))
            error += "\nPassword does not contain an Upper Case character";

        if (error != null)
            throw new PasswordException(error);
        else
            password = Password;
    }

    protected String getPassword() {
        return password;
    }

    //base toString method
    public String toString() {
        return "This is a user profile";
    }
    //method to validate if digit is present in string
    protected static boolean hasDigit(String s) {
        boolean hasNumber = false;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
        }
        return hasNumber;
    }
    //method to validate if upperCase is present in string
    private static boolean hasUpper(String s) {
        boolean hasUpper = false;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
                break;
            }
        }
        return hasUpper;
    }

    protected static void checkUserFiles(){
        Admin.fileCheck();
        Student.fileCheck();
    }
    //method to restore all admins/students
    //restores from original text files
    protected static void resetAllUsers(){
        Admin.restoreAdmins('o');
        Admin.serialize();
        Admin.deserialize();
        Student.restoreStudents('o');
        Student.serialize();
        Student.deserialize();
    }
    //method to back up all admin & Student data to text file
    protected static void backupAllUsers(){
        Admin.backupToFile();
        Student.backupToFile();
    }
    //method to restore all admins/students
    //restores from most recent backup file
    protected static void restoreAllUsersFromLatestBackup(){
        Admin.restoreAdmins('b');
        Admin.serialize();
        Student.restoreStudents('b');
        Student.serialize();
    }
    //method to deserialize all admins/students from file
    protected static void deserializeAllUsers(){
        Admin.deserialize();
        Student.deserialize();
    }

}//class