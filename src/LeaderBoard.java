import javax.swing.*;

/**
 * Created by Aaron McCloskey on 27/11/2022
 * Leaderboard to display students' high scores in rank order
 * Freshly populated from the student arraylist each time it is called
 * Class Name: LeaderBoard.java
 **/
public class LeaderBoard {

    private static final int STUDENTDISPLAY = 3; // No. of high scores seen by students
    private static final String[] leaderNames = new String[checkLeaderboardSize()];
    private static final int[] leaderScores = new int[checkLeaderboardSize()];

    // method checks current No. of students in order to inform size of arrays needed
    private static int checkLeaderboardSize() {
        return (Student.studentList.size());
    }

    private static void updateLeaderboard() {
        // clear the arrays of any previous data
        for (int index = 0; index <= (checkLeaderboardSize() - 1); index++) {
            leaderScores[index] = 0;
            leaderNames[index] = "clear";
        }

        // work through the student arraylist one by one
        for (Student studentUser : Student.studentList) {

            // if only 1 student exists they are automatically top of the leaderboard
            if (checkLeaderboardSize() == 1) {
               leaderScores[0] = studentUser.getHighestScore();
               leaderNames[0] = studentUser.getUsername();
            }//if (1 student)

            // if only 2 students exist - decide which order they are in
            else if (checkLeaderboardSize() == 2) {
                if (studentUser.getHighestScore() >= leaderScores[0]) {
                    leaderScores[1] =  leaderScores[0];
                    leaderNames[1] = leaderNames[0];
                    leaderScores[0] = studentUser.getHighestScore();
                    leaderNames[0] = studentUser.getUsername();
                }
               else {
                    leaderScores[1] = studentUser.getHighestScore();
                    leaderNames[1] = studentUser.getUsername();
               }
            }//if 2 students

            // if 3 or more students exist - more complex sorting algorithm
            else if (checkLeaderboardSize() >= 3) {
                    // if the student's highest score is a new top score
                    if (studentUser.getHighestScore() >= leaderScores[0]) {
                        for (int index = (checkLeaderboardSize() - 1); index >= 1; index--) {
                            leaderScores[index] = leaderScores[index - 1];
                            leaderNames[index] = leaderNames[index - 1];
                        }//for
                        leaderScores[0] = studentUser.getHighestScore();
                        leaderNames[0] = studentUser.getUsername();
                    }//if new top score

                    // if somewhere in between top and bottom
                    else if ((studentUser.getHighestScore() < leaderScores[0]) && (studentUser.getHighestScore() >= leaderScores[checkLeaderboardSize() - 2])) {
                        // work from top to bottom to find out exactly where it fits
                        for (int index = 0; index <= (checkLeaderboardSize() - 2); index++) {
                            if ((studentUser.getHighestScore() < leaderScores[index]) && (studentUser.getHighestScore() >= leaderScores[index + 1])) {
                                // then update everything below accordingly
                                for (int update = (checkLeaderboardSize() - 1); update >= index + 2; update--) {
                                    leaderScores[update] = leaderScores[update - 1];
                                    leaderNames[update] = leaderNames[update - 1];
                                }//for
                                //and insert the new values
                                leaderScores[index + 1] = studentUser.getHighestScore();
                                leaderNames[index + 1] = studentUser.getUsername();
                            }//if
                        }//for
                    }//if somewhere in between top and bottom

                    // if student's score is the very bottom score
                    else if ((studentUser.getHighestScore() < leaderScores[checkLeaderboardSize() - 2]) && (studentUser.getHighestScore() >= leaderScores[checkLeaderboardSize() - 1])) {
                        leaderScores[checkLeaderboardSize() - 1] = studentUser.getHighestScore();
                        leaderNames[checkLeaderboardSize() - 1] = studentUser.getUsername();
                    }//if bottom score
            }//if 3 or more students exist

        }//for working through student arraylist

    }//updateLeaderboard

    protected static void printLeaderboard(String leaderboardUser) {
        updateLeaderboard();

        // display student leaderboard which only shows selected top subset
        if (leaderboardUser.equals("student")) {
            System.out.println("\nStudent Leaderboard");
            System.out.println("*******************");
            System.out.println("Rank\tName\t\tHigh Score (%)");
            for (int count = 1; count <= STUDENTDISPLAY; count++) {
                System.out.println(count + ":\t\t" + leaderNames[count - 1] + "\t\t\t" + leaderScores[count - 1]);
            }//for
        }//if

        // display admin leaderboard which shows all students
        else if (leaderboardUser.equals("admin")) {
            System.out.println("\nAdmin Leaderboard");
            System.out.println("*****************");
            System.out.println("Rank\tName\t\tHigh Score (%)");
            for (int count = 1; count <= checkLeaderboardSize(); count++) {
                System.out.println(count + ":\t\t" + leaderNames[count - 1] + "\t\t\t" + leaderScores[count - 1]);
            }//for
        }//if
    }

}//class