import java.util.Scanner;

public class QuizMenu {
    public static void display() {
        Scanner keyboard = new Scanner(System.in);
        String studentMenuInput;
        String chosenTopic;
        int quizMenuChoice;
        int topicIndex;
        do {
            System.out.println("\nWelcome to Quiz Menu");
            System.out.println("*************************");
            System.out.println("1. Take Main Quiz \n2. Take Topic Quiz \n3. Log out & return to Main Menu \nPlease enter a selection: ");
            studentMenuInput = keyboard.nextLine();
        } while (!Globals.validMenuChoice(studentMenuInput, 1, 3));
        quizMenuChoice = Integer.parseInt(studentMenuInput);
        switch (quizMenuChoice) {
            case 1:
                Globals.populateAllQuestions();
                runQuiz(QuizMaster.MAIN_QUIZ);
                break;
            case 2:
                Globals.populateAllQuestions();
                topicIndex = Question.chosenTopic("Which topic would you like to be quizzed on?");
                chosenTopic = Question.qnUniqueTopicList.get(topicIndex);
                runQuiz(chosenTopic);
                break;
            case 3:
                Main.displayMainMenu();
                break;
            default:
        }
    }

    protected static void runQuiz(String quizTopic) {
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
        } while (!Globals.validMenuChoice(choice, 1, 2));
        retakeChoice = Integer.parseInt(choice);
        return retakeChoice == 1;
    }
}
