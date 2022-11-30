/**
 * Created by Aaron McCloskey on 27/11/2022
 * Edited by Marcus Campbell 30/11/2022 - added get() methods, simplified set() methods
 * Basic initial leaderboard which does not write to file
 * Class Name: LeaderBoard.java
 **/
public class LeaderBoard
{
    private static String slot1Name;
    private static int slot1Score;
    private static String slot2Name;
    private static int slot2Score;
    private static String slot3Name;
    private static int slot3Score;

    public static void printLeaderboard() //SHOULD THIS BE A TOSTRING() METHOD?
    {
        System.out.println("Leader Board");
        System.out.println("*******************");
        System.out.println("Name\t\t\tScore");
        System.out.println("1st: " + slot1Name + "\t\t\t" + slot1Score);
        System.out.println("2nd: " + slot2Name + "\t\t\t" + slot2Score);
        System.out.println("3rd: " + slot3Name + "\t\t\t" + slot3Score);
    }

    public static void setSlot1Name(String newSlot1Name) { slot1Name = newSlot1Name; }
    public static String getSlot1Name()
    {
        return slot1Name;
    }

    public static void setSlot1Score(int newSlot1Score)
    {
        slot1Score = newSlot1Score;
    }
    public static int getSlot1Score()
    {
        return slot1Score;
    }

    public static void setSlot2Name(String newSlot2Name)
    {
        slot2Name = newSlot2Name;
    }
    public static String getSlot2Name()
    {
        return slot2Name;
    }

    public static void setSlot2Score(int newSlot2Score)
    {
        slot2Score = newSlot2Score;
    }
    public static int getSlot2Score()
    {
        return slot2Score;
    }

    public static void setSlot3name(String newSlot3name)
    {
        slot3Name = newSlot3name;
    }
    public static String getSlot3Name()
    {
        return slot3Name;
    }

    public static void setSlot3Score(int newSlot3Score)
    {
        slot3Score = newSlot3Score;
    }
    public static int getSlot3Score()
    {
        return slot3Score;
    }

}//class