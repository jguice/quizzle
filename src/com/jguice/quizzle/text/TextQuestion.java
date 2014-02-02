/*
 * Created on Nov 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.jguice.quizzle.text;

import com.jguice.quizzle.interfaces.Question;

/**
 * @author josh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TextQuestion implements Question {
	
	protected String question;
	protected String answer;
	
	public TextQuestion() {}
	
	public TextQuestion(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Question#doPrompt()
	 */
	public String doPrompt() {
		// TODO Auto-generated method stub
		return question;

	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Question#checkAnswer(java.lang.String)
	 */
	public boolean checkAnswer(String userInput) {
		if (userInput.equals(answer)) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return Returns the answer.
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer The answer to set.
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * @return Returns the question.
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question The question to set.
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
}
