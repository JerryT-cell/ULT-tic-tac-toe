package uttt.game;

import uttt.UTTTFactory;
import uttt.utils.Move;
import uttt.utils.Symbol;

public class Simulator implements SimulatorInterface {

    private BoardInterface[] boards;
    private static int BOARDS_LENGHT = 9;
    private Symbol currentPlayerSymbol;
    private int nextBoardIndex;
    private Symbol winner;
 


    public Simulator(){
     boards = new BoardInterface[9];
     for(int i= 0; i<BOARDS_LENGHT; i++){
        boards[i] = UTTTFactory.createBoard();
     }
     nextBoardIndex = -1;
     currentPlayerSymbol = Symbol.EMPTY;

    }

    @Override
    public void run(PlayerInterface playerOne, PlayerInterface playerTwo, UserInterface ui)
            throws IllegalArgumentException {
        // implemented
        if (playerOne == null || playerTwo == null || ui == null) {
            throw new IllegalArgumentException();
        }

        setCurrentPlayerSymbol(playerOne.getSymbol());
        setIndexNextBoard(-1);
        PlayerInterface currentPlayer = playerOne;
        do {
            Move playerMove = currentPlayer.getPlayerMove(this, ui);
            if (!setMarkAt(currentPlayer.getSymbol(), playerMove.getBoardIndex(), playerMove.getMarkIndex())) {
                ui.updateScreen(this);
                continue;
            }
            currentPlayerSymbol = currentPlayerSymbol.flip();
            currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
            ui.updateScreen(this);
        } while (!isGameOver());

        ui.showGameOverScreen(getWinner());
        ui.updateScreen(this);
    }


    @Override
    public BoardInterface[] getBoards() {
        return boards;
    }

    @Override
    public void setBoards(BoardInterface[] boards) throws IllegalArgumentException {
        if(boards.length !=9){
            throw new IllegalArgumentException("not sufficient boards");
        }
        for(int i = 0; i < Simulator.BOARDS_LENGHT;i++){
            if(boards[i]==null){
                throw new IllegalArgumentException("one of the boards is null");
            }
            this.boards[i] = boards[i];
        }
    }

    @Override
    public Symbol getCurrentPlayerSymbol() {
        return currentPlayerSymbol;
    }

    @Override
    public void setCurrentPlayerSymbol(Symbol symbol) throws IllegalArgumentException {
       
        currentPlayerSymbol = symbol;
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int boardIndex, int markIndex) throws IllegalArgumentException {
        if(symbol != currentPlayerSymbol ){
            throw new IllegalArgumentException("The symbol is not that of the current player");
        }
        if(boardIndex<0||boardIndex>8){
            throw new IllegalArgumentException("The boardindex is wrong");
        }
        if(nextBoardIndex != boardIndex && nextBoardIndex != -1){
            return false;
        }
        boolean couldSet = this.boards[boardIndex].setMarkAt(symbol, markIndex);
        if(!couldSet){
            return false;
        }
        if(this.boards[markIndex].isClosed()){
            nextBoardIndex = -1;
        }else{
            nextBoardIndex = markIndex;
        }

        return true;
        
    }

    @Override
    public int getIndexNextBoard() {
       return nextBoardIndex;
    }

    @Override
    public void setIndexNextBoard(int index) throws IllegalArgumentException {
        if(index<-1||index>8)
         throw new IllegalArgumentException("Index is incorrect");
        if(index == -1){
            nextBoardIndex = -1;
            return;
        }
        if(nextBoardIndex==-1)
          nextBoardIndex = index; 
        if(!this.boards[index].isClosed()){
            nextBoardIndex = index;
        }
    }
    

    @Override
    public boolean isGameOver() {
        if(calWinner() != Symbol.EMPTY){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isMovePossible(int boardIndex) throws IllegalArgumentException {
         // implemented
         if (boardIndex < 0 || boardIndex > 8) {
            throw new IllegalArgumentException();
        }
        if (isGameOver()) {
            return false;
        }
        if (nextBoardIndex != -1 && boardIndex != nextBoardIndex) {
            return false;
        }
        return !boards[boardIndex].isClosed();

    }

    @Override
    public boolean isMovePossible(int boardIndex, int markIndex) throws IllegalArgumentException {
        if(boardIndex<0||boardIndex>8|markIndex<0||markIndex>8){
            throw new IllegalArgumentException("the index is not correct");
        }
        if (!isMovePossible(boardIndex)) {
            return false;
        }
        if(this.boards[boardIndex].isMovePossible(markIndex)){
            return true;
        }
        return false;

    }

    @Override
    public Symbol getWinner() {
        return this.winner;
    }


    public Symbol calWinner() {
        // implemented
        Symbol winner = Symbol.EMPTY;
        for (int i = 0; i < 3; i++) {
            if ((winner = rowWon(i)) != Symbol.EMPTY   ||
                (winner = columnWon(i)) != Symbol.EMPTY||
                (winner = diagonalWon(i)) != Symbol.EMPTY) {
               
                break;
            }
           
        }
        this.winner= winner;
        return winner;
    }

    private Symbol rowWon(int rowIndex) {
        rowIndex *= 3;
        if(!this.boards[rowIndex].isClosed()||!this.boards[rowIndex+1].isClosed()||!this.boards[rowIndex+2].isClosed()){
            return Symbol.EMPTY;
        }
        if (this.boards[rowIndex].getWinner() == this.boards[rowIndex + 1].getWinner() && this.boards[rowIndex + 1].getWinner()  == this.boards[rowIndex + 2].getWinner() ) {
            return this.boards[rowIndex].getWinner();
        }
        return Symbol.EMPTY;
    }

    private Symbol columnWon(int columnIndex) {
        if(!this.boards[columnIndex].isClosed()||!this.boards[columnIndex+3].isClosed()||!this.boards[columnIndex+6].isClosed()){
            return Symbol.EMPTY;
        }
        if (this.boards[columnIndex].getWinner() == this.boards[columnIndex + 3].getWinner() && this.boards[columnIndex+ 3].getWinner()  == this.boards[columnIndex + 6].getWinner() ) {
            return this.boards[columnIndex].getWinner();
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

        if (!this.boards[side].isClosed()|| !this.boards[side + indexAdder].isClosed()|| !this.boards[side + 2*indexAdder].isClosed()){
           return Symbol.EMPTY;
        }
  
        if (this.boards[side].getWinner() == this.boards[side + indexAdder].getWinner() && this.boards[side].getWinner() == this.boards[side + 2*indexAdder].getWinner()) {
            return this.boards[side].getWinner();
        }
        return Symbol.EMPTY;
    }
    
}
