package chessSet;

public class King extends Piece {
	  
    public King(Color color) 
    { 
        super(color, Score.KING); 
    }     
  
    @Override
    public boolean canMove(int startX, int startY, Spot end) 
    { 
        // we can't move the piece to a Spot that  
        // has a piece of the same color 
        if (end.getPiece() != null && end.getPiece().getColor() == this.getColor()) { 
            return false; 
        } 
        
        int x = Math.abs(startX - end.getX()); 
        int y = Math.abs(startY - end.getY()); 
        
        if(this.isDiagonalMove(startX, startY, end.getX(), end.getY())) {
        	return x + y == 2;
        }else {
        	return x + y == 1;
        }
    }
}
