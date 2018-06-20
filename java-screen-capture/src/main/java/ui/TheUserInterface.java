package ui;

import capture.CaptureScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TheUserInterface extends JFrame implements ActionListener {

  private JPanel pnl;
  private JButton btnCapture;
  private JTextField txtImageName;


  public TheUserInterface(){
    initComponents();
    addComponents();
    registerListener();
    setTheFrame();
  }

  private void initComponents() {
    pnl = new JPanel(new GridLayout(1,0));
    btnCapture=new JButton("Capture");
    txtImageName = new JTextField("",15);
  }

  private void addComponents() {
    pnl.add(txtImageName);
    pnl.add(btnCapture);
    add(pnl);
  }

  private void registerListener() {
    btnCapture.addActionListener(this);
    txtImageName.addActionListener(this);
  }

  private void setTheFrame() {
    add(pnl);
    setTitle("Screen Capture");
    setSize(400,80);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    String actionCommand = event.getActionCommand();
    if(actionCommand.equals("Capture")){
      CaptureScreen captureScreen = new CaptureScreen();
      try {
        captureScreen.captureScreen(txtImageName.getText().concat(".png"));
        System.out.println(txtImageName.getText().concat(".png"));

      } catch (AWTException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
