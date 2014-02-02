/*
 * Created on Nov 19, 2004
 *
 */
package com.jguice.quizzle.text;

import com.jguice.quizzle.interfaces.Question;
import com.jguice.quizzle.interfaces.Quiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * @author josh
 *
 */
public class TextQuiz implements Quiz {
	
	protected Vector questions;
	protected int[] seenQuestion;
    protected int[] redoQuestion;
	protected int questionsLeft;
    protected int questionNum;
    protected int numQuestions;

	public TextQuiz (File dataFile) throws IOException {
		questions = new Vector();
		numQuestions = questionsLeft = readDataFile(dataFile);
		seenQuestion = new int[questionsLeft];
        redoQuestion = new int[questionsLeft];
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Quiz#hasQuestions()
	 */
	public boolean hasQuestions() {
		if (questionsLeft > 0) {
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Quiz#nextQuestion()
	 */
	public Question nextQuestion() {
        // TODO Need to add a way to just get redo questions
        questionNum = (int) (Math.random() * (double) questions.size());
        while (seenQuestion[questionNum] == 1) { 
        		questionNum = (int) (Math.random() * (double) questions.size());
        	}

        seenQuestion[questionNum] = 1;
        questionsLeft--;

        return (Question) questions.elementAt(questionNum);
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Quiz#reset()
	 */
	public void reset() {
        // TODO This should reset any redo state
		for (int i=0; i < questions.size(); i++) {
			seenQuestion[i] = redoQuestion[i] = 0;
		}
		
		numQuestions = questionsLeft = questions.size();
	}

    // TODO Not sure if this is the best way...just need to somehow denote we want redo questions from nextQuestion
    public void resetForRedo() {
        int questionCount = 0;

        for (int i=0; i < questions.size(); i++) {
            if (redoQuestion[i] == 1) {
                seenQuestion[i] = 0;
                redoQuestion[i] = 0;
                questionCount++;
            }
            else{
                seenQuestion[i] = 1;
            }
        }

        numQuestions = questionsLeft = questionCount;
    }

    public void saveQuestionForRedo() {
        redoQuestion[questionNum] = 1;
    }

	private int readDataFile(File file) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line,question,answer = null;
        int lineNo = 0;

		while (true) {
            line = br.readLine();
            if (line != null) lineNo++;
            else break;

            if (line.trim().equals("")) continue;

			question = line.trim();

            while (true) {
                line = br.readLine();
                if (line != null) lineNo++;
                else {
                    System.out.println("Warning!  EOF on line " + lineNo + " in file " + file.getName()
                            + ".  Last Question (" + question + ") has no Answer...omitting");
                    return questions.size();
                }

                if (line.trim().equals("")) continue;

                answer = line.trim();
                break;
            }

            Question q = new TextQuestion();
            q.setQuestion(question);
            q.setAnswer(answer);

			questions.add(q);
		}
		
		return questions.size();
		
	}
	
	public void terminate() {
		
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Quiz#getNumQuestions()
	 */
	public int getNumQuestions() {
		return numQuestions;
	}

}
