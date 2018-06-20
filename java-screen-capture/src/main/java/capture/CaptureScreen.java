package capture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CaptureScreen {


  public CaptureScreen() {

  }

  public void captureScreen(String fileName) throws AWTException, IOException {

    new Thread(() -> {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Rectangle screenBounds = new Rectangle(screenSize);
      Robot screenCapture = null;
      try {
        screenCapture = new Robot();
      } catch (AWTException e) {
        e.printStackTrace();
      }
      BufferedImage bufferedImage = screenCapture.createScreenCapture(screenBounds);
      try {
        ImageIO.write(bufferedImage,"png",new File(fileName));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();

  }
}
