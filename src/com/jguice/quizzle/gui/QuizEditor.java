package com.jguice.quizzle.gui;

import com.jguice.quizzle.QuizzleGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: Jan 19, 2005
 * Time: 11:19:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuizEditor {
    private JPanel quizEditPanel;
    private JTextArea quizEditTextArea;
    private JButton dontSaveButton;
    private JButton saveButton;

    private String quizName;

    final JFrame editFrame;

    public QuizEditor(final String quizName,String data) {
        this.quizName = quizName;
        quizEditTextArea.setText(data);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Save");
                QuizzleGUI.getManager().setQuizData(quizName,quizEditTextArea.getText());
                editFrame.dispose();
            }
        });

        dontSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Don't Save");
                editFrame.dispose();
            }
        });

        editFrame = new JFrame("Edit Quiz");
        editFrame.setContentPane(quizEditPanel);
        editFrame.setLocationRelativeTo(QuizzleGUI.getMainFrame());
        editFrame.pack();
        editFrame.setSize(320,240);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.show();
    }

}
