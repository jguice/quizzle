/*
 * Created on Nov 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.jguice.quizzle.interfaces;

import java.io.IOException;

/**
 * @author josh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Quizzer {
	public abstract void doQuiz() throws IOException;
	public abstract void doCorrectAction();
	public abstract void doIncorrectAction();
	public abstract int getNumQuestions();
	public abstract int getQuestionsSoFar();
	public abstract int getNumCorrect();
	public abstract void resetQuiz();
}
