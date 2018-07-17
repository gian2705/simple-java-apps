import builder.TheFrame;

public class ExtendedDemo{


  public static void main(String[] args) {

    new TheFrame.Builder()
            .frameName("Extended Demo")
            .frameHeight(400)
            .frameWidth(600)
            .makeResizable(false)
            .isUndecorated(true)
            .build();

  }

}
