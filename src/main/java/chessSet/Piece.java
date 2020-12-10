package chessSet;

public abstract class Piece {
	public enum Color{
		BLACK, WHITE
	}
	public enum  Score {
		PAWN(10),
		BISHOP(40),
		ROOK(60),
		KNIGHT(30),
		QUEEN(5),
		KING(100);
		
		private int pieceVal;
		Score(int pieceVal){
			this.pieceVal = pieceVal;
		}
		
		public int getPieceVal() {
			return pieceVal;
		}	
	}
	
	static final int FIRST_INDEX = 0;
	static final int LAST_INDEX = 15;
	
	private boolean killed = false; 
    private Color color;
    private Score score;
  
    public Piece(Color color, Score score) 
    { 
    	this.color = color; 
    	this.score = score;
    } 
    
    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isKilled() 
    { 
        return this.killed; 
    } 
  
    public void setKilled(boolean killed) 
    { 
        this.killed = killed; 
    }         

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public abstract boolean canMove(int startX, int startY, Spot end);
    
    protected boolean isInsideBoard(int x, int y) {
		boolean isValid = true;
		if(x > LAST_INDEX || x < FIRST_INDEX) {
			isValid = false;
		}		
		if(y > LAST_INDEX || y < FIRST_INDEX) {
			isValid = false;
		}
		
		return isValid;
	}
   
    protected boolean isMoveBackwards(int startY, int endY) {    	
    	switch (this.getColor()) {
		case WHITE:
			return (startY - endY) < 0;
		case BLACK:
			return (startY - endY) > 0;
		default:
			return false;
		}
    }
    
    protected boolean isMoveSideways(int startX, int endX) {
    	return  Math.abs(startX) -  Math.abs(endX) != 0;
    }
    
    protected boolean isMoveHorizontal(int startX, int endX, int startY, int endY) {
    	return  Math.abs(startX) -  Math.abs(endX) != 0 && Math.abs(startY) - Math.abs(endY) == 0;
    }
    
    protected boolean isMoveVertical(int startY, int endY, int startX, int endX) {
    	return  Math.abs(startY) -  Math.abs(endY) != 0 && Math.abs(startX) -  Math.abs(endX) == 0;
    }
    
    protected boolean isDiagonalMove(int startX, int startY, int endX, int endY) {
    	int x = Math.abs(startX - endX); 
        int y = Math.abs(startY - endY);
        if(x == 0 || y == 0) return false;
        return x == y;
    }
    
    protected boolean isPieceRivalOf(Piece piece) {
    	return this.getColor() != piece.getColor();
    }
    
    protected int countStraightSpots (int start, int end) {
        int y = Math.abs(start - end);
        return y;
    }
    
//    protected boolean isVerticalBlocked(int startX, int startY, int endX, int endY) {
//    	int horizontal = endX - startX;
//    	int vertical = endY - startY;
//    	if(horizontal != 0) {
//    		
//    		for(int x = 0 ; x != horizontal ; x = x + (horizontal/Math.abs(horizontal))) {
//    			
//    		}
//    	}
//    }
}
