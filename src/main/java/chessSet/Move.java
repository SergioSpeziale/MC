package chessSet;

public class Move {
	
	private Spot start;
	private Spot end;
	private int score;
	
	public Move(Spot start, Spot end, int score) {
		this.start = start;
		this.end = end;
		this.score = score;
	}
	
		
	public Spot getStart() {
		return start;
	}

	public void setStart(Spot start) {
		this.start = start;
	}

	public Spot getEnd() {
		return end;
	}

	public void setEnd(Spot end) {
		this.end = end;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
