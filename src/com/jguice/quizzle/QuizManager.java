/*
 * Created on Nov 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.jguice.quizzle;

import com.jguice.quizzle.audio.AudioQuiz;
import com.jguice.quizzle.interfaces.Quiz;
import com.jguice.quizzle.text.TextQuiz;

import java.io.*;

/**
 * @author josh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QuizManager {
	
	File quizFolder;
	String[] quizNames;

    public QuizManager() {
		quizFolder = new File("data");
	}
	
	public Quiz getQuizByName(String name) throws IOException {
		for (int i = 0; i < quizNames.length; i++) {
			if (quizNames[i].equals(name + ".aqz")) {
				return setupAudioQuiz(quizNames[i]);
			}
			else if (quizNames[i].equals(name + ".tqz")) {
				return setupTextQuiz(quizNames[i]);
			}
		}
		return null;
	}

    public String[] getQuizNames() {
        updateQuizList();
        return quizNames;
    }

    public Quiz setupQuiz(String quizName) throws IOException {
        if (quizName.endsWith(".aqz"))
            return setupAudioQuiz(quizName);
        else if (quizName.endsWith(".tqz"))
            return setupTextQuiz(quizName);
        // This should never happen
        else
            return null;
    }

	private Quiz setupAudioQuiz(String quizName) throws IOException {
		File quizFile = new File(quizFolder + System.getProperty("file.separator") + quizName);
		Quiz quiz = new AudioQuiz(quizFile);
		return quiz;
	}
	
	private Quiz setupTextQuiz(String quizName) throws IOException {
		File quizFile = new File(quizFolder + System.getProperty("file.separator") + quizName);
		Quiz quiz = new TextQuiz(quizFile);
		return quiz;
	}

    public String getQuizData(String quizName) {
        File quizFile = new File(quizFolder + System.getProperty("file.separator") + quizName);

        StringBuffer quizData = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(quizFile) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        String line;

        try {
            while ((reader != null) && ( line = reader.readLine()) != null) {
                quizData.append(line);
                quizData.append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return quizData.toString();
    }

    public void setQuizData(String quizName, String data) {
        File quizFile = new File(quizFolder + System.getProperty("file.separator") + quizName);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(quizFile) );
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            // TODO Add else that logs fact that writer was null, need to add this everywhere this construct is used
            if (writer != null) writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public boolean removeQuiz(String quizName) {
        File quizFile = new File(quizFolder + System.getProperty("file.separator") + quizName);
        return quizFile.delete();
    }

    private void updateQuizList() {
        if (quizFolder.isDirectory() && quizFolder.canRead()) {

            FilenameFilter quizFilter = new FilenameFilter () {
                public boolean accept(File dir, String name) {
                    if (name.endsWith("qz")) { return true; }
                    return false;
                }
            };

            quizNames = quizFolder.list(quizFilter);
        }
        else {
            try {
                System.out.println(quizFolder.getCanonicalPath() + " not found...creating");
                if ((new File(quizFolder.getName())).mkdir()) {
                    System.out.println(quizFolder.getCanonicalPath() + " created successfully");
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
