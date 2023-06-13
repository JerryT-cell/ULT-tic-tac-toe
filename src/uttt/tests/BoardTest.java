package uttt.tests;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
import uttt.utils.Symbol;

public class BoardTest {

     @Test
     public void simpleSetValidMarks(){

        BoardInterface board = UTTTFactory.createBoard();
       MarkInterface[] marks1 =  TestUtil.generateMarkArray(new Symbol[]{Symbol.CIRCLE,Symbol.CROSS,Symbol.EMPTY,
                                                Symbol.CROSS,Symbol.CIRCLE,Symbol.CIRCLE,
                                                Symbol.CIRCLE,Symbol.CROSS,Symbol.EMPTY});

        board.setMarks(marks1);
        assertArrayEquals(marks1,board.getMarks());
        
        
        MarkInterface[] marks2 =  TestUtil.generateMarkArray(new Symbol[]{Symbol.CROSS,Symbol.CIRCLE,Symbol.EMPTY,
                                                Symbol.CIRCLE,Symbol.CROSS,Symbol.CIRCLE,
                                                Symbol.CIRCLE,Symbol.CROSS,Symbol.EMPTY});

        board.setMarks(marks2);
        assertArrayEquals(marks2,board.getMarks());
                                                

     }



     @Test 
      public void setInvalidMarks(){


      }

     @Test
      public void getMarksFromBoard(){


      }

      @Test 
      public void getMarksTest(){




      }

      @Test
      public void setMarksInPosition(){


      }

      @Test 
      public void TestMoves(){


      }

      @Test 
      public void getWinnerSymbolTest(){


      }


    
}
