/*
 * Created on Nov 20, 2004
 *
 */
package com.jguice.quizzle.audio;

import com.sun.speech.freetts.Voice;
import com.jguice.quizzle.interfaces.Question;

/**
 * @author josh
 *
 */
public class AudioQuestion implements Question {
	
	protected String question;
	protected String answer;
	
	protected Voice voice;
	
	public AudioQuestion(Voice voice) {
		this.voice = voice;
	}
	
	public AudioQuestion(Voice voice, String question, String answer) {
		this.voice = voice;
		this.question = question;
		this.answer = answer;
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Question#doPrompt()
	 */
	public String doPrompt() {
		voice.speak(question);
        return "";
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Question#checkAnswer(java.lang.String)
	 */
	public boolean checkAnswer(String userInput) {
		if (userInput.equals(answer)) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Question#getQuestion()
	 */
	public String getQuestion() {
		return question;
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Question#setQuestion(java.lang.String)
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Question#getAnswer()
	 */
	public String getAnswer() {
		return answer;
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Question#setAnswer(java.lang.String)
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
