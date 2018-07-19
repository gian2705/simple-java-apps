package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressMonitor extends JFrame implements ActionListener {

  private int numOfMoves;
  private String timeElapsed;

  private JLabel lbNumOfMoves;
  private JLabel lbTimeElapsed;

  private JButton btRestart;
  private JButton btExit;

  public int getNumOfMoves() {
    return numOfMoves;
  }

  public void setNumOfMoves(int numOfMoves) {
    this.numOfMoves = numOfMoves;
  }

  ProgressMonitor(){
    setTheLayout();

    Runnable initComponentsThread = this::initComponents;
    Runnable addButtonsThread = this::addComponents;
    initComponentsThread.run();
    addButtonsThread.run();

    registerButtonsListener();

    setTheFrame();
  }

  private void setTheLayout(){

  }

  private void initComponents() {

  }

  private void addComponents() {

  }

  private void registerButtonsListener() {

  }

  private void setTheFrame() {
    setSize(100,100);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
