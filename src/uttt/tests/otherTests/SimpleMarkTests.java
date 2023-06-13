package uttt.tests.otherTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.MarkInterface;
import uttt.utils.Symbol;

public class SimpleMarkTests {

    @Test
    public void testValidMarks() {
        createVerifiedMark(Symbol.CROSS, 0);
        createVerifiedMark(Symbol.CIRCLE, 4);
        createVerifiedMark(Symbol.CROSS, 8);

        createVerifiedMark(Symbol.EMPTY, 0);
    }

    @Test
    public void testInvalidMarks() {
         assertThrows(IllegalArgumentException.class, () -> {
            UTTTFactory.createMark(Symbol.CROSS, -1);
         });
         assertThrows(IllegalArgumentException.class, () -> {
            UTTTFactory.createMark(Symbol.CIRCLE, 9);
         });
    }

    @Test
    public void testSetSymbol() {
        MarkInterface mark = createVerifiedMark(Symbol.EMPTY, 4);
        mark.setSymbol(Symbol.CIRCLE);
        assertEquals(Symbol.CIRCLE, mark.getSymbol());
        mark.setSymbol(Symbol.CROSS);
        assertEquals(Symbol.CROSS, mark.getSymbol());
        mark.setSymbol(Symbol.EMPTY);
        assertEquals(Symbol.EMPTY, mark.getSymbol());
    }

    private MarkInterface createVerifiedMark(Symbol symbol, int j) {
        MarkInterface mark = UTTTFactory.createMark(symbol, j);

        assertNotNull(mark);
        assertEquals(symbol, mark.getSymbol());
        assertEquals(j, mark.getPosition());

        return mark;
    }
}
