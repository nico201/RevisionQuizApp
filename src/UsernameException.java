/**
 * COM809: Group 5
 * Purpose: Custom Exception to deal with invalid username creation
 **/
public class UsernameException extends Exception {
    protected UsernameException(String Reason) {
        super(Reason);
    }

}//class
