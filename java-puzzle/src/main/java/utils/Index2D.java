package utils;

import java.util.ArrayList;

/**
 * @author iampius
 */

public class Index2D {
  private int xIndex;
  private int yIndex;

  public Index2D() {
  }

  public Index2D(int xIndex, int yIndex) {
    this.xIndex = xIndex;
    this.yIndex = yIndex;
  }

  public int getXIndex() {
    return xIndex;
  }

  public void setXIndex(int xIndex) {
    this.xIndex = xIndex;
  }

  public int getYIndex() {
    return yIndex;
  }

  public void setYIndex(int yIndex) {
    this.yIndex = yIndex;
  }

  public boolean compareIndex2D(Index2D a, Index2D b){
    return a.getXIndex() == b.getXIndex() && a.getYIndex() == b.getYIndex();
  }

  /**
   * @param index2D Index2D value to be printed
   */
  public void printIndex(Index2D index2D){
    System.out.println("Index > ["+index2D.getXIndex()+","+index2D.getYIndex()+"]");
  }

  /**
   * Used To Find nearby Index2D's
   * @param matrixSize the size of the matrix under inspection
   * @param index2D the 2D index value to be examined
   * @return ArrayList of Index2D values that neighbors the Index2D value examined
   */

  public ArrayList<Index2D> nearbyIndices(int matrixSize, Index2D index2D){

    if(index2D.getXIndex()>=matrixSize || index2D.getYIndex()>=matrixSize){
      System.out.println("Index and Matrix Size Do Not Match");
      System.exit(0);
    }
    ArrayList<Index2D> list
            = new ArrayList<>();

    for (int dx = -1; dx <=1; dx++) {
      for (int dy = -1;dy <=1; dy++) {

        /*
        Invalid : dx==dy e.g [1,1] ,[0,0]
        Invalid : dx+dy =0; eg [1,-1] ,
        */
        int checkValidity = dx+dy;
        if((dx==dy)||(checkValidity==0)){
          continue;
        }

        int x = index2D.getXIndex()+dx;
        int y = index2D.getYIndex()+dy;

        if(x>=matrixSize || y>=matrixSize || x<0 || y<0){
          continue;
        }
        list.add(new Index2D(x,y));
      }
    }

    //list.forEach(this::printIndex);

    return list;
  }

  /**
   *
   * @param value ID index to be compared
   * @param index2D 2D index to be compared
   * @param matrixSize size of the matrix
   * @return true if the 1D value represent the same position as the Index2D value in the matrix
   */

  public boolean valueEqualsIndex(int value, Index2D index2D, int matrixSize){
    return value == matrixSize * index2D.getXIndex() + index2D.getYIndex();
  }

  /**
   * This method converts the Index2D value into ID value
   * @param matrixSize size of the matrix
   * @param index2D Index2D value to be transformed
   * @return 1D index value that equals 2D
   */
  public int index2DToID(int matrixSize, Index2D index2D){
    return matrixSize*index2D.getXIndex() + index2D.getYIndex();
  }

  /*public static void main(String[] args) {

    System.out.println("Enter X");
    int x = new Scanner(System.in).nextInt();
    System.out.println("Enter Y");
    int y= new Scanner(System.in).nextInt();
    ArrayList<Index2D> list =
            new ArrayList<>(new Index2D().nearbyIndices(6,new Index2D(x,y)));


  }*/
}
