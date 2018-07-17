package builder;

public interface FrameFactory {
  TheFrame makeFrame();
  void  theLayout(TheFrame theFrame);
  void initComponents(TheFrame theFrame);
  void addComponents(TheFrame theFrame);
  void registerListeners(TheFrame theFrame);
}
