package uttt.game;

import uttt.UTTTFactory;
import uttt.utils.Symbol;

/*
 *  0  1  2
 *  3  4  5
 *  6  7  8
 * 
 */

public class BoardImplementation implements BoardInterface {

    private MarkInterface[] marks;
    private Symbol winnerSymbol ;
    public static int BOARD_LENGHT = 9;


    public BoardImplementation(){
        marks = new MarkInterface[9];
        for(int i = 0; i<9; i++){
            marks[i]= UTTTFactory.createMark(Symbol.EMPTY, i);
        }
        winnerSymbol = Symbol.EMPTY;

    }

    @Override
    public MarkInterface[] getMarks() {
      MarkInterface[] boardmarks = new MarkInterface[9];
      for(int i =0; i<BoardImplementation.BOARD_LENGHT;i++){
           boardmarks[i] = this.marks[i];
      }
       return boardmarks;
    }

    @Override
    public void setMarks(MarkInterface[] marks) throws IllegalArgumentException {
          if(marks==null ||marks.length>9||marks.length<0){
            throw new IllegalArgumentException();
          }
          for(int i =0; i<marks.length;i++){
            if(marks[i]==null){
                throw new IllegalArgumentException();  
            }
            setMarkAt(marks[i]);
          }
          calWinner();
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int markIndex) throws IllegalArgumentException {
       MarkInterface mark = UTTTFactory.createMark(symbol, markIndex); //all mark checks done inside here
       if(winnerSymbol != Symbol.EMPTY){
        return false;
       }
       if (!marks[markIndex].getSymbol().equals(Symbol.EMPTY)){
             return false;
       }
       this.marks[markIndex]= mark;
       calWinner();
      
       return true;
    }

  
    public boolean setMarkAt(MarkInterface mark) throws IllegalArgumentException {
      /* if (!marks[mark.getPosition()].getSymbol().equals(Symbol.EMPTY)){
             return false;
       }*/
       this.marks[mark.getPosition()]= mark;
       calWinner();
       return true;
    }

    @Override
    public boolean isClosed() {
         /*There is then a winning symbol */
      if (winnerSymbol != Symbol.EMPTY) {
          return true;
      }
        if( calWinner()!= Symbol.EMPTY){
        return true;
         }
      for (int i = 0; i < 9; i++) {
          if (marks[i].getSymbol() == Symbol.EMPTY) {
              return false;
          }
      }
      return true;
    }

 
    private Symbol calWinner() {
        // implemented
        Symbol winner = Symbol.EMPTY;
        for (int i = 0; i < 3; i++) {
            if ((winner = rowWon(i)) != Symbol.EMPTY   ||
                (winner = columnWon(i)) != Symbol.EMPTY||
                (winner = diagonalWon(i)) != Symbol.EMPTY) {
               
                break;
            }
           
        }
        this.winnerSymbol = winner;
        return winner;
    }

    private Symbol rowWon(int rowIndex) {
        rowIndex *= 3;
        if (marks[rowIndex].getSymbol() == marks[rowIndex + 1].getSymbol() && marks[rowIndex + 1].getSymbol() == marks[rowIndex + 2].getSymbol()) {
            return marks[rowIndex].getSymbol();
        }
        return Symbol.EMPTY;
    }

    private Symbol columnWon(int columnIndex) {
        if (marks[columnIndex].getSymbol() == marks[columnIndex + 3].getSymbol() && marks[columnIndex + 3].getSymbol() == marks[columnIndex + 6].getSymbol()) {
            return marks[columnIndex].getSymbol();
        }
        return Symbol.EMPTY;
    }


    /**
     * Checks if a diagonal is won by a player
     * @param side 0 - top left to bottom right, 1 - bottom left to top right
     * @return Symbol of winner, EMPTY if no winner or side > 2
     */
    private Symbol diagonalWon(int side) {
      if (side > 2) {
          return Symbol.EMPTY;
      }

      int indexAdder = side == 0 ? 4 : -2;
      side = side == 0 ? 0 : 6;

      if (marks[side].getSymbol() == marks[side + indexAdder].getSymbol() && marks[side].getSymbol() == marks[side + 2*indexAdder].getSymbol()) {
          return marks[side].getSymbol();
      }
      return Symbol.EMPTY;
  }



    @Override
    public boolean isMovePossible(int markIndex) throws IllegalArgumentException {
         if(winnerSymbol != Symbol.EMPTY){
          return false;
         }
        
        if(markIndex<0||markIndex>8){
            throw new IllegalArgumentException("The markIndex is wrong");
          }

       if (!marks[markIndex].getSymbol().equals(Symbol.EMPTY)){
            return false;
      }
      return true;
    }

    @Override
    public Symbol getWinner() {
        return this.winnerSymbol;
    }
   





    
}
