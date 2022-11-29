
/**
 * Created by Aaron McCloskey on 25/11/2022
 * Superclass for Creating User Profile
 * Class Name: User.java
 **/
public class User
{

    private boolean isAdmin;


    public User(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

   public User()
   {

   }

   public String toString(){
        return "This is a user profile";
    }


}//class