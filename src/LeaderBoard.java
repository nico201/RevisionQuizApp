/**
 * Created by Aaron McCloskey on 27/11/2022
 * Basic initial leaderboard which does not write to file
 * Class Name: LeaderBoard.java
 **/
public class LeaderBoard
{

   private static String[] leaderNames = new String[3];
   private static int[] leaderScores = new int[3];

   protected static void printLeaderboard()
   {
      System.out.println("\nLeaderboard");
      System.out.println("***********");
      System.out.println("Name\t\t\tScore (%)");
      System.out.println("1st: " + leaderNames[0] + "\t\t" + leaderScores[0]);
      System.out.println("2nd: " + leaderNames[1] + "\t\t" + leaderScores[1]);
      System.out.println("3rd: " + leaderNames[2] + "\t\t" + leaderScores[2]);
   }

   protected static void updateLeaderboard(String latestName, int latestScore)
   {
      if (latestScore >= leaderScores[0])
      {
         leaderScores[2] = leaderScores[1];
         leaderNames[2] = leaderNames[1];
         leaderScores[1] = leaderScores[0];
         leaderNames[1] = leaderNames[0];
         leaderScores[0] = latestScore;
         leaderNames[0] = latestName;
      } else if (latestScore >= leaderScores[1])
      {
         leaderScores[2] = leaderScores[1];
         leaderNames[2] = leaderNames[1];
         leaderScores[1] = latestScore;
         leaderNames[1] = latestName;
      } else if (latestScore >= leaderScores[2])
      {
         leaderScores[2] = latestScore;
         leaderNames[2] = latestName;
      }
   }

}//class