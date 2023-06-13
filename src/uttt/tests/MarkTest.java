package uttt.tests;
import uttt.UTTTFactory;
import uttt.game.MarkInterface;
import uttt.utils.*;
import static org.junit.Assert.*;

import org.junit.*;

public class MarkTest {



    @Test
    public void creatingMark(){
       createAndVerify(Symbol.CROSS, 0);
       createAndVerify(Symbol.CIRCLE, 2);
       createAndVerify(Symbol.EMPTY, 8);

    }


    @Test
    public void TestSetSymbol(){
        MarkInterface mark1 = createAndVerify(Symbol.CROSS, 0);
        MarkInterface mark2 =  createAndVerify(Symbol.CIRCLE, 2);
        MarkInterface mark3 = createAndVerify(Symbol.EMPTY, 4);

        mark1.setSymbol(Symbol.CIRCLE);
        assertEquals(mark1.getSymbol(),Symbol.CIRCLE);
        mark1.setSymbol(Symbol.CROSS);
        assertEquals(mark1.getSymbol(),Symbol.CROSS);
        mark1.setSymbol(Symbol.EMPTY);
        assertEquals(mark1.getSymbol(),Symbol.EMPTY);

        mark2.setSymbol(Symbol.CROSS);
        assertEquals(mark2.getSymbol(),Symbol.CROSS);
        mark2.setSymbol(Symbol.CIRCLE);
        assertEquals(mark2.getSymbol(),Symbol.CIRCLE);
        mark2.setSymbol(Symbol.EMPTY);
        assertEquals(mark2.getSymbol(),Symbol.EMPTY);

        mark3.setSymbol(Symbol.CIRCLE);
        assertEquals(mark3.getSymbol(),Symbol.CIRCLE);
        mark3.setSymbol(Symbol.CROSS);
        assertEquals(mark3.getSymbol(),Symbol.CROSS);
        mark3.setSymbol(Symbol.EMPTY);
        assertEquals(mark3.getSymbol(),Symbol.EMPTY);
       

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




    private MarkInterface createAndVerify(Symbol symbol, int j){
      MarkInterface mark = UTTTFactory.createMark(symbol, j);
      assertNotNull(mark);
      assertEquals(mark.getSymbol(),symbol);
      assertEquals(j, mark.getPosition());

      return mark;
    }
    
}
