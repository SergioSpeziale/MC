package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import chessSet.Pawn;
import chessSet.Piece;
import chessSet.Knight;
import chessSet.Spot;

@SpringBootTest
public class KnightTests {
	@Test
	void testKnightCannotMoveOutsideBoard() {
		Knight knight = this.givenAWhiteKnight();
		Spot start = new Spot(5, 15, knight);
		Spot end = new Spot(7, 16, null);
		boolean isValid = knight.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testKnightCannotTakeAlly() {
		Knight knight = this.givenAWhiteKnight();
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(5, 15, knight);
		Spot end = new Spot(4, 13, pawn);
		boolean isValid = knight.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testIsValidDirection() {
		Knight knight = this.givenAWhiteKnight();
		Spot start = new Spot(5, 10, knight);
		Spot endFirstCorrectMove = new Spot(4, 12, null);
		Spot endSecondCorrectMove = new Spot(7, 9, null);
		Spot endThirdCorrectMove = new Spot(3, 9, null);
		Spot endForward = new Spot(5, 1, null);
		Spot endBackwards = new Spot(5, 12, null);
		Spot endSuperiorDiagonal = new Spot(9, 6, null);
		Spot endInferiorDiagonal = new Spot(2, 13, null);
		Spot endFirstRandomError = new Spot(9, 5, null);
		Spot endSecondRandomError = new Spot(1, 15, null);
		boolean isValidFirstCorrectMove = knight.canMove(start.getX(), start.getY(), endFirstCorrectMove);
		boolean isValidSecondCorrectMove = knight.canMove(start.getX(), start.getY(), endSecondCorrectMove);
		boolean isValidThirdCorrectMove = knight.canMove(start.getX(), start.getY(), endThirdCorrectMove);
		boolean isValidEndForward = knight.canMove(start.getX(), start.getY(), endForward);
		boolean isValidEndBackwards = knight.canMove(start.getX(), start.getY(), endBackwards);
		boolean isValidEndSuperiorDiagonal = knight.canMove(start.getX(), start.getY(), endSuperiorDiagonal);
		boolean isValidEndInferiorDiagonal = knight.canMove(start.getX(), start.getY(), endInferiorDiagonal);
		boolean isValidEndFirstRandomError = knight.canMove(start.getX(), start.getY(), endFirstRandomError);
		boolean isValidEndSecondRandomError = knight.canMove(start.getX(), start.getY(), endSecondRandomError);
		assertTrue(isValidFirstCorrectMove);
		assertTrue(isValidSecondCorrectMove);
		assertTrue(isValidThirdCorrectMove);
		assertTrue(!isValidEndForward);
		assertTrue(!isValidEndBackwards);
		assertTrue(!isValidEndSuperiorDiagonal);
		assertTrue(!isValidEndInferiorDiagonal);
		assertTrue(!isValidEndFirstRandomError);
		assertTrue(!isValidEndSecondRandomError);
	}
	
	
	private Knight givenAWhiteKnight() {
		Knight knight = new Knight(Piece.Color.WHITE);
		return knight;
	}
	
	private Pawn givenAWhitePawn() {
		Pawn pawn = new Pawn(Piece.Color.WHITE);
		return pawn;
	}
}
