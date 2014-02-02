/*
 * Created on Nov 20, 2004
 *
 */
package com.jguice.quizzle.audio;

import com.jguice.quizzle.interfaces.Question;
import com.jguice.quizzle.interfaces.Quiz;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * @author josh
 *
 */
public class AudioQuiz implements Quiz {
	
	protected VoiceManager voiceManager;
	protected Voice voice;
	
	protected Vector questions;
	protected int[] seenQuestion;
	protected int questionsLeft;
	
	public AudioQuiz(File dataFile) throws IOException {
		voiceManager = VoiceManager.getInstance();
		voice = voiceManager.getVoice("kevin16");
		voice.allocate();

		questions = new Vector();
		questionsLeft = readDataFile(dataFile);
		seenQuestion = new int[questionsLeft];
		
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
        int questionNum = (int) (Math.random() * (double) questions.size());
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
		for (int i=0; i < questions.size(); i++) {
			seenQuestion[i] = 0;
		}
		
		questionsLeft = questions.size();

	}

    public void resetForRedo() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void saveQuestionForRedo() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private int readDataFile(File file) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line;
		
		while ((line = br.readLine()) != null) {
			Question q = new AudioQuestion(voice);
			
			q.setQuestion(line.trim());
			
			// TODO If there isn't a matched q/a this will fail.  Need to check line
			line = br.readLine();
			q.setAnswer(line.trim());
			
			questions.add(q);
		}
		
		return questions.size();
		
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Quiz#terminate()
	 */
	public void terminate() {
		voice.deallocate();
	}

	/* (non-Javadoc)
	 * @see com.jguice.quizzle.interfaces.Quiz#getNumQuestions()
	 */
	public int getNumQuestions() {
		return questions.size();
	}

}
