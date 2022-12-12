import java.util.Collections;
import java.util.Scanner;

/**
 * Filename: QuizMaster
 * Created by: nsweeney-ortiz on 01/12/2022
 * Comment: Class & Methods to run Quiz, record student answers, and output results
 **/
public class QuizMaster {
    // static constants
    protected static final String MAIN_QUIZ = "mainQuiz";
    private static final String TFQN = "tfQn", MCQN = "mcQn", SHORTQN = "shortQn";

    // static class attributes
    private static int mainQuizNumTFQns = 1;
    private static int mainQuizNumShortQns = 1;
    private static int mainQuizNumMCQns = 1;
    private static int numQuestionsAsked;
    private static int totalMarkAvailable;
    private static int totalMarkAwarded;

    // instance attributes
    private Boolean ansCorrect; // May not be required.
    private int markAvail;
    private int markAwarded;

    private QuizMaster() {
        this.ansCorrect = false;
        this.markAvail = 1;
        this.markAwarded = 0;
    }//default constructor

    private static void runQuiz() {
        int maxTFQns, maxMCQns, maxShortQns;
        maxTFQns = TrueFalseQuestion.tfQnList.size();
        maxShortQns = ShortQuestion.shortQnList.size();
        maxMCQns = MultipleChoiceQuestion.mcQnList.size();
        // Run quiz and ask all questions in question bank
        runQuiz(maxTFQns, maxShortQns, maxMCQns, MAIN_QUIZ);
    }

    protected static void runQuiz(String quizTopic) {
        // Run quiz with specified parameters for number of question and topic or main quiz
        runQuiz(mainQuizNumTFQns, mainQuizNumShortQns, mainQuizNumMCQns, quizTopic);
    }

    protected static void runQuiz(int numTfQns, int numShortQns, int numMCQns, String quizTopic) {
        QuizMaster quizMaster;

        // Limit max number of questions asked by number of question in question bank
        numTfQns = Math.min(numTfQns, TrueFalseQuestion.tfQnList.size());
        numShortQns = Math.min(numShortQns, ShortQuestion.shortQnList.size());
        numMCQns = Math.min(numMCQns, MultipleChoiceQuestion.mcQnList.size());

        // Randomize Question Banks
        Collections.shuffle(TrueFalseQuestion.tfQnList);
        Collections.shuffle(ShortQuestion.shortQnList);
        Collections.shuffle(MultipleChoiceQuestion.mcQnList);

        // Ask specified number of T/F questions from specified topic
        for (int qNum = 0; qNum < numTfQns; qNum++) {
            quizMaster = new QuizMaster();
            quizMaster.askQuestion(TFQN, quizTopic);
        }

        // Ask specified number of short answer questions from specified topic
        for (int qNum = 0; qNum < numShortQns; qNum++) {
            quizMaster = new QuizMaster();
            quizMaster.askQuestion(SHORTQN, quizTopic);
        }

        // Ask specified number of multiple choice questions from specified topic
        for (int qNum = 0; qNum < numMCQns; qNum++) {
            quizMaster = new QuizMaster();
            quizMaster.askQuestion(MCQN, quizTopic);
        }
    }

    private void askQuestion(String questionType, String quizTopic) {
        // Call appropriate 'askQuestion' method based on required question type and topic
        if (questionType.equalsIgnoreCase(TFQN)) askTFQuestion(quizTopic);
        else if (questionType.equalsIgnoreCase(MCQN)) askMCQuestion(quizTopic);
        else if (questionType.equalsIgnoreCase(SHORTQN)) askShortQuestion(quizTopic);
    }

