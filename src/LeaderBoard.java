/**
 * Created by Aaron McCloskey on 27/11/2022
 * Basic initial leaderboard which does not write to file
 * Class Name: LeaderBoard.java
 **/
public class LeaderBoard
{

    private static String [] leaderNames = new String [3];
    private static int [] leaderScores = new int [3];

    public static void printLeaderboard()
    {
        System.out.println("\nLeader Board");
        System.out.println("*******************");
        System.out.println("Name\t\t\tScore");
        System.out.println("1st: " + leaderNames[0] + "\t\t" + leaderScores[0]);
        System.out.println("2nd: " + leaderNames[1] + "\t\t" + leaderScores[1]);
        System.out.println("3rd: " + leaderNames[2] + "\t\t" + leaderScores[2]);
    }

    /*public static void setSlot1Name(String newSlot1Name) { slot1Name = newSlot1Name; }
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
    */

}//class