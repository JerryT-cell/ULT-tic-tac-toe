package uttt.tests.otherTests;

import static org.junit.Assert.assertNotNull;

import uttt.UTTTFactory;
import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
import uttt.utils.Symbol;

public class UtilsForTests {
 
    /**
     * Generates mark array with crosses and circles according to the indices array
     * @param indices 0 - empty mark, 1 - cross, 2 - circle
     * @return
     */
    public MarkInterface[] generateMarkArray(int[] indices) {
        MarkInterface marks[] = new MarkInterface[indices.length];

        for (int i = 0; i < indices.length; i++) {
            switch (indices[i]) {
                case 0:
                    marks[i] = UTTTFactory.createMark(Symbol.EMPTY, i);
                    break;
                case 1:
                    marks[i] = UTTTFactory.createMark(Symbol.CROSS, i);
                    break;
                case 2:
                    marks[i] = UTTTFactory.createMark(Symbol.CIRCLE, i);
                    break;
                default:
                    break;
            }
        }
        return marks;
    }

    public BoardInterface generateBoard(int[] markArray) {
        BoardInterface board = UTTTFactory.createBoard();
        assertNotNull(board);
        board.setMarks(generateMarkArray(markArray));
        return board;
    }

    public BoardInterface[] generateBoardArray(int[][] markArrays) {
        BoardInterface boards[] = new BoardInterface[markArrays.length];

        for (int i = 0; i < markArrays.length; i++) {
            boards[i] = UTTTFactory.createBoard();
            assertNotNull(boards[i]);
            boards[i].setMarks(generateMarkArray(markArrays[i]));
        }
        return boards;
    }

    public Symbol numToSymbol(int i) throws IllegalArgumentException {
        switch (i) {
            case 0:
                return Symbol.EMPTY;
            case 1:
                return Symbol.CROSS;
            case 2:
                return Symbol.CIRCLE;
            default:
                throw new IllegalArgumentException();
        }
    }

}
