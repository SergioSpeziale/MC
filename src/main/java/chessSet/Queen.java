package chessSet;

public class Queen extends Piece {

	public Queen(Color color) 
    { 
        super(color, Score.QUEEN); 
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
		if(this.isMoveHorizontal(startX, end.getX(), startY, end.getY())) return true; 
		if(this.isMoveVertical(startY, end.getY(),startX, end.getX())) return true;
		if(this.isDiagonalMove(startX, startY, end.getX(), end.getY())) return true;
		return false;
	}

}
