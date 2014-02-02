/*
 * Created on Nov 19, 2004
 *
 */
package com.jguice.quizzle;

import com.jguice.quizzle.interfaces.Quiz;
import com.jguice.quizzle.interfaces.Quizzer;
import com.jguice.quizzle.audio.AudioQuiz;
import com.jguice.quizzle.text.TextQuizzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/** TODO switch to maven (but still build fat jar?)
 *  TODO look for updated voice library with better pronunciation
 *  TODO integrate platform-specific distributions into maven pom
 *  TODO remove need to duplicate words when making spelling type test somehow
 */

/**
 * @author josh
 *
 */
public class Quizzle {
	
	private static Quiz quiz;
	private static Quizzer quizzer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		setupQuiz();
		String input = "y";
		int correct;
		int possible;
		
		while (input.toLowerCase().equals("y")) {
			quizzer.doQuiz();
			quizzer.resetQuiz();
			System.out.println("Try again? (y/n)");
			input = br.readLine();
		}
	}
	
	public static void setupQuiz() {
		File f = new File("/Users/josh/quiz.txt");
		quiz = null;
		
		try {
			quiz = new AudioQuiz(f);
			//quiz = new TextQuiz(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		quizzer = new TextQuizzer(quiz);
	}	
}