    private void askTFQuestion(String topic) {
        Scanner keyboard = new Scanner(System.in);
        String answer, correctAnswer;
        boolean qnFound = false;
        int index = 0;
        do {
            // Find question of specified topic or take first question from bank for main quiz
            if (topic.equalsIgnoreCase(MAIN_QUIZ) | TrueFalseQuestion.tfQnList.get(index).getTopic().equalsIgnoreCase(topic)) {
                qnFound = true;
                // Set available marks for question asked
                setMarkAvail(TrueFalseQuestion.tfQnList.get(index).getPoints());
                do {
                    // Ask question and get student response
                    System.out.println("\nTopic: " + TrueFalseQuestion.tfQnList.get(index).getTopic());
                    System.out.println((numQuestionsAsked + 1) + ". " + TrueFalseQuestion.tfQnList.get(index).getQuestionText());
                    System.out.println("Number of points available: " + TrueFalseQuestion.tfQnList.get(index).getPoints());
                    System.out.println("\nAnswer 'true' or 'false': ");
                    answer = keyboard.nextLine().trim();
                    // Handle unexpected student inputs
                } while (!(answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("false")));
                if (TrueFalseQuestion.tfQnList.get(index).getAnswer() == 'T') correctAnswer = "true";
                else correctAnswer = "false";
                // Evaluate student answer & set instance variables for this question
                if (answer.equalsIgnoreCase(correctAnswer)) {
                    this.ansCorrect = true;
                    this.markAwarded = this.markAvail;
                } else this.ansCorrect = false;
                // Save students score for this question
                recordQuestionMark();
                // Remove this question from question bank so that it is not re-asked for this user
                TrueFalseQuestion.tfQnList.remove(index);
                // Increment static class variable tracking number of questions asked
                numQuestionsAsked++;
            } else index++;
            // If no questions are found of specified topic in the question bank then this question type is skipped
        } while (!qnFound && ((index + 1) <= TrueFalseQuestion.tfQnList.size()));
    }

    private void askShortQuestion(String topic) {
        Scanner keyboard = new Scanner(System.in);
        String answer, correctAnswer;
        boolean qnFound = false;
        int index = 0;
        do {
            // Find question of specified topic or take first question from bank for main quiz
            if (topic.equalsIgnoreCase(MAIN_QUIZ) | ShortQuestion.shortQnList.get(index).getTopic().equalsIgnoreCase(topic)) {
                qnFound = true;
                // Set available marks for question asked
                setMarkAvail(ShortQuestion.shortQnList.get(index).getPoints());
                // Ask question and get student response
                System.out.println("\nTopic: " + ShortQuestion.shortQnList.get(index).getTopic());
                System.out.println((numQuestionsAsked + 1) + ". " + ShortQuestion.shortQnList.get(index).getQuestionText());
                System.out.println("Number of points available: " + ShortQuestion.shortQnList.get(index).getPoints());
                System.out.println("\nPlease enter a short answer: ");
                answer = keyboard.nextLine().trim();
                correctAnswer = ShortQuestion.shortQnList.get(index).getAnswer();
                // Evaluate student answer & set instance variables for this question
                if (answer.equalsIgnoreCase(correctAnswer)) {
                    this.ansCorrect = true;
                    this.markAwarded = this.markAvail;
                } else this.ansCorrect = false;
                // Save students score for this question
                recordQuestionMark();
                // Remove this question from question bank so that it is not re-asked for this user
                ShortQuestion.shortQnList.remove(index);
                // Increment static class variable tracking number of questions asked
                numQuestionsAsked++;
            } else index++;
            // If no questions are found of specified topic in the question bank then this question type is skipped
        } while (!qnFound && ((index + 1) <= ShortQuestion.shortQnList.size()));
    }

