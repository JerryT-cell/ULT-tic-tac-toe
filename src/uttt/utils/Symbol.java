package uttt.utils;

public enum Symbol {
	CROSS("X"), CIRCLE("O"), EMPTY(" ");

	private String name;

	Symbol(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public Symbol flip() {
		if (this == CROSS)
			return CIRCLE;
		if (this == CIRCLE)
			return CROSS;
		return EMPTY;
	}
}
