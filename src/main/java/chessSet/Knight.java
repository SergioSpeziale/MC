package chessSet;

public class Knight extends Piece{
	public Knight(Color color) 
    { 
        super(color, Score.KNIGHT); 
    } 
  
    @Override
    public boolean canMove(int startX, int startY, Spot end) 
    { 
    	if (!this.isInsideBoard(end.getX(), end.getY())) {
			return false;
		}
    	
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) { 
            return false; 
  		} 
  
        int x = Math.abs(startX - end.getX()); 
        int y = Math.abs(startY - end.getY()); 
        return x * y == 2; 
    } 
}