    private void askMCQuestion(String topic) {
        Scanner keyboard = new Scanner(System.in);
        String userInput;
        int answer, correctAnswer;
        boolean qnFound = false;
        int index = 0;
        do {
            // Find question of specified topic or take first question from bank for main quiz
            if (topic.equalsIgnoreCase(MAIN_QUIZ) | MultipleChoiceQuestion.mcQnList.get(index).getTopic().equalsIgnoreCase(topic)) {
                qnFound = true;
                // Set available marks for question asked
                setMarkAvail(MultipleChoiceQuestion.mcQnList.get(index).getPoints());
                do {
                    // Ask question and get student response
                    System.out.println("\nTopic: " + MultipleChoiceQuestion.mcQnList.get(index).getTopic());
                    System.out.println((numQuestionsAsked + 1) + ". " + MultipleChoiceQuestion.mcQnList.get(index).getQuestionText());
                    System.out.println("\t1. " + MultipleChoiceQuestion.mcQnList.get(index).getOption1());
                    System.out.println("\t2. " + MultipleChoiceQuestion.mcQnList.get(index).getOption2());
                    System.out.println("\t3. " + MultipleChoiceQuestion.mcQnList.get(index).getOption3());
                    System.out.println("\t4. " + MultipleChoiceQuestion.mcQnList.get(index).getOption4());
                    System.out.println("Number of points available: " + MultipleChoiceQuestion.mcQnList.get(index).getPoints());
                    System.out.println("\nEnter correct option: ");
                    userInput = keyboard.nextLine().trim();
                    // Handle unexpected student inputs
                } while (!Globals.validMenuChoice(userInput, 1, 4));
                answer = Integer.parseInt(userInput);
                correctAnswer = MultipleChoiceQuestion.mcQnList.get(index).getCorrectOption();
                // Evaluate student answer & set instance variables for this question
                if (answer == correctAnswer) {
                    this.ansCorrect = true;
                    this.markAwarded = this.markAvail;
                } else this.ansCorrect = false;
                // Save students score for this question
                recordQuestionMark();
                // Remove this question from question bank so that it is not re-asked for this user
                MultipleChoiceQuestion.mcQnList.remove(index);
                // Increment static class variable tracking number of questions asked
                numQuestionsAsked++;
            } else index++;
            // If no questions are found of specified topic in the question bank then this question type is skipped
        } while (!qnFound && ((index + 1) <= MultipleChoiceQuestion.mcQnList.size()));
    }

    private void setMarkAvail(int markAvail) {
        this.markAvail = markAvail;
    }

    private void recordQuestionMark() {
        totalMarkAvailable += this.markAvail;
        totalMarkAwarded += this.markAwarded;
    }

    protected static void initializeQuizMaster() {
        QuizMaster.numQuestionsAsked = 0;
        QuizMaster.totalMarkAvailable = 0;
        QuizMaster.totalMarkAwarded = 0;
    }

    protected static int getMainQuizNumTFQns() {
        return mainQuizNumTFQns;
    }

    protected static int getMainQuizNumShortQns() {
        return mainQuizNumShortQns;
    }

    protected static int getMainQuizNumMCQns() {
        return mainQuizNumMCQns;
    }

    protected static void setMainQuizNumTFQns(int numTFQns) {
        mainQuizNumTFQns = numTFQns;
    }

    protected static void setMainQuizNumShortQns(int numShortQns) {
        mainQuizNumShortQns = numShortQns;
    }

    protected static void setMainQuizNumMCQns(int numMCQns) {
        mainQuizNumMCQns = numMCQns;
    }

    protected static int getTotalMarkAvailable() {
        return totalMarkAvailable;
    }

    protected static int getTotalMarkAwarded() {
        return totalMarkAwarded;
    }

    protected static void printQuizResult() {
        System.out.println("\nQuiz Result: " + QuizMaster.getTotalMarkAwarded() + " marks out of " + QuizMaster.getTotalMarkAvailable());
        System.out.println("Quiz Score: " + getQuizScore());
    }

    protected static int getQuizScore() {
        int score;
        if (QuizMaster.getTotalMarkAvailable() == 0)
            score = 0;
        else
            score = (100 * QuizMaster.getTotalMarkAwarded() / QuizMaster.getTotalMarkAvailable());

        return score;
    }

}//class
