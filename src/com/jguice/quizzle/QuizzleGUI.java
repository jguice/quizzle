package com.jguice.quizzle;

import com.jguice.quizzle.gui.GraphicalQuizzer;
import com.jguice.quizzle.gui.QuizEditThread;
import com.jguice.quizzle.interfaces.Quiz;
import com.jguice.quizzle.types.QuizType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: Jan 18, 2005
 * Time: 11:01:14 PM
 */
public class QuizzleGUI {
    private static QuizzleGUI form;
    private static JFrame frame;
    private static QuizManager manager;

    private JButton editButton;
    private JButton deleteButton;
    private JButton addButton;
    private JButton startButton;

    private JSplitPane mainPane;
    private JList quizList;


    public QuizzleGUI() {
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Add");
                String quizBaseName, quizExt, quizName;
                quizBaseName = JOptionPane.showInputDialog(mainPane,"Enter Quiz Name","Quiz Name",
                        JOptionPane.QUESTION_MESSAGE);
                if (quizBaseName == null || quizBaseName.trim().length() == 0) { return; }

                // TODO Get Quiz Types from somewhere else
                QuizType audioQuiz = new QuizType("Audio","aqz");
                QuizType textQuiz = new QuizType("Text","tqz");
                QuizType[] quizTypes = {audioQuiz, textQuiz};

                QuizType quizType = (QuizType)JOptionPane.showInputDialog(mainPane,"Choose Quiz Type",
                "Quiz Type Selection", JOptionPane.INFORMATION_MESSAGE, null,
                quizTypes, quizTypes[0]);
                if (quizType == null) { return; }

                quizExt = quizType.getExtension();

                quizName = quizBaseName + "." + quizExt;
                manager.setQuizData(quizName,"");
                quizList.setListData(manager.getQuizNames());

                QuizEditThread editThread = new QuizEditThread(quizName);
                editThread.start();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Delete");
                if (! manager.removeQuiz(getSelectedQuizName())) {
                    //System.out.println("Error removing " + quizName);
                }
                else {
                    //System.out.println("Removed " + quizName);
                }
                quizList.setListData(manager.getQuizNames());
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Edit");
                // TODO Change this to a password field
                String password = JOptionPane.showInputDialog(mainPane,"Enter Password","Protected Area",
                        JOptionPane.QUESTION_MESSAGE);
                if (password != null && password.equals("nocheating")) {
                    QuizEditThread editThread = new QuizEditThread();
                    editThread.start();
                }

            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Start");
                class QuizRunThread extends Thread {
                    public void run() {
                        try {
                            Quiz quiz = manager.setupQuiz(getSelectedQuizName());
                            GraphicalQuizzer quizzer = new GraphicalQuizzer(quiz);
                            quizzer.doQuiz();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                if (getSelectedQuizName() != null) {
                    QuizRunThread runThread = new QuizRunThread();
                    runThread.start();
                }
            }
        });
    }

    public static void main(String[] args) {

        form = new QuizzleGUI();

        frame = new JFrame("Quizzle");
        frame.setContentPane(form.mainPane);

        manager = new QuizManager();


        Object[] quizzes = manager.getQuizNames();
        if (quizzes != null)
            form.quizList.setListData(quizzes);

        frame.pack();
        frame.setSize(320,240);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.show();
    }

    public static QuizManager getManager() {
        return manager;
    }

    public static QuizzleGUI getForm() {
        return form;
    }

    public static JFrame getMainFrame() {
        return frame;
    }

    public String getSelectedQuizName() {
        if (quizList.getSelectedValue() == null) return null;
        return quizList.getSelectedValue().toString();
    }
}
