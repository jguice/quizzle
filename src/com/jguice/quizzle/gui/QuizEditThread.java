package com.jguice.quizzle.gui;

import com.jguice.quizzle.QuizzleGUI;

/**
 * $Log: not supported by cvs2svn $
 *
 * @author $Author: josh $
 * @version $Version$
 */
public class QuizEditThread extends Thread {
    private String quizName;

    public QuizEditThread () {
        this.quizName = QuizzleGUI.getForm().getSelectedQuizName();
    }
    public QuizEditThread (String quizName) {
        this.quizName = quizName;
    }
    public void run() {
            String quizContents = QuizzleGUI.getManager().getQuizData(quizName);
            new QuizEditor(quizName,quizContents);
    }
}
