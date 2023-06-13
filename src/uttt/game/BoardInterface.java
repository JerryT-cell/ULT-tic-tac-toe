package uttt.game;

import uttt.utils.Symbol;

public interface BoardInterface extends GameRunnerInterface {

	/**
	 * Gets copies of all marks on the board.
	 *
	 * @return Copies of all marks on the board.
	 *
	 *         Note: Empty cells have marks with symbol 'Empty'.
	 */
	public MarkInterface[] getMarks();

	/**
	 * Sets all marks on the board.
	 *
	 *
	 * Note: Convenient for testing. Not allowed to use in the AI.
	 */
	public void setMarks(MarkInterface[] marks) throws IllegalArgumentException;

	/**
	 * Sets the symbol of a mark at the given position (markIndex) on the board.
	 * Note: If the field is already occupied the mark does not change its symbol.
	 *
	 * @param symbol    The symbol the mark shall use.
	 * @param markIndex the index of the field on the board.
	 *
	 * @return If the mark was correctly added.
	 */
	public boolean setMarkAt(Symbol symbol, int markIndex) throws IllegalArgumentException;

	/**
	 * Tells if the board is already won by a player or a tie.
	 *
	 * @return True if a player has won the board or there is no valid move left on
	 *         the board, false otherwise.
	 */
	public boolean isClosed();

	/**
	 * Tells if its possible to add a mark at markIndex.
	 *
	 * @param markIndex The location where to check if a move is possible.
	 *
	 * @return If at position markIndex on this board is a move possible.
	 */
	public boolean isMovePossible(int markIndex) throws IllegalArgumentException;

	/**
	 * Gets the winner symbol of the board.
	 *
	 * @return The symbol of the winner.
	 *
	 *         Note: A board that is finished (full or won) gets the winning symbol
	 *         assigned or 'Empty' if it is a tie. An unfinished board also returns
	 *         'Empty'.
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
	
	/**
	 * !!! Relevant for BONUS exercise only. !!!
	 * 
	 * Allows to simulate computer moves for a (non-Ultimate) TicTacToe game.
	 * The default implementation is non-sense and serves the sole purpose that you can
	 * ignore this method in case you do not want to implement the bonus exercise.
	 * 
	 * @param playerOne The first player in the game.
	 * @param playerTwo The second player in the game.
	 * @param ui        The ui that shows the game.
	 */
	@Override
	default public void run(PlayerInterface playerOne, PlayerInterface playerTwo, UserInterface ui)
			throws IllegalArgumentException {
		// do nothing
	}
}
