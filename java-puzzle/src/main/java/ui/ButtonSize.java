package ui;

public enum ButtonSize {
  LEVEL_THREE(133),
  LEVEL_FOUR(100),
  LEVEL_FIVE(80);

  private final int buttonSize;

  ButtonSize(int buttonSize){

    this.buttonSize = buttonSize;
  }
  public int getButtonSize() {
    return buttonSize;
  }
}
