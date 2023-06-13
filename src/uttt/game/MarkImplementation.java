package uttt.game;

import uttt.utils.Symbol;

public class MarkImplementation implements MarkInterface {

    private Symbol symbol;
    private int position;


    public MarkImplementation(Symbol symbol, int position)throws IllegalArgumentException{
        setParameters(symbol, position);
    }

    @Override
    public Symbol getSymbol() {
        return this.symbol;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void setSymbol(Symbol symbol) throws IllegalArgumentException {
        if(symbol == null){
            throw new IllegalArgumentException("The symbol is invalid");
        }
        if ((symbol.equals(Symbol.CIRCLE)||symbol.equals(Symbol.CROSS)
             || symbol.equals(Symbol.EMPTY))){
                this.symbol = symbol;
        }else{
            throw new IllegalArgumentException("The symbol is invalid");
        }
    }

    private void setParameters(Symbol s, int position) throws IllegalArgumentException {
          if(position<0||position>8){
            throw new IllegalArgumentException("The p is wrong");
          }else{
            this.position = position;
          }
          setSymbol(s);
    }
    
}
