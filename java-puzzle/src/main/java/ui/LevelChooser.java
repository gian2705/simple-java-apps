package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelChooser extends JFrame implements ActionListener {

  private JPanel pnl;
  private JButton btnPlay;
  private JRadioButton rbLevelOne;
  private JRadioButton rbLevelTwo;
  private JRadioButton rbLevelThree;
  private ButtonGroup buttonGroup;

  private int levelChosen;

  private String levelOne = "Level 3";
  private String levelTwo= "Level 4";
  private String levelThree = "Level 5";
  private String playCommand = "Play";

  private int getLevelChosen() {
    return levelChosen;
  }

  private void setLevelChosen(int levelChosen) {
    this.levelChosen = levelChosen;
  }

  public LevelChooser(){
    initComponents();
    addComponents();
    registerListenerForButtons();
    setTheFrame();
  }

  private void initComponents(){
    pnl=new JPanel(new GridLayout(1,0));
    btnPlay=new JButton(playCommand);

    //Unable the play button initially;
    btnPlay.setEnabled(false);

    rbLevelOne=new JRadioButton(levelOne);
    rbLevelTwo=new JRadioButton(levelTwo);
    rbLevelThree=new JRadioButton(levelThree);
    buttonGroup=new ButtonGroup();
  }


  private void addComponents(){
    buttonGroup.add(rbLevelOne);
    buttonGroup.add(rbLevelTwo);
    buttonGroup.add(rbLevelThree);

    pnl.add(rbLevelOne);
    pnl.add(rbLevelTwo);
    pnl.add(rbLevelThree);
    pnl.add(btnPlay);
  }


  private void setTheFrame(){

    add(pnl);
    setTitle("Choose Game Level");
    setSize(400,80);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
  }

  private void registerListenerForButtons(){
    rbLevelTwo.addActionListener(this);
    rbLevelOne.addActionListener(this);
    rbLevelThree.addActionListener(this);
    btnPlay.addActionListener(this);
  }
  @Override
  public void actionPerformed(ActionEvent event) {


    if(event.getSource()== rbLevelOne){

      btnPlay.setEnabled(true);
    }

    else if(event.getSource()==rbLevelTwo){

      btnPlay.setEnabled(true);
    }

    else if(event.getSource()==rbLevelThree){

      btnPlay.setEnabled(true);
    }
    String actionCommand = event.getActionCommand();

    if(actionCommand.equals(levelOne)){
      setLevelChosen(3);

    }else if(actionCommand.equals(levelTwo)){
      setLevelChosen(4);

    }else if(actionCommand.equals(levelThree)){
      setLevelChosen(5);

    }
    if(actionCommand.equals(playCommand)){

      SwingUtilities.invokeLater(() -> new TheUserInterface(getLevelChosen()).setVisible(true));
      this.dispose();
    }

  }

}
