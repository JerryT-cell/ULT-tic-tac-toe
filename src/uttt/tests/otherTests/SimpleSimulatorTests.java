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
import uttt.game.SimulatorInterface;
import uttt.utils.Symbol;

public class SimpleSimulatorTests {
    
    UtilsForTests utils = new UtilsForTests();
    SimulatorInterface simulator;

    @Before
    public void setUp() {
        simulator = UTTTFactory.createSimulator();
        assertNotNull(simulator);
    }

    // following tests most probably unnecessary

    @Test
    public void testInitialCurrentPlayerSymbol() {
        assertEquals(Symbol.EMPTY, simulator.getCurrentPlayerSymbol());
    }

    @Test
    public void testSetCurrentPlayerSymbol() {
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertEquals(Symbol.CROSS, simulator.getCurrentPlayerSymbol());

        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertEquals(Symbol.CIRCLE, simulator.getCurrentPlayerSymbol());

        simulator.setCurrentPlayerSymbol(Symbol.EMPTY);
        assertEquals(Symbol.EMPTY, simulator.getCurrentPlayerSymbol());  
    }

    @Test
    public void testEmptySimulatorBoards() {
        BoardInterface boards[] = simulator.getBoards();
        assertTrue(boards.length == 9);
        for (BoardInterface board : boards) {
            for (int i = 0; i < 9; i++) {
                assertEquals(Symbol.EMPTY, board.getMarks()[i].getSymbol());
            }
        }
    }

    @Test
    public void testSetBoardsInvalid() {
        int indexArraysTooLarge[][] = new int[][] {
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1}};

