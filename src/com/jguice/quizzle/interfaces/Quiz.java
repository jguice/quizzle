/*
 * Created on Nov 16, 2004
 *
 */
package com.jguice.quizzle.interfaces;



/**
 * @author josh
 *
 */
public interface Quiz {
	public abstract boolean hasQuestions();

	public abstract Question nextQuestion();
	
	public abstract int getNumQuestions();
	
	public abstract void reset();

    public abstract void resetForRedo();

    public abstract void saveQuestionForRedo();

	// TODO determine necessity of this
	public abstract void terminate();
}