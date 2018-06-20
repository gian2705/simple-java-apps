import ui.TheUserInterface;

import javax.swing.*;

public class Main {
  public static void main(String[] args) {

    try{
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (IllegalAccessException |
            UnsupportedLookAndFeelException |
            InstantiationException |
            ClassNotFoundException e) {
      e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> new TheUserInterface().setVisible(true));
  }
}
