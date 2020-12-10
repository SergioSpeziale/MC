package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import chessSet.Pawn;
import chessSet.Piece;
import chessSet.Rook;
import chessSet.Spot;

@SpringBootTest
public class RookTests {
	@Test
	void testRookCannotMoveOutsideBoard() {
		Rook rook = this.givenAWhiteRook();
		Spot start = new Spot(5, 0, rook);
		Spot end = new Spot(5, 16, null);
		boolean isValid = rook.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testRookCannotTakeAlly() {
		Rook rook = this.givenAWhiteRook();
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0, 15, rook);
		Spot end = new Spot(0, 5, pawn);
		boolean isValid = rook.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testIsValidDirection() {
		Rook rook = this.givenAWhiteRook();
		Spot start = new Spot(5, 10, rook);
		Spot endForward = new Spot(5, 1, null);
		Spot endBackwards = new Spot(5, 12, null);
		Spot endSuperiorDiagonal = new Spot(9, 6, null);
		Spot endInferiorDiagonal = new Spot(2, 13, null);
		Spot endFirstError = new Spot(9, 5, null);
		Spot endSecondError = new Spot(1, 15, null);
		
		boolean isValidEndForward = rook.canMove(start.getX(), start.getY(), endForward);
		boolean isValidEndBackwards = rook.canMove(start.getX(), start.getY(), endBackwards);
		boolean isValidEndSuperiorDiagonal = rook.canMove(start.getX(), start.getY(), endSuperiorDiagonal);
		boolean isValidEndInferiorDiagonal = rook.canMove(start.getX(), start.getY(), endInferiorDiagonal);
		boolean isValidEndFirstError = rook.canMove(start.getX(), start.getY(), endFirstError);
		boolean isValidEndSecondError = rook.canMove(start.getX(), start.getY(), endSecondError);
		assertTrue(isValidEndForward);
		assertTrue(isValidEndBackwards);
		assertTrue(!isValidEndSuperiorDiagonal);
		assertTrue(!isValidEndInferiorDiagonal);
		assertTrue(!isValidEndFirstError);
		assertTrue(!isValidEndSecondError);
	}
	
	
	private Rook givenAWhiteRook() {
		Rook rook = new Rook(Piece.Color.WHITE);
		return rook;
	}
	
	private Pawn givenAWhitePawn() {
		Pawn pawn = new Pawn(Piece.Color.WHITE);
		return pawn;
	}
}
