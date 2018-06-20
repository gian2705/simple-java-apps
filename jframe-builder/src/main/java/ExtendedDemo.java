import builder.FrameFactory;
import builder.TheFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtendedDemo extends TheFrame implements FrameFactory ,ActionListener{

  private JButton btnOne;
  private JButton btnTwo;

  private ExtendedDemo(Builder builder) {
    super(builder);

  }

  public static void main(String[] args) {
    TheFrame frame= new ExtendedDemo.Builder().
            frameName("Extended Demo").
            frameHeight(300).
            frameWidth(450).
            makeResizable(false).
            build();
    frame.frameLayout();
    frame.initComponents();
    frame.addComponents();
    frame.registerListeners();
    frame.setVisible(true);
  }

  @Override
  public void frameLayout() {
    super.frameLayout();
    setLayout(new GridLayout(1,0));
  }

  @Override
  public void initComponents() {
    super.initComponents();
    btnOne=new JButton("ONE");
    btnTwo=new JButton("TWO");
  }

  @Override
  public void addComponents() {
    super.addComponents();

    add(btnOne);
    add(btnTwo);
  }

  @Override
  public void registerListeners() {
    super.registerListeners();
    btnOne.addActionListener(this);
    btnTwo.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();
    if(actionCommand.equals("ONE"))
      System.out.println("Button 1 Clicked");

    else if(actionCommand.equals("TWO"))
      System.out.println("Button 2 Clicked");
  }
}
