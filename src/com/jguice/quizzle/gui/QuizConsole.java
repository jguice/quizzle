package com.jguice.quizzle.gui;

import com.jguice.quizzle.QuizzleGUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: Jan 21, 2005
 * Time: 7:27:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class QuizConsole {
    private JFrame frame;
    private JPanel quizConsolePanel;
    private JTextArea messageText;
    private JTextField answerField;
    private JButton okButton;
    private JButton repeatButton;

    private boolean answerComplete;
    private String answer;

    public QuizConsole() {
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                doAnswer();
            }
        });
        answerField.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent event) {
            }

            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    doAnswer();
                }
            }

            public void keyReleased(KeyEvent event) {
            }
        });

        frame = new JFrame("Quiz Console");
        frame.getContentPane().add(quizConsolePanel);
        frame.setLocationRelativeTo(QuizzleGUI.getMainFrame());
        // TODO Put sizes in preferences
        frame.setSize(320,240);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.show();
    }

    private void doAnswer() {
        answer = answerField.getText();
        answerField.setText("");
        appendMessage(answer + System.getProperty("line.separator"));
        answerComplete = true;
    }

    public void appendMessage(String message) {
        messageText.append(message);
        messageText.setCaretPosition(messageText.getDocument().getLength());
    }

    public String getAnswer() {
        requestAnswerFocus();
        while (! answerComplete) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        answerComplete = false;
        return answer;
    }

    public void requestAnswerFocus() {
        answerField.requestFocus();
    }

    public JButton getRepeatButton() {
        // TODO return a listener instead?
        return repeatButton;
    }

    public void dispose() {
        frame.dispose();
    }
}
