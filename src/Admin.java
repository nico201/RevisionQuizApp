
/**
 * Created by Aaron McCloskey on 25/11/2022
 * Extends User class to create an admin profile;
 * Class Name: Admin.java
 **/
public class Admin extends User
{

    public Admin(boolean isAdmin)
    {
        super(isAdmin);
    }

    public String toString(){
        return "This is the admin profile";
    }


}//class