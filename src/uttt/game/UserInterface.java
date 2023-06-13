package uttt.game;

import uttt.utils.Move;
import uttt.utils.Symbol;

public interface UserInterface {

	/**
	 * Allow the user to choose the next move direction.
	 * 
	 * @return The move direction chosen by the user.
	 */
	public Move getUserMove();

	/**
	 * Show a "Game Over" screen to the user.
	 * 
	 * @param game The winner Symbol after the game is over.
	 */
	public void showGameOverScreen(Symbol winner);

	/**
	 * Update the current state of the board visible to the user.
	 *
	 * @param game The current UltimateTicTacToe game instance.
	 */
	public void updateScreen(SimulatorInterface game);

	/**
	 * !!! Relevant for BONUS exercise only. !!!
	 * 
	 * Update the current state of the board visible to the user.
	 *
	 * @param game The current TicTacToe game instance.
	 */
	void updateScreen(BoardInterface game);

	/**
	 * Allow the user to answer a specific question.
	 * 
	 * @param string        The question the user is supposed to answer.
	 * @param possibleAnswers The acceptable answers.
	 * 
	 * @return The answer by the user that was in PossibleAnswers.
	 * 
	 *         Note: This function will not be tested but might allow you to easily
	 *         test and present your project later. (see also DEBUG_ASK_USER in
	 *         Main.java).
	 */
	public String getUserInput(String string, String[] possibleAnswers);

}
