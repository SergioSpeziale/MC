package chessSet;

import java.util.HashMap;

import chessSet.Piece.Color;

public class Turn {
	
	public String turnToken;
	public Piece.Color color; 
	public Board board;
	
	public Turn(String turnToken, Board board, Color color) {
		this.turnToken = turnToken;
		this.color = color;
		this.board = board;
	}
		
	public HashMap<String,Object> getNextMove() {		
		Move move = board.scanBestMove(this.color);
		Spot start = move.getStart();
		Spot end = move.getEnd();
		
		HashMap<String,Object> body = new HashMap<String,Object>();
		
		if(start.getPiece().canMove(start.getX(), start.getY(), end)) {
			body.put("board_id", board.boardId);
			body.put("turn_token", this.turnToken);
			body.put("from_row", start.getY());
			body.put("from_col", start.getX());
			body.put("to_row", end.getY());
			body.put("to_col", end.getX());
		}		
		return body;		
	}
}
