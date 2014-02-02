package com.jguice.quizzle.gui;

import com.jguice.quizzle.interfaces.Question;
import com.jguice.quizzle.interfaces.Quiz;
import com.jguice.quizzle.interfaces.Quizzer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: Jan 19, 2005
 * Time: 10:34:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class GraphicalQuizzer implements Quizzer{
    protected Quiz quiz;
    Question question;
    protected int numQuestionsSoFar;
    protected int numCorrect;
    private QuizConsole quizConsole;

    private final String CR = System.getProperty("line.separator");

    public GraphicalQuizzer(Quiz quiz) {
        this.quiz = quiz;
    }
    /* (non-Javadoc)
     * @see com.jguice.quizzle.interfaces.Quizzer#doQuiz()
     */
    public void doQuiz() throws IOException {
        String userInput = "y";
        int numQuestionsLeft;

        quizConsole = new QuizConsole();

        quizConsole.getRepeatButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                getQuestion().doPrompt();
                quizConsole.requestAnswerFocus();
            }
        });

        // TODO Improve user input processing
        while (userInput.toLowerCase().equals("y") || userInput.toLowerCase().equals("r")) {
            if (userInput.toLowerCase().equals("y") || (quiz.getNumQuestions() - numCorrect) == 0)
                resetQuiz();
            else redoQuiz();
            quizConsole.getRepeatButton().setEnabled(true);

            while (quiz.hasQuestions()) {
                question = quiz.nextQuestion();
                numQuestionsSoFar++;
                numQuestionsLeft = quiz.getNumQuestions() - numQuestionsSoFar;
                quizConsole.appendMessage(question.doPrompt() + CR);

                userInput = quizConsole.getAnswer();

                if (question.checkAnswer(userInput)) {
                    doCorrectAction();
                    numCorrect++;
                }
                else {
                    doIncorrectAction();
                }

                if (numQuestionsLeft > 0) {
                        quizConsole.appendMessage("You have gotten " + numCorrect + " out of "
                                + numQuestionsSoFar + " so far (" + numQuestionsLeft
                            + " left)" + CR + CR);
                }
                else {
                    //System.out.println();
                    quizConsole.appendMessage(CR);
                }
            }
            quizConsole.getRepeatButton().setEnabled(false);

            // TODO Replace with a dialog
            quizConsole.appendMessage("You got " + numCorrect + " out of " + quiz.getNumQuestions()
                    + " possible" + CR);

            if ((quiz.getNumQuestions() - numCorrect) == 0)
                quizConsole.appendMessage("Try again? (y/n)?" + CR + CR);
            else
                quizConsole.appendMessage("Try again? (y/n) or Redo Missed (r)?" + CR + CR);

            userInput = quizConsole.getAnswer();
        }
        quiz.terminate();
        quizConsole.dispose();
    }

    public void resetQuiz() {
        quiz.reset();
        numQuestionsSoFar = 0;
        numCorrect = 0;
    }

    public void redoQuiz() {
        quiz.resetForRedo();
        numQuestionsSoFar = 0;
        numCorrect = 0;
    }

    public void endQuiz() {
        quiz.terminate();
    }

    public void doCorrectAction() {
        quizConsole.appendMessage("You got it!" + CR);
    }

    public void doIncorrectAction() {
        quizConsole.appendMessage("Sorry, you missed it." + CR);
        quiz.saveQuestionForRedo();
    }

    protected Question getQuestion() {
        return question;
    }
    /* (non-Javadoc)
     * @see com.jguice.quizzle.interfaces.Quizzer#getNumQuestions()
     */
    public int getNumQuestions() {
        return quiz.getNumQuestions();
    }
    /* (non-Javadoc)
     * @see com.jguice.quizzle.interfaces.Quizzer#getQuestionsSoFar()
     */
    public int getQuestionsSoFar() {
        return numQuestionsSoFar;
    }
    /* (non-Javadoc)
     * @see com.jguice.quizzle.interfaces.Quizzer#getNumCorrect()
     */
    public int getNumCorrect() {
        return numCorrect;
    }
}
