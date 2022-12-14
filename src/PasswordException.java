/**
 * COM809: Group 5
 * Purpose: Custom Exception to deal with invalid password creation
 * Author: Vicky Campbell
 **/
public class PasswordException extends Exception {
    protected PasswordException(String Reason) {
        super(Reason);
    }

}//class
