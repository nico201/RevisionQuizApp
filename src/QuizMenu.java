import java.util.Scanner;

/**
 *  COM809: Group 5
 *  Purpose: Displays Student Quiz Menu
 */
public class QuizMenu {

    /*
     * Authors: Nico Sweeney-Ortiz
     * Purpose: Displays Student Quiz Menu to allow the selection of taking
     *          the main quiz or a topic specific quiz, or return to main menu
     */
    public static void display() {
        Scanner keyboard = new Scanner(System.in);
        String studentMenuInput;
        String chosenTopic;
        int quizMenuChoice;
        int topicIndex;
        do { // Print menu and menu options to screen
            System.out.println("\nWelcome to Quiz Menu");
            System.out.println("*************************");
            System.out.println("1. Take Main Quiz \n2. Take Topic Quiz \n3. Log out & return to Main Menu \nPlease enter a selection: ");
            // Get student input
            studentMenuInput = keyboard.nextLine();
            // Handle unexpected student input
        } while (!Main.validMenuChoice(studentMenuInput, 1, 3));
        quizMenuChoice = Integer.parseInt(studentMenuInput);
        // Carry-out requested action
        switch (quizMenuChoice) {
            case 1:
                // Run 'Main' quiz on all topics with pre-defined number of questions
                Main.populateAllQuestions();
                executeQuiz(QuizMaster.MAIN_QUIZ);
                break;
            case 2:
                // Display available quiz topics to student
                Main.populateAllQuestions();
                // Get student topic selection
                topicIndex = Question.chosenTopic("Which topic would you like to be quizzed on?");
                // Get choosen topic
                chosenTopic = Question.qnUniqueTopicList.get(topicIndex);
                // Run 'topic' quiz on requested topic with pre-defined number of questions
                executeQuiz(chosenTopic);
                break;
            case 3:
                // Return to Main Menu
                Main.displayMainMenu();
                break;
            default:
        }
    }

    /*
     * Authors: Aaron McCloskey, Marcus Campbell, David Fadeyi, Vicky Campbell, Nico Sweeney-Ortiz
     * Purpose: Executes the Quiz once user indicates they are ready. Displays the student mark,
     *          updates the student high score, prints the 'student' leaderboard to screen and
     *          then asks the user if they would like to retake the quiz or return to main menu
     */
    protected static void executeQuiz(String quizTopic) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nWelcome to the Quiz");
        System.out.println("Press enter to begin:");
        keyboard.nextLine();
        // Resets QuizMaster static class variables tracking student quiz score
        QuizMaster.initializeQuizMaster();
        // Use quizMaster.runQuiz() to ask ALL questions in question bank or specify num in call as below
        QuizMaster.runQuiz(quizTopic);
        QuizMaster.printQuizResult();
        // Update the student user's highest score - For main quiz only
        if (quizTopic.equalsIgnoreCase(QuizMaster.MAIN_QUIZ)) {
            for (Student studentUser : Student.studentList) {
                if (studentUser.getUsername().equals(Main.currentStudent.username)) {
                    studentUser.setHighestScore(QuizMaster.getQuizScore());
                }
            }
            Student.serialize();
            // Display the student version of the Leaderboard
            LeaderBoard.printLeaderboard("student");
        }
        // And provide option to retake the quiz
        boolean retake = retakeQuiz();
        if (retake) {
            QuizMenu.display();
        } else {
            Main.displayMainMenu();
        }
    }

    protected static boolean retakeQuiz() {
        Scanner keyboard = new Scanner(System.in);
        String choice;
        int retakeChoice;
        do {
            System.out.println("\nPlease enter a selection: \n1. Retake Quiz \n2. Logout and return to Main Menu");
            choice = keyboard.nextLine();
        } while (!Main.validMenuChoice(choice, 1, 2));
        retakeChoice = Integer.parseInt(choice);
        return retakeChoice == 1;
    }
}
