import builder.TheFrame;

import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    //SwingUtilities.invokeLater(() -> new TheFrame.Builder().build().setVisible(true));

    SwingUtilities.invokeLater(() -> new TheFrame.Builder()
           .frameName("Demo").frameWidth(500)
           .frameHeight(200)
           .makeResizable(false)
            .isUndecorated(false)
           .build().setVisible(true));
  }
}
