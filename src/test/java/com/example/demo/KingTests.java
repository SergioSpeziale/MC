package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import chessSet.Pawn;
import chessSet.Piece;
import chessSet.King;
import chessSet.Spot;

@SpringBootTest
public class KingTests {
	@Test
	void testKingCannotMoveOutsideBoard() {
		King king = this.givenAWhiteKing();
		Spot start = new Spot(5, 0, king);
		Spot end = new Spot(5, 16, null);
		boolean isValid = king.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testKingCannotTakeAlly() {
		King king = this.givenAWhiteKing();
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0, 15, king);
		Spot end = new Spot(0, 14, pawn);
		boolean isValid = king.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testIsValidDirectionWrongSpots() {
		King king = this.givenAWhiteKing();
		Spot start = new Spot(5, 10, king);
		Spot endFarForward = new Spot(5, 1, null);
		Spot endFarBackwards = new Spot(5, 12, null);
		Spot endFarSuperiorDiagonal = new Spot(9, 6, null);
		Spot endFarInferiorDiagonal = new Spot(2, 13, null);
		Spot endFirstError = new Spot(4, 8, null);
		Spot endSecondError = new Spot(4, 12, null);
		
		boolean isValidEndForward = king.canMove(start.getX(), start.getY(), endFarForward);
		boolean isValidEndBackwards = king.canMove(start.getX(), start.getY(), endFarBackwards);
		boolean isValidEndSuperiorDiagonal = king.canMove(start.getX(), start.getY(), endFarSuperiorDiagonal);
		boolean isValidEndInferiorDiagonal = king.canMove(start.getX(), start.getY(), endFarInferiorDiagonal);
		boolean isValidEndFirstError = king.canMove(start.getX(), start.getY(), endFirstError);
		boolean isValidEndSecondError = king.canMove(start.getX(), start.getY(), endSecondError);
		assertTrue(!isValidEndForward);
		assertTrue(!isValidEndBackwards);
		assertTrue(!isValidEndSuperiorDiagonal);
		assertTrue(!isValidEndInferiorDiagonal);
		assertTrue(!isValidEndFirstError);
		assertTrue(!isValidEndSecondError);
	}
	
	@Test
	void testIsValidDirectionRightSpots() {
		King king = this.givenAWhiteKing();
		Spot start = new Spot(5, 10, king);
		Spot endForward = new Spot(5, 9, null);
		Spot endBackwards = new Spot(5, 11, null);
		Spot endSuperiorDiagonal = new Spot(6, 9, null);
		Spot endInferiorDiagonal = new Spot(4, 11, null);
		
		boolean isValidEndForward = king.canMove(start.getX(), start.getY(), endForward);
		boolean isValidEndBackwards = king.canMove(start.getX(), start.getY(), endBackwards);
		boolean isValidEndSuperiorDiagonal = king.canMove(start.getX(), start.getY(), endSuperiorDiagonal);
		boolean isValidEndInferiorDiagonal = king.canMove(start.getX(), start.getY(), endInferiorDiagonal);
		assertTrue(isValidEndForward);
		assertTrue(isValidEndBackwards);
		assertTrue(isValidEndSuperiorDiagonal);
		assertTrue(isValidEndInferiorDiagonal);
	}
	
	
	private King givenAWhiteKing() {
		King king = new King(Piece.Color.WHITE);
		return king;
	}
	
	private Pawn givenAWhitePawn() {
		Pawn pawn = new Pawn(Piece.Color.WHITE);
		return pawn;
	}
}
