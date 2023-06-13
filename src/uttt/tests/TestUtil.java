package uttt.tests;
import uttt.utils.*;
import uttt.UTTTFactory;
import uttt.game.MarkInterface;

public class TestUtil {





    public static MarkInterface[] generateMarkArray(Symbol[] symbols){

         assert(symbols.length==9);
        MarkInterface[] marks = new MarkInterface[9];
        for(int i = 0; i<9; i++){
            marks[i] = UTTTFactory.createMark(symbols[i], i);
           
        }

        return marks;



    }
    
}
