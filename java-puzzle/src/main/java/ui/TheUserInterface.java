package ui;

import utils.Index2D;
import utils.RandomNumberGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TheUserInterface extends JFrame implements ActionListener {

  private ArrayList<ImageIcon> icons;
  private ArrayList<URL> urls;

  private ClassLoader classLoader;

  private int BUTTON_SIZE= ButtonSize.LEVEL_FOUR.getButtonSize();
  private final int BUTTON_SPACE=1;
  private JButton [][] btnImages;

  private void setBUTTON_SIZE(int level){
    if (level==3){
      this.BUTTON_SIZE=ButtonSize.LEVEL_THREE.getButtonSize();
    }
    else if(level==4){
      this.BUTTON_SIZE=ButtonSize.LEVEL_FOUR.getButtonSize();
    }
    else if(level ==5){
      this.BUTTON_SIZE=ButtonSize.LEVEL_FIVE.getButtonSize();
    }
  }

  public int getBUTTON_SIZE() {
    return BUTTON_SIZE;
  }

  //TODO: Game will be 9,16 or 25 pieces of the puzzle
  //TODO: Size can take number 3 ,4 or 5

  private int gameSize;

  /**
   * Getter Method For Game Size (Game Level)
   * @return (int) the game level
   */
  public int getGameSize() {
    return gameSize;
  }

  public void setGameSize(int gameSize) {
    this.gameSize = gameSize;
  }

  private Index2D keyIndex;

  private Index2D getKeyIndex() {
    return keyIndex;
  }
  private void setKeyIndex(Index2D keyIndex) {
    this.keyIndex = keyIndex;
  }


  private JButton[][] getButtons() {
    return btnImages;
  }

  private JButton getButton(int i, int j){
    return this.btnImages[i][j];
  }

  private ArrayList<ImageIcon> getIcons() {
    return icons;
  }

  private void setIcons(ArrayList<ImageIcon> icons) {
    this.icons = icons;
  }

  private ArrayList<URL> getUrls() {
    return urls;
  }

  private void setUrls(ArrayList<URL> urls) {
    this.urls = urls;
  }

  /**
   * @param level Level of the game
   * @return ArrayList of URLs where images used in a particular game level are found
   */

  private ArrayList<URL> imagesUrls(int level){
    ArrayList<URL> urls = new ArrayList<>();
    for (int i = 1; i <= level; i++) {
      for (int j = 1; j <=level; j++) {
        urls.add(classLoader.getResource("img/level"+level+"/row-"+i+"-col-"+j+".jpg"));
      }
    }
    return urls;
  }

  /**
   *
   * @param level Game Level
   * @return ArrayList of Icons to be used in a particular game level
   */

  private ArrayList<ImageIcon> imageIcons(int level){
    ArrayList<ImageIcon> icons
            = new ArrayList<>();

    for (int i = 0; i < (int)Math.pow(getGameSize(),2); i++) {
      icons.add(new ImageIcon(getUrls().get(i)));
    }

    return icons;
  }


  public TheUserInterface(int gameSize) {
    this.gameSize=gameSize;

    classLoader = Thread.currentThread().getContextClassLoader();
    setBUTTON_SIZE(getGameSize());

    setUrls(imagesUrls(getGameSize()));
    //getUrls().forEach(url -> System.out.println(url));
    setIcons(imageIcons(getGameSize()));

    btnImages = new JButton[getGameSize()][getGameSize()];

    setLayout(null);
    Runnable initComponentsThread = this::initComponents;
    Runnable addButtonsThread = this::addButtons;
    initComponentsThread.run();
    addButtonsThread.run();

    registerButtonsListener();

    setTheFrame();
  }

  private void initComponents() {

    RandomNumberGenerator rng =
            new RandomNumberGenerator();
    rng.setLimit((int)Math.pow(getGameSize(),2));
    List<Integer> randomInts = new ArrayList<>(rng.getRandomInteger());
    //@param value Keep Track Of Buttons Sequentially from 0 --> size power 2 (16);

    int value;
    for (int i = 0; i <getGameSize(); i++) {
      for (int j = 0; j <getGameSize() ; j++) {
        //@param key biggest randomly generated value
        int key = (int) Math.pow(getGameSize(),2);
        value =getGameSize()*i + j;

        if((randomInts.get(value)+1)==key){
          setKeyIndex(new Index2D(i,j));
          System.out.println("Key Index-->["+getKeyIndex().getXIndex()+","+getKeyIndex().getYIndex()+"]");

        }

        if(randomInts.get(value)+1 == (int)Math.pow(getGameSize(),2)){
          btnImages[i][j] = new JButton();
          continue;
        }

        btnImages[i][j] = new JButton(getIcons().get(randomInts.get(value)));

        /*SAFE MODE


        Uncomment this piece of code if You Want To Launch The game with all pieces
        correctly arranged. Before That You Should Comment lines 126 t0 133*/
        //btnImages[i][j] = new JButton(getIcons().get(value));

      }
    }

    //lbFullImage = new JLabel(new ImageIcon(Objects.requireNonNull(classLoader.getResource("img/waalove.jpeg"))));
}

  private void addButtons() {

    int x_pos;
    int y_pos=0;

    for (int i = 0; i <getGameSize() ; i++) {
      x_pos=0;
      for (int j = 0; j < getGameSize(); j++) {
        btnImages[i][j].setBounds(x_pos,y_pos,getBUTTON_SIZE(),getBUTTON_SIZE());
        add(btnImages[i][j]);
        x_pos+=getBUTTON_SIZE() +BUTTON_SPACE;

      }

      y_pos+=getBUTTON_SIZE()+BUTTON_SPACE;
    }

    //int x = SIZE*(BUTTON_SIZE*BUTTON_SPACE)-BUTTON_SPACE+10;

   // lbFullImage.setBounds(x,2,400,400);
   // add(lbFullImage);


  }

  /**
   * This method registers the action listener to all btnImages
   */
  private void registerButtonsListener() {
    for (int i = 0; i <getGameSize() ; i++) {
      for (int j = 0; j < getGameSize(); j++) {
        btnImages[i][j].addActionListener(this);
      }
    }

  }

  private void setTheFrame() {
    setTitle("The PuzzleX");
    setSize(getGameSize()*(BUTTON_SPACE+getBUTTON_SIZE()),getGameSize()*(BUTTON_SPACE+getBUTTON_SIZE())+38);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    Index2D pressedIndex = getPressedButtonIndices(e,getButtons());
    assert pressedIndex != null;
    int pressedXIndex = pressedIndex.getXIndex();
    int pressedYIndex = pressedIndex.getYIndex();

    //System.out.println("Pressed Button Index-->["+pressedIndex.getXIndex()+","+pressedIndex.getYIndex()+"]");

    if(new Index2D().compareIndex2D(pressedIndex,getKeyIndex())){
      error();
    }else{

      //Check Nearby Indices
      ArrayList<Index2D> nearIndices
              = new Index2D().nearbyIndices(getGameSize(),pressedIndex);
      //Print Nearby Indices
      // nearIndices.forEach(index->new Index2D().printIndex(index));

      /*
       * Option<Index2D> lst below equal null if the nearby indices do not
       * contains Index2D that resembles the key Index2D which is actually
       * the key button itself
       */
      Optional<Index2D> lst = nearIndices.stream()
              .filter(p->new Index2D().compareIndex2D(p,getKeyIndex())).findFirst();
      Index2D theKey  = lst.orElse(null);

      /*
        Option<Index2D> lst above contains either null or 1 Index2D value which
        is the index of the key button.
        If it is not null then the move (ImageIcon swapping) is allowed.
        Index of the key is then updated.
       */

      if(theKey!=null){
        new Index2D().printIndex(theKey);
        JButton b = (JButton) e.getSource();
        ImageIcon tempIcon = (ImageIcon) b.getIcon();
        b.setIcon(null);
        btnImages[getKeyIndex().getXIndex()][getKeyIndex().getYIndex()].setIcon(tempIcon);
        setKeyIndex(new Index2D(pressedXIndex,pressedYIndex));
        new Index2D().printIndex(getKeyIndex());

      }else {
        error();
      }
    }

    /*
        Checking The Game Completion
        If the game is complete
              -->Embed a last piece of the Image
              -->Freeze (Disable) all neighbouring Indices2D
              -->Show A Pop Up Message
     */

    if(new Index2D().compareIndex2D(pressedIndex,new Index2D((getGameSize()-1),(getGameSize()-1)))){
      if(isGameComplete()){
        System.out.println("[COMPLETE]");

        btnImages[getGameSize()-1][getGameSize()-1].setIcon(new ImageIcon(Objects.requireNonNull(classLoader.getResource("img/row-4-col-4.jpg"))));
        //Freeze Neighbours
        //int x=getGameSize()-1;
        //int x1 = x-2;
        btnImages[2][3].setEnabled(false);
        btnImages[3][2].setEnabled(false);
        showGameOver();
      }else{
        System.out.println("[NOT COMPLETE]");
      }
    }

  }


  /**
   * This Method is called every time the last Index2D is pressed and checks if it was a final move
   * @return true if the game is complete and false if not
   */

  private boolean isGameComplete(){

    boolean a= true;

    int _1DIndex;
    for (int i = 0; i <getGameSize(); i++) {
      for (int j = 0; j <getGameSize() ; j++) {
        _1DIndex=getGameSize()*i + j;

        ImageIcon icn = (ImageIcon) getButton(i,j).getIcon();
        ImageIcon icn2 = getIcons().get(_1DIndex);

        if(i==getGameSize()-1 && j==getGameSize()-1){
          continue;
        }

        if(!(icn.equals(icn2))){
          a=false;
        }

      }
    }
    return a;
  }

  /**
   * This method is called to identify the position of the pressed button(in Index2D format)
   * @param e ActionEvent of the button pressed
   * @param btnImages pressed button
   * @return Index2D value of the pressed button in a matrix
   */
  private Index2D getPressedButtonIndices(ActionEvent e, JButton [][] btnImages){
    for (int i = 0; i <getGameSize() ; i++) {
      for (int j = 0; j <getGameSize(); j++) {
        if(e.getSource()==btnImages[i][j]){
          return  new Index2D(i,j);
        }
      }
    }
    return null;
  }

  /**
   * When called this method sound a default OS error beep.
   */
  private void error(){
    Toolkit.getDefaultToolkit().beep();
  }

  /**
   * This Method launches a JDialog its called when game is over
   */

  private void showGameOver() {

    Object[] options = {"RESTART","EXIT"};
    int n = JOptionPane.showOptionDialog(null,"WELL DONE!!",
            "GAME IS OVER",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[1]);
    if(n==JOptionPane.YES_OPTION) {
      new TheUserInterface(getGameSize());
      setVisible(true);
      this. dispose();
    }

    if (n==JOptionPane.NO_OPTION) {
      System.exit(0);
    }

  }//end method showGameOver

}
