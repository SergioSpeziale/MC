package chessSet;

public class Rook extends Piece {

	public Rook(Color color) 
    { 
        super(color, Score.ROOK); 
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
		return false;
	}
}
