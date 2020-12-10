package chessSet;

public class Bishop extends Piece{

	public Bishop(Color color) 
    { 
        super(color, Score.BISHOP); 
    } 
	
	@Override
	public boolean canMove(int startX, int startY, Spot end) {
		if (!this.isInsideBoard(end.getX(), end.getY())) {
			return false;
		}
		
		if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) { 
          return false; 
		} 
		
		if(!this.isValidDirection(startX, startY, end)) {
			return false;
		}
		
		return true;
	}

	private boolean isValidDirection(int startX, int startY, Spot end) {
		if(this.isDiagonalMove(startX, startY, end.getX(), end.getY())) return true;
		return false;
	}
}

