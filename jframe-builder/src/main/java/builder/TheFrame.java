package builder;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TheFrame extends JFrame  {

  int pX,pY;

  private String frameName;
  private int frameWidth;
  private int frameHeight;
  private boolean makeResizable;
  private boolean isUndecorated;

  private TheFrame(Builder builder){
    this.isUndecorated=builder.isUndecorated;
    this.frameName = builder.frameName;
    this.frameWidth = builder.frameWidth;
    this.frameHeight = builder.frameHeight;
    this.makeResizable = builder.makeResizable;
    setFrame();

  }


  private void setFrame() {
    setTitle(frameName);
    setSize(frameWidth,frameHeight);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        pX= getX();
        pY= getY();
      }
    });

    addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        setLocation(getLocation().x+getX()-pX, getLocation().y+getY()-pY);
      }
    });
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setUndecorated(isUndecorated);
    setResizable(makeResizable);
    setVisible(true);
  }


  public static class Builder{
    private String frameName="";
    private int frameWidth=200;
    private int frameHeight=200;
    private boolean makeResizable=true;
    private boolean isUndecorated=false;

    public Builder() {

    }


    public Builder frameName(String frameName){
      this.frameName=frameName;
      return this;
    }

    public Builder frameWidth(int frameWidth){
      this.frameWidth=frameWidth;
      return this;
    }

    public Builder frameHeight(int frameHeight){
      this.frameHeight=frameHeight;
      return this;
    }
    public Builder makeResizable(boolean makeResizable){
      this.makeResizable=makeResizable;
      return this;
    }

    public Builder isUndecorated (boolean isUndecorated){
      this.isUndecorated=isUndecorated;
      return this;
    }

    public TheFrame build(){

      return new TheFrame(this);
    }
  }///end of Builder class

  public String getFrameName() {
    return frameName;
  }

  public int getFrameWidth() {
    return frameWidth;
  }

  public int getFrameHeight() {
    return frameHeight;
  }

  public boolean isFrameResizable() {
    return makeResizable;
  }

  public boolean isUndecorated() {
    return isUndecorated;
  }


  public TheFrame getFrame(){
    return this;
  }


  /**
   * This method prints the frame details in the form as shown here
   * [FrameName, FrameHeight,FrameWidth,IsFrameResizable,IsFrameUndecorated]
   * [String, int, int, boolean, boolean]
   */
  public void printDetails(){
    System.out.println("["+frameName+ ","+frameHeight+","+frameWidth+","+makeResizable+","+isUndecorated+"]");
  }
}
