import builder.TheFrame;

public class ExtendedDemo{


  public static void main(String[] args) {
    TheFrame theFrame = new TheFrame.Builder()
            .frameName("Extended Demo")
            .frameHeight(400)
            .frameWidth(600)
            .makeResizable(false)
            .isUndecorated(false)
            .build();
    theFrame.setVisible(true);


    System.out.println("[" + theFrame.getFrameName()+","+theFrame.getFrameHeight()+","+
            theFrame.getFrameWidth()+","+theFrame.isFrameResizable()+","+theFrame.isUndecorated()+"]");
    theFrame.printDetails();
  }

}
