package uttt.game;

public interface GameRunnerInterface {

	/**
	 * Runs the game until it is finished. Ensures that the players move
	 * alternately. Player one starts always the game with its symbol according to
	 * the rules of ultimate tic-tac-toe.
	 * 
	 * Note: After each move the UI has to be updated via 'ui.updateScreen()'.
	 * 
	 * @param playerOne The first player in the game.
	 * @param playerTwo The second player in the game.
	 * @param ui        The ui that shows the game.
	 */
	public void run(PlayerInterface playerOne, PlayerInterface playerTwo, UserInterface ui)
			throws IllegalArgumentException;

}
