package chessSet;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Board {
	String boardId;
	
	Spot[][] boxes; 
	List<Spot> enemySpots = new ArrayList<Spot>();
	List<Spot> mySpots = new ArrayList<Spot>();
	List<Spot> neutralSpots = new ArrayList<Spot>(); 
	  
    public Board(String boardId) {
    	this.boardId = boardId;
    } 
  
    public Spot getBox(int x, int y) throws Exception 
    { 
  
        if (x < 0 || x > 15 || y < 0 || y > 15) { 
            throw new Exception("Index out of bound"); 
        } 
  
        return boxes[x][y]; 
    } 
    
    public void fillSpots(String boardLine, Piece.Color myColor) {
    	
    	this.boxes = new Spot[16][16];
    	
    	int lineIndex = 0;
    	
    	// Translating the String into an array of Spots
    	for(int y = 0 ; y < 16 ; y ++) {
    		for(int x = 0 ; x < 16 ; x ++) {
    			switch ( boardLine.charAt(lineIndex) ) {
				case 'p':
					boxes[y][x] = new Spot(x, y, new Pawn(Piece.Color.BLACK)); 
					break;
				case 'P':
					boxes[y][x] = new Spot(x, y, new Pawn(Piece.Color.WHITE)); 
					break;
				case 'k':
					boxes[y][x] = new Spot(x, y, new King(Piece.Color.BLACK)); 
					break;
				case 'K':
					boxes[y][x] = new Spot(x, y, new King(Piece.Color.WHITE)); 
					break;
				case 'q':
					boxes[y][x] = new Spot(x, y, new Queen(Piece.Color.BLACK)); 
					break;
				case 'Q':
					boxes[y][x] = new Spot(x, y, new Queen(Piece.Color.WHITE)); 
					break;
				case 'r':
					boxes[y][x] = new Spot(x, y, new Rook(Piece.Color.BLACK)); 
					break;
				case 'R':
					boxes[y][x] = new Spot(x, y, new Rook(Piece.Color.WHITE)); 
					break;
				case 'h':
					boxes[y][x] = new Spot(x, y, new Knight(Piece.Color.BLACK)); 
					break;
				case 'H':
					boxes[y][x] = new Spot(x, y, new Knight(Piece.Color.WHITE)); 
					break;
				case 'b':
					boxes[y][x] = new Spot(x, y, new Bishop(Piece.Color.BLACK)); 
					break;
				case 'B':
					boxes[y][x] = new Spot(x, y, new Bishop(Piece.Color.WHITE)); 
					break;
				default:
					boxes[y][x] = new Spot(x, y, null); 
					break;
				}
    			
    			// For evaluation purposes, spots are classified as neutral, 
    			// enemy or my own 
    			Piece piece = boxes[y][x].getPiece();    			
    			if(piece == null) {
    				neutralSpots.add(boxes[y][x]);
    			}else {
    				if(piece.getColor() != myColor) {
        				enemySpots.add(boxes[y][x]);
        			}else {
        				mySpots.add(boxes[y][x]);
        			}
    			}
    			
    			lineIndex ++;
    		}
    	}    	
    }
    
public Move scanBestMove(Piece.Color myColor) {
    	
    	Move killingMove = new Move(null,null,0);  
    	
    	// First, enemy's spots are evaluated: the higher their score, the more valuable the move    	
    	switch (myColor) {
		case WHITE:			
			for(Spot ownSpot : mySpots) {
	    		Piece myPiece = ownSpot.getPiece();
	    		for(Spot enemy : enemySpots) {
	    			if(myPiece.canMove(ownSpot.getX(), ownSpot.getY(), enemy)) {   
	    				if(!isPathBlocked(ownSpot, enemy)) {
	    					
	    					/* the score can be the value of the enemy piece, or the value of
	    					   one of my pieces under that enemy's  sight.
	    					   If there is a rook that can be taken, but an enemy pawn is threatening
	    					   one of my kings, that pawn will be given 100 points, becoming my best move	    					
	    					 */
	    					int enemyScore = this.calculateScore(enemy);
	    					if(killingMove.getScore() == 0 || killingMove.getScore() < enemyScore) {
		    					killingMove = new Move(ownSpot, enemy, enemyScore);
		    				} 
	    				}	    				   				
	    			}
	    		}
			}
			break;
			
		case BLACK:
			// black pieces need to see the board in a reverse way, so that the further spots
			// are the first to be evaluated
			ListIterator<Spot> reverseListMySpots = mySpots.listIterator(mySpots.size());

	    	while (reverseListMySpots.hasPrevious()) {
	    		Spot ownSpot = reverseListMySpots.previous();
	    		Piece myPiece = ownSpot.getPiece();
	    		
	    		ListIterator<Spot> reverseListEnemySpots = enemySpots.listIterator(enemySpots.size());

		    	while (reverseListEnemySpots.hasPrevious()) {
		    		Spot enemy = reverseListEnemySpots.previous();
		    		if(myPiece.canMove(ownSpot.getX(), ownSpot.getY(), enemy) ) {   
		    			if(!isPathBlocked(ownSpot, enemy)) {
		    				int enemyScore = this.calculateScore(enemy);
		    				if(killingMove.getScore() == 0 || killingMove.getScore() < enemyScore) {
		    					killingMove = new Move(ownSpot, enemy, enemyScore);
		    				}
		    			}	    				    				
	    			}
		    	}
	    	}
			break;		
		}    	
    	
    	// If killing moves where not found, then the neutral spots are evaluated
    	if(killingMove.getScore() > 0) {
    		return killingMove;
    	}else {    		   		
    		switch (myColor) {
			case BLACK:
				ListIterator<Spot> listIteratorMySpots = mySpots.listIterator(mySpots.size());

		    	while (listIteratorMySpots.hasPrevious()) {
		    		Spot ownSpot = listIteratorMySpots.previous();
		    		Piece myPiece = ownSpot.getPiece();   
		    		ListIterator<Spot> listIterator = neutralSpots.listIterator(neutralSpots.size());

    		    	while (listIterator.hasPrevious()) {
    		    		Spot neutral = listIterator.previous();
    		    		if(myPiece.canMove(ownSpot.getX(), ownSpot.getY(), neutral)) {
    		    			if(!isPathBlocked(ownSpot, neutral)) {
                				return new Move(ownSpot, neutral, 0);
    		    			}
            			}
    		    	}
		    	} 
		    	break;   				
			case WHITE:
				for(Spot ownSpot : mySpots) {
	        		Piece myPiece = ownSpot.getPiece();     
    				for(Spot neutral : neutralSpots) {
            			if(myPiece.canMove(ownSpot.getX(), ownSpot.getY(), neutral)) {
            				if(!isPathBlocked(ownSpot, neutral)) {
                				return new Move(ownSpot, neutral, 0);	
            				}
            			}
            		}
				}
		    	break;    				
        	}
    	}    	
    	return null;
    }
    
    public boolean isPathBlocked(Spot start, Spot end) {
    	int startX = start.getX();
    	int startY = start.getY();
    	int endX = end.getX();
    	int endY = end.getY();
    	boolean isBlocked = false;
    	int horizontal = endX - startX;
    	int vertical = endY - startY;
    	
    	if (horizontal != 0 && vertical != 0) { 
    		return this.isDiagonalBlocked(horizontal, vertical, startX, startY, end);
    	} else if (horizontal != 0 && vertical == 0) {
    		return this.isHorizontalBlocked(horizontal, startX, startY, endX);    		
    	} else if (vertical != 0 && horizontal == 0) {
    		return this.isVerticalBlocked(vertical, startY, startX, endY);
    	}
    	return isBlocked;
    }
    
    public boolean isDiagonalBlocked(int horizontal, int vertical, int startX, int startY, Spot end) {
    	boolean isBlocked = false;
    	int stepH = (horizontal/Math.abs(horizontal));
		int startingH = startX + stepH;
		int stepV = (vertical/Math.abs(vertical));
		int startingV = startY + stepV;
		
    	if(end.getX() != startingH && end.getY() != startingV) {   		
    		boolean spotsEvalPending = true;
    		while(spotsEvalPending) {
    			isBlocked = this.boxes[startingV][startingH].getPiece() != null;
    			if(isBlocked) spotsEvalPending = false;
    			startingH += stepH;
        		startingV += stepV;
        		if(startingH == end.getX() && startingV == end.getY()) {
        			spotsEvalPending = false;
        		}
    		}	
    	}    		
		return isBlocked;
    }
    
    public boolean isVerticalBlocked(int vertical, int startY, int startX, int endY) {
    	boolean isBlocked = false;
    	int step = (vertical/Math.abs(vertical));
		int startingEvalPoint = startY + step;
		for(int y = startingEvalPoint ; y != endY ; y = y + step) {
			isBlocked = this.boxes[y][startX].getPiece() != null;
			if(isBlocked) break;
		}    	
    	return isBlocked;
    }
    
    public boolean isHorizontalBlocked(int horizontal, int startX, int startY, int endX) {
    	boolean isBlocked = false;
    	int step = (horizontal/Math.abs(horizontal));
		int startingEvalPoint = startX + step;
		for(int x = startingEvalPoint ; x != endX ; x = x + step) {
			isBlocked = this.boxes[startY][x].getPiece() != null;
			if(isBlocked) break;
		}    	
    	return isBlocked;
    }
    
    public int calculateScore(Spot enemy) {
    	Piece enemyPiece = enemy.getPiece();
    	int totalScore = enemyPiece.getScore().getPieceVal();
    	
    	switch (enemy.getPiece().getColor()) {
		case BLACK:
			ListIterator<Spot> listIterator = mySpots.listIterator(mySpots.size());

	    	while (listIterator.hasPrevious()) {
	    		Spot ally = listIterator.previous();
	    		if(enemy.getPiece().canMove(enemy.getX(), enemy.getY(), ally) ) {   
	    			if(!isPathBlocked(enemy, ally)) {
	    				int potentialLossValue = ally.getPiece().getScore().getPieceVal();
	    				
	    				if(potentialLossValue > totalScore) {
	    					totalScore = potentialLossValue;
	    				}
	    			}	    				    				
    			}
	    	}
		case WHITE:
			for(Spot ally : mySpots) {
    			if(enemyPiece.canMove(enemy.getX(), enemy.getY(), ally)) {   
    				if(!isPathBlocked(enemy, ally)) {
    					int potentialLossValue = ally.getPiece().getScore().getPieceVal();
	    				
	    				if(potentialLossValue > totalScore) {
	    					totalScore = potentialLossValue;
	    				}
    				}	    				   				
    			}
    		}
		} 
    	return totalScore;
    }
}
 