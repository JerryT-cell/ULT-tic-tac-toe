package uttt.game;

import uttt.utils.Move;
import uttt.utils.Symbol;

public interface PlayerInterface {

	/**
	 * Gets the symbol of the player.
	 * 
	 * @return The symbol the player has assigned.
	 * 
	 *         Note: Player one uses 'X' and player two uses 'O'.
	 */
	public Symbol getSymbol();

	/**
	 * Allows the player (human or computer) to choose the next move direction.
	 * 
	 * @param game The current UltimateTicTacToe game instance.
	 * @param ui   The user interface allows for communication. Hint: ui might be
	 *             null if it is meant for the AI Player.
	 * 
	 * @return The coordinate chosen by the user to set their mark.
	 * 
	 *         Hint: If it is a human player, one gets the move of the human via
	 *         'ui.getUserMove()'
	 */
	Move getPlayerMove(SimulatorInterface game, UserInterface ui) throws IllegalArgumentException;

	/**
	 * !!! Relevant for BONUS exercise only. !!!
	 * 
	 * Allows to simulate computer moves for a (non-Ultimate) TicTacToe game.
	 * The default implementation is non-sense and serves the sole purpose that you can
	 * ignore this method in case you do not want to implement the bonus exercise.
	 * 
	 * @param game The current TicTacToe game instance (as single board).
	 * 
	 * @return The coordinate chosen by the user to set their mark.
	 * 
	 */
	default int getPlayerMove(BoardInterface game, UserInterface ui) {
		return -1; // invalid move
	}
}
