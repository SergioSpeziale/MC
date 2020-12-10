package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import chessSet.Pawn;
import chessSet.Piece;
import chessSet.Queen;
import chessSet.Spot;

@SpringBootTest
public class QueenTests {
	
	@Test
	void testQueenCannotMoveOutsideBoard() {
		Queen queen = this.givenAWhiteQueen();
		Spot start = new Spot(5, 0, queen);
		Spot end = new Spot(5, -1, null);
		boolean isValid = queen.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testQueenCannotTakeAlly() {
		Queen queen = this.givenAWhiteQueen();
		Pawn pawn = this.givenAWhitePawn();
		Spot start = new Spot(5, 13, queen);
		Spot end = new Spot(5, 12, pawn);
		boolean isValid = queen.canMove(start.getX(), start.getY(), end);
		assertTrue(!isValid);
	}
	
	@Test
	void testIsValidDirection() {
		Queen queen = this.givenAWhiteQueen();
		Spot start = new Spot(5, 10, queen);
		Spot endForward = new Spot(5, 1, null);
		Spot endBackwards = new Spot(5, 12, null);
		Spot endSuperiorDiagonal = new Spot(9, 6, null);
		Spot endInferiorDiagonal = new Spot(2, 13, null);
		Spot endFirstError = new Spot(9, 5, null);
		Spot endSecondError = new Spot(1, 15, null);
		
		boolean isValidEndForward = queen.canMove(start.getX(), start.getY(), endForward);
		boolean isValidEndBackwards = queen.canMove(start.getX(), start.getY(), endBackwards);
		boolean isValidEndSuperiorDiagonal = queen.canMove(start.getX(), start.getY(), endSuperiorDiagonal);
		boolean isValidEndInferiorDiagonal = queen.canMove(start.getX(), start.getY(), endInferiorDiagonal);
		boolean isValidEndFirstError = queen.canMove(start.getX(), start.getY(), endFirstError);
		boolean isValidEndSecondError = queen.canMove(start.getX(), start.getY(), endSecondError);
		assertTrue(isValidEndForward);
		assertTrue(isValidEndBackwards);
		assertTrue(isValidEndSuperiorDiagonal);
		assertTrue(isValidEndInferiorDiagonal);
		assertTrue(!isValidEndFirstError);
		assertTrue(!isValidEndSecondError);
	}
	
	@Test
	void testIsValidDirectionBlackQueen() {
		Queen queen = this.givenABlackQueen();
		Queen enemyQueen = this.givenAWhiteQueen();
		Spot start = new Spot(6, 0, queen);		
		Spot endInferiorDiagonal = new Spot(1, 3, enemyQueen);		
		boolean isValidEndInferiorDiagonal = queen.canMove(start.getX(), start.getY(), endInferiorDiagonal);
		assertTrue(!isValidEndInferiorDiagonal);
	}
	
	
	private Queen givenAWhiteQueen() {
		Queen queen = new Queen(Piece.Color.WHITE);
		return queen;
	}
	
	private Queen givenABlackQueen() {
		Queen queen = new Queen(Piece.Color.WHITE);
		return queen;
	}
	
	private Pawn givenAWhitePawn() {
		Pawn pawn = new Pawn(Piece.Color.WHITE);
		return pawn;
	}
}
