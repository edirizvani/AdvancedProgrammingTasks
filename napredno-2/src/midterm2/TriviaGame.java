package midterm2;//package midterm2;

import java.util.ArrayList;
import java.util.Scanner;

interface Question {
    void askQuestion(int index);

    boolean validateQuestion(String userAnswer);

    int getValue();

    String getCorrectAnswer();
}


class TrueFalseQuestion implements Question {
    public String question;
    public String answer;
    public int value;

    public TrueFalseQuestion(String question, String answer, int value) {
        this.question = question;
        this.answer = answer;
        this.value = value;
    }

    @Override
    public void askQuestion(int index) {
        System.out.println("Question " + (index + 1) + ".  " + value + " points.");
        System.out.println(question);
        System.out.println("Enter 'T' for true or 'F' for false.");

    }

    @Override
    public boolean validateQuestion(String userAnswer) {
        return userAnswer.trim().toLowerCase().equalsIgnoreCase(answer);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getCorrectAnswer() {
        return answer;
    }
}

class FreeFormQuestion implements Question {
    public String question;
    public String answer;
    public int value;

    public FreeFormQuestion(String question, String answer, int value) {
        this.question = question;
        this.answer = answer;
        this.value = value;
    }

    @Override
    public void askQuestion(int index) {
        System.out.println("Question " + (index + 1) + ".  " + value + " points.");
        System.out.println(question);
    }

    @Override
    public boolean validateQuestion(String userAnswer) {
        return userAnswer.trim().equalsIgnoreCase(answer);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getCorrectAnswer() {
        return answer;
    }
}


class TriviaQuestion {

    public static final int TRUEFALSE = 0;
    public static final int FREEFORM = 1;
    public String question;        // Actual question
    public String answer;        // Answer to question
    public int value;            // Point value of question
    public int type;            // Question type, TRUEFALSE or FREEFORM

    public TriviaQuestion() {
        question = "";
        answer = "";
        value = 0;
        type = FREEFORM;
    }

    public TriviaQuestion(String q, String a, int v, int t) {
        question = q;
        answer = a;
        value = v;
        type = t;
    }
}

class TriviaData {

    private ArrayList<Question> data;

    public TriviaData() {
        data = new ArrayList<>();
    }

    public void addQuestion(Question q) {
        data.add(q);
    }

    public void showQuestion(int index) {
        data.get(index).askQuestion(index);
    }

    public int numQuestions() {
        return data.size();
    }

    public Question getQuestion(int index) {
        return data.get(index);
    }
}

public class TriviaGame {

    public TriviaData questions;    // Questions

    public TriviaGame() {
        // Load questions
        questions = new TriviaData();
        questions.addQuestion(new FreeFormQuestion("The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3));
        questions.addQuestion(new TrueFalseQuestion("Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1));
        questions.addQuestion(new FreeFormQuestion("1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2));
        questions.addQuestion(new FreeFormQuestion("I am the geometric figure most like a lost parrot",
                "polygon", 2));
        questions.addQuestion(new TrueFalseQuestion("Generics were introducted to Java starting at version 5.0.",
                "T", 1));
    }
    // Main game loop

    public static void main(String[] args) {
        int score = 0;            // Overall score
        int questionNum = 0;    // Which question we're asking
        TriviaGame game = new TriviaGame();
        Scanner keyboard = new Scanner(System.in);
        // Ask a question as long as we haven't asked them all
        while (questionNum < game.questions.numQuestions()) {
            // Show question
            Question question = game.questions.getQuestion(questionNum);
            question.askQuestion(questionNum);
            // Get answer
            String answer = keyboard.nextLine();
            // Validate answer
            if (question.validateQuestion(answer)) {
                System.out.println("That is correct!  You get " + question.getValue() + " points.");
                score += question.getValue();

            } else {
                System.out.println("Wrong, the correct answer is " + question.getCorrectAnswer());
            }

            System.out.println("Your score is " + score);
            questionNum++;
        }
        System.out.println("Game over!  Thanks for playing!");
    }
}
