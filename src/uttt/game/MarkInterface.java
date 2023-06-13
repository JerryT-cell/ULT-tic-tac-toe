package uttt.game;

import uttt.utils.Symbol;

public interface MarkInterface {

	/**
	 * Get the symbol of the mark.
	 * 
	 * @return The symbol of the mark.
	 */
	public Symbol getSymbol();

	/**
	 * Get the position of the mark.
	 * 
	 * @return The position of the mark on its board.
	 */
	public int getPosition();

	/**
	 * Set the symbol of the mark.
	 * 
	 * @param symbol The symbol to which the mark shall be assigned.
	 */
	public void setSymbol(Symbol symbol) throws IllegalArgumentException;

	/**
	 * 
	 * !!! Relevant for BONUS exercise only. !!!
	 * 
	 * Overrides: hashCode() in Object
	 * 
	 * @return a hash code value for this object.
	 * 
	 */
	public int hashCode();

	/**
	 * 
	 * !!! Relevant for BONUS exercise only. !!!
	 * 
	 * Overrides: equals(...) in Object
	 * 
	 * @param obj the reference object with which to compare.
	 * 
	 * @return true if this object is the same as the obj argument; false otherwise.
	 * 
	 */
	public boolean equals(Object obj);
}
