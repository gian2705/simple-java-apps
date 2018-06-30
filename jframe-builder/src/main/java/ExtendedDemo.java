import builder.TheFrame;

public class ExtendedDemo extends TheFrame{


  private ExtendedDemo(Builder builder) {
    super(builder);

  }

  public static void main(String[] args) {
    new ExtendedDemo.Builder().
            frameName("Extended Demo").
            frameHeight(300).
            frameWidth(450).
            makeResizable(false).
            build().setVisible(true);

  }

}
