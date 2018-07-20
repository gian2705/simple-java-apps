
import ui.LevelChooser;

import javax.swing.*;

/**
 * The Class containing meain method
 */
public class App {
  public static void main(String[] args){

    //Getting the OS default look and feel
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (IllegalAccessException |
             ClassNotFoundException |
             UnsupportedLookAndFeelException |
             InstantiationException e) {
      e.printStackTrace();
    }

    SwingUtilities.invokeLater(()
            -> new LevelChooser().setVisible(true));
  }
}
