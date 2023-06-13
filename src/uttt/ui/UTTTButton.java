package uttt.ui;

import javax.swing.*;

import uttt.utils.Move;

import java.awt.*;

public class UTTTButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Move move;

	public UTTTButton(int boardIndex, int pieceIndex) { // creating blank board
		move = new Move(boardIndex, pieceIndex);
		setFont(new Font("Dialog", 1, 40));
	}

	public Move getMove() {
		return move;
	}
}
