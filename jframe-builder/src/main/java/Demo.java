import builder.TheFrame;

public class Demo extends TheFrame {
  private Demo(Builder builder) {
    super(builder);
  }

  public static void main(String[] args) {
    new Demo.Builder().build().setVisible(true);
  }
}
