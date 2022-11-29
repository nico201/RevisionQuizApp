
/**
 * Created by Aaron McCloskey on 27/11/2022
 * Basic initial leaderboard which does not write to file
 * Class Name: LeaderBoard.java
 **/
public class LeaderBoard
{
    private static String slot1Name;
    private static int slot1Score;
    private static String slot2Name;
    private static int slot2Score;
    private static String slot3name;
    private static int slot3Score;

    public static void printLeaderboard()
    {
       System.out.println("Leader Board");
       System.out.println("*******************");
       System.out.println("Name\t\t\tScore");
       System.out.println("1st: " + slot1Name + "\t\t\t" + slot1Score);
       System.out.println("2nd: " + slot2Name + "\t\t\t" + slot2Score);
       System.out.println("3rd: " + slot3name + "\t\t\t" + slot3Score);
    }

    public static void setSlot1Name(String slot1Name)
    {
        LeaderBoard.slot1Name = slot1Name;
    }

    public static void setSlot1Score(int slot1Score)
    {
        LeaderBoard.slot1Score = slot1Score;
    }

    public static void setSlot2Name(String slot2Name)
    {
        LeaderBoard.slot2Name = slot2Name;
    }

    public static void setSlot2Score(int slot2Score)
    {
        LeaderBoard.slot2Score = slot2Score;
    }

    public static void setSlot3name(String slot3name)
    {
        LeaderBoard.slot3name = slot3name;
    }

    public static void setSlot3Score(int slot3Score)
    {
        LeaderBoard.slot3Score = slot3Score;
    }
}//class