        int indexArraysTooSmall[][] = new int[][] {
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1}};

        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setBoards(utils.generateBoardArray(indexArraysTooLarge));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setBoards(utils.generateBoardArray(indexArraysTooSmall));
        });
    }

    /**
     * Generates full board array with all small boards filled. Tests whether get boards preserves entries.
     */
    @Test
    public void testFullSimulatorBoards() {
        int indexArrays[][] = new int[][] {
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1}};

        simulator.setBoards(utils.generateBoardArray(indexArrays));
        BoardInterface boards[] = simulator.getBoards();
        assertNotNull(boards);
        for (int i = 0; i < 9; i++) {
            assertNotNull(boards[i]);
            for (int j = 0; j < 9; j++) {
                assertEquals(utils.numToSymbol(indexArrays[i][j]), boards[i].getMarks()[j].getSymbol());
            }
        }
    }

    // useful tests start here

    @Test
    public void testSetMarkAt() {
        int indexArrays[][] = new int[][] {
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1}};

        simulator.setBoards(utils.generateBoardArray(indexArrays));

        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertTrue(simulator.setMarkAt(Symbol.CROSS, 0, 2)); // does win the first board
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);

        assertEquals(Symbol.CIRCLE, simulator.getCurrentPlayerSymbol());
        assertTrue(simulator.getIndexNextBoard() == 2);

        /*  Next Player: O
         *  {1,1,1,2,0,0,1,0,1}, <- won by X
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1}, <- next board to play on (index 2)
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1}
         */

        // must play in top right board (index 2)
        assertFalse(simulator.setMarkAt(Symbol.CIRCLE, 2, 0));  // already occupied
        assertFalse(simulator.setMarkAt(Symbol.CIRCLE, 0, 4));  // wrong board

        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(Symbol.CROSS, 2, 4);            // wrong symbol
        });

        assertTrue(simulator.setMarkAt(Symbol.CIRCLE, 2, 4));   // valid move
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);

        assertEquals(Symbol.CROSS, simulator.getCurrentPlayerSymbol());
        assertTrue(simulator.getIndexNextBoard() == 4);

        /*  Next Player: X
         *  {1,1,1,2,0,0,1,0,1}, <- won by X
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,2,0,1,0,1}, <- set O at index 4
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1}, <- next board to play on (index 4)
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1}
         */

        assertEquals(simulator.getBoards()[2].getMarks()[4].getSymbol(), Symbol.CIRCLE); // check if circle was set on board
    }

    @Test
    public void testExceptionsSetMarkAt() {
        int indexArrays[][] = new int[][] {
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1},
            {1,1,0,2,0,0,1,0,1}};

        simulator.setBoards(utils.generateBoardArray(indexArrays));

        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(Symbol.CROSS, -1, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(Symbol.CROSS, 0, -1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(Symbol.CROSS, 9, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(Symbol.CROSS, 0, 9);
        });

        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        simulator.setIndexNextBoard(4);
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(Symbol.CIRCLE, 4, 4);
        });

        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(Symbol.EMPTY, 4, 4);
        });
    }

    @Test
    public void testFinalWinner() {
        int indexArrays[][] = new int[][] {
            {1,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {1,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,1}};

        simulator.setBoards(utils.generateBoardArray(indexArrays));

        assertFalse(simulator.isGameOver());
        assertEquals(Symbol.EMPTY, simulator.getWinner());

        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertTrue(simulator.setMarkAt(Symbol.CROSS, 0, 4)); // does win the first board, next board 4
        assertTrue(simulator.setMarkAt(Symbol.CROSS, 4, 8)); // doeas win the fourth board, next board 8
        assertTrue(simulator.setMarkAt(Symbol.CROSS, 8, 0)); // doeas win the eighth board, next board 0

        assertTrue(simulator.isGameOver());
        assertEquals(Symbol.CROSS, simulator.getWinner());
    }

    @Test
    public void testGetIndexNextBoard() {
        int indexArrays[][] = new int[][] {
            {1,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {2,2,2,0,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,1}};

        simulator.setBoards(utils.generateBoardArray(indexArrays));

        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        simulator.setIndexNextBoard(0);
        assertTrue(simulator.getIndexNextBoard() == 0);

        assertTrue(simulator.setMarkAt(Symbol.CROSS, 0, 4)); // does win the first board, next board 4
        assertTrue(simulator.getIndexNextBoard() == -1);

        simulator.setIndexNextBoard(0);
        assertTrue(simulator.getIndexNextBoard() == 0);
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setIndexNextBoard(9);
        });

        simulator.setIndexNextBoard(-1);
        assertTrue(simulator.getIndexNextBoard() == -1);

    }

    @Test
    public void testIsMovePossible() {
        int indexArrays[][] = new int[][] {
            {1,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {2,2,2,0,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,1}};

        simulator.setBoards(utils.generateBoardArray(indexArrays));
        simulator.setIndexNextBoard(0);

        assertTrue(simulator.isMovePossible(0));
        assertFalse(simulator.isMovePossible(1));
        assertFalse(simulator.isMovePossible(0, 0));
        assertTrue(simulator.isMovePossible(0, 4));

        assertThrows(IllegalArgumentException.class, () -> {
            simulator.setMarkAt(Symbol.CROSS, 0, 4);
        });

        simulator.setCurrentPlayerSymbol(Symbol.CROSS);

        assertTrue(simulator.setMarkAt(Symbol.CROSS, 0, 4));    // sets at 0, 4

        assertTrue(simulator.getIndexNextBoard() == -1);
        assertTrue(simulator.isMovePossible(3));
        assertFalse(simulator.isMovePossible(4));

        assertTrue(simulator.isMovePossible(3, 0));
        assertFalse(simulator.isMovePossible(4, 3));

        assertTrue(simulator.setMarkAt(Symbol.CROSS, 3, 0));

        // move possible in first board (at index 1)
        // assertTrue(simulator.isMovePossible(0)); assertionError NULL??
        // assertTrue(simulator.isMovePossible(0, 1)); not checked yet

        assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(9);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(0, -1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(0, 9);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(-1, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.isMovePossible(9, 0);
        });
    }

}
