/**
 * Created by Aaron McCloskey on 27/11/2022
 * Basic initial leaderboard which does not write to file
 * Class Name: LeaderBoard.java
 **/
public class LeaderBoard
{

   private static final int MAXSTUDENTS = 10; // leaderboard will accommodate 10 students
   private static final int STUDENTDISPLAY = 3; // students will see top 3
   private static String[] leaderNames = new String[MAXSTUDENTS];
   private static int[] leaderScores = new int[MAXSTUDENTS];

   public static void printLeaderboard(String leaderboardUser) // can be student or admin leaderboard
   {
      // show header
      System.out.println("\nLeaderboard");
      System.out.println("*************************");
      System.out.println("Rank\tName\t\tScore (%)");

      // student leaderboard only shows selected top subset
      if (leaderboardUser.equals("student")) {
         for (int count = 1; count <= STUDENTDISPLAY; count++) {
            System.out.println(count + ":\t\t" + leaderNames[count-1] + "\t\t" + leaderScores[count-1]);
         }//for
      }//if

      // admin leaderboard shows all
      else if (leaderboardUser.equals("admin")) {
         for (int count = 1; count <= MAXSTUDENTS; count++) {
            System.out.println(count + ":\t\t" + leaderNames[count-1] + "\t\t" + leaderScores[count-1]);
         }//for
      }//if

   }// printLeaderboard

   public static void updateLeaderboard(String latestName, int latestScore)
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