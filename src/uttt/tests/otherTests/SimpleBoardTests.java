package uttt.tests.otherTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
import uttt.utils.Symbol;

public class SimpleBoardTests {
    
    UtilsForTests utils = new UtilsForTests();
    BoardInterface board;

    @Before
    public void setUp() {
        board = UTTTFactory.createBoard();
        assertNotNull(board);
    }

    /**
     * Tests whether all marks of a newly created board are correctly set to EMPTY marks at the correct position
     */
    @Test
    public void testEmptyBoard() {
        MarkInterface marks[] = board.getMarks();
        assertNotNull(marks);

        assertTrue(9 == marks.length);
        for(int i = 0; i < 9; i++) {
            assertEquals(Symbol.EMPTY, marks[i].getSymbol());
            assertTrue(marks[i].getPosition() == i);
        }
    }

    /**
     * Tests whether the setMarks() method correctly sets the marks array or rejects it with an IllegalArgumentException if either length or content is wrong
     */
    @Test
    public void testSetMarks() {
        MarkInterface invalidMarksNullElements[] = new MarkInterface[9];
        MarkInterface invalidMarksLength[] = new MarkInterface[10];
        MarkInterface validMarks[] = new MarkInterface[9];
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                invalidMarksNullElements[i] = UTTTFactory.createMark(Symbol.CIRCLE, i);
                invalidMarksLength[i] = UTTTFactory.createMark(Symbol.CROSS, i);
                validMarks[i] = UTTTFactory.createMark(Symbol.CIRCLE, i);
            } else {
                invalidMarksLength[i] = UTTTFactory.createMark(Symbol.EMPTY, i);
                validMarks[i] = UTTTFactory.createMark(Symbol.CROSS, i);
            }
        }
        invalidMarksLength[9] = UTTTFactory.createMark(Symbol.CIRCLE, 8);

        assertThrows(IllegalArgumentException.class, () -> {
            board.setMarks(invalidMarksNullElements);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board.setMarks(invalidMarksLength);
        });

        board.setMarks(validMarks);
        for (int i = 0; i < 9; i++) {
            assertEquals(validMarks[i].getSymbol(), board.getMarks()[i].getSymbol());
        }
    }

    @Test
    public void testSetMarkAt() {
        assertTrue(board.setMarkAt(Symbol.CROSS, 0));
        assertTrue(board.setMarkAt(Symbol.CIRCLE, 4));
        assertTrue(board.setMarkAt(Symbol.CIRCLE, 8));
        checkMarkAt(board, Symbol.CROSS, 0);
        checkMarkAt(board, Symbol.CIRCLE, 4);
        checkMarkAt(board, Symbol.CIRCLE, 8);

        assertFalse(board.setMarkAt(Symbol.CROSS, 0));
        assertFalse(board.setMarkAt(Symbol.CROSS, 4));
        // assertFalse(board.setMarkAt(Symbol.EMPTY, 1));  // TODO: laut testruns -> AssertionError: null??
        // assertFalse(board.setMarkAt(Symbol.EMPTY, 0));  // TODO: oder ist das hier eine IllegalArgumentException?

        assertThrows(IllegalArgumentException.class, () -> {
            board.setMarkAt(Symbol.CIRCLE, 9);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board.setMarkAt(Symbol.CROSS, -1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board.setMarkAt(null, 1);
        });
    }
    /**
     *  cross empty empty
     *  empty cross empty
     *  empty empty cross
     */

    @Test
    public void testIsClosedAndWinningSymbol() {
        // closed tests
        assertFalse(board.isClosed());
        board.setMarks(utils.generateMarkArray(new int[]{1,0,0,0,1,0,0,0,1}));
        assertTrue(board.isClosed());
        assertEquals(Symbol.CROSS, board.getWinner());
        resetBoard(board);

        assertFalse(board.isClosed());
        board.setMarks(utils.generateMarkArray(new int[]{1,1,1,0,2,0,0,0,2}));
        assertTrue(board.isClosed());
        assertEquals(Symbol.CROSS, board.getWinner());
        resetBoard(board);

        assertFalse(board.isClosed());
        board.setMarks(utils.generateMarkArray(new int[]{0,0,2,0,2,0,2,0,1}));
        assertTrue(board.isClosed());
        assertEquals(Symbol.CIRCLE, board.getWinner());
        resetBoard(board);

        assertFalse(board.isClosed());
        board.setMarks(utils.generateMarkArray(new int[]{1,2,2,2,1,1,1,2,2}));  // draw
        assertTrue(board.isClosed());
        assertEquals(Symbol.EMPTY, board.getWinner());
        resetBoard(board);

        assertFalse(board.isClosed());
        board.setMarks(utils.generateMarkArray(new int[]{1,0,0,1,0,0,1,0,0}));
        assertTrue(board.isClosed());
        assertEquals(Symbol.CROSS, board.getWinner());
        resetBoard(board);

        // non-closed tests
        assertFalse(board.isClosed());
        board.setMarks(utils.generateMarkArray(new int[]{2,2,0,0,1,2,0,0,1}));
        assertFalse(board.isClosed());
        assertEquals(Symbol.EMPTY, board.getWinner());
        resetBoard(board);

        assertFalse(board.isClosed());
        board.setMarks(utils.generateMarkArray(new int[]{2,0,2,0,0,0,2,0,2}));
        assertFalse(board.isClosed());
        assertEquals(Symbol.EMPTY, board.getWinner());
        resetBoard(board);

        // Some manual, unordered tests
        assertFalse(board.isClosed());
        board.setMarkAt(Symbol.CIRCLE, 1);
        board.setMarkAt(Symbol.CIRCLE, 4);
        assertFalse(board.isClosed());
        assertEquals(Symbol.EMPTY, board.getWinner());
        board.setMarkAt(Symbol.CIRCLE, 7);
        assertTrue(board.isClosed());
        assertEquals(Symbol.CIRCLE, board.getWinner());
        resetBoard(board);

        assertFalse(board.isClosed());
        board.setMarkAt(Symbol.CROSS, 0);
        board.setMarkAt(Symbol.CIRCLE, 2);
        board.setMarkAt(Symbol.CIRCLE, 6);
        board.setMarkAt(Symbol.CROSS, 8);
        assertFalse(board.isClosed());
        assertEquals(Symbol.EMPTY, board.getWinner());
        board.setMarkAt(Symbol.CIRCLE, 4);
        assertTrue(board.isClosed());
        assertEquals(Symbol.CIRCLE, board.getWinner());
        resetBoard(board);

        assertFalse(board.isClosed());
    }

    @Test
    public void testIsMovePossible() {
        board.setMarks(utils.generateMarkArray(new int[]{1,2,1,0,2,0,0,1,1}));
        assertFalse(board.isMovePossible(0));
        assertFalse(board.isMovePossible(1));
        assertFalse(board.isMovePossible(2));
        assertFalse(board.isMovePossible(4));
        assertFalse(board.isMovePossible(7));
        assertFalse(board.isMovePossible(8));

        assertTrue(board.isMovePossible(3));
        assertTrue(board.isMovePossible(5));
        assertTrue(board.isMovePossible(6));

        resetBoard(board);
        for (int i = 0; i < 9; i++) {
            assertTrue(board.isMovePossible(i));
        }

        // TODO: necessary?
        /* resetBoard(board);
        board.setMarks(utils.generateMarkArray(new int[]{2,2,2,2,2,2,2,2,2}));
        for (int i = 0; i < 9; i++) {
            assertFalse(board.isMovePossible(i));
        } */
    }

    @Test
    public void testCopyMethod() {
        assertEquals(Symbol.EMPTY, board.getMarks()[0].getSymbol());
        board.getMarks()[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        assertEquals(Symbol.EMPTY, board.getMarks()[0].getSymbol());
    }

    // fixes daily.TestBoardTests::testWrongExceptionCaseIsMovePossible!!
    @Test
    public void testIsMovePossibleWonBoard() {
        board.setMarks(utils.generateMarkArray(new int[]{0,1,0,1,1,1,0,1,0}));
        assertFalse(board.isMovePossible(0));
    }

    // TODO: necessary?
    /* @Test
    public void testIsMovePossibleEdges() {
        board.setMarks(utils.generateMarkArray(new int[]{0,1,0,1,0,1,0,1,0}));
        assertTrue(board.isMovePossible(0));
        assertTrue(board.isMovePossible(8));
    } */

    // TODO: necessary?
    /* @Test
    public void testIsMovePossibleException() {
        assertThrows(IllegalArgumentException.class, () -> {
            board.isMovePossible(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board.isMovePossible(9);
        });
    } */


    


    /**
     * Checks whether a mark at some position index is correctly set. Null checks included (useless?).
     * @param board Board to check
     * @param symbol Symbol that will be asserted
     * @param index Index that will be checked and asserted as position
     */
    private void checkMarkAt(BoardInterface board, Symbol symbol, int index) {
        assertNotNull(board.getMarks());
        assertNotNull(board.getMarks()[index]);
        assertNotNull(board.getMarks()[index].getSymbol());

        assertEquals(symbol, board.getMarks()[index].getSymbol());
        assertTrue(index == board.getMarks()[index].getPosition());
    }

    /**
     * Resets all marks on a board to EMPTY
     * @param board The board to reset
     */
    private void resetBoard(BoardInterface board) {
        assertTrue(board.getMarks().length == 9);
            board.setMarks(utils.generateMarkArray(new int[]{0,0,0,0,0,0,0,0,0}));
      
    }

}
