package uttt.game;

import uttt.utils.Symbol;

public interface SimulatorInterface extends GameRunnerInterface {

	/**
	 * Get all boards on the game-board.
	 * 
	 * @return The boards on the game-board.
	 */
	public BoardInterface[] getBoards();

	/**
	 * Sets all boards in the simulator.
	 * 
	 * Note: Convenient for testing. Not allowed to use in the AI.
	 * 
	 * @param boards The boards for the simulator.
	 * 
	 */
	public void setBoards(BoardInterface[] boards) throws IllegalArgumentException;

	/**
	 * Gets the symbol of the current player.
	 * 
	 * @return The symbol of the current player.
	 */
	public Symbol getCurrentPlayerSymbol();

	/**
	 * Sets the symbol of the current player.
	 * 
	 * Note: Convenient for testing. Not allowed to use in the AI.
	 * 
	 * @param symbol The symbol for the current player.
	 * 
	 */
	public void setCurrentPlayerSymbol(Symbol symbol) throws IllegalArgumentException;

	/**
	 * Sets the symbol of a mark at the given position (markIndex) on the
	 * boardIndex-th board. Note: The symbol has to be that from the current player.
	 * 
	 * After setting a mark the index for the next mark is also determined according
	 * to the rules of ultimate tic-tac-toe.
	 * 
	 * @param boardIndex the index of the board
	 * @param markIndex  the index of the field on the board.
	 * 
	 * @return If the mark was correctly added.
	 */
	public boolean setMarkAt(Symbol symbol, int boardIndex, int markIndex) throws IllegalArgumentException;

	/**
	 * 
	 * @return The index of the next board to play on.
	 * 
	 *         Note: Returns -1 if there is no restriction for the next move.
	 */
	public int getIndexNextBoard();

	/**
	 * 
	 * Note: Convenient for testing. Not allowed to use in the AI.
	 * 
	 * @param index The index of the next board to play on.
	 * 
	 *              Note: index has to satisfy the board boundaries; is allowed to
	 *              be -1 if the next board is not fixed.
	 */
	public void setIndexNextBoard(int index) throws IllegalArgumentException;

	/**
	 * Tells if the game is over.
	 * 
	 * @return True if a player has won the game or there is no valid move left on
	 *         the boards, false otherwise.
	 */
	public boolean isGameOver();

	/**
	 * Tells if the next move is possible on board boardIndex according to the rules
	 * of ultimate tic-tac-toe.
	 * 
	 * 
	 * @param boardIndex The index of the board to check if a move is possible.
	 * 
	 * @return If on board at position boardIndex is a move possible.
	 * 
	 *         Note: The last mark location is important.
	 */
	public boolean isMovePossible(int boardIndex) throws IllegalArgumentException;

	/**
	 * Tells if its possible to add a mark at markIndex on board boardIndex.
	 * 
	 * @param boardIndex The index of the board on which to check if a piece can be
	 *                   added.
	 * @param markIndex  The location where to check if a move is possible.
	 * 
	 * @return If at position markIndex on the boardIndexth board is a move
	 *         possible.
	 * 
	 *         Note: The last mark location is important.
	 */
	public boolean isMovePossible(int boardIndex, int markIndex) throws IllegalArgumentException;

	/**
	 * Gets the winner symbol of the game.
	 * 
	 * Note: 'Empty' if it is a draw or the game is not over yet.
	 * 
	 * @return The symbol of the winner.
	 */
	public Symbol getWinner();

	/**
	 * 
	 * !!! Relevant for BONUS exercise only. !!!
	 * 
	 * Overrides: hashCode() in Object
	 * 
	 * @return a hash code value for this object.
	 * 
	 */
	public int hashCode();

	/**
	 * 
	 * !!! Relevant for BONUS exercise only. !!!
	 * 
	 * Overrides: equals(...) in Object
	 * 
	 * @param obj the reference object with which to compare.
	 * 
	 * @return true if this object is the same as the obj argument; false otherwise.
	 * 
	 */
	public boolean equals(Object obj);

}
