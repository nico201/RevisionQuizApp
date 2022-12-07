/**
 * Created by Aaron McCloskey on 27/11/2022
 * Basic initial leaderboard which does not write to file
 * Class Name: LeaderBoard.java
 **/
public class LeaderBoard
{

   private static final int MAXSTUDENTS = 10; // full leaderboard will accommodate 10 students
   private static final int STUDENTDISPLAY = 3; // No. of places in student leaderboard
   private static String[] leaderNames = new String[MAXSTUDENTS];
   private static int[] leaderScores = new int[MAXSTUDENTS];

   public static void printLeaderboard(String leaderboardUser) // can be student or admin leaderboard
   {

      // student leaderboard only shows selected top subset
      if (leaderboardUser.equals("student")) {
         System.out.println("\nStudent Leaderboard");
         System.out.println("*************************");
         System.out.println("Rank\tName\t\tScore (%)");
         for (int count = 1; count <= STUDENTDISPLAY; count++) {
            System.out.println(count + ":\t\t" + leaderNames[count-1] + "\t\t" + leaderScores[count-1]);
         }//for
      }//if

      // admin leaderboard shows all
      else if (leaderboardUser.equals("admin")) {
         System.out.println("\nAdmin Leaderboard");
         System.out.println("*************************");
         System.out.println("Rank\tName\t\tHigh Score (%)");
         for (Student studentUser : Student.studentList) {
            System.out.println("\t" + studentUser.getUsername() + "\t" + studentUser.getHighestScore());
         }
         //for (int count = 1; count <= MAXSTUDENTS; count++) {
         //   System.out.println(count + ":\t\t" + leaderNames[count-1] + "\t\t" + leaderScores[count-1]);
         //}//for
      }//if

   }// printLeaderboard

   public static void updateLeaderboard(String latestName, int latestScore)
   {
      // new top score
      if (latestScore >= leaderScores[0]) {
         for (int index = MAXSTUDENTS-1; index >= 1; index--) {
            leaderScores[index] = leaderScores[index - 1];
            leaderNames[index] = leaderNames[index - 1];
         }//for
         leaderScores[0] = latestScore;
         leaderNames[0] = latestName;
      }//if

      // new bottom score
      else if ((latestScore < leaderScores[8]) && (latestScore >= leaderScores[9])) {
         leaderScores[9] = latestScore;
         leaderNames[9] = latestName;
      }//if

      // if somewhere in between
      else if ((latestScore < leaderScores[0]) && (latestScore >= leaderScores[MAXSTUDENTS-2])) {
         // work from top to bottom to find out exactly where it fits
         for (int index = 0; index <= MAXSTUDENTS-2; index++) {
            if ((latestScore < leaderScores[index]) && (latestScore >= leaderScores[index+1])) {
               // then update everything below accordingly
               for (int update = MAXSTUDENTS-1; update >= index+2; update--) {
                  leaderScores[update] = leaderScores[update - 1];
                  leaderNames[update] = leaderNames[update - 1];
               }//for
               //and insert the new values
               leaderScores[index+1] = latestScore;
               leaderNames[index+1] = latestName;
            }//if
         }//for
      }//if

   }//updateLeaderboard

}//class