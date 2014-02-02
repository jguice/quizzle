/*
 * Created on Nov 16, 2004
 *
 */
package com.jguice.quizzle.text;

import com.jguice.quizzle.interfaces.Question;
import com.jguice.quizzle.interfaces.Quiz;
import com.jguice.quizzle.interfaces.Quizzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author josh
 *
 */
public class TextQuizzer implements Quizzer {

	protected Quiz quiz;
	protected int numQuestionsSoFar;
	protected int numCorrect;
	
	public TextQuizzer(Quiz quiz) {
		this.quiz = quiz;
	}
	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Quizzer#doQuiz()
	 */
	public void doQuiz() throws IOException {
		Question question;
		String userInput = "y";
		int numQuestionsLeft;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Quiz Started");
        while (userInput.toLowerCase().equals("y")) {
            resetQuiz();

            while (quiz.hasQuestions()) {
                question = quiz.nextQuestion();
                numQuestionsSoFar++;
                numQuestionsLeft = quiz.getNumQuestions() - numQuestionsSoFar;
                question.doPrompt();

                userInput = br.readLine();

                if (question.checkAnswer(userInput)) {
                    doCorrectAction();
                    numCorrect++;
                }
                else {
                    doIncorrectAction();
                }

                if (numQuestionsLeft > 0) {
                        System.out.println("You have gotten " + numCorrect + " out of "
                                + numQuestionsSoFar + " so far (" + numQuestionsLeft
                            + " left)");
                }
                else {
                    System.out.println();
                }
            }

            System.out.println("You got " + numCorrect + " out of " + quiz.getNumQuestions()
                    + " possible");

            System.out.println("Try again? (y/n)");
            userInput = br.readLine();
        }
        quiz.terminate();
        System.out.println("Quiz Ended");
    }
	
	public void resetQuiz() {
		quiz.reset();
		numQuestionsSoFar = 0;
		numCorrect = 0;
	}
	
	public void doCorrectAction() {
		System.out.println ("You got it!");
	}
	
	public void doIncorrectAction() {
		System.out.println("Sorry, you missed it.");
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
