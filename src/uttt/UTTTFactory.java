package uttt;

import uttt.game.BoardImplementation;
import uttt.game.BoardInterface;
import uttt.game.MarkImplementation;
import uttt.game.MarkInterface;
import uttt.game.Player;
import uttt.game.PlayerInterface;
import uttt.game.Simulator;
import uttt.game.SimulatorInterface;
import uttt.game.UserInterface;
import uttt.ui.GUI;
import uttt.utils.Symbol;

public class UTTTFactory {

	/**
	 * Create a Ultimate TicTacToe mark on board index j.
	 *
	 * @param symbol The symbol for the new mark.
	 * @param j      The index of the piece in its board.
	 *
	 * @return A Ultimate TicTacToe mark.
	 */
	public static MarkInterface createMark(Symbol symbol, int j) throws IllegalArgumentException {
		 return new MarkImplementation(symbol, j);
	}

	/**
	 * Create a Ultimate TicTacToe board.
	 *
	 * @return A Ultimate TicTacToe board.
	 */
	public static BoardInterface createBoard() {
		return new BoardImplementation();
	}

	/**
	 * Create a Ultimate TicTacToe simulator.
	 *
	 * @return A Ultimate TicTacToe simulator.
	 */
	public static SimulatorInterface createSimulator() {
		return new Simulator();
	}

	/**
	 * Create a user interface for a Ultimate TicTacToe simulator.
	 *
	 * @param symbol Indicates if a simple TicTacTac Toe UI or Ultimate TicTacToe
	 *               UI should be started.
	 * 
	 * @return A graphical user interface for a Ultimate TicTacToe simulator.
	 */
	public static UserInterface createUserInterface(boolean playUTTT) {
		return new GUI(playUTTT);
	}

	/**
	 * Create a Human Ultimate TicTacToe player.
	 *
	 * @param symbol The symbol the player uses during the game.
	 *
	 * @return A Ultimate TicTacToe player that uses the user interface to
	 *         communicate with the human player to select moves.
	 */
	public static PlayerInterface createHumanPlayer(Symbol symbol) {
		return new Player(symbol);
	}

	/**
	 * 
	 * !!! Relevant for BONUS exercise only. !!!
	 * 
	 * Create a Computer Ultimate TicTacToe player.
	 *
	 * @param symbol The symbol the player uses during the game.
	 *
	 * @return A Ultimate TicTacToe player that will play automatically.
	 */
	public static PlayerInterface createBonusPlayer(Symbol symbol) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
