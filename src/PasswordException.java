/**
 * Created by V.Campbell on 01/12/2022
 * Custom Exception to deal with invalid password creation
 **/
public class PasswordException extends Exception
{
    protected PasswordException(String Reason) {
        super(Reason);
    }

}//class
