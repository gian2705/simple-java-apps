
import ui.LevelChooser;

import javax.swing.*;

public class App {
  public static void main(String[] args){
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (IllegalAccessException |
             ClassNotFoundException |
             UnsupportedLookAndFeelException |
             InstantiationException e) {
      e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> new LevelChooser().setVisible(true));
  }
}
