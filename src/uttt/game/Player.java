package uttt.game;

import uttt.utils.Move;
import uttt.utils.Symbol;

public class Player implements PlayerInterface {

    private Symbol symbol;

    public Player(Symbol symbol){
        this.symbol = symbol;
    }

    @Override
    public Symbol getSymbol() {
       return symbol;
    }

    @Override
    public Move getPlayerMove(SimulatorInterface game, UserInterface ui) throws IllegalArgumentException {
       
        Move playerMove = ui.getUserMove();
        ui.updateScreen(game);
        return playerMove;

    }
    
}
