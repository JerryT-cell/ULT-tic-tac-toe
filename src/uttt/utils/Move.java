package uttt.utils;

public class Move {

	private int boardIndex;

	private int markIndex;

	public Move(int boardIndex, int markIndex) {
		this.boardIndex = boardIndex;
		this.markIndex = markIndex;
	}

	public int getBoardIndex() {
		return boardIndex;
	}

	public int getMarkIndex() {
		return markIndex;
	}

	@Override
	public String toString() {
		return "Move [boardIndex=" + boardIndex + ", markIndex=" + markIndex + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardIndex;
		result = prime * result + markIndex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (boardIndex != other.boardIndex)
			return false;
		if (markIndex != other.markIndex)
			return false;
		return true;
	}

}
