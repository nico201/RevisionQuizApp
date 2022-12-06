import java.io.Serializable;
import java.util.Random;

/**
 * Created by V.Campbell on 01/12/2022
 * abstract base class for user creation
 **/
abstract public class User implements Serializable
{
   private static String error = null;
   private String forename;
   private String surname;
   protected String username;
   protected String password;

   public User()
   {
      username = "";
      password = "";
   }

   public User(String Username, String Password) throws UsernameException, PasswordException
   {
      error = null;
      username = Username;
      setPassword(Password);
   }

   public User(String Forename, String Surname, String Password) throws UsernameException, PasswordException
   {
      error = null;
      forename = Forename;
      surname = Surname;
      setUsername();
      setPassword(Password);
   }

   public void setUsername() throws UsernameException
   {
      if (forename.length() > 2 && surname.length() > 2)
      {
         Random rnd = new Random();
         int studentNum = rnd.nextInt(1000);
         username = forename.toLowerCase().charAt(0) + surname.toLowerCase() + studentNum;
      } else
      {
         throw new UsernameException("Forename or Surname don't meet minimum length requirements");
      }
   }

   public String getUsername()
   {
      return username;
   }

   public void setPassword(String Password) throws PasswordException
   {
      boolean validLength = false;
      if (Password.length() > 8)
      {
         validLength = true;
      } else
      {
         error += "\nPassword is not at least 8 characters";
      }

      if (!hasDigit(Password))
      {
         error += "\nPassword does not contain a digit";
      }
      if (!hasUpper(Password))
      {
         error += "\nPassword does not contain an Upper Case character";
      }
      if (error != null)
      {
         throw new PasswordException(error);
      } else
      {
         password = Password;
      }

   }

   public String getPassword()
   {
      return password;
   }

   public String toString()
   {
      return "This is a user profile";
   }

   private static boolean hasDigit(String s)
   {
      boolean hasNumber = false;
      for (char c : s.toCharArray())
      {
         if (Character.isDigit(c))
         {
            hasNumber = true;
         }
      }
      return hasNumber;
   }

   private static boolean hasUpper(String s)
   {
      boolean hasUpper = false;
      for (char c : s.toCharArray())
      {
         if (Character.isUpperCase(c))
         {
            hasUpper = true;
         }
      }
      return hasUpper;
   }

}//class