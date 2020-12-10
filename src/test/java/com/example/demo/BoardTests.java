package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import chessSet.Bishop;
import chessSet.Board;
import chessSet.King;
import chessSet.Move;
import chessSet.Pawn;
import chessSet.Piece;
import chessSet.Queen;
import chessSet.Rook;
import chessSet.Spot;

@SpringBootTest
public class BoardTests {
	
	@Test
	void testIsVerticalMoveBlocked() {
		String boardLine = 
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"P               " +
				"P               " +
				"                " +
				"                " +
				"                ";
		Board board = new Board("mockID");
		board.fillSpots(boardLine, Piece.Color.WHITE);
		
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0,12, pawn);
		Spot end = new Spot(0,10, null);
		boolean isBlocked = board.isPathBlocked(start, end);
		
		assertTrue(isBlocked);
	}
	
	@Test
	void testIsDiagonalBlockedLeftToRightSuperior() {
		String boardLine = 
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"   P            " +
				"                " +
				" B              " +
				"                ";
		Board board = new Board("mockID");
		board.fillSpots(boardLine, Piece.Color.WHITE);
		
		Bishop bishop = this.givenAWhiteBishop();
		Spot start = new Spot(1,14, bishop);
		Spot end = new Spot(4,11, null);
		boolean isBlocked = board.isPathBlocked(start, end);
		
		assertTrue(isBlocked);		
	}
	
	@Test
	void testIsDiagonalBlockedLeftToRightInferior() {
		String boardLine = 
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"    B           " +
				"     P          " +
				"                " +
				"                " +
				"                ";
		Board board = new Board("mockID");
		board.fillSpots(boardLine, Piece.Color.WHITE);
		
		Bishop bishop = this.givenAWhiteBishop();
		Spot start = new Spot(4,11, bishop);
		Spot end = new Spot(7,14, null);
		boolean isBlocked = board.isPathBlocked(start, end);
		
		assertTrue(isBlocked);		
	}
	
	@Test
	void testIsDiagonalBlockedRightToLeftSuperior() {
		String boardLine = 
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"     P          " +
				"                " +
				"       B        " +
				"                ";
		Board board = new Board("mockID");
		board.fillSpots(boardLine, Piece.Color.WHITE);
		
		Bishop bishop = this.givenAWhiteBishop();
		Spot start = new Spot(7,14, bishop);
		Spot end = new Spot(4,11, null);
		boolean isBlocked = board.isPathBlocked(start, end);
		
		assertTrue(isBlocked);		
	}
	
	@Test
	void testIsDiagonalBlockedRightToLeftInferior() {
		String boardLine = 
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"    B           " +
				"   P            " +
				"                " +
				"                " +
				"                ";
		Board board = new Board("mockID");
		board.fillSpots(boardLine, Piece.Color.WHITE);
		
		Bishop bishop = this.givenAWhiteBishop();
		Spot start = new Spot(4,11, bishop);
		Spot end = new Spot(1,14, null);
		boolean isBlocked = board.isPathBlocked(start, end);
		
		assertTrue(isBlocked);		
	}	
	
	@Test
	void testIsDiagonalBlockedRightToLeftInferior2() {
		String boardLine = 
				"                " +
				"                " +
				"                " +
				"  p             " +
				" Q              " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                ";
		Board board = new Board("mockID");
		board.fillSpots(boardLine, Piece.Color.BLACK);
		
		Pawn myPawn = this.givenABlackPawn();
		Queen enemyQueen = this.givenAWhiteQueen();
		Spot start = new Spot(3,2, myPawn);
		Spot end = new Spot(4,1, enemyQueen);
		boolean isBlocked = board.isPathBlocked(start, end);
		
		assertTrue(!isBlocked);		
	}	
	
	@Test
	void testScanBestMove() {
		String boardLine = 
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"  p k           " +
				"   P            " +
				"                " +
				"                " +
				"                ";
		Board board = new Board("mockID");
		board.fillSpots(boardLine, Piece.Color.WHITE);
		
		Pawn myPawn = this.givenAWhitePawn();
		King enemyKing = this.givenABlackKing();
		Spot start = new Spot(3,12, myPawn);
		Spot end = new Spot(4,11, enemyKing);
		Move bestMove = new Move(start, end, end.getPiece().getScore().getPieceVal());
		Move actual = board.scanBestMove(myPawn.getColor());
		String expected = "desde x:" + bestMove.getStart().getX() + " y:" + bestMove.getStart().getY() + " hasta x:" + bestMove.getEnd().getX() + " y:" + bestMove.getEnd().getY();
		String actualResult = "desde x:" + actual.getStart().getX() + " y:" + actual.getStart().getY() + " hasta x:" + actual.getEnd().getX() + " y:" + actual.getEnd().getY();
//		System.out.println("desde x:" + start.getX() + " y:" + start.getY() + " hasta x:" + end.getX() + " y:" + end.getY());
//		System.out.println("desde x:" + actual.getStart().getX() + " y:" + actual.getStart().getY() + " hasta x:" + actual.getEnd().getX() + " y:" + actual.getEnd().getY());
		
		assertEquals(expected, actualResult);
	}
	
	@Test
	void testScanBestMoveWrongDiagonal() {
		String boardLine = 
				"rrhhbbqqkkbbhhrr" +
				"rrhhbbqqkkbbhhrr" +
				"pppppppppppppppp" +
				"ppppppppppppppp " +
				"                " +
				"    Q           " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"PPPP PPPPPPPPPPP" +
				"PPPPPPPPPPPPPP P" +
				"RRHHBBQQKKBBHHRR" +
				"RRHHBBQQKKBBHHRR";
		Board board = new Board("mockID");
		board.fillSpots(boardLine, Piece.Color.BLACK);
		
		Pawn myPawn = this.givenABlackPawn();
		Queen enemyQueen = this.givenAWhiteQueen();
		Spot start = new Spot(6,3, myPawn);
		Spot end = new Spot(4,5, enemyQueen);
		Move wrongMove = new Move(start, end, end.getPiece().getScore().getPieceVal());
		Move actual = board.scanBestMove(myPawn.getColor());
		String expected = "desde x:" + wrongMove.getStart().getX() + " y:" + wrongMove.getStart().getY() + " hasta x:" + wrongMove.getEnd().getX() + " y:" + wrongMove.getEnd().getY();
		String actualResult = "desde x:" + actual.getStart().getX() + " y:" + actual.getStart().getY() + " hasta x:" + actual.getEnd().getX() + " y:" + actual.getEnd().getY();
		System.out.println("desde x:" + start.getX() + " y:" + start.getY() + " hasta x:" + end.getX() + " y:" + end.getY());
		System.out.println("desde x:" + actual.getStart().getX() + " y:" + actual.getStart().getY() + " hasta x:" + actual.getEnd().getX() + " y:" + actual.getEnd().getY());
		
		assertNotEquals(expected, actualResult);
	}
	
	
	@Test
	void testScanBestMoveBiggerThreat() {
		String boardLine = 
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"                " +
				"              q " +
				"  p r           " +
				"   P        K   " +
				"                " +
				"                " +
				"              R ";
		Board board = new Board("mockID");
		board.fillSpots(boardLine, Piece.Color.WHITE);
		
		Rook myRook = this.givenAWhiteRook();
		Queen enemyQueen = this.givenABlackQueen();
		Spot start = new Spot(14,15, myRook);
		Spot end = new Spot(14,10, enemyQueen);
		Move bestMove = new Move(start, end, end.getPiece().getScore().getPieceVal());
		Move actual = board.scanBestMove(myRook.getColor());
		String expected = "desde x:" + bestMove.getStart().getX() + " y:" + bestMove.getStart().getY() + " hasta x:" + bestMove.getEnd().getX() + " y:" + bestMove.getEnd().getY();
		String actualResult = "desde x:" + actual.getStart().getX() + " y:" + actual.getStart().getY() + " hasta x:" + actual.getEnd().getX() + " y:" + actual.getEnd().getY();
		System.out.println("desde x:" + start.getX() + " y:" + start.getY() + " hasta x:" + end.getX() + " y:" + end.getY() + " puntaje:" + bestMove.getScore());
		System.out.println("desde x:" + actual.getStart().getX() + " y:" + actual.getStart().getY() + " hasta x:" + actual.getEnd().getX() + " y:" + actual.getEnd().getY() + " puntaje:" + actual.getScore());
		
		assertEquals(expected, actualResult);
	}
	
	private Pawn givenAWhitePawn() {
		Pawn pawn = new Pawn(Piece.Color.WHITE);
		return pawn;
	}
	
	private Pawn givenABlackPawn() {
		Pawn pawn = new Pawn(Piece.Color.BLACK);
		return pawn;
	}
	
	private Bishop givenAWhiteBishop() {
		Bishop bishop = new Bishop(Piece.Color.WHITE);
		return bishop;
	}
	
	private Queen givenAWhiteQueen() {
		Queen queen = new Queen(Piece.Color.WHITE);
		return queen;
	}
	
	private Queen givenABlackQueen() {
		Queen queen = new Queen(Piece.Color.WHITE);
		return queen;
	}
	
	private King givenABlackKing() {
		King king = new King(Piece.Color.BLACK);
		return king;
	}
	
	private Rook givenAWhiteRook() {
		Rook rook = new Rook(Piece.Color.WHITE);
		return rook;
	}
}
