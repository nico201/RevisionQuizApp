/**
 * Created by V.Campbell on 01/12/2022
 * Custom Exception to deal with invalid username creation
 **/
public class UsernameException extends Exception
{
   protected UsernameException(String Reason) {
      super(Reason);
   }

}//class
