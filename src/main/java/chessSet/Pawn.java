package chessSet;

public class Pawn extends Piece{
	
	static final int BLACK_PAWN_INITIAL_SPOT = 3;
	static final int WHITE_PAWN_INITIAL_SPOT = 12;
	
	public Pawn(Color color) 
    { 
        super(color, Score.PAWN); 
    } 
	
	@Override
    public boolean canMove(int startX, int startY, Spot end) { 
		
		if (!this.isInsideBoard(end.getX(), end.getY())) {
			return false;
		}
		
		if (this.isMoveBackwards(startY, end.getY())) {
			return false;
		}	
		
		if(countStraightSpots(startY, end.getY()) > 2) {
			return false;
		}
	
		if (this.isDiagonalMove(startX, startY, end.getX(), end.getY())) {
			Piece piece = end.getPiece();
			
			if(countStraightSpots(startY, end.getY()) > 1) {
				return false;
			}
			
			if (piece != null) {
				if (this.isPieceRivalOf(piece)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		if(this.isMoveSideways(startX, end.getX())) {
			return false;
		}
				
		if(countStraightSpots(startY, end.getY()) == 2) {
			switch (this.getColor()) {
			case BLACK:
				if(startY != BLACK_PAWN_INITIAL_SPOT) {
					return false;
				}
				break;
			case WHITE:
				if(startY != WHITE_PAWN_INITIAL_SPOT) {
					return false;
				}
				break;
			default:
				break;
			}			
		}
		
		if(end.getPiece() != null) {
			return false;
		}	
		
		return true;
    }	
}
