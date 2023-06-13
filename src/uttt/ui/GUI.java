package uttt.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
import uttt.game.SimulatorInterface;
import uttt.game.UserInterface;
import uttt.utils.Move;
import uttt.utils.Symbol;

public class GUI implements UserInterface {

	private Move UserMoveChoice = null;

	private final int PANEL_AMOUNT;
	private final UTTTButton[][] buttons; 
	private final JPanel[] panels; 

	private Symbol currentPlayer = Symbol.CROSS;
	private String label = "Turn of Player with Symbol: ";
	private JLabel jLabel = new JLabel(label + currentPlayer);
	
	public GUI(boolean playUTTT) {
		if (playUTTT) {
			PANEL_AMOUNT = 9;
		} else {
			PANEL_AMOUNT = 1;
		}
		buttons = new UTTTButton[PANEL_AMOUNT][9];
		panels = new JPanel[PANEL_AMOUNT];
		gamePanel();
	}

	public void gamePanel() {
		JFrame frame = new JFrame("Ultimate Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel areaAndResultsPane = new JPanel();
		areaAndResultsPane.setLayout(new BoxLayout(areaAndResultsPane, BoxLayout.PAGE_AXIS));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(PANEL_AMOUNT == 1 ? 1 : 3, PANEL_AMOUNT == 1 ? 1 : 3));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		mainPanel.setBackground(Color.black);

		for (int i = 0; i < PANEL_AMOUNT; i++) {
			JPanel panel = new JPanel(); // creating a panel with a box like a tic tac toe board
			panel.setLayout(new GridLayout(3, 3));
			panel.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
			panel.setBackground(Color.white);
			mainPanel.add(panel);
			panels[i] = panel;
		}

		for (int i = 0; i < PANEL_AMOUNT; i++) {
			for (int j = 0; j < 9; j++) { // placing the button onto the board
				buttons[i][j] = new UTTTButton(i, j);
				buttons[i][j].addActionListener(this::buttonAction);
				panels[i].add(buttons[i][j]);
			}
		}
		JTextArea textArea = new JTextArea("Turn of Player with Symbol:" + Symbol.CROSS);
		textArea.setFont(new Font("Serif", Font.ITALIC, 16));

		areaAndResultsPane.add(mainPanel);
		areaAndResultsPane.add(jLabel);
		frame.add(areaAndResultsPane);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(800, 800);// set frame size and let the game begin
	}

	public void clearButtons() {
		for (int i = 0; i < PANEL_AMOUNT; i++) {
			for (int j = 0; j < 9; j++) {
				buttons[i][j].setText("");
			}
		}
	}

	@Override
	public Move getUserMove() {
		synchronized (this) {
			UserMoveChoice = null;
			try {
				while (UserMoveChoice == null)
					this.wait(50);
				return UserMoveChoice;
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		return null;
	}

	@Override
	public void showGameOverScreen(Symbol winner) {
		JOptionPane.showMessageDialog(null, winner.toString() + " has won the game.", "Game Over", 0);
	}

	@Override
	public void updateScreen(SimulatorInterface game) {
		BoardInterface[] boards = game.getBoards();
		for (int boardIndex = 0; boardIndex < 9; boardIndex++) {
			panels[boardIndex].setBorder(BorderFactory.createLineBorder(Color.gray, 5));
		}
		int boardIndex = game.getIndexNextBoard();
		if (boardIndex == -1) {
			for (boardIndex = 0; boardIndex < 9; boardIndex++) {
				panels[boardIndex].setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
			}
		} else {
			panels[boardIndex].setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
		}
		int j = -1;
		for (int i = 0; i < boards.length; i++) {
			BoardInterface b = boards[i];
			MarkInterface[] pieces = b.getMarks();
			for (MarkInterface p : pieces) {
				j = p.getPosition();
				String symbol = p.getSymbol().toString();
				this.buttons[i][j].setText(symbol);

			}
			if (b.isClosed()) {
				panels[i].setBorder(BorderFactory.createLineBorder(Color.gray, 5));
			}
		}
		currentPlayer = game.getCurrentPlayerSymbol();
		jLabel.setText(label + currentPlayer);
	}


	@Override
	public void updateScreen(BoardInterface game) {
		MarkInterface[] pieces = game.getMarks();
		panels[0].setBorder(BorderFactory.createLineBorder(Color.gray, 5));

		for (MarkInterface p : pieces) {
			String symbol = p.getSymbol().toString();
			this.buttons[0][p.getPosition()].setText(symbol);
		}
		
		jLabel.setText("");
	}

	@Override
	public String getUserInput(String Question, String[] PossibleAnswers) {
		while (true) {
			String Answer = JOptionPane.showInputDialog(Question, "");

			for (String PossibleAnswer : PossibleAnswers)
				if (PossibleAnswer.equals(Answer))
					return Answer;
		}
	}

	public void buttonAction(ActionEvent e) {
		UTTTButton source = (UTTTButton) e.getSource();
		UserMoveChoice = source.getMove();

	}
}
