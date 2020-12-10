package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import chessSet.Board;
import chessSet.Pawn;
import chessSet.Piece;
import chessSet.Spot;

@SpringBootTest
public class PawnTests {
	
	@Test
	void testPawnCanMoveForward() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0,12, pawn);
		Spot end = new Spot(0,11, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(isValid);
	}
	
	@Test
	void testPawnCanMoveSideWays() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0,11, pawn);
		Spot end = new Spot(1,11, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(!isValid);
	}
	
	@Test
	void testPawnCannotMoveOutsideBoard() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(5, 0, pawn);
		Spot end = new Spot(5, -1, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testPawnCannotMoveBackwards() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0, 11, pawn);
		Spot end = new Spot(0, 12, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(!isValid);
	}
	
	@Test
	void testPawnCanKill() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0, 14, pawn);
		Pawn enemy = this.givenABlackPawn();
		Spot end = new Spot(1, 13, enemy);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(isValid);
	}
	
	@Test
	void testPawnCanMoveTwoSpots() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0, 12, pawn);
		Spot end = new Spot(0, 10, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(isValid);
	}
	
	@Test
	void testBlackPawnCanMoveTwoSpots() {
		Pawn pawn = this.givenABlackPawn();
		Spot start = new Spot(0, 3, pawn);
		Spot end = new Spot(0, 5, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(isValid);
	}
	
	@Test
	void testPawnCanMoveToEmptySpot() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0, 12, pawn);
		Pawn anotherPawn = this.givenAWhitePawn();
		Spot end = new Spot(0, 11, anotherPawn);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(!isValid);
	}
	
	@Test
	void testPawnCanMoveMoreThanTwoStepsAtStartingPosition() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0, 12, pawn);
		Spot end = new Spot(0, 9, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(!isValid);
	}
	
	@Test
	void testPawnCanMoveMoreThanOneStep() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0, 9, pawn);
		Spot end = new Spot(0, 7, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(!isValid);
	}
	
	@Test
	void testPawnCanMoveMoreThanOneStepDiagonal() {
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(0, 9, pawn);
		Spot end = new Spot(2, 7, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(!isValid);
	}
	
	@Test
	void testPawnCanMoveOneStepDiagonal() {
		Pawn pawn = this.givenAWhitePawn();
		Pawn enemy = this.givenABlackPawn();
		Spot start = new Spot(0, 9, pawn);
		Spot end = new Spot(1, 8, enemy);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(isValid);
	}
	
	@Test
	void testPawnCanTakeAlly() {
		Pawn pawn = this.givenAWhitePawn();
		Pawn ally = this.givenAWhitePawn();
		Spot start = new Spot(0, 9, pawn);
		Spot end = new Spot(1, 8, ally);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(!isValid);
	}
	
	@Test
	void testBlackPawnCanMoveForward() {
		
		String boardLine = 
				"                " + 
				"                " + 
				"                " + 
				"pppppppppppppppp" + 
				"p               " + 
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
		
		Pawn pawn = this.givenABlackPawn();
		Spot start = new Spot(0, 3, pawn);
		Spot end = new Spot(2, 4, null);
		boolean isValid = pawn.canMove(start.getX(), start.getY(), end);
		
		assertTrue(!isValid);
	}	
	
	private Pawn givenAWhitePawn() {
		Pawn pawn = new Pawn(Piece.Color.WHITE);
		return pawn;
	}
	
	private Pawn givenABlackPawn() {
		Pawn pawn = new Pawn(Piece.Color.BLACK);
		return pawn;
	}
}
