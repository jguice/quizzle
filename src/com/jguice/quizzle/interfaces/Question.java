/*
 * Created on Nov 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.jguice.quizzle.interfaces;

/**
 * @author josh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Question {
	
	public abstract String doPrompt();
	public abstract boolean checkAnswer(String userInput);
	
	public abstract String getQuestion();
	public abstract void setQuestion(String question);
	
	public abstract String getAnswer();
	public abstract void setAnswer(String answer);
